package zambou.test;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import afrique.cm.dla.zambou.snack.bean.LoginData;
import afrique.cm.dla.zambou.snack.bean.ProduitData;
import afrique.cm.dla.zambou.snack.bean.TableData;
import afrique.cm.dla.zambou.snack.bean.VenteData;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import zambou.test.a.BDD;
import zambou.test.a.Parameter;

public class CaissierController implements Initializable{
	@FXML private TableView <ProduitData>Table_Produit;
	@FXML private TableView <VenteData>Table_Vente;
	@FXML private TextField Txt_Stock;
	@FXML private TextField Txt_Produit;
	@FXML private TextField Txt_Reference;
	@FXML private TextField Txt_Designation;
	@FXML private TextField Txt_Rangement;
	@FXML private TextField Txt_Fournisseur;
	@FXML private TextField Txt_Prix;
	@FXML private TextField Txt_Nou;
	@FXML private TextField Txt_Fac;
	@FXML private TextField Txt_Remise;
	@FXML private TextField Txt_Rech;
	@FXML private Label Label_Sub;
	@FXML private Label Label_Total;
	@FXML private ComboBox<String>Com_Rech;
	private static final Logger log = LoggerFactory.getLogger(PersonnelController.class);
	private ProduitData data;
	private VenteData ventedata;
	ResultSet rs;
    BDD db;
    String id_Tmp,Code_Tmp,Ref_Tmp,Des_Tmp,Ran_Tmp,Four_Tmp,Rem_Tmp,Prix_Tmp,Stock_Tmp;
    public CaissierController() {
    	
    }
    
    public void table() {
		BDD db = new BDD(new Parameter().HOST_DB, new Parameter().USERNAME_DB, new Parameter().PASSWORD_DB,
				new Parameter().IPHOST, new Parameter().PORT);
		
		 log.info("Chargement table personnel .............. ");
		 System.out.println("ssss");
		 System.out.println("ssss");
		  data = new ProduitData();
		  System.out.println("eee");
		 String a[] = {"id", "code_produit", "reference", "designation", "rangement", "fournisseur", "remise", "prix", "stock"}; 
		 System.out.println("iii");
		 rs =db.querySelect(a,"produit");
		 System.out.println("bbbb");
		try {
			while (rs.next()) {
			id_Tmp=rs.getString("id");
			Code_Tmp=rs.getString("code_produit");
			Ref_Tmp=rs.getString("reference");
			Des_Tmp=rs.getString("designation");
			Ran_Tmp=rs.getString("rangement");
			Four_Tmp=rs.getString("fournisseur");
			Rem_Tmp=rs.getString("remise");
			Prix_Tmp=rs.getString("prix");
			Stock_Tmp=rs.getString("stock");
			System.out.println(id_Tmp);
			data.setId(id_Tmp);
			data.setCode_produit(Code_Tmp);
			data.setReference(Ref_Tmp);
			data.setDesignation(Des_Tmp);
			data.setRangement(Ran_Tmp);
			data.setFournisseur(Four_Tmp);
			data.setRemise(Rem_Tmp);
			data.setPrix(Prix_Tmp);
			data.setStock(Stock_Tmp);
			System.out.println(data.getId());
			Table_Produit.getItems().add(data);
			data = new ProduitData();
			}
			
			} catch (SQLException ex) {
		 
	            log.error(ex.getMessage() );
	     }
       Com_Rech.getItems().addAll("code_produit","rangement","fournisseur","designation");      
	}
    

   
   public void zams(MouseEvent ev) {
		// data = Table_User.getSelectionModel().getSelectedItem();
		//Txt_UserName.setText(data.getUsername());
		//Txt_Matricule.setText(data.getMatricule());
		//Txt_UserPassword.setText(data.getPassword());
	    //Txt_Type.setValue(data.getType());
	
		 
	}
   
// code pour ajouter un un produit a la table de ventes 
	public void Produit_Ajouter() {
		BDD db = new BDD(new Parameter().HOST_DB, new Parameter().USERNAME_DB, new Parameter().PASSWORD_DB,
				new Parameter().IPHOST, new Parameter().PORT);
		data = new ProduitData();
		data.setStock(Txt_Stock.getText());
		data.setCode_produit(Txt_Produit.getText());
		data.setReference(Txt_Reference.getText());
		data.setDesignation(Txt_Designation.getText());
		data.setRangement(Txt_Rangement.getText());
		data.setFournisseur(Txt_Fournisseur.getText());
		data.setPrix(Txt_Prix.getText());
		data.setRemise(Txt_Remise.getText());
		System.out.println("ok332");
		
		                                         
	        if (data.getStock().equals("") || data.getCode_produit().equals("") || data.getReference().equals("")
	                || data.getDesignation().equals("") || data.getRangement().equals("") || data.getFournisseur().equals("") || 
	                data.getPrix().equals("")|| data.getRemise().equals("")) {
	            //JOptionPane.showMessageDialog(this, "SVP entrer vos donneé");
	        //} else if (txt_fac.getText().equals("")) {
	           // JOptionPane.showMessageDialog(this, "SVP entrer le num de facture");
	        } else {
	            try {
	                if (!test_stock()) { 
	                   // JOptionPane.showMessageDialog(this, "le stock est Limiter");
	                } else {
	                	String[] colon = {"num_facture","code_produit", "reference", "prix_vente", "stock_sortie", "subtotal"};
	    	            String[] isi = {Txt_Fac.getText(), data.getCode_produit(), data.getReference(), data.getPrix(), data.getStock(),
	    	                Label_Sub.getText()};
	    	            System.out.println(db.queryInsert("vente", colon, isi));
	                    def(); //true
	                    table(); //true
	                }
	            }catch(SQLException ex){
	            	log.error(ex.getMessage() );
	            }
	            subtotal();
	            importer();
	            total();
	        }
	    } 

	// code pour supprimer un personnel
		public void Produit_Supprimer() {
			BDD db = new BDD(new Parameter().HOST_DB, new Parameter().USERNAME_DB, new Parameter().PASSWORD_DB,
					new Parameter().IPHOST, new Parameter().PORT);
              data=new ProduitData();
			  data = Table_Produit.getSelectionModel().getSelectedItem();
			  String id= data.getId();
			 /*String.valueOf(Table_User.getValueAt(Table_User.getSelectedRow(),0));
			  if (JOptionPane.showConfirmDialog(this,
			 * "est ce que tu es sure que tu veux suuprimer", "attention!!!",
			 * JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
			 * db.queryDelete("utilisateur", "id=" + id); } else { return; }
			 */
			db.queryDelete("utilisateur", "id=" + id);
			table();
		}
		
		
		// code pour recherche 
		public void Produit_Recherche() {
			BDD db = new BDD(new Parameter().HOST_DB, new Parameter().USERNAME_DB, new Parameter().PASSWORD_DB,
					new Parameter().IPHOST, new Parameter().PORT);
			String selectedItemValue = Com_Rech.getSelectionModel().getSelectedItem();
			if (Txt_Rech.getText().equals("")) {
				// JOptionPane.showMessageDialog(this, "SVP entrer quelque chose");
			} else {

				if (selectedItemValue.equals("code_produit")) {
					System.out.println("ssss222");
				data=new ProduitData();
				System.out.println("ssss332");
					rs = db.querySelectAll("produit", "code_produit LIKE '%" + Txt_Rech.getText() + "%' ");
					try {
						while (rs.next()) {
							id_Tmp=rs.getString("id");
							Code_Tmp=rs.getString("code_produit");
							Ref_Tmp=rs.getString("reference");
							Des_Tmp=rs.getString("designation");
							Ran_Tmp=rs.getString("rangement");
							Four_Tmp=rs.getString("fournisseur");
							Rem_Tmp=rs.getString("remise");
							Prix_Tmp=rs.getString("prix");
							Stock_Tmp=rs.getString("stock");
							System.out.println(id_Tmp);
							data.setId(id_Tmp);
							data.setCode_produit(Code_Tmp);
							data.setReference(Ref_Tmp);
							data.setDesignation(Des_Tmp);
							data.setRangement(Ran_Tmp);
							data.setFournisseur(Four_Tmp);
							data.setRemise(Rem_Tmp);
							data.setPrix(Prix_Tmp);
							data.setStock(Stock_Tmp);
							System.out.println(data.getId());
							Table_Produit.getItems().add(data);
						}
						
						} catch (SQLException ex) {
					 
				            log.error(ex.getMessage() );
				     }
				}
				
				else if (selectedItemValue.equals("rangement")) {
					System.out.println("ssss222");
				data=new ProduitData();
				System.out.println("ssss332");
					rs = db.querySelectAll("produit", "rangement LIKE '%" + Txt_Rech.getText() + "%' ");
					try {
						while (rs.next()) {
							id_Tmp=rs.getString("id");
							Code_Tmp=rs.getString("code_produit");
							Ref_Tmp=rs.getString("reference");
							Des_Tmp=rs.getString("designation");
							Ran_Tmp=rs.getString("rangement");
							Four_Tmp=rs.getString("fournisseur");
							Rem_Tmp=rs.getString("remise");
							Prix_Tmp=rs.getString("prix");
							Stock_Tmp=rs.getString("stock");
							System.out.println(id_Tmp);
							data.setId(id_Tmp);
							data.setCode_produit(Code_Tmp);
							data.setReference(Ref_Tmp);
							data.setDesignation(Des_Tmp);
							data.setRangement(Ran_Tmp);
							data.setFournisseur(Four_Tmp);
							data.setRemise(Rem_Tmp);
							data.setPrix(Prix_Tmp);
							data.setStock(Stock_Tmp);
							System.out.println(data.getId());
							Table_Produit.getItems().add(data);
						}
						
						} catch (SQLException ex) {
					 
				            log.error(ex.getMessage() );
				     }
				}
				
				else if (selectedItemValue.equals("designation")) {
					System.out.println("ssss222");
				data=new ProduitData();
				System.out.println("ssss332");
					rs = db.querySelectAll("produit", "designation LIKE '%" + Txt_Rech.getText() + "%' ");
					try {
						while (rs.next()) {
							id_Tmp=rs.getString("id");
							Code_Tmp=rs.getString("code_produit");
							Ref_Tmp=rs.getString("reference");
							Des_Tmp=rs.getString("designation");
							Ran_Tmp=rs.getString("rangement");
							Four_Tmp=rs.getString("fournisseur");
							Rem_Tmp=rs.getString("remise");
							Prix_Tmp=rs.getString("prix");
							Stock_Tmp=rs.getString("stock");
							System.out.println(id_Tmp);
							data.setId(id_Tmp);
							data.setCode_produit(Code_Tmp);
							data.setReference(Ref_Tmp);
							data.setDesignation(Des_Tmp);
							data.setRangement(Ran_Tmp);
							data.setFournisseur(Four_Tmp);
							data.setRemise(Rem_Tmp);
							data.setPrix(Prix_Tmp);
							data.setStock(Stock_Tmp);
							System.out.println(data.getId());
							Table_Produit.getItems().add(data);
						}
						
						} catch (SQLException ex) {
					 
				            log.error(ex.getMessage() );
				     }
				}
				
				else if (selectedItemValue.equals("fournisseur")) {
					System.out.println("ssss222");
				data=new ProduitData();
				System.out.println("ssss332");
					rs = db.querySelectAll("fournisseur", "fournisseur LIKE '%" + Txt_Rech.getText() + "%' ");
					try {
						while (rs.next()) {
							id_Tmp=rs.getString("id");
							Code_Tmp=rs.getString("code_produit");
							Ref_Tmp=rs.getString("reference");
							Des_Tmp=rs.getString("designation");
							Ran_Tmp=rs.getString("rangement");
							Four_Tmp=rs.getString("fournisseur");
							Rem_Tmp=rs.getString("remise");
							Prix_Tmp=rs.getString("prix");
							Stock_Tmp=rs.getString("stock");
							System.out.println(id_Tmp);
							data.setId(id_Tmp);
							data.setCode_produit(Code_Tmp);
							data.setReference(Ref_Tmp);
							data.setDesignation(Des_Tmp);
							data.setRangement(Ran_Tmp);
							data.setFournisseur(Four_Tmp);
							data.setRemise(Rem_Tmp);
							data.setPrix(Prix_Tmp);
							data.setStock(Stock_Tmp);
							System.out.println(data.getId());
							Table_Produit.getItems().add(data);
						}
						
						} catch (SQLException ex) {
					 
				            log.error(ex.getMessage() );
				     }
				}
			}
		}
		
		public void Vente_Recherche() {
			
		}
		public boolean test_stock() throws SQLException {
			int old=0,dec=0;
	        boolean teststock;
	        rs = db.querySelectAll("produit","code_produit='" + data.getCode_produit() + "'");
	        while (rs.next()) {
	            old = rs.getInt("stock");
	        }
	        dec = Integer.parseInt(data.getStock());
	        if (old < dec) {
	            teststock = false;
	        } else {
	            teststock = true;
	        }
	        return teststock;
	    }
		
		 public void def() throws SQLException {
			 int old=0,dec=0,now=0;
		        rs = db.querySelectAll("produit", "code_produit='" + data.getCode_produit() + "'");
		        while (rs.next()) {
		            old = rs.getInt("stock");
		        }
		        dec = Integer.parseInt(data.getStock());
		        now = old - dec;
		         String nvstock = Integer.toString(now);

		        String a = String.valueOf(nvstock);
		        String[] colon = {"stock"};
		        String[] isi = {a};
		        System.out.println(db.queryUpdate("produit", colon, isi, "code_produit='" + data.getCode_produit() + "'"));
		    }
		 
		 public void subtotal() {
		        double a = parseDoubleNew(Txt_Nou.getText());
		        double b = parseDoubleNew(data.getStock());
		        double c = a * b;
		        Label_Sub.setText(String.valueOf(c));
		        }
	//code du sub_total	 
	public void Subtotal_Label() {
		subtotal();
	}
	
	//code Total
    public void total() {
        rs = db.executionQuery("SELECT SUM(subtotal) as subtotal FROM vente WHERE num_facture = '" + Txt_Fac.getText() + "'");
        try {
            rs.next();
            Label_Total.setText(rs.getString("subtotal"));
        } catch (SQLException ex) {
        	log.error(ex.getMessage() );
        }
    }
	
	//code pour envoyer les données a la table de vente 
	 public void importer() {
		 String id_TmpV,num_facV,Prix_VenteV,Stock_SortieV;
	        String colon[] = {"id","num_facture","code_produit","reference","prix_vente","stock_sortie","subtotal"};
	        rs = db.fcSelectCommand(colon, "vente", "num_facture='" + Txt_Fac.getText() + "'");
	        try {
	        id_TmpV=rs.getString("id");
	        num_facV=rs.getString("num_facture");
	        Prix_VenteV=rs.getString("prix_vente");
	        Stock_SortieV=rs.getString("stock_sortie");
	        ventedata.setId(id_TmpV);
	        ventedata.setnum_facture(num_facV);
	        ventedata.setPrix_Vente(Prix_VenteV);
	        ventedata.setStock_Sortie(Stock_SortieV);
	        } catch (SQLException ex) {
		 
	            log.error(ex.getMessage() );
	     }
			
			System.out.println(data.getId());
			Table_Vente.getItems().add(ventedata);
	       // table_vente.setModel(new ResultSetTableModel(rs));
	    }
		 
		//code pour la convertion
		 public double parseDoubleNew(String s){
			    if(s == null || s.isEmpty()) 
			        return 0.0;
			    else
			        return Double.parseDouble(s);
			}
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		table();
	}
	

}

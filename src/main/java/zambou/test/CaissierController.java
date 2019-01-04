package zambou.test;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.input.KeyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import zambou.test.a.BDD;
import zambou.test.a.Parameter;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;


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
	@FXML private TextField Txt_Reste;
	@FXML private TextField Txt_Cash;
	@FXML private Label Label_Sub;
	@FXML private Label Label_Total;
	@FXML private ComboBox<String>Com_Rech;
	private static final Logger log = LoggerFactory.getLogger(CaissierController.class);
	private ProduitData data;
	private VenteData ventedata;
	private Label label;
	ResultSet rs;
	String Id_Del;
	String stockIni="";
	String invoice;
	String Id_Table;
	String nr_facture;
	ArrayList<String> IdListe = new ArrayList<String>();
	ArrayList<String> IdListe_delete = new ArrayList<String>();

    
    BDD db = new BDD(new Parameter().HOST_DB, new Parameter().USERNAME_DB, new Parameter().PASSWORD_DB,
			new Parameter().IPHOST, new Parameter().PORT);
    String id_Tmp,Code_Tmp,Ref_Tmp,Des_Tmp,Ran_Tmp,Four_Tmp,Rem_Tmp,Prix_Tmp,Stock_Tmp;
    public CaissierController() {
    	
    }
    
    public void table() {
		/*BDD db = new BDD(new Parameter().HOST_DB, new Parameter().USERNAME_DB, new Parameter().PASSWORD_DB,
				new Parameter().IPHOST, new Parameter().PORT);*/
		
		 log.info("Chargement table personnel .............. ");
		 System.out.println("ssss");
		 System.out.println("ssss");
		  data = new ProduitData();
		  System.out.println("eee");
		 String a[] = {"id", "code_produit", "reference", "designation", "rangement", "fournisseur", "remise", "prix", "stock"}; 
		 System.out.println("iii");
		 rs =db.querySelect(a,"produit");
		 System.out.println("bbbb  "+Txt_Stock);
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
			stockIni=rs.getString("stock");
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
     // NumeroFacture();
      //Txt_Fac.setText(nr_facture);
       
	}
    
    private void showAlertWithDefaultHeaderTextAjouter() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Attention ");
 
        alert.setContentText("données saisies sont incompletes!");
 
        alert.showAndWait();
    }
    private void showAlertWithDefaultHeaderTextRecherche() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Attention ");
 
        alert.setContentText("Veuillez entrer quelque chose s´il vous plait!");
 
        alert.showAndWait();
    }
    private void showAlertWithDefaultHeaderTextStock() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Attention ");
 
        alert.setContentText("le stock est limité!");
 
        alert.showAndWait();
    }
   

   
// code pour ajouter un un produit a la table de ventes 
	public void Produit_Ajouter() {
		BDD db = new BDD(new Parameter().HOST_DB, new Parameter().USERNAME_DB, new Parameter().PASSWORD_DB,
				new Parameter().IPHOST, new Parameter().PORT);
		if(Txt_Fac.getText().equals("")) {
			NumeroFacture();
			Txt_Fac.setText(nr_facture);
		}
		data = new ProduitData();
		data.setStock(Txt_Stock.getText());
		data.setCode_produit(Txt_Produit.getText());
		data.setReference(Txt_Reference.getText());
		data.setDesignation(Txt_Designation.getText());
		data.setRangement(Txt_Rangement.getText());
		data.setFournisseur(Txt_Fournisseur.getText());
		data.setPrix(Txt_Nou.getText());
		data.setRemise(Txt_Remise.getText());
		
		
		                                         
	        if (data.getStock().equals("") || data.getCode_produit().equals("") || data.getReference().equals("")
	                || data.getDesignation().equals("") || data.getRangement().equals("") || data.getFournisseur().equals("") || 
	                data.getPrix().equals("")|| data.getRemise().equals("")) {
	        	showAlertWithDefaultHeaderTextAjouter();
	        	
	        } else {
	            try {
	                if (!test_stock()) { 
	                	showAlertWithDefaultHeaderTextStock();
	                } else {
	                	System.out.println("ok332666");
	                	String[] colon = {"num_facture","designation","code_produit", "reference", "prix_vente", "stock_sortie", "subtotal"};
	    	            String[] isi = {Txt_Fac.getText(), data.getDesignation(),data.getCode_produit(), data.getReference(), data.getPrix(), data.getStock(),
	    	                Label_Sub.getText()};
	    	            System.out.println(db.queryInsert("vente", colon, isi));
	                    //def(); //true
	                   // table(); //true
	                    System.out.println("ok33777");
	                }
	            }catch(SQLException ex){
	            	log.error(ex.getMessage() );
	            }
	            System.out.println("ok332888");
	           // subtotal();
	            importer();
	            total();
	            Label_Sub.setText("0.0");
	            Txt_Stock.setText("");
	        }
	    } 

	// code pour supprimer un produit
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
		// code pour le clic sur la table 
		@FXML
		 public void zams(MouseEvent ev) {
			   data=new ProduitData();
				data = Table_Produit.getSelectionModel().getSelectedItem();
				Txt_Produit.setText(data.getCode_produit());
				Txt_Reference.setText(data.getReference());
			    Txt_Designation.setText(data.getDesignation());
			    Txt_Rangement.setText(data.getRangement());
			    Txt_Fournisseur.setText(data.getFournisseur());
			    Txt_Prix.setText(data.getPrix());
			    Txt_Remise.setText(data.getRemise());
			    cout();
			
				 
			}
		
		// code pour recherche 
		public void Produit_Recherche() {
			BDD db = new BDD(new Parameter().HOST_DB, new Parameter().USERNAME_DB, new Parameter().PASSWORD_DB,
					new Parameter().IPHOST, new Parameter().PORT);
			String selectedItemValue = Com_Rech.getSelectionModel().getSelectedItem();
			if (Txt_Rech.getText().equals("")) {
				// texte vide
				showAlertWithDefaultHeaderTextRecherche();
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
		
		@FXML
		public void Vente_Recherche() {
			importer();
		}
		
		//code pour supprimer 
		private void showConfirmation() {
			BDD db = new BDD(new Parameter().HOST_DB, new Parameter().USERNAME_DB, new Parameter().PASSWORD_DB,
					new Parameter().IPHOST, new Parameter().PORT);
			 
		      Alert alert = new Alert(AlertType.CONFIRMATION);
		      alert.setTitle("Supprimer une vente ");
		      alert.setHeaderText("Voulez vous vraiment supprimer cette vente ?");
		      Optional<ButtonType> option = alert.showAndWait();
		      if (option.get() == null) {
		         this.label.setText("No selection!");
		      } else if (option.get() == ButtonType.OK) {
		    	  db.queryDelete("vente", "id=" + Id_Del);
		      } else if (option.get() == ButtonType.CANCEL) {
		    	  alert.close();
		      } else {
		         this.label.setText("-");
		      }
		   }
		private void showConfirmationEffacer() {
			BDD db = new BDD(new Parameter().HOST_DB, new Parameter().USERNAME_DB, new Parameter().PASSWORD_DB,
					new Parameter().IPHOST, new Parameter().PORT);
			 
		      Alert alert = new Alert(AlertType.CONFIRMATION);
		      alert.setTitle("Supprimer une vente ");
		      alert.setHeaderText("Voulez vous vraiment supprimer cette vente ?");
		      Optional<ButtonType> option = alert.showAndWait();
		      if (option.get() == null) {
		         this.label.setText("No selection!");
		      } else if (option.get() == ButtonType.OK) {
		    	  db.queryDelete("vente","num_facture LIKE '%" +invoice+ "%' ");
		      } else if (option.get() == ButtonType.CANCEL) {
		    	  alert.close();
		      } else {
		         this.label.setText("-");
		      }
		   }
		
		@FXML
		public void Vente_Supprimer() {
			showConfirmation();
			Table_Vente.getItems().clear();
	        importer_vente();
	        total();
	    
		}
		public void retourProduit() {
			invoice=Txt_Fac.getText();
			BDD db = new BDD(new Parameter().HOST_DB, new Parameter().USERNAME_DB, new Parameter().PASSWORD_DB,
					new Parameter().IPHOST, new Parameter().PORT);
			String Code_Tmpr="",Stock_Tmpr="";
			int oldP=0,oldV=0,newP=0; 			
            rs=db.querySelectAll("vente","num_facture='" +invoice+ "'");
			 try {
					while (rs.next()) {
					Code_Tmpr=rs.getString("code_produit");
					Stock_Tmpr=rs.getString("stock_sortie");
					oldV=Integer.parseInt(Stock_Tmpr);
					 oldP=Integer.parseInt(stockIni);
					  newP=oldP-oldV;
					  String newStock=Integer.toString(newP);
					  System.out.println("good1");
					  modifierTableProduit(newStock,Code_Tmpr);
					  
					}
					
					} catch (SQLException ex) {
				 
			            log.error(ex.getMessage() );
			     }
			  table();
			
		}
		public void modifierTableProduit(String b,String a) {
			BDD db = new BDD(new Parameter().HOST_DB, new Parameter().USERNAME_DB, new Parameter().PASSWORD_DB,
					new Parameter().IPHOST, new Parameter().PORT);
         String colon[] = {"stock"}; 
         String[] inf = {b};
        System.out.println(db.queryUpdate("produit", colon, inf, "code_produit='" + a + "'"));
        System.out.println("good3");
			
		}
		
		@FXML
		public void Vente_Effacer() {
	        showConfirmationEffacer();
	        actualiserData();
	        Table_Vente.getItems().clear();
		}
		
		public void  actualiser() {
			ventedata=new VenteData();
			ventedata.setId("");
			ventedata.setNum_Facture("");
			ventedata.setPrix_Vente("");
			ventedata.setStock_Sortie("");
			ventedata.setSub_Total("");
			Table_Vente.getItems().add(ventedata);
		}
		public void actualiserData() {
			Txt_Produit.setText("");
			Txt_Reference.setText("");
		    Txt_Designation.setText("");
		    Txt_Rangement.setText("");
		    Txt_Fournisseur.setText("");
		    Txt_Prix.setText("");
		    Txt_Remise.setText("");
		    Label_Sub.setText("");
            Txt_Stock.setText("");
            Txt_Reste.setText("");
            Txt_Cash.setText("");
            Txt_Nou.setText("");
            Txt_Fac.setText("");
            Label_Total.setText("");
		}
		
		@FXML
		public void Vente_Mous(MouseEvent ev) {
			   ventedata=new VenteData();
				ventedata = Table_Vente.getSelectionModel().getSelectedItem();
				invoice=ventedata.getNum_Facture();
						Id_Del=ventedata.getId();
			
				 
			}
		 
		public boolean test_stock() throws SQLException {
			BDD db = new BDD(new Parameter().HOST_DB, new Parameter().USERNAME_DB, new Parameter().PASSWORD_DB,
					new Parameter().IPHOST, new Parameter().PORT);
			int old=0,dec=0;
	        boolean teststock;
	        rs = db.querySelectAll("produit","code_produit='" + Txt_Produit.getText() + "'");
	        while (rs.next()) {
	            old = rs.getInt("stock");
	        }
	        dec = Integer.parseInt(Txt_Stock.getText());
	        if (old < dec) {
	            teststock = false;
	        } else {
	            teststock = true;
	        }
	        return teststock;
	    }

		//actualiser la quantité dans la base de donnés
		 public void def() throws SQLException {
			 BDD db = new BDD(new Parameter().HOST_DB, new Parameter().USERNAME_DB, new Parameter().PASSWORD_DB,
						new Parameter().IPHOST, new Parameter().PORT);
			 int old=0,dec=0,now=0;
		        rs = db.querySelectAll("produit", "code_produit='" + Txt_Produit.getText() + "'");
		        while (rs.next()) {
		            old = rs.getInt("stock");
		        }
		        dec = Integer.parseInt(Txt_Stock.getText());
		        now = old - dec;
		         String nvstock = Integer.toString(now);

		        String a = String.valueOf(nvstock);
		        String[] colon = {"stock"};
		        String[] isi = {a};
		        System.out.println(db.queryUpdate("produit", colon, isi, "code_produit='" + data.getCode_produit() + "'"));
		    }
		 
		 public void subtotal() {
		        double a = parseDoubleNew(Txt_Nou.getText());
		        double b = parseDoubleNew(Txt_Stock.getText());
		        double c = a * b;
		        Label_Sub.setText(String.valueOf(c));
		        }
		 
	//code du sub_total	 
	 @FXML 
	 private void Subtotal_Label(KeyEvent event) {
		subtotal();
	}
	 
	 @FXML
	 private void Cash_Label(KeyEvent event) {
		 double a =parseDoubleNew(Label_Total.getText());
		 double b =parseDoubleNew(Txt_Cash.getText());
		 double c =b-a;
		 Txt_Reste.setText(String.valueOf(c));
		 
		 
	 }
	
	//code Total
    public void total() {
    	BDD db = new BDD(new Parameter().HOST_DB, new Parameter().USERNAME_DB, new Parameter().PASSWORD_DB,
				new Parameter().IPHOST, new Parameter().PORT);
        rs = db.executionQuery("SELECT SUM(subtotal) as subtotal FROM vente WHERE num_facture = '" + Txt_Fac.getText() + "'");
        try {
            rs.next();
            Label_Total.setText(rs.getString("subtotal"));
        } catch (SQLException ex) {
        	log.error(ex.getMessage() );
        }
    }
    // code pour le nouveau prix 
  public void cout () {
 double a =Double.parseDouble(Txt_Prix.getText());
 double b=Double.parseDouble(Txt_Remise.getText());
 double c=a-a*(b/100);
 Txt_Nou.setText(String.valueOf(c));
  }
 
 
	
	//code pour envoyer les données a la table de vente 
	 public void importer() {
		 BDD db = new BDD(new Parameter().HOST_DB, new Parameter().USERNAME_DB, new Parameter().PASSWORD_DB,
					new Parameter().IPHOST, new Parameter().PORT);
		 ventedata=new VenteData();
		 String id_TmpV,num_facV,Prix_VenteV,Stock_SortieV,SubtotalV;
	        String colon[] = {"id","num_facture","designation","code_produit","reference","prix_vente","stock_sortie","subtotal"};
	        rs = db.fcSelectCommand(colon, "vente", "num_facture='" + Txt_Fac.getText() + "'");
	        try {
	        	
	        	while(rs.next()) {
	        id_TmpV=rs.getString("id");
	        num_facV=rs.getString("num_facture");
	        Prix_VenteV=rs.getString("prix_vente");
	        Stock_SortieV=rs.getString("stock_sortie");
	        SubtotalV=rs.getString("subtotal");
	        
	        
	        ventedata.setId(id_TmpV);
	        ventedata.setNum_Facture(num_facV);
	        ventedata.setPrix_Vente(Prix_VenteV);
	        ventedata.setStock_Sortie(Stock_SortieV);
	        ventedata.setSub_Total(SubtotalV);
	        if (IdListe.contains(id_TmpV)==false) {
	        Table_Vente.getItems().add(ventedata);
	        
	        IdListe.add(id_TmpV);
	        }
	        ventedata=new VenteData();
	                	}
	        	
	        } catch (SQLException ex) {
		 
	            log.error(ex.getMessage() );
	     }
		
			ventedata=new VenteData();
	    }
	 
	 //importer apres suppression
	 public void importer_vente() {
		 BDD db = new BDD(new Parameter().HOST_DB, new Parameter().USERNAME_DB, new Parameter().PASSWORD_DB,
					new Parameter().IPHOST, new Parameter().PORT);
		 ventedata=new VenteData();
		 String id_TmpV,num_facV,Prix_VenteV,Stock_SortieV,SubtotalV;
	        String colon[] = {"id","num_facture","designation","code_produit","reference","prix_vente","stock_sortie","subtotal"};
	        rs = db.fcSelectCommand(colon, "vente", "num_facture='" + Txt_Fac.getText() + "'");
	        try {
	        	
	        	while(rs.next()) {
	        id_TmpV=rs.getString("id");
	        num_facV=rs.getString("num_facture");
	        Prix_VenteV=rs.getString("prix_vente");
	        Stock_SortieV=rs.getString("stock_sortie");
	        SubtotalV=rs.getString("subtotal");
	        
	        
	        ventedata.setId(id_TmpV);
	        ventedata.setNum_Facture(num_facV);
	        ventedata.setPrix_Vente(Prix_VenteV);
	        ventedata.setStock_Sortie(Stock_SortieV);
	        ventedata.setSub_Total(SubtotalV);
	        
	        Table_Vente.getItems().add(ventedata);
	       // if (IdListe_delete.contains(id_TmpV)==false) {
		        //Table_Vente.getItems().add(ventedata);
		        
		       // IdListe_delete.add(id_TmpV);
		       // }
	       ventedata=new VenteData();
	                	}
	        	
	        } catch (SQLException ex) {
		 
	            log.error(ex.getMessage() );
	     }
			ventedata=new VenteData();
	    }
	 
	 // code pour imprimer 
		 public void Imprimer () throws DocumentException {
			 System.out.println("fact1");
			 String id_TmpV,Prix_VenteV,Stock_SortieV,SubtotalV;
			 BDD db = new BDD(new Parameter().HOST_DB, new Parameter().USERNAME_DB, new Parameter().PASSWORD_DB,
						new Parameter().IPHOST, new Parameter().PORT);
			 Paragraph Paragraph1=new Paragraph();
     		Paragraph Paragraph2=new Paragraph();
     		Paragraph Paragraph3=new Paragraph();
     		Paragraph Paragraph4=new Paragraph();
     		Paragraph Paragraph5=new Paragraph();
			 
			 Document document =new Document ();
			   try {
	               PdfWriter.getInstance (document, new FileOutputStream("Facture_Stock.pdf"));
	           } catch (FileNotFoundException ex) {
	        	   log.error(ex.getMessage() );
	           } catch (DocumentException ex) {
	        	   log.error(ex.getMessage() );
	           }
	    document.open();
	           try {
	        	   document.add(new Paragraph("ETS Zambou"));
	               document.add(new Paragraph("Nom    | Quantite      | Prix  "));
	           } catch (DocumentException ex) {
	        	   log.error(ex.getMessage() );
	           }
	           String colon[] = {"id","num_facture","designation","code_produit","reference","prix_vente","stock_sortie","subtotal"};
		        rs = db.fcSelectCommand(colon, "vente", "num_facture='" + Txt_Fac.getText() + "'");
		        try {
		        	System.out.println("fact2");
		        	while(rs.next()) {
		        id_TmpV=rs.getString("designation");
		        SubtotalV=rs.getString("subtotal");
		        Stock_SortieV=rs.getString("stock_sortie");
		        Paragraph1.add(id_TmpV);
		        Paragraph1.add("       ");
		        Paragraph1.add(Stock_SortieV);
		        Paragraph1.add("                    ");
		        Paragraph1.add(SubtotalV);
		        document.add(Paragraph1);
		        	}
		        	
		        	Paragraph2.add("-----------------------------------------------------------------------");
		        	document.add(Paragraph2);
		        	Paragraph3.add("                                          ");
		        	Paragraph3.add(Label_Total.getText());
		        	document.add(Paragraph3);
		        	Paragraph4.add("             ");
		        	Paragraph4.add("Payé :                               ");
		        	Paragraph4.add(Txt_Cash.getText());
		        	document.add(Paragraph4);
		        	Paragraph5.add("             ");
		        	Paragraph5.add("Reste :                              ");
		        	Paragraph5.add(Txt_Reste.getText());
		        	System.out.println("fact3");
			        document.add(Paragraph5);
			        document.close();
			        retourProduit();
			        actualiserData();
			        Txt_Fac.setText("");
			        Table_Vente.getItems().clear();
			        Table_Produit.getItems().clear();
		          } catch (SQLException ex) {
		   		 
		            log.error(ex.getMessage() );
		     }

		 }
		//code pour la convertion
		 public double parseDoubleNew(String s){
			    if(s == null || s.isEmpty()) 
			        return 0.0;
			    else
			        return Double.parseDouble(s);
			}
		  public void NumeroFacture() {
		    	//dispose();
		    	rs = db.querySelectAllFacNum();
		          try {
			            while (rs.next()) {
			            	 nr_facture= rs.getString("nr_facture");
			            }
			        } catch (SQLException ex) {
			            log.error(ex.getMessage() );
			        }
		          System.out.println(nr_facture);

		    	
		    }
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		table();
		
	}
	

}

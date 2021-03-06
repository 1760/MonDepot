package zambou.test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import afrique.cm.dla.zambou.snack.bean.LoginData;
import afrique.cm.dla.zambou.snack.bean.ProduitData;
import afrique.cm.dla.zambou.snack.bean.TableData;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import zambou.test.a.BDD;
import zambou.test.a.Parameter;
import zambou.test.a.ShowAlertError;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;

public class ProduitController implements Initializable{
	@FXML private TableView <ProduitData>Table_Produit;
	@FXML private TextField Txt_Stock;
	@FXML private TextField Txt_Produit;
	@FXML private TextField Txt_Reference;
	@FXML private TextField Txt_Designation;
	@FXML private TextField Txt_Rangement;
	@FXML private TextField Txt_Fournisseur;
	@FXML private TextField Txt_Prix;
	@FXML private TextField Txt_Remise;
	@FXML private TextField Txt_Rech;
	@FXML private TextField Txt_Path;
	@FXML private ComboBox<String>Com_Rech;
	private static final Logger log = LoggerFactory.getLogger(PersonnelController1.class);
	private ProduitData data;
	ResultSet rs;
    BDD db;
    String id_Tmp,Code_Tmp,Ref_Tmp,Des_Tmp,Ran_Tmp,Four_Tmp,Rem_Tmp,Prix_Tmp,Stock_Tmp;
    public ProduitController() {
    	
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
    
   public void actualiser () {
	   data=new ProduitData();
	   
	   data.setCode_produit("");
	   data.setDesignation("");
	   data.setFournisseur("");
	   data.setRangement("");
	   data.setReference("");
	   data.setRemise("");
	   data.setStock("");
	   data.setPrix("");
	   Txt_Stock.setText(data.getStock());
       Txt_Produit.setText(data.getCode_produit());
	   Txt_Reference.setText(data.getReference());
	   Txt_Designation.setText(data.getDesignation());
	   Txt_Rangement.setText(data.getRangement());
	   Txt_Fournisseur.setText(data.getFournisseur());
	   Txt_Prix.setText(data.getPrix());
       Txt_Remise.setText(data.getRemise());
	   
   }
   
   public void zams(MouseEvent ev) {
	   data=new ProduitData();
		data = Table_Produit.getSelectionModel().getSelectedItem();
		Txt_Stock.setText(data.getStock());
		Txt_Produit.setText(data.getCode_produit());
		Txt_Reference.setText(data.getReference());
	    Txt_Designation.setText(data.getDesignation());
	    Txt_Rangement.setText(data.getRangement());
	    Txt_Fournisseur.setText(data.getFournisseur());
	    Txt_Prix.setText(data.getPrix());
	    Txt_Remise.setText(data.getRemise());
	
		 
	}
   public void File_Search() {
	   FileChooser fileChooser = new FileChooser();
	   fileChooser.setTitle("select file");
	   FileChooser.ExtensionFilter extFilter =new FileChooser.ExtensionFilter("*", "*");
	   fileChooser.getExtensionFilters().add(extFilter);
	   File file =fileChooser.showOpenDialog(null);
	   if (file!=null) {
		   Txt_Path.setText(file.getPath());
	   }
   }
   public void Excel_Importer() throws IOException {
		BDD db = new BDD(new Parameter().HOST_DB, new Parameter().USERNAME_DB, new Parameter().PASSWORD_DB,
				new Parameter().IPHOST, new Parameter().PORT);
	   Date date=new Date();
	   Calendar calendar=Calendar.getInstance();
	   calendar.setTime(date);
	   String Year=Integer.toString(calendar.get(Calendar.YEAR));
	   String Month=Integer.toString(calendar.get(Calendar.MONTH));
	   String Day=Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
	   String date_Tmp=Year.concat("-").concat(Month).concat("-").concat(Day);
	   String code_Produit;
	   String entree_Tmp;
	   String Type="E";
	   String Sortie="0";
	   String Inventaire="0";
	   File file =new File(Txt_Path.getText());
	   //Read XSL file
	   FileInputStream inputStream=new FileInputStream(file);
	   //Get the Workbook instance for XLS file
	   XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
	   XSSFSheet sheet = workbook.getSheetAt(0);
	   int rows=sheet.getPhysicalNumberOfRows();
	   for(int i=1;i<=rows;i++) {
		   System.out.println(rows);
	   XSSFCell cell = sheet.getRow(i).getCell(0);
	   XSSFCell cell1 = sheet.getRow(i).getCell(1);
	   //Double.toString
	   code_Produit=cell.getStringCellValue();
	   entree_Tmp=Double.toString(cell1.getNumericCellValue());
	   String[] colon = {"Date","code_produit", "Type_Invt", "Entree", "Sortie", "Inventaire"};
	   String[] inf = {date_Tmp,code_Produit,Type,entree_Tmp,Sortie,Inventaire};
		System.out.println(db.queryInsert("entree", colon, inf));
		if(i==(rows-1)) {
			System.out.println("zams");
			  inputStream.close();
		      workbook.close();
		      Txt_Path.setText("");
		      break;
		   }
	   }
   }
   
// code pour ajouter un personnel
	public void Produit_Ajouter() {
		BDD db = new BDD(new Parameter().HOST_DB, new Parameter().USERNAME_DB, new Parameter().PASSWORD_DB,
				new Parameter().IPHOST, new Parameter().PORT);
		ShowAlertError logmessage=new ShowAlertError();
		String ContentText="les donn�es saisies sont incorrectes!";
		String Title="Attention";
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
				|| data.getDesignation().equals("Type") ||data.getRangement().equals("Type") ||
				data.getFournisseur().equals("Type") || data.getPrix().equals("Type") || 
				data.getRemise().equals("Type")) {
			logmessage.showAlertWithDefaultHeaderTextAjouter(Title, ContentText);
			// JOptionPane.showMessageDialog(this, "SVP entrer les informations complete");
			System.out.println("ok333");
		} else {
			String[] colon = {"code_produit", "reference", "designation", "rangement", "fournisseur", "remise", "prix", "stock"};
			String[] inf = {data.getCode_produit(), data.getReference(), data.getDesignation()
					,data.getRangement(),data.getFournisseur(),data.getRemise(),data.getPrix(),data.getStock()};
			System.out.println(db.queryInsert("produit", colon, inf));
			table();
			actualiser();
		}
	}
	
	public void Produit_Modifier() {
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
		

		if (data.getStock().equals("") || data.getCode_produit().equals("") || data.getReference().equals("")
				|| data.getDesignation().equals("Type") ||data.getRangement().equals("Type") ||
				data.getFournisseur().equals("Type") || data.getPrix().equals("Type") || 
				data.getRemise().equals("Type")) {
			// JOptionPane.showMessageDialog(this, "SVP entrer les informations complete");
		} else {
			String[] colon = {"id", "code_produit", "reference", "designation", "rangement", "fournisseur", "remise", "prix", "stock"};
			String[] inf = { data.getCode_produit(), data.getReference(), data.getDesignation()
					,data.getRangement(),data.getFournisseur(),data.getRemise(),data.getPrix(),data.getStock()};
			data = Table_Produit.getSelectionModel().getSelectedItem();
			String id=data.getId();
			System.out.println(db.queryUpdate("produit", colon, inf, "id='" + id + "'"));
			table();
			actualiser();
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
		
		// code pour actualiser la liste de personnel
		public void Produit_Actualiser() {
			actualiser();
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
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		table();
	}
	

}

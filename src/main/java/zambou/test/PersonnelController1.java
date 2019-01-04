package zambou.test;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import afrique.cm.dla.zambou.snack.bean.LoginData;
import afrique.cm.dla.zambou.snack.bean.TableData;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import zambou.test.a.BDD;
import zambou.test.a.ShowAlertError;
import zambou.test.a.Parameter;

public class PersonnelController1 implements Initializable {

	@FXML
	private TableView<TableData> Table_User;
	@FXML
	private TextField Txt_Matricule;
	@FXML
	private TextField Txt_UserName;
	@FXML
	private TextField Txt_UserPassword;
	@FXML
	//private ComboBox<Bier> Txt_Type;
	private ComboBox<String>Txt_Type;
	@FXML
	private ComboBox<String>Com_Rech;
	/*@FMXL
	private ComboBox<String>Txt_Type1;*/
	@FXML
	private TextField Txt_Rech;
	private static final Logger log = LoggerFactory.getLogger(PersonnelController1.class);
	BDD db;
	ShowAlertError logmessage;
	ResultSet rs;
	private  LoginData Data;
	private TableData data;
	
	String id_Tmp,Matricule_Tmp,Password_Tmp,Type_Tmp,Username_Tmp;


	public PersonnelController1() {

		BDD db = new BDD(new Parameter().HOST_DB, new Parameter().USERNAME_DB, new Parameter().PASSWORD_DB,
				new Parameter().IPHOST, new Parameter().PORT);
		

	}
	
	public void Ajouter_Personnel() {
		System.out.println("ssss");
	}

	public void table() {
		BDD db = new BDD(new Parameter().HOST_DB, new Parameter().USERNAME_DB, new Parameter().PASSWORD_DB,
				new Parameter().IPHOST, new Parameter().PORT);
		
		 log.info("Chargement table personnel .............. ");
		 System.out.println("ssss");
		 System.out.println("ssss");
		  data = new TableData();
		  System.out.println("eee");
		 String a[] = {"id", "id_user", "username", "password", "type"}; 
		 System.out.println("iii");
		 rs =db.querySelect(a,"utilisateur");
		 System.out.println("bbbb");
		try {
			while (rs.next()) {
			id_Tmp=rs.getString("id");
			Matricule_Tmp=rs.getString("id_user");
			Password_Tmp=rs.getString("password");
			Type_Tmp=rs.getString("type");
			Username_Tmp=rs.getString("username");
			data.setId(id_Tmp);
			data.setMatricule(Matricule_Tmp);
			data.setPassword(Password_Tmp);
			data.setType(Type_Tmp);
			data.setUsername(Username_Tmp);
			Table_User.getItems().add(data);
			data = new TableData();
			}
			
			} catch (SQLException ex) {
		 
	            log.error(ex.getMessage() );
	     }
		
		 data = Table_User.getSelectionModel().getSelectedItem(); 
		// System.out.println("ssss222");
		 //Txt_UserName.setText(data.getUsername());
		 
		
       Txt_Type.getItems().addAll("caissier","directeur");
       Com_Rech.getItems().addAll("Id","Matricule","Username","Type");      
	}
	public void actualiser() {
		
		Txt_Matricule.setText("");
		Txt_UserName.setText("");
		Txt_UserPassword.setText("");
	    Txt_Type.getItems().removeAll("caissier","directeur");
	    Com_Rech.getItems().removeAll("Id","Matricule","Username","Type"); 
	}
	
	public void zams(MouseEvent ev) {
		 data = Table_User.getSelectionModel().getSelectedItem();
		Txt_UserName.setText(data.getUsername());
		Txt_Matricule.setText(data.getMatricule());
		Txt_UserPassword.setText(data.getPassword());
	    Txt_Type.setValue(data.getType());
	
		 
	}
	

	// code pour ajouter un personnel
	public void Personnel_Ajouter() {
		Data = new LoginData();
		ShowAlertError logmessage=new ShowAlertError();
		Data.setMatricule(Txt_Matricule.getText());
		Data.setUsername(Txt_UserName.getText());
		Data.setPassword(Txt_UserPassword.getText());
		String ContentText="Veuillez entrer quelque chose s´il vous plait!";
		String Title="Attention";
		String selectedItemValue = Txt_Type.getSelectionModel().getSelectedItem();
		Data.setType(selectedItemValue);

		if (Data.getMatricule().equals("") || Data.getUsername().equals("") || Data.getPassword().equals("")
				|| Data.getType().equals("Type")) {
			// JOptionPane.showMessageDialog(this, "SVP entrer les informations complete");
			logmessage.showAlertWithDefaultHeaderTextAjouter(Title,ContentText);
			
			
		} else {
			BDD db = new BDD(new Parameter().HOST_DB, new Parameter().USERNAME_DB, new Parameter().PASSWORD_DB,
					new Parameter().IPHOST, new Parameter().PORT);
			String[] colon = { "id_user", "username", "password", "type" };
			String[] inf = { Data.getMatricule(), Data.getUsername(), Data.getPassword(), Data.getType().toString() };
			System.out.println(db.queryInsert("utilisateur", colon, inf));
			Table_User.getItems().clear();
			table();
		}
	}

	// code pour modifier les données d´un employé
	public void Personnel_Modifier() {
		ShowAlertError logmessage=new ShowAlertError();
		Data = new LoginData();
		Data.setMatricule(Txt_Matricule.getText());
		Data.setUsername(Txt_UserName.getText());
		Data.setPassword(Txt_UserPassword.getText());
		String selectedItemValue = Txt_Type.getSelectionModel().getSelectedItem();
		String ContentText="Veuillez entrer quelque chose s´il vous plait!";
		String Title="Attention";
		Data.setType(selectedItemValue);
	if (Data.getMatricule().equals("") || Data.getUsername().equals("") || Data.getPassword().equals("")
				|| Data.getType().equals("")){
			logmessage.showAlertWithDefaultHeaderTextAjouter(Title,ContentText);
		}else{
		BDD db = new BDD(new Parameter().HOST_DB, new Parameter().USERNAME_DB, new Parameter().PASSWORD_DB,
				new Parameter().IPHOST, new Parameter().PORT);
			 String[] colon = {"id_user", "username", "password", "type"}; 
			 String[] inf ={Data.getMatricule(), Data.getUsername(), Data.getPassword(),
			 Data.getType().toString() }; 
			 data = Table_User.getSelectionModel().getSelectedItem();
			 String id =data.getMatricule();
			// String.valueOf(Table_User.getValueAt(Table_User.getSelectedRow(), 0));
			 System.out.println(db.queryUpdate("utilisateur", colon, inf, "id_user='" + id + "'"));
			actualiser();
			Table_User.getItems().clear();
			table();
		}
	}
	


	// code pour supprimer un personnel
	public void Personnel_Supprimer() {
		ShowAlertError logmessage=new ShowAlertError();
		String ContentText="Voulez vraiment supprimer cet Utilisateur?";
		String Title="Supprimer un Utilisateur";
		String DataBaseTable="utilisateur";
		Table_User.getSelectionModel().getSelectedItem();

		  data = Table_User.getSelectionModel().getSelectedItem();
		  String id= data.getMatricule();
		 /*String.valueOf(Table_User.getValueAt(Table_User.getSelectedRow(),0));
		  if (JOptionPane.showConfirmDialog(this,
		 * "est ce que tu es sure que tu veux suprimer", "attention!!!",
		 * JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
		 * db.queryDelete("utilisateur", "id=" + id); } else { return; }
		 */
		  
		  
logmessage.showAlertConfirmationDelete(Title, ContentText, DataBaseTable, id);
        actualiser();
		Table_User.getItems().clear();
		table();
	}

	// code pour actualiser la liste de personnel
	public void Personnel_Actualiser() {
		actualiser();
		table();
	}

	// Code pour une recherche spécifique
	public void Personnel_Recherche() {
		BDD db = new BDD(new Parameter().HOST_DB, new Parameter().USERNAME_DB, new Parameter().PASSWORD_DB,
				new Parameter().IPHOST, new Parameter().PORT);
		String selectedItemValue = Com_Rech.getSelectionModel().getSelectedItem();
		if (Txt_Rech.getText().equals("")) {
			// JOptionPane.showMessageDialog(this, "SVP entrer quelque chose");
		} else {

			if (selectedItemValue.equals("Id")) {
				System.out.println("ssss222");
			data=new TableData();
			System.out.println("ssss332");
				rs = db.querySelectAll("utilisateur", "id LIKE '%" + Txt_Rech.getText() + "%' ");
				try {
					while (rs.next()) {
						System.out.println("ssss112");
					id_Tmp=rs.getString("id");
					Matricule_Tmp=rs.getString("id_user");
					Password_Tmp=rs.getString("password");
					Type_Tmp=rs.getString("type");
					Username_Tmp=rs.getString("username");
					data.setId(id_Tmp);
					data.setMatricule(Matricule_Tmp);
					data.setPassword(Password_Tmp);
					data.setType(Type_Tmp);
					data.setUsername(Username_Tmp);
					Table_User.getItems().add(data);
					data = new TableData();
					}
					
					} catch (SQLException ex) {
				 
			            log.error(ex.getMessage() );
			     }
			}
			
			  else if (selectedItemValue.equals("Matricule")) { 
			 rs = db.querySelectAll("utilisateur", "id_user LIKE '%" + Txt_Rech.getText() + "%' "); 
			 try {
				 data=new TableData();
					while (rs.next()) {
						System.out.println("ssss221");
					id_Tmp=rs.getString("id");
					Matricule_Tmp=rs.getString("id_user");
					Password_Tmp=rs.getString("password");
					Type_Tmp=rs.getString("type");
					Username_Tmp=rs.getString("username");
					data.setId(id_Tmp);
					data.setMatricule(Matricule_Tmp);
					data.setPassword(Password_Tmp);
					data.setType(Type_Tmp);
					data.setUsername(Username_Tmp);
					Table_User.getItems().add(data);
					data = new TableData();
					}
					
					} catch (SQLException ex) {
				 
			            log.error(ex.getMessage() );
			     } 
			  } else if(selectedItemValue.equals("Username")) { rs =
			  db.querySelectAll("utilisateur", "username LIKE '%" + Txt_Rech.getText() +"%' "); 
			  try {
				    data=new TableData();
					while (rs.next()) {
					id_Tmp=rs.getString("id");
					Matricule_Tmp=rs.getString("id_user");
					Password_Tmp=rs.getString("password");
					Type_Tmp=rs.getString("type");
					Username_Tmp=rs.getString("username");
					data.setId(id_Tmp);
					data.setMatricule(Matricule_Tmp);
					data.setPassword(Password_Tmp);
					data.setType(Type_Tmp);
					data.setUsername(Username_Tmp);
					Table_User.getItems().add(data);
					data = new TableData();
					}
					
					} catch (SQLException ex) {
				 
			            log.error(ex.getMessage() );
			     }			  
			  } else if (selectedItemValue.equals("Type")) { 
				  rs = db.querySelectAll("utilisateur", "type LIKE '%" + Txt_Rech.getText() +"%' "); 
				  try {
					    data=new TableData();
						while (rs.next()) {
						id_Tmp=rs.getString("id");
						Matricule_Tmp=rs.getString("id_user");
						Password_Tmp=rs.getString("password");
						Type_Tmp=rs.getString("type");
						Username_Tmp=rs.getString("username");
						data.setId(id_Tmp);
						data.setMatricule(Matricule_Tmp);
						data.setPassword(Password_Tmp);
						data.setType(Type_Tmp);
						data.setUsername(Username_Tmp);
						Table_User.getItems().add(data);
						data = new TableData();
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

	/*public void initialize(URL location, ResourceBundle resources) {
		
*/
}

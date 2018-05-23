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
import zambou.test.a.Parameter;

public class PersonnelController implements Initializable {

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
	private static final Logger log = LoggerFactory.getLogger(PersonnelController.class);
	BDD db;
	ResultSet rs;
	private  LoginData Data;
	private TableData data;
	//private Bier arthur;
	String id_Tmp,Matricule_Tmp,Password_Tmp,Type_Tmp,Username_Tmp;


	public PersonnelController() {

		BDD db = new BDD(new Parameter().HOST_DB, new Parameter().USERNAME_DB, new Parameter().PASSWORD_DB,
				new Parameter().IPHOST, new Parameter().PORT);
		//table();
	}

	 
	
	
	public void Ajouter_Personnel() {
		System.out.println("ssss");
		
		//table();
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
			System.out.println(id_Tmp);
			data.setId(id_Tmp);
			data.setMatricule(Matricule_Tmp);
			data.setPassword(Password_Tmp);
			data.setType(Type_Tmp);
			data.setUsername(Username_Tmp);
			System.out.println(data.getId());
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
		
		Data = new LoginData();
		Data.setUsername("");
		Data.setMatricule("");
		Data.setPassword("");
		Data.setType("Type");
		Txt_Matricule.setText(Data.getMatricule());
		Txt_UserName.setText(Data.getUsername());
		Txt_UserPassword.setText(Data.getPassword());
	    Txt_Type.setValue(Data.getType());
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
		Data.setMatricule(Txt_Matricule.getText());
		Data.setName(Txt_UserName.getText());
		Data.setPassword(Txt_UserPassword.getText());
		String selectedItemValue = Txt_Type.getSelectionModel().getSelectedItem();
		Data.setType(selectedItemValue);

		if (Data.getMatricule().equals("") || Data.getUsername().equals("") || Data.getPassword().equals("")
				|| Data.getType().equals("Type")) {
			// JOptionPane.showMessageDialog(this, "SVP entrer les informations complete");
		} else {
			String[] colon = { "id_user", "username", "password", "type" };
			String[] inf = { Data.getMatricule(), Data.getUsername(), Data.getPassword(), Data.getType().toString() };
			System.out.println(db.queryInsert("utilisateur", colon, inf));
			table();
			actualiser();
		}
	}

	// code pour modifier les données d´un employé
	public void Personnel_Modifer() {
		Data = new LoginData();
		Data.setMatricule(Txt_Matricule.getText());
		Data.setName(Txt_UserName.getText());
		Data.setPassword(Txt_UserPassword.getText());
		String selectedItemValue = Txt_Type.getSelectionModel().getSelectedItem();
		Data.setType(selectedItemValue);
		if (Data.getMatricule().equals("") || Data.getUsername().equals("") || Data.getPassword().equals("")
				|| Data.getType().equals("Type")) {
			// JOptionPane.showMessageDialog(this, "SVP entrer les informations complete");
		} else {
			
			 String[] colon = {"id_user", "username", "password", "type"}; 
			 String[] inf ={Data.getMatricule(), Data.getUsername(), Data.getPassword(),
			 Data.getType().toString() }; 
			 data = Table_User.getSelectionModel().getSelectedItem();
			 String id =data.getId();
			// String.valueOf(Table_User.getValueAt(Table_User.getSelectedRow(), 0));
			 System.out.println(db.queryUpdate("utilisateur", colon, inf, "id='" + id + "'"));
			table();
			actualiser();
		}
	}
	


	// code pour supprimer un personnel
	public void Personnel_Supprimer() {
		Table_User.getSelectionModel().getSelectedItem();

		  data = Table_User.getSelectionModel().getSelectedItem();
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

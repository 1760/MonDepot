package zambou.test;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import afrique.cm.dla.zambou.snack.bean.LoginData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import zambou.test.a.BDD;
import zambou.test.a.Parameter;

public class LoginController 
{
	private Scene scene;
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @FXML private BorderPane RootPanne;
    @FXML private TextField userLogin;
    @FXML private Label messageLabel;
    @FXML private PasswordField userPassword;
    
    
    private LoginData data ;
    
    BDD    db= new BDD (new Parameter().HOST_DB,new Parameter().USERNAME_DB,new Parameter().PASSWORD_DB,new Parameter().IPHOST,
			new Parameter().PORT);
    
    ResultSet rs;
    String username1,password1,hak,nr_facture;
    
    private void showAlertWithHeaderText() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Erreur coonecxion");
        alert.setHeaderText("Results: mot de passe ou nom utilisateur incorrect");
        alert.setContentText("échec de connection!");
 
        alert.showAndWait();
    }


    public void userLogin() throws  IOException{
       log.info("Action .............. ");

		 data = new LoginData();
		 data.setName( userLogin.getText());
		 data.setPassword( userPassword.getText());
		 
    	 rs = db.querySelectAll("utilisateur"
       		  , "username='"+data.getName()+"' and password='"+ data.getPassword()+ "'");

		  try {
	            while (rs.next()) {
	                username1 = rs.getString("username");
	                password1 = rs.getString("password");
	                hak = rs.getString("type");
	            }
	        } catch (SQLException ex) {
	            log.error(ex.getMessage() );
	        }

		  System.out.println(hak);
	        if (username1 == null && password1 == null) {
	        	
	            //error handling
	        	 showAlertWithHeaderText();
	        } else {
	        	log.info(hak);
	            if (hak.equals("directeur")) {
	            	
	            	String fxmlFile = "/fxml/Produit.fxml";
	           	 FXMLLoader loader = new FXMLLoader();
	           	BorderPane pane = loader.load(getClass().getResourceAsStream(fxmlFile));
	           //	TabPane pane = loader.load(getClass().getResourceAsStream(fxmlFile));
	           	 
	           	
	           	RootPanne.getChildren().setAll(pane);
	           	
	           	} else {
	            	String fxmlFile = "/fxml/Caissier.fxml";
		           	 FXMLLoader loader = new FXMLLoader();
		           	BorderPane pane = loader.load(getClass().getResourceAsStream(fxmlFile));
		        

		           	Object obj = loader.getController();
		           	System.out.println(obj);
		           	RootPanne.getChildren().setAll(pane);
	            }
	        }
    	
    }
    
   /* @FXML private void userLogin(ActionEvent event) throws IOException {
    	String fxmlFile = "/fxml/Directeur.fxml";
    	 FXMLLoader loader = new FXMLLoader();
    	BorderPane pane=loader.load(getClass().getResourceAsStream(fxmlFile));
    	RootPanne.getChildren().setAll(pane);
    	
    }*/
    public void userExit() {
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

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
    
    
    
}

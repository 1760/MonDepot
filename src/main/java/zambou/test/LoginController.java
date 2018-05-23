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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import zambou.test.a.BDD;
import zambou.test.a.Parameter;

public class LoginController
{
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @FXML private BorderPane RootPanne;
    @FXML private TextField userLogin;
    @FXML private Label messageLabel;
    @FXML private PasswordField userPassword;
    
    private LoginData data ;
    
    BDD    db= new BDD (new Parameter().HOST_DB,new Parameter().USERNAME_DB,new Parameter().PASSWORD_DB,new Parameter().IPHOST,
			new Parameter().PORT);
    
    ResultSet rs;
    String username1,password1,hak;


    public void userLogin() {
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
	        	
	            //JOptionPane.showMessageDialog( this, "le nom utilisateur ou le mots de pass est incorrect");
	        } else {
	        	log.info(hak);
	            if (hak.equals("directeur")) {
	            	
	            	/*
	                Principale h = new Principale();
	                h.setVisible(true);
	                this.dispose();
	                */
	            	
	            

	            } else {
	            	/*
	                Caissier k = new Caissier();
	                k.setVisible(true);
	                this.dispose();
	                */
	            }
	        }
    	
    }
    
    @FXML private void userLogin(ActionEvent event) throws IOException {
    	String fxmlFile = "/fxml/Directeur.fxml";
    	 FXMLLoader loader = new FXMLLoader();
    	BorderPane pane=loader.load(getClass().getResourceAsStream(fxmlFile));
    	RootPanne.getChildren().setAll(pane);
    	
    }
    public void userExit() {
    	//dispose();
    }
    
    
    
}

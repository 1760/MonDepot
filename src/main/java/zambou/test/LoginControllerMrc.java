package zambou.test;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import afrique.cm.dla.zambou.snack.bean.LoginDataMrc;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import zambou.test.a.BDDMrc;
import zambou.test.a.ParameterMrc;

public class LoginControllerMrc
{
    private static final Logger log = LoggerFactory.getLogger(LoginControllerMrc.class);
    @FXML private BorderPane RootPanne;
    @FXML private TextField userLogin;
    @FXML private Label messageLabel;
    @FXML private PasswordField userPassword;
    int a=1;
    
    private LoginDataMrc data ;
    
    BDDMrc    db= new BDDMrc (new ParameterMrc().HOST_DB,new ParameterMrc().USERNAME_DB,new ParameterMrc().PASSWORD_DB,
			new ParameterMrc().PORT);
    
    ResultSet rs;
    String username1,password1,hak;


    public void userLogin() throws IOException{
       log.info("Action .............. ");

		 data = new LoginDataMrc();
		 data.setName( userLogin.getText());
		 data.setPassword( userPassword.getText());
		 System.out.println("keine passende info");
    	 rs = db.querySelectAll("Mrc_Directoire"
       		  , "Username='"+data.getName()+"' and Password='"+ data.getPassword()+ "'");

		  try {
	            while (rs.next()) {
	                username1 = rs.getString("Username");
	                password1 = rs.getString("Password");
	            }
	        } catch (SQLException ex) {
	            log.error(ex.getMessage() );
	        }

		  System.out.println(hak);
	        if (username1 == null && password1 == null) {
	        	
	            //JOptionPane.showMessageDialog( this, "le nom utilisateur ou le mots de pass est incorrect");
	        	 System.out.println("keine passende info");
	        	  
	        } 
	        
	        else {
	        	String fxmlFile = "/fxml/Directeur.fxml";
	       	 FXMLLoader loader = new FXMLLoader();
	       	BorderPane pane=loader.load(getClass().getResourceAsStream(fxmlFile));
	       	RootPanne.getChildren().setAll(pane);
	        }
	        /*else {
	        	log.info(hak);
	            if (hak.equals("directeur")) {
	            	
	            	/*
	                Principale h = new Principale();
	                h.setVisible(true);
	                this.dispose();
	                
	            	
	            

	            } else {
	            	/*
	                Caissier k = new Caissier();
	                k.setVisible(true);
	                this.dispose();
	                
	            }
	        }*/
    	
    }
    
   /* @FXML private void userLogin(ActionEvent event) throws IOException {
    	if (a==0) {
    		
    	String fxmlFile = "/fxml/Directeur.fxml";
    	 FXMLLoader loader = new FXMLLoader();
    	BorderPane pane=loader.load(getClass().getResourceAsStream(fxmlFile));
    	RootPanne.getChildren().setAll(pane);
    	
    	}
    	
    }*/
    public void userExit() {
    	//dispose();
    }
    
    
    
}

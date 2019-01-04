package zambou.test;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;



public class MainApp extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) throws Exception {
        launch(args);
    }
    class WindowButtons extends HBox {

        public WindowButtons() {
            Button closeBtn = new Button("X");

            closeBtn.setOnAction(new EventHandler<ActionEvent>() {

                
                public void handle(ActionEvent actionEvent) {
                    Platform.exit();
                }
            });

            this.getChildren().add(closeBtn);
        }
    }

    public void start(Stage stage) throws Exception {

        log.info("Starting Hello JavaFX and Maven demonstration application");
      // stage.initStyle(StageStyle.TRANSPARENT);
        
        ToolBar toolBar = new ToolBar();
        int height =25;
        toolBar.setPrefHeight(height);
        toolBar.setMinHeight(height);
        toolBar.setMaxHeight(height);
        ToolBar toolBar1=new ToolBar();
        toolBar1.setPrefHeight(height);
        toolBar1.setMinHeight(height);
        toolBar1.setMaxHeight(height);
        
        //toolBar.getItems().add(new WindowButtons());
        

        String fxmlFile = "/fxml/Login.fxml";
       // fxmlFile = "/fxml/Produit.fxml";
        
        log.debug("Loading FXML for main view from: {}", fxmlFile);
        FXMLLoader loader = new FXMLLoader();
        BorderPane rootNode1=(BorderPane)loader.load(getClass().getResourceAsStream(fxmlFile));
        //rootNode1.setStyle("-fx-background-color: #ffffff;");
        rootNode1.setStyle("-fx-background-color: green;");
        //rootNode1.setTop(toolBar);
        //Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        //rootNode.setStyle("-fx-background-color: #ffffff;");
      //rootNode1.setBottom(toolBar1);
        

        LoginController cont = (LoginController) loader.getController();
        
        log.debug("Showing JFX scene");
        
        Scene scene = new Scene(rootNode1, 1000, 600);
        
     
       // scene.getStylesheets().add("/styles/styles.css");
        
        //scene.setRoot(value);
        cont.setScene(scene);
        //rootNode.getScene()
        
        
        
        stage.setTitle("                      Gestion Stock");
        stage.setScene(scene);
        stage.show();
       // https://www.programcreek.com/java-api-examples/?api=javafx.stage.Modality

//System.exit(-1);

    }
}

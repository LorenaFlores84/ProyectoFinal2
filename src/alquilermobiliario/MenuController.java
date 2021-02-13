/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilermobiliario;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class MenuController implements Initializable {

    @FXML
    private Button btnMobiliario;
    @FXML
    private Button btnCliente;
    @FXML
    private Button btnOrden;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnMobiliario_click(ActionEvent event) throws IOException {
    
        Stage stage = new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("Mobiliario.fxml"));
           Scene scene = new Scene(root);
           
           stage.setResizable(false);
           stage.setTitle("Mobiliario");
           //stage.getIcons().add(new Image("/img/book.png"));
           
           stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
               
               @Override
               public void handle(WindowEvent e){
                   Platform.exit();
                   System.exit(0);
               }
            });
           
             stage.setScene(scene);
             stage.show();
           
             ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void btnCliente_click(ActionEvent event) {
    }

    @FXML
    private void btnOrden_click(ActionEvent event) {
    }
    
}

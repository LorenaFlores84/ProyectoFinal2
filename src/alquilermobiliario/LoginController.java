/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilermobiliario;

import java.awt.Image;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Loly
 */
public class LoginController implements Initializable {
    
    
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtContrasena;
    @FXML
    private Button btnEntrar;
    @FXML
    private Button btnSalir;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnEntrar_click(ActionEvent event) throws IOException {
       String usuario = txtUsuario.getText();
       String contrasena = txtContrasena.getText();
       if(usuario.equals("admin") && contrasena.equals("123")){
           Stage stage = new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
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
           
           //oculto ventana de login
           ((Node)(event.getSource())).getScene().getWindow().hide();
       }else{
           JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrecta",
                   "Acceso a usuarios",JOptionPane.ERROR_MESSAGE);
       }
    }

    @FXML
    private void btnSalir_click(ActionEvent event) {
        System.exit(0);
    }
    
}

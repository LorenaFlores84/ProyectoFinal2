 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilermobiliario;


import custom.NumberField;
import entidades.Mobiliario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javax.swing.JOptionPane;
import repo.MobiliarioRepo;
import reportes.Reporte;

/**
 * FXML Controller class
 *
 * @author Loly
 */
public class MobiliarioController implements Initializable {

    public ObservableList<Mobiliario> dataMobiliario = FXCollections.observableArrayList();
    @FXML
    private Tab tabEntrada;
    @FXML
    private Button btnNuevo;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Tab tabSalida;
    @FXML
    private TextField txtBuscar;
    @FXML
    private ChoiceBox<String> choiceBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnRefrescar;
    @FXML
    private TableView<Mobiliario> tablaMobiliario;
    @FXML
    private TableColumn<Mobiliario, Long> coId;
    @FXML
    private TableColumn<Mobiliario, String> coTipo;
    @FXML
    private TableColumn<Mobiliario, Integer> coCantidad;
    @FXML
    private TableColumn<Mobiliario, String> coDescripcion;
    @FXML
    private Button btnReporte;
    @FXML
    private Label labResultado;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtTipo;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private NumberField txtCantidad;
    @FXML
    private TabPane tabPaneMobiliario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurarTabla();
        rellenarTablaMobiliario();
        vaciarCampos();
        deshabilitarCampos();
        
        
        choiceBuscar.getItems().addAll("Id", "Tipo", "Cantidad", "Descripcion");
        choiceBuscar.setValue("Id");
        
    }    
    public void configurarTabla(){
        coId.setCellValueFactory(new PropertyValueFactory<>("id"));
        coTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        coCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        coDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tablaMobiliario.setItems(dataMobiliario);
    }
    public void rellenarTablaMobiliario(){
        dataMobiliario.clear();
        MobiliarioRepo mobiliarioRepo = new MobiliarioRepo();
        ObservableList<Mobiliario> mobiliarios = mobiliarioRepo.buscarTodos();
        dataMobiliario.setAll(mobiliarios);
        int resultados = mobiliarios.size();
        labResultado.setText("Resultados: "+resultados);
    }
    
    private void vaciarCampos(){
        txtId.setText("");
        txtTipo.setText("");
        txtCantidad.setText("");
        txtDescripcion.setText("");
    }
    private void deshabilitarCampos(){
        txtId.setDisable(true);
        txtTipo.setDisable(true);
        txtCantidad.setDisable(true);
        txtDescripcion.setDisable(true);
    }
    private void habilitarCampos(){
        txtId.setDisable(true);//siempre desabilitado
        txtTipo.setDisable(false);
        txtCantidad.setDisable(false);
        txtDescripcion.setDisable(false);
    }
    @FXML
    private void btnNuevo_click(ActionEvent event) {
        vaciarCampos();
        habilitarCampos();
        btnNuevo.setDisable(true);
        btnGuardar.setDisable(false);
        btnEliminar.setDisable(true);
        btnCancelar.setDisable(false);
        
    }

    @FXML
    private void btnGuardar_click(ActionEvent event) {
        long id;
        String tipo = txtTipo.getText();
        int cantidad;
        String descripcion = txtDescripcion.getText();
        
        try{
            cantidad = Integer.parseInt(txtCantidad.getText());
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "No puede ingresar letras en: "
            + "campos numericos.", "Error de validacion", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Mobiliario mobiliario;
        if(txtId.getText().trim().equals("")){
            mobiliario = new Mobiliario(tipo, cantidad, descripcion);
        }
            else{
                    id = Long.parseLong(txtId.getText());
                    mobiliario = new Mobiliario(id, tipo, cantidad, descripcion);
                    
                }
            MobiliarioRepo mobiliarioRepo = new MobiliarioRepo();
            if(mobiliarioRepo.guardar(mobiliario)){
                vaciarCampos();
                deshabilitarCampos();
                btnNuevo.setDisable(false);
                btnGuardar.setDisable(true);
                btnEliminar.setDisable(true);
                btnCancelar.setDisable(true);
                JOptionPane.showMessageDialog(null,"Se han guardado los cambios.",
                        "Aviso",JOptionPane.INFORMATION_MESSAGE);
                rellenarTablaMobiliario();
            
            }
    }

    @FXML
    private void btnEliminar_click(ActionEvent event) {
      int opcion = JOptionPane.showConfirmDialog(null,"Desea eliminar el"
      +"registro", "Confirmacion",JOptionPane.YES_NO_OPTION,2);
      if(opcion == JOptionPane.YES_OPTION){
          long id = Long.parseLong(txtId.getText());
          MobiliarioRepo mobiliarioRepo = new MobiliarioRepo();
          mobiliarioRepo.eliminar(id);
          JOptionPane.showMessageDialog(null,"Registro eliminado con exito.",
                        "Aviso",JOptionPane.INFORMATION_MESSAGE);
          
        vaciarCampos();
        habilitarCampos();
        btnNuevo.setDisable(false);
        btnGuardar.setDisable(true);
        btnEliminar.setDisable(true);
        btnCancelar.setDisable(true);
        rellenarTablaMobiliario();
      }
    }

    @FXML
    private void btnCancelar_click(ActionEvent event) {
        vaciarCampos();
        habilitarCampos();
        btnNuevo.setDisable(false);
        btnGuardar.setDisable(true);
        btnEliminar.setDisable(true);
        btnCancelar.setDisable(true);
    }

    @FXML
    private void btnBuscar_click(ActionEvent event) {
        dataMobiliario.clear();
        String modoBusqueda = choiceBuscar.getValue();
        if(modoBusqueda.equals("Id")){
            long id=0;
            try{
                id = Long.parseLong(txtBuscar.getText());
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Ingrese un Id valido.",
                        "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            MobiliarioRepo mobiliarioRepo = new MobiliarioRepo();
            Mobiliario mobiliario = mobiliarioRepo.buscarMobiliario(id);
            if(mobiliario != null){
                dataMobiliario.add(mobiliario);
                labResultado.setText("Resultados: ");
            }else{
                labResultado.setText("Resultados : 0");
            }
            return;   
        }
        MobiliarioRepo mobiliarioRepo = new MobiliarioRepo();
        ObservableList<Mobiliario> mobiliario = FXCollections.observableArrayList();
        
        switch(modoBusqueda){
            case "Tipo":
                String tipoBusqueda = txtBuscar.getText();
                mobiliario = mobiliarioRepo.buscarPorTipo(tipoBusqueda);
                break;
            case "Cantidad":
                
                break;
            case "Descripcion":
                
                break;
            default:
                JOptionPane.showMessageDialog(null, "Modo de busqueda incorrecta.",
                        "Error",JOptionPane.WARNING_MESSAGE);
                return;
        }
        dataMobiliario.setAll(mobiliario);
        int resultados = mobiliario.size();
        labResultado.setText("Resultados: "+resultados);
    }

    @FXML
    private void btnRefrescar_click(ActionEvent event) {
        rellenarTablaMobiliario();
    }

    @FXML
    private void contextMenu_click(ContextMenuEvent event) {
        ContextMenu menu = new ContextMenu();
        MenuItem itemModificar = new MenuItem("Modificar");
        itemModificar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                Mobiliario mobiliario = tablaMobiliario.getSelectionModel().getSelectedItem();
                if(mobiliario == null){
                    JOptionPane.showMessageDialog(null, "Seleccione un libro para"+
                             "editar.","Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                txtId.setText(String.valueOf(mobiliario.getId()));
                txtTipo.setText(mobiliario.getTipo());
                txtCantidad.setText(String.valueOf(mobiliario.getCantidad()));
                txtDescripcion.setText(mobiliario.getDescripcion());
                
                habilitarCampos();
                btnNuevo.setDisable(true);
                btnGuardar.setDisable(false);
                btnEliminar.setDisable(false);
                btnCancelar.setDisable(false);
                
                //cambiar a la pestaña
                SingleSelectionModel<Tab> selectionModel = tabPaneMobiliario.getSelectionModel();
                selectionModel.select(0);
            }
        });
        menu.getItems().add(itemModificar);
        tablaMobiliario.setContextMenu(menu);
    }

    @FXML
    private void btnReporte_click(ActionEvent event) {
        Reporte reporte = new Reporte("mobiliario");
        reporte.generarReporte();
    }
    
}

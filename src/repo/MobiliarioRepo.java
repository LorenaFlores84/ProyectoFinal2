/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repo;

import db.JdbcHelper;
import entidades.Mobiliario;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author Loly
 */
public class MobiliarioRepo {
    
    private boolean insertar(Mobiliario mobiliario){
        String query = "INSERT INTO mobiliario (tipo,cantidad,descripcion) "
                + "VALUES ('"+mobiliario.getTipo()+"','"
                +mobiliario.getCantidad()+"','"
                +mobiliario.getDescripcion()+"')";
        JdbcHelper jdbc = new JdbcHelper();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;
    }
    
    private boolean modificar(Mobiliario mobiliario){
        String query = "UPDATE libro SET "+
                "tipo = '"+mobiliario.getTipo()+"',"+
                "cantidad = '"+mobiliario.getCantidad()+"',"+
                "descripcion = '"+mobiliario.getDescripcion()+"',"+
                "'WHERE id = "+mobiliario.getId();
        JdbcHelper jdbc = new JdbcHelper();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;
                
    }
    public boolean eliminar(long id){
        String query = "DELETE FROM mobiliario WHERE id = "+id;
        JdbcHelper jdbc = new JdbcHelper();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;
    }
    public boolean validar(Mobiliario mobiliario){
        StringBuilder sb = new StringBuilder();
        boolean esValido = true;
        if(mobiliario == null){
            esValido = false;
            sb.append("*No existe libro\n");
        }
        if(mobiliario.getTipo().trim().equals("")){
            esValido = false;
            sb.append("*Nombre requerido\n");
        } 
         if(mobiliario.getCantidad() <= 0){
            esValido = false;
            sb.append("*Páginas debe ser mayor a cero\n");
        }
        if(mobiliario.getDescripcion().trim().equals("")){
            esValido = false;
            sb.append("*Autor requerido\n");
        }
      
        if(!esValido){
            JOptionPane.showMessageDialog(null,"Se encontraron los siguientes "
                    + "errores: \n"+sb.toString(),"Error de validación",
                    JOptionPane.WARNING_MESSAGE);
        }
        return esValido;
    }
    
    public boolean guardar(Mobiliario mobiliario){
        if(validar(mobiliario) == false){
            return false;
        }
        boolean exito;
        if(mobiliario.getId() == 0)
            exito = insertar(mobiliario);
        else
            exito = modificar(mobiliario);
        return exito;
    }
    
    public Mobiliario buscarMobiliario(long idBusqueda){
        String query = "SELECT * FROM mobiliario WHERE id = "+idBusqueda;
        JdbcHelper jdbc = new JdbcHelper();
        ResultSet rs = jdbc.realizarConsulta(query);
        
        Mobiliario mobiliario = null;
        
        try{
            if(rs.next()){
                long id =idBusqueda;
                String tipo = rs.getString("tipo");
                int cantidad = rs.getInt("cantidad");
                String descripcion = rs.getString("descripcion");
                mobiliario = new Mobiliario(id, tipo, cantidad, descripcion);
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Error al buscar mobiliario: "+ex,
                    "Error",JOptionPane.ERROR_MESSAGE);
        }
        return mobiliario;
    }
    
    public ObservableList<Mobiliario> buscarTodos(){
        String query = "SELECT * FROM mobiliario";
        JdbcHelper jdbc = new JdbcHelper();
        ResultSet rs = jdbc.realizarConsulta(query);
        
        ObservableList<Mobiliario> mobiliarios = FXCollections.observableArrayList();
 
        
        try{
            while(rs.next()){
                long id = rs.getLong("id");
                String tipo = rs.getString("tipo");
                int cantidad = rs.getInt("cantidad");
                String descripcion = rs.getString("descripcion");
                mobiliarios.add(new Mobiliario(id, tipo, cantidad, descripcion));
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Error al buscar mobiliario: "+ex,
                    "Error",JOptionPane.ERROR_MESSAGE);
        }
        return mobiliarios;
    }

    public ObservableList<Mobiliario> buscarPorTipo(String tipoBusqueda){
        String query = "SELECT * FROM mobiliario WHERE tipo LIKE '%"+tipoBusqueda+"%'";
        JdbcHelper jdbc = new JdbcHelper();
        ResultSet rs = jdbc.realizarConsulta(query);
        
        ObservableList<Mobiliario> mobiliarios = FXCollections.observableArrayList();
 
        
        try{
            while(rs.next()){
                long id = rs.getLong("id");
                String tipo = rs.getString("tipo");
                int cantidad = rs.getInt("cantidad");
                String descripcion = rs.getString("descripcion");
                mobiliarios.add(new Mobiliario(id, tipo, cantidad, descripcion));
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Error al buscar mobiliario pot tipo: "+ex,
                    "Error",JOptionPane.ERROR_MESSAGE);
        }
        return mobiliarios;
    }
    //Tutorial agrega busqueda por autor y por genero.
}

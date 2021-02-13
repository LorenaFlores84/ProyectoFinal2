/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Loly
 */
public class JdbcHelper {
    //Para operaciones de consulta
    public ResultSet realizarConsulta(String query){
        ConexionMySql conexionMySql = new ConexionMySql();
        Connection conn = conexionMySql.conectar();
        ResultSet rs = null;
        Statement stQuery;
        try{
            stQuery = conn.createStatement();
            rs = stQuery.executeQuery(query);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Error al ejecutar "+query+": "+ ex);
        }
        return rs;
    }
    //Para operaciones de ejecutar, modificar y eliminar
    public boolean ejecutarQuery(String query){
        ConexionMySql conexionMySql = new ConexionMySql();
        Connection conn = conexionMySql.conectar();
        boolean exito = false;
        try{
            PreparedStatement ps = conn.prepareStatement(query);
            int affectedRows = ps.executeUpdate();
            if(affectedRows!=0)
                exito = true;
            else
                exito = false;
            System.out.println("Affected rows: "+affectedRows);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error al ejecutar"+query+": "+ex);
            exito = false;
        }
        return exito;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Loly
 */
public class ConexionMySql {
    public String database = "alquiler-mobiliario1";
    public String url = "jdbc:mysql://localhost/"+database;
    public String user = "root";
    public String password = "";
    
    public Connection conectar(){
        Connection link = null;
        try{
            Class.forName("org.gjt.mm.mysql.Driver");
            link = DriverManager.getConnection(this.url,this.user,this.password);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"ERROR 0 al conectar: "+ex
            +"\nAsegurese de que el servidor este encendido.", "ERROR", 
            JOptionPane.ERROR_MESSAGE);
        }
        return link;
    }
}

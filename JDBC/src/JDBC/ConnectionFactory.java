/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Gabriel
 */
public class ConnectionFactory {
    
    public Connection conectar() {
        
        //String [] info = ConfigBanco.infoBanco();
        
        String DRIVE = "com.mysql.jdbc.Driver";
       //String URL   = "jdbc:mysql://" + info[0] + ":" + info[1]  + "/" + info[2];
        String URL   = "jbdc:mysql://localhost:3306/";
        String USER  = "root";
        String PASS  = "larissa";
        
        try {
            Class.forName(DRIVE);
            return DriverManager.getConnection(URL, USER, PASS);

        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("Erro: " + ex);
            return null;
        }
        
    }

}

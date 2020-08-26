/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elie Tshibangu
 */
public class Connexion {
    
    //
    private static final String HOST="127.0.0.1";
    private static final String DRIVER="mysql";
    private static final String DBNAME="gestion_hospi";
    private static final String USER="root";
    private static final String PWD="";
    private static final String PORT="3306";
    //
    private static Connection connection=null;
    //
    public static Connection getConnection(){
        
        if(connection==null){
            
            try {
                
                String url="jdbc:"+DRIVER+"://"+HOST+":"+PORT+"/"+DBNAME;
                
                connection= DriverManager.getConnection(url,USER,PWD);
                
            } catch (SQLException ex) {
                Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }
    
    
}

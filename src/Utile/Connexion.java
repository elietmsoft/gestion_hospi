/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utile;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Elie Tshibangu
 */
public class Connexion {
   
   public static Connection con=null;
    //
    //static String url="jdbc:sqlserver://DESKTOP-G7PA6N2\\ELIETMSSERVER\\ELIETMSSERVER:1433;databaseName=exemple";
    static String url="jdbc:mysql://127.0.0.1:3306/gestion_hospi";
    static String user="root";
    static String pwd="";
    //creation d'un constructeur: établissement de la connexion
    public Connexion(){
        try {
            con=DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            System.out.println("ERROR : CONNEXION()"+e.getMessage());
        }
    }
    //fermeture de la connexion
    public static void FERMERCONNEXION(){
        try {
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR : CONNEXION()"+e.getMessage());
        }
    }
    public Connection conx(){
        return con;
    }
}
    


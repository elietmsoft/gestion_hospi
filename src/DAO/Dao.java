/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.Connexion;

/**
 *
 * @author Elie Tshibangu
 */
public abstract class Dao<T> {
    
    // les attributs que chaque dao va porter
    protected Connection connection;
    protected ResultSet rs;
    protected PreparedStatement statement;
    protected String sql;
    //
    // Contructeur n'est pas générique
    public Dao(){
        connection=Connexion.getConnection();
    }
    //
    // Méthodes abstraites dont tous les dao devront implémenter
    public abstract int add(T obj);
    public abstract int update(T obj);
    public abstract int delete(T obj);
    //
    
}

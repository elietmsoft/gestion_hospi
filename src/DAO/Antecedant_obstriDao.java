/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Antecedant_obstri;
import Model.Antecedant_obstri;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rodrigue Splendeur
 */


public class Antecedant_obstriDao extends Dao<Antecedant_obstri> {

    @Override
    public int add(Antecedant_obstri antecedant_obstri) {
        try {
            sql="insert into table_antecedant(nombre_gda,"
                                             + "nombre_ffa,"
                                             + "nombre_evdm,"
                                             + "nombre_ffaa,"
                                             + "fiche_id) values(?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, antecedant_obstri.getNombre_gda());
            statement.setInt(2, antecedant_obstri.getNombre_ffa());
            statement.setInt(3, antecedant_obstri.getNombre_evdm());
            statement.setInt(4, antecedant_obstri.getNombre_ffaa());
            statement.setInt(5,antecedant_obstri.getFiche().getFiche_id());
            
            int feed = statement.executeUpdate();
            if(feed > 0){
                //sql="select IDENT_CURRENT('table_antecedant') as id from table_antecedant";
                sql = "select last_insert_id() as id";
                statement = connection.prepareStatement(sql);
                rs = statement.executeQuery();
                feed = 0;
                if(rs != null && rs.next()){
                    feed = rs.getInt("id");
                    antecedant_obstri.setAtcd_id(feed);
                }
                rs.close();
            }
            return feed;
        } catch (SQLException ex) {
            Logger.getLogger(Antecedant_obstriDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
        
    }

    @Override
    public int delete(Antecedant_obstri antecedant_obstri) {
        
        try {
            sql="delete from table_antecedant where atcd_id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,antecedant_obstri.getAtcd_id());
            
            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Antecedant_obstriDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public int update(Antecedant_obstri antecedant_obstri) {
        try {
            sql="update table_antecedant set "
                    + "nombre_gda=?,nombre_ffa=?,nombre_evdm=?,nombre_ffaa=?,fiche_id=? where atcd_id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, antecedant_obstri.getNombre_gda());
            statement.setInt(2, antecedant_obstri.getNombre_ffa());
            statement.setInt(3, antecedant_obstri.getNombre_evdm());
            statement.setInt(4, antecedant_obstri.getNombre_ffaa());
            statement.setInt(5,antecedant_obstri.getFiche().getFiche_id());
            statement.setInt(6,antecedant_obstri.getAtcd_id());
            
            int feed = statement.executeUpdate();
           
            return feed;
        } catch (SQLException ex) {
            Logger.getLogger(Antecedant_obstriDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;   
    }
    
    // Requetes de types select
    
    private Antecedant_obstri createAntecedant_obstri(ResultSet rs) throws SQLException{
        
        Antecedant_obstri antecedant_obstri=new Antecedant_obstri();
        antecedant_obstri.setAtcd_id(rs.getInt("atcd_id"));
        antecedant_obstri.setNombre_gda(rs.getInt("nombre_gda"));
        antecedant_obstri.setNombre_ffa(rs.getInt("nombre_ffa"));
        antecedant_obstri.setNombre_evdm(rs.getInt("nombre_evdm"));
        antecedant_obstri.setNombre_ffaa(rs.getInt("nombre_ffaa"));
        
        antecedant_obstri.setFiche(new FicheDao().getFiche(rs.getInt("fiche_id")));
        
        return antecedant_obstri;
    }
    
    public List<Antecedant_obstri> getAntecedant_obstris(){
        
        List<Antecedant_obstri> antecedant_obstris=new ArrayList<>();
            
        try {
            
            sql="select * from table_antecedant";
            statement=connection.prepareStatement(sql);
            rs=statement.executeQuery();
            if(rs != null){
                while(rs.next()){
                    antecedant_obstris.add(createAntecedant_obstri(rs));
                }
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Antecedant_obstriDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return antecedant_obstris;
    }
    
    
    public Antecedant_obstri getAntecedant_obstri(int antecedant_obstri_id){
        
        Antecedant_obstri antecedant_obstri=null;         
        try {
            
            sql="select * from table_antecedant where atcd_id=?";
            statement=connection.prepareStatement(sql);
            statement.setInt(1, antecedant_obstri_id);
            rs=statement.executeQuery();
            if(rs != null){
                while(rs.next()){
                    antecedant_obstri=createAntecedant_obstri(rs);
                }
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Antecedant_obstriDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return antecedant_obstri;
    }
}
    
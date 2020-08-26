/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Fiche;
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
public class FicheDao extends Dao<Fiche> {

    @Override
    public int add(Fiche fiche) {
        try {
            sql="insert into table_fiche(nom_mere,"
                                       + "postnom_mere,"
                                       + "prenom_mere,"
                                       + "age,"
                                       + "etat_civil,"
                                       + "adresse,"
                                       + "niveau_etude,"
                                       + "nom_marie,"
                                       + "profession_marie,"
                                       + "telephone) values(?,?,?,?,?,?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,fiche.getNom_mere());
            statement.setString(2,fiche.getPostnom_mere());
            statement.setString(3,fiche.getPrenom_mere());
            statement.setInt(4,fiche.getAge());
            statement.setString(5,fiche.getEtat_civil());
            statement.setString(6,fiche.getAdresse());
            statement.setString(7,fiche.getNiveau_etude());
            statement.setString(8,fiche.getNom_marie());
            statement.setString(9,fiche.getProfession_marie());
            statement.setInt(10,fiche.getTelephone());
            
            int feed = statement.executeUpdate();
            if(feed > 0){
                //sql="select IDENT_CURRENT('table_fiche') as id from table_fiche";
                sql = "select last_insert_id() as id";
                statement = connection.prepareStatement(sql);
                rs = statement.executeQuery();
                feed = 0;
                if(rs != null && rs.next()){
                    feed = rs.getInt("id");
                    fiche.setFiche_id(feed);
                }
                rs.close();
            }
            return feed;
        } catch (SQLException ex) {
            Logger.getLogger(FicheDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
        
    }

   

    @Override
    public int delete(Fiche fiche) {
        try {
            sql="delete from table_fiche where fiche_id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,fiche.getFiche_id());
            
            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FicheDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
        
    @Override
    public int update(Fiche fiche) {
        try {
            sql="update t_faculte set "
                    + "nom_mere=?,postnom_mere=?,prenom_mere=?,age=?,etat_civil=?,adresse=?,niveau_etude=?,nom_de_marie=?,profession_marie=?,telephone=? where fiche_id=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,fiche.getNom_mere());
            statement.setString(2,fiche.getPostnom_mere());
            statement.setString(3,fiche.getPrenom_mere());
            statement.setInt(4,fiche.getAge());
            statement.setString(5,fiche.getEtat_civil());
            statement.setString(6,fiche.getAdresse());
            statement.setString(7,fiche.getNiveau_etude());
            statement.setString(8,fiche.getNom_marie());
            statement.setString(9,fiche.getProfession_marie());
            statement.setInt(10,fiche.getTelephone());
            
             int feed = statement.executeUpdate();
           
            return feed;
        } catch (SQLException ex) {
            Logger.getLogger(FicheDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    
        
    }
    //Type de requête select
    
    private Fiche createFiche(ResultSet rs) throws SQLException{
          Fiche fiche = new Fiche();
          fiche.setFiche_id(rs.getInt("fiche_id"));
          fiche.setNom_mere(rs.getString("nom_mere"));
          fiche.setPostnom_mere(rs.getString("postnom_mere")); 
          fiche.setPrenom_mere(rs.getString("prenom_mere")); 
          fiche.setAge(rs.getInt("age")); 
          fiche.setEtat_civil(rs.getString("etat_civil")); 
          fiche.setAdresse(rs.getString("adresse")); 
          fiche.setNiveau_etude(rs.getString("niveau_etude")); 
          fiche.setNom_marie(rs.getString("nom_marie")); 
          fiche.setProfession_marie(rs.getString("profession_marie")); 
          fiche.setTelephone(rs.getInt("telephone")); 
          
          
          return fiche;
    }
    public Fiche getFiche(int fiche_id){
        Fiche fiche = null;
            
        try {
            sql="select * from table_fiche where fiche_id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, fiche_id);
            rs = statement.executeQuery();
            
            if(rs != null && rs.next()){
              fiche = createFiche(rs);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(FicheDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fiche;
    }
    
    public List<Fiche> getFiche(){
        List<Fiche> fiches = new ArrayList<>();
            
        try {
            sql="select * from table_fiche";
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                  fiches.add(createFiche(rs));
                }  
            rs.close();
           }
        } catch (SQLException ex) {
            Logger.getLogger(FicheDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fiches;
    }
   
}



        
    

    

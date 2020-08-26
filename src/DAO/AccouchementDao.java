/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Accouchement;
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
public class AccouchementDao extends Dao<Accouchement>{
    
    @Override
    public int add(Accouchement accouchement) {
        try {
            sql="insert into table_accouchement(sexe_enfant,"
                                             + "poids_enfant,"
                                             + "coloration_enfant,"
                                             + "premier_cri_enfant,"
                                             + "etat_enfant,"
                                             + "voie_accouchement,"
                                             + "date,"
                                             + "fiche_id) values(?,?,?,?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, accouchement.getSexe_enfant());
            statement.setFloat(2, accouchement.getPoids_enfant());
            statement.setString(3, accouchement.getColoration_enfant());
            statement.setString(4, accouchement.getPremier_cri_enfant());
            statement.setString(5, accouchement.getEtat_enfant());
            statement.setString(6, accouchement.getVoie_accouchement());
            statement.setString(7, accouchement.getDate());
            statement.setInt(8,accouchement.getFiche().getFiche_id());
            
            
            int feed = statement.executeUpdate();
            if(feed > 0){
                //sql="select IDENT_CURRENT('table_accouchement') as id from table_accouchement";
                sql = "select last_insert_id() as id";
                statement = connection.prepareStatement(sql);
                rs = statement.executeQuery();
                feed = 0;
                if(rs != null && rs.next()){
                    feed = rs.getInt("id");
                    accouchement.setAccouchement_id(feed);
                }
                rs.close();
            }
            return feed;
        } catch (SQLException ex) {
            Logger.getLogger(AccouchementDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;     
    }

    @Override
    public int delete(Accouchement accouchement) {
        try {
            sql="delete from table_accouchement where accouchement_id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,accouchement.getAccouchement_id());
            
            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccouchementDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
        
    }

    @Override
    public int update(Accouchement accouchement) {
        try {
            sql="update table_accouchement set "
                    + "sexe_enfant=?,poids_enfant=?,coloration_enfant=?,premier_cri_enfant=?,etat_enfant=?,voie_accouchement=?,date=?,fiche_id=? where accouchement_id=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, accouchement.getSexe_enfant());
            statement.setFloat(2, accouchement.getPoids_enfant());
            statement.setString(3, accouchement.getColoration_enfant());
            statement.setString(4, accouchement.getPremier_cri_enfant());
            statement.setString(5, accouchement.getEtat_enfant());
            statement.setString(6, accouchement.getVoie_accouchement());
            statement.setString(7, accouchement.getDate());
            statement.setInt(8,accouchement.getFiche().getFiche_id());
            statement.setInt(9,accouchement.getAccouchement_id());
            
            int feed = statement.executeUpdate();
           
            return feed;
        } catch (SQLException ex) {
            Logger.getLogger(AccouchementDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
        
    }
    
    // Requetes de types select
    
    private Accouchement createAccouchement(ResultSet rs) throws SQLException{
        
        Accouchement accouchement=new Accouchement();
        accouchement.setAccouchement_id(rs.getInt("accouchement_id"));
        accouchement.setSexe_enfant(rs.getString("sexe_enfant"));
        accouchement.setPoids_enfant(rs.getFloat("poids_enfant"));
        accouchement.setColoration_enfant(rs.getString("coloration_enfant"));
        accouchement.setPremier_cri_enfant(rs.getString("premier_cri_enfant"));
        accouchement.setEtat_enfant(rs.getString("etat_enfant"));
        accouchement.setVoie_accouchement(rs.getString("voie_accouchement"));
        accouchement.setDate(rs.getString("date"));
       
        accouchement.setFiche(new FicheDao().getFiche(rs.getInt("fiche_id")));
        
        return accouchement;
    }
    
    public List<Accouchement> getAccouchements(){
        
        List<Accouchement> accouchements=new ArrayList<>();
            
        try {
            
            sql="select * from table_accouchement";
            statement=connection.prepareStatement(sql);
            rs=statement.executeQuery();
            if(rs != null){
                while(rs.next()){
                    accouchements.add(createAccouchement(rs));
                }
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccouchementDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accouchements;
    }
    
    
    public Accouchement getAccouchement(int accouchement_id){
        
        Accouchement accouchement=null;         
        try {
            
            sql="select * from table_accouchement where accouchement_id=?";
            statement=connection.prepareStatement(sql);
            statement.setInt(1, accouchement_id);
            rs=statement.executeQuery();
            if(rs != null){
                while(rs.next()){
                    accouchement=createAccouchement(rs);
                }
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccouchementDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accouchement;
    }
}


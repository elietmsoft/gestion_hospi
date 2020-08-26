/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Cpn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rodrigue Slendeur
 */
public class CpnDao extends Dao<Cpn> {

    @Override
    public int add(Cpn cpn) {
        try {
            sql="insert into table_cpn(poids_mere,"
                                     + "tension_mere,"
                                     + "position_enfant,"
                                     + "etat_serologique_mere,"
                                     + "glycemie_mere,"
                                     + "fiche_id) values(?,?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setFloat(1, cpn.getPoids_mere());
            statement.setString(2,cpn.getTension_mere());
            statement.setString(3,cpn.getPosition_enfant());
            statement.setString(4,cpn.getEtat_serologique_mere());
            statement.setString(5,cpn.getGlycemie_mere());
            statement.setInt(6,cpn.getFiche().getFiche_id());
            
            int feed = statement.executeUpdate();
            if(feed > 0){
                //sql="select IDENT_CURRENT('table_cpn') as id from table_cpn";
                sql = "select last_insert_id() as id";
                statement = connection.prepareStatement(sql);
                rs = statement.executeQuery();
                feed = 0;
                if(rs != null && rs.next()){
                    feed = rs.getInt("id");
                    cpn.setCpn_id(feed);
                }
                rs.close();
            }
            return feed;
        } catch (SQLException ex) {
            Logger.getLogger(CpnDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public int delete(Cpn cpn) {
        try {
            sql="delete from table_cpn where cpn_id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,cpn.getCpn_id());
            
            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CpnDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public int update(Cpn cpn) {
        try {
            sql="update table_cpn set "
                    + "poids_mere=?,tension_mere=?,position_enfant=?,etat_serologique_mere=?,glycemie_mere=?,fiche_id=? where cpn_id=?";
            statement = connection.prepareStatement(sql);
            statement.setFloat(1,cpn.getPoids_mere());
            statement.setString(2,cpn.getTension_mere());
            statement.setString(3,cpn.getPosition_enfant());
            statement.setString(4,cpn.getEtat_serologique_mere());
            statement.setString(5,cpn.getGlycemie_mere());
            statement.setInt(6,cpn.getFiche().getFiche_id());
            statement.setInt(7,cpn.getCpn_id());
            
            int feed = statement.executeUpdate();
           
            return feed;
        } catch (SQLException ex) {
            Logger.getLogger(CpnDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    // Requetes de types select
    
    private Cpn createCpn(ResultSet rs) throws SQLException{
        
        Cpn cpn=new Cpn();
        cpn.setCpn_id(rs.getInt("cpn_id"));
        cpn.setPoids_mere(rs.getFloat("poids_mere"));
        cpn.setEtat_serologique_mere(rs.getString("etat_serologique_mere"));
        cpn.setTension_mere(rs.getString("tension_mere"));
        cpn.setGlycemie_mere(rs.getString("glycemie_mere"));
        cpn.setPosition_enfant(rs.getString("position_enfant"));
        
        cpn.setFiche(new FicheDao().getFiche(rs.getInt("fiche_id")));
        
        return cpn;
    }
    
    public List<Cpn> getCpns(){
        
        List<Cpn> cpns=new ArrayList<>();
            
        try {
            
            sql="select * from table_cpn";
            statement=connection.prepareStatement(sql);
            rs=statement.executeQuery();
            if(rs != null){
                while(rs.next()){
                    cpns.add(createCpn(rs));
                }
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CpnDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cpns;
    }
    
    
    public Cpn getCpn(int cpn_id){
        
        Cpn cpn=null;         
        try {
            
            sql="select * from table_cpn where cpn_id=?";
            statement=connection.prepareStatement(sql);
            statement.setInt(1, cpn_id);
            rs=statement.executeQuery();
            if(rs != null){
                while(rs.next()){
                    cpn=createCpn(rs);
                }
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CpnDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cpn;
    }
    
}
          

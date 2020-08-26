/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elie Tshibangu
 */
public class DbUtilss {
    
    public static String dateToDateTimeString(Date date){
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date);
    }
    
    public static String dateToDateString(Date date){
        return new SimpleDateFormat("yyyy/MM/dd").format(date);
    }
    
    public static Date stringToDateTime(String dateString){
        try { 
            return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(dateString);
        } catch (ParseException ex) {
            Logger.getLogger(DbUtilss.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public static Date stringToDate(String dateString){
        try { 
            return new SimpleDateFormat("yyyy/MM/dd").parse(dateString);
        } catch (ParseException ex) {
            Logger.getLogger(DbUtilss.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
public static List<String> getNiveauEtudes(){
     List<String> niveaux = new ArrayList<String>();
     niveaux.add("D4");
     niveaux.add("D6");
     niveaux.add("graduée");
     niveaux.add("licenciée");
     niveaux.add("master");
     niveaux.add("doctorat");
     niveaux.add("autres");

     return niveaux;
}
public static List<String> getEtatCivils(){
     List<String> niveaux = new ArrayList<String>();
     niveaux.add("Mariée");
     niveaux.add("Célibataire");
     niveaux.add("Divorcée");
     niveaux.add("Veuve");

     return niveaux;
}
public static List<String> getProfessionMaries(){
     List<String> niveaux = new ArrayList<String>();
     niveaux.add("Travailleur");
     niveaux.add("Chomeur");
     niveaux.add("Etudiant");
     niveaux.add("Autres");

     return niveaux;
}
public static List<String> getTensionMeres(){
     List<String> niveaux = new ArrayList<String>();
     niveaux.add("Normale");
     niveaux.add("Hypotension");
     niveaux.add("Hypertension");

     return niveaux;
}
public static List<String> getPositionEnfants(){
     List<String> niveaux = new ArrayList<String>();
     niveaux.add("Normale");
     niveaux.add("Anormale");

     return niveaux;
}
public static List<String> getEtatSeriologiques(){
     List<String> niveaux = new ArrayList<String>();
     niveaux.add("Négatif");
     niveaux.add("Positif");

     return niveaux;
}
public static List<String> getGlycemies(){
     List<String> niveaux = new ArrayList<String>();
     niveaux.add("Normale");
     niveaux.add("Hypoglycémie");
     niveaux.add("Hyperglycémie");
     return niveaux;
}
public static List<String> getSexes(){
     List<String> niveaux = new ArrayList<String>();
     niveaux.add("M");
     niveaux.add("F");
     return niveaux;
}
public static List<String> getEtatEnfants(){
     List<String> niveaux = new ArrayList<String>();
     niveaux.add("Normal");
     niveaux.add("Anormal");
     return niveaux;
}
public static List<String> getVoies(){
     List<String> niveaux = new ArrayList<String>();
     niveaux.add("Normale");
     niveaux.add("Césarienne");
     return niveaux;
}

}

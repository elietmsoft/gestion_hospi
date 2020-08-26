/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rodrigue Splendeur
 */
public class Fiche {
    private int fiche_id;
    private String nom_mere;
    private String postnom_mere;
    private String prenom_mere;
    private int age;
    private String adresse;
    private String niveau_etude;
    private String etat_civil;
    private String nom_marie;
    private String profession_marie;
    private int telephone;
    private List<Cpn>cpn;
    private List<Antecedant_obstri>antecedant_obstri;
    private List<Accouchement>accouchement;
    
    //constructeur
    public Fiche (){
        this.fiche_id=0;
        this.nom_mere="";
        this.postnom_mere="";
        this.prenom_mere="";
        this.age=0;
        this.adresse="";
        this.niveau_etude="";
        this.etat_civil="";
        this.nom_marie="";
        this.profession_marie="";
        this.telephone=0;
        this.cpn=new ArrayList<>();
        this.antecedant_obstri=new ArrayList<>();
        this.accouchement=new ArrayList<>();
    
    }

    public int getFiche_id() {
        return fiche_id;
    }

    public void setFiche_id(int fiche_id) {
        this.fiche_id = fiche_id;
    }

    public String getNom_mere() {
        return nom_mere;
    }

    public void setNom_mere(String nom_mere) {
        this.nom_mere = nom_mere;
    }

    public String getPostnom_mere() {
        return postnom_mere;
    }

    public void setPostnom_mere(String postnom_mere) {
        this.postnom_mere = postnom_mere;
    }

    public String getPrenom_mere() {
        return prenom_mere;
    }

    public void setPrenom_mere(String prenom_mere) {
        this.prenom_mere = prenom_mere;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNiveau_etude() {
        return niveau_etude;
    }

    public void setNiveau_etude(String niveau_etude) {
        this.niveau_etude = niveau_etude;
    }

    public String getEtat_civil() {
        return etat_civil;
    }

    public void setEtat_civil(String etat_civil) {
        this.etat_civil = etat_civil;
    }

    public String getNom_marie() {
        return nom_marie;
    }

    public void setNom_marie(String nom_marie) {
        this.nom_marie = nom_marie;
    }

    public String getProfession_marie() {
        return profession_marie;
    }

    public void setProfession_marie(String profession_marie) {
        this.profession_marie = profession_marie;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public List<Cpn> getCpn() {
        return cpn;
    }

    public void setCpn(List<Cpn> cpn) {
        this.cpn = cpn;
    }

    public List<Antecedant_obstri> getAntecedant_obstri() {
        return antecedant_obstri;
    }

    public void setAntecedant_obstri(List<Antecedant_obstri> antecedant_obstri) {
        this.antecedant_obstri = antecedant_obstri;
    }

    public List<Accouchement> getAccouchement() {
        return accouchement;
    }

    public void setAccouchement(List<Accouchement> accouchement) {
        this.accouchement = accouchement;
    }

    @Override
    public String toString() {
        return this.nom_mere+"-"+this.postnom_mere+"-"+this.prenom_mere;
    }

    @Override
    public boolean equals(Object obj) {     
        if(!(obj instanceof Fiche))
            return false;
        Fiche fiche=(Fiche)obj;
        
        return (this.fiche_id>0 && this.fiche_id==fiche.getFiche_id()) ||
               (this.nom_mere.equalsIgnoreCase(fiche.getNom_mere()) && this.postnom_mere.equalsIgnoreCase(fiche.getPostnom_mere()) &&
                this.prenom_mere.equalsIgnoreCase(fiche.getPrenom_mere()));
    }
    
    
    
    
}

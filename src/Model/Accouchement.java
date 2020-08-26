/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Rodrigue Splendeur
 */
public class Accouchement {
    private int accouchement_id;
    private String sexe_enfant;
    private float poids_enfant;
    private String coloration_enfant;
    private String premier_cri_enfant;
    private String etat_enfant;
    private String voie_accouchement;
    private String date;
    private Fiche fiche;
    
    //constructeur
    public Accouchement(){
        this.accouchement_id=0;
        this.sexe_enfant="";
        this.poids_enfant=0;
        this.coloration_enfant="";
        this.premier_cri_enfant="";
        this.etat_enfant="";
        this.voie_accouchement="";
        this.date="";
        
    }

    public int getAccouchement_id() {
        return accouchement_id;
    }

    public void setAccouchement_id(int accouchement_id) {
        this.accouchement_id = accouchement_id;
    }

    public String getSexe_enfant() {
        return sexe_enfant;
    }

    public void setSexe_enfant(String sexe_enfant) {
        this.sexe_enfant = sexe_enfant;
    }

    public float getPoids_enfant() {
        return poids_enfant;
    }

    public void setPoids_enfant(float poids_enfant) {
        this.poids_enfant = poids_enfant;
    }

    public String getColoration_enfant() {
        return coloration_enfant;
    }

    public void setColoration_enfant(String coloration_enfant) {
        this.coloration_enfant = coloration_enfant;
    }

    public String getPremier_cri_enfant() {
        return premier_cri_enfant;
    }

    public void setPremier_cri_enfant(String premier_cri_enfant) {
        this.premier_cri_enfant = premier_cri_enfant;
    }

    public String getEtat_enfant() {
        return etat_enfant;
    }

    public void setEtat_enfant(String etat_enfant) {
        this.etat_enfant = etat_enfant;
    }

    public String getVoie_accouchement() {
        return voie_accouchement;
    }

    public void setVoie_accouchement(String voie_accouchement) {
        this.voie_accouchement = voie_accouchement;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Fiche getFiche() {
        return fiche;
    }

    public void setFiche(Fiche fiche) {
        this.fiche = fiche;
    }

    @Override
    public String toString() {
        return "Accouchement de "+this.fiche.getNom_mere();
    }

    @Override
    public boolean equals(Object obj) {       
        if(!(obj instanceof Accouchement))
            return false;
        Accouchement accouchement =(Accouchement)obj;
        
        return (this.accouchement_id>0 && this.accouchement_id==accouchement.getAccouchement_id()) ||
               (this.fiche.equals(accouchement.getFiche())) ;
    }

    
}
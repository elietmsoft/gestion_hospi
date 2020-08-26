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
public class Cpn {
    private int cpn_id;
    private float poids_mere;
    private String tension_mere;
    private String position_enfant;
    private String etat_serologique_mere;
    private String glycemie_mere;
    private Fiche fiche;
    
    //constructeur
    public Cpn(){
        this.cpn_id=0;
        this.poids_mere=0;
        this.tension_mere="";
        this.position_enfant="";
        this.etat_serologique_mere="";
        this.glycemie_mere="";
        
        
    
    }

    public int getCpn_id() {
        return cpn_id;
    }

    public void setCpn_id(int cpn_id) {
        this.cpn_id = cpn_id;
    }

    public float getPoids_mere() {
        return poids_mere;
    }

    public void setPoids_mere(float poids_mere) {
        this.poids_mere = poids_mere;
    }

    public String getTension_mere() {
        return tension_mere;
    }

    public void setTension_mere(String tension_mere) {
        this.tension_mere = tension_mere;
    }

    public String getPosition_enfant() {
        return position_enfant;
    }

    public void setPosition_enfant(String position_enfant) {
        this.position_enfant = position_enfant;
    }

    public String getEtat_serologique_mere() {
        return etat_serologique_mere;
    }

    public void setEtat_serologique_mere(String etat_serologique_mere) {
        this.etat_serologique_mere = etat_serologique_mere;
    }

    public String getGlycemie_mere() {
        return glycemie_mere;
    }

    public void setGlycemie_mere(String glycemie_mere) {
        this.glycemie_mere = glycemie_mere;
    }

    public Fiche getFiche() {
        return fiche;
    }

    public void setFiche(Fiche fiche) {
        this.fiche = fiche;
    }

    @Override
    public String toString() {
        return "CPN de "+this.fiche.getNom_mere();
    }

    @Override
    public boolean equals(Object obj) {      
        if(!(obj instanceof Cpn))
            return false;
        Cpn cpn=(Cpn)obj;
        
        return (this.cpn_id>0 && this.cpn_id==cpn.getCpn_id()) ||
               (this.fiche.equals(cpn.getFiche())) ;
    }

    
    
}

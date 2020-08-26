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
public class Antecedant_obstri {
    private int atcd_id;
    private int nombre_gda;
    private int nombre_ffa;
    private int nombre_evdm;
    private int nombre_ffaa;
    private Fiche fiche;
    
    //constructeur
    
    public Antecedant_obstri(){
        this.atcd_id=0;
        this.nombre_gda=0;
        this.nombre_ffa=0;
        this.nombre_evdm=0;
        this.nombre_ffaa=0;
        
    
    }

    public int getAtcd_id() {
        return atcd_id;
    }

    public void setAtcd_id(int atcd_id) {
        this.atcd_id = atcd_id;
    }

    public int getNombre_gda() {
        return nombre_gda;
    }

    public void setNombre_gda(int nombre_gda) {
        this.nombre_gda = nombre_gda;
    }

    public int getNombre_ffa() {
        return nombre_ffa;
    }

    public void setNombre_ffa(int nombre_ffa) {
        this.nombre_ffa = nombre_ffa;
    }

    public int getNombre_evdm() {
        return nombre_evdm;
    }

    public void setNombre_evdm(int nombre_evdm) {
        this.nombre_evdm = nombre_evdm;
    }

    public int getNombre_ffaa() {
        return nombre_ffaa;
    }

    public void setNombre_ffaa(int nombre_ffaa) {
        this.nombre_ffaa = nombre_ffaa;
    }

    public Fiche getFiche() {
        return fiche;
    }

    public void setFiche(Fiche fiche) {
        this.fiche = fiche;
    }

    @Override
    public String toString() {
        return "Antécédant Obstrical de "+this.fiche.getNom_mere();
    }

    @Override
    public boolean equals(Object obj) {     
        if(!(obj instanceof Antecedant_obstri))
            return false;
        Antecedant_obstri antecedant =(Antecedant_obstri)obj;
        
        return (this.atcd_id>0 && this.atcd_id==antecedant.getAtcd_id()) ||
               (this.fiche.equals(antecedant.getFiche())) ;
    }

    
    
    
}

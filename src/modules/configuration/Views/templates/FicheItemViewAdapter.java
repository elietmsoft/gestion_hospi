/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.configuration.Views.templates;

import Model.Fiche;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Elie Tshibangu
 */
public class FicheItemViewAdapter extends ListCell<Fiche> implements Initializable {

    FXMLLoader loader;

    
    @FXML
    private GridPane gridView;

    @FXML
    private Label txtIntitule;

    @FXML
    private Label txtNomMari;

    @FXML
    private Label txtTelephone;
    
    List<Fiche> fiches;
    Fiche fiche;
    
    // Constructeur
    public FicheItemViewAdapter(List<Fiche> fiches){     
        this.fiches=fiches;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @Override
    protected void updateItem(Fiche item, boolean empty) {
        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
        
        if (item != null && !empty) {
            try {
                loader = new FXMLLoader(getClass().getResource("/modules/configuration/Views/templates/FicheItemView.fxml"));
                loader.setController(this);
                
                loader.load();
                
                fiche = item;
                setupViewHolder();
                
            } catch (IOException ex) {
                Logger.getLogger(FicheItemViewAdapter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            setGraphic(null);
            setText(null);
        }
        
    }
    
     private void setupViewHolder() {
        
        if(fiche != null)
        {
            txtIntitule.setText(fiche.getNom_mere().toUpperCase()+" - "+fiche.getPostnom_mere().toUpperCase()+" - "+fiche.getPrenom_mere().toUpperCase());
            txtNomMari.setText(fiche.getNom_marie().toUpperCase());
            txtTelephone.setText("Téléphone : "+fiche.getTelephone());
           
            this.setGraphic(gridView);
        }
    }
    
}

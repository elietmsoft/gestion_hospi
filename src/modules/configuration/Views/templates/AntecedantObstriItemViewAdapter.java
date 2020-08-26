/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.configuration.Views.templates;

import Model.Antecedant_obstri;
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
public class AntecedantObstriItemViewAdapter extends ListCell<Antecedant_obstri> implements Initializable {

    
    FXMLLoader loader;
    
    @FXML
    private GridPane gridView;

    @FXML
    private Label txtIntitule;
    
    List<Antecedant_obstri> antecedants;
    
    Antecedant_obstri antecedant;
    
    public AntecedantObstriItemViewAdapter(List<Antecedant_obstri> antecedants){
        
        this.antecedants= antecedants;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    protected void updateItem(Antecedant_obstri item, boolean empty) {
        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
        
        if (item != null && !empty) {
            try {
                loader = new FXMLLoader(getClass().getResource("/modules/configuration/Views/templates/AntecedantObstriItemView.fxml"));
                loader.setController(this);
                
                loader.load();
                
                antecedant = item;
                setupViewHolder();
                
            } catch (IOException ex) {
                Logger.getLogger(AntecedantObstriItemViewAdapter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            setGraphic(null);
            setText(null);
        }
    }

    private void setupViewHolder() {
        
        if(antecedant != null)
        {
            txtIntitule.setText("Antécédant Obstrical de "+antecedant.getFiche().getNom_mere().toUpperCase()+"-"+antecedant.getFiche().getPostnom_mere().toUpperCase());      
            this.setGraphic(gridView);
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.configuration.Views.templates;

import Model.Accouchement;
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
public class AccouchementItemViewAdapter extends ListCell<Accouchement> implements Initializable {

    FXMLLoader loader;
    
    @FXML
    private GridPane gridView;

    @FXML
    private Label txtNoms;
    @FXML
    private Label txtLogin;

    @FXML
    private Label txtDate;
    
    List<Accouchement> accouchements;
    Accouchement accouchement;
    
    //Contructeur
    public AccouchementItemViewAdapter( List<Accouchement> accouchements){    
        this.accouchements=accouchements;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   
    @Override
    protected void updateItem(Accouchement item, boolean empty) {
        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
        
        if (item != null && !empty) {
            try {
                loader = new FXMLLoader(getClass().getResource("/modules/configuration/Views/templates/AccouchementItemView.fxml"));
                loader.setController(this);
                
                loader.load();
                
                accouchement= item;
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
        
        if(accouchement != null)
        {
            txtNoms.setText(accouchement.getFiche().getNom_mere().toUpperCase()+" - "+accouchement.getFiche().getPostnom_mere().toUpperCase()+" - "+accouchement.getFiche().getPrenom_mere().toUpperCase());
            txtDate.setText(accouchement.getDate());
            this.setGraphic(gridView);
        }
    }
}

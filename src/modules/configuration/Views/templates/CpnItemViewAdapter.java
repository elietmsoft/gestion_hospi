/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.configuration.Views.templates;

import Model.Cpn;
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
    public class CpnItemViewAdapter extends ListCell<Cpn> implements Initializable {

    FXMLLoader loader;
    
    @FXML
    private GridPane gridView;

    @FXML
    private Label txtIntitule;

    @FXML
    private Label txtSection;

    @FXML
    private Label txtPoids;

    @FXML
    private Label txtEtatSerologique;

    List<Cpn> cpns;
    
    Cpn cpn;
    
    public CpnItemViewAdapter(List<Cpn> cpns){     
        this.cpns=cpns;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    protected void updateItem(Cpn item, boolean empty) {
        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
        
        if (item != null && !empty) {
            try {
                loader = new FXMLLoader(getClass().getResource("/modules/configuration/Views/templates/CpnItemView.fxml"));
                loader.setController(this);
                
                loader.load();
                
                cpn = item;
                setupViewHolder();
                
            } catch (IOException ex) {
                Logger.getLogger(CpnItemViewAdapter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            setGraphic(null);
            setText(null);
        }
        
    }
    
     private void setupViewHolder() {
        
        if(cpn != null)
        {
            txtIntitule.setText("CPN de "+cpn.getFiche().getNom_mere().toUpperCase()+" - "+cpn.getFiche().getPostnom_mere().toUpperCase()+" - "+cpn.getFiche().getPrenom_mere().toUpperCase());
            txtPoids.setText(""+cpn.getPoids_mere()+"Kg");
            txtEtatSerologique.setText("Etat Sérologique : "+cpn.getEtat_serologique_mere());
           
            this.setGraphic(gridView);
        }
    }
    
    
}

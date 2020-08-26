/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.configuration.Views;

import DAO.Antecedant_obstriDao;
import DAO.FicheDao;
import Model.Antecedant_obstri;
import Model.Fiche;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import modules.configuration.Views.templates.AntecedantObstriItemViewAdapter;
import protocols.ISegmentable;

/**
 * FXML Controller class
 *
 * @author Elie Tshibangu
 */
public class AntecedantObstriViewFXMLController implements Initializable,ISegmentable {

    @FXML
    private TextField txtNombreGDA;

    @FXML
    private TextField txtNombreFFA;

    @FXML
    private TextField txtNombreEVDM;

    @FXML
    private TextField txtNombreFFAA;

    @FXML
    private ComboBox<Fiche> cbFiches;

    @FXML
    private Button btnFreshFiche;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

    @FXML
    private TextField txtFilter;

    @FXML
    private ListView<Antecedant_obstri> lstAntecedantObstris;
    ObservableList<Antecedant_obstri> antecedant_obstris;


    @FXML
    void btnFresh_clicked(ActionEvent event) {
        
        Button btn=(Button)event.getSource();
        
        if(btn != null){
            
            switch(btn.getId()){
            case "btnFreshFiche":
                loadFiches();
               break;
            
             default:
                 break;
            }
        }
    }
   
    @FXML
    void btnCancel_Clicked(ActionEvent event) {
        resetForm();
    }

    @FXML
    void btnSave_Clicked(ActionEvent event) {
        String nombre_gda = txtNombreGDA.getText().trim();
        String nombre_ffa = txtNombreFFA.getText().trim();
        String nombre_evdm = txtNombreEVDM.getText().trim();
        String nombre_ffaa = txtNombreFFAA.getText().trim();
        Fiche fiche = cbFiches.getSelectionModel().getSelectedItem();
        
        if("".equals(nombre_gda) || "".equals(nombre_ffa) || "".equals(nombre_evdm) || "".equals(nombre_ffaa) || fiche.equals(null)){
            showAlert(Alert.AlertType.ERROR,"Champs vides!!","Veuillez saisir tous les champs!!",ButtonType.OK);
            return;
        }
       
        int gda = Integer.parseInt(nombre_gda);
        int ffa = Integer.parseInt(nombre_ffa);
        int evdm = Integer.parseInt(nombre_evdm);
        int ffaa = Integer.parseInt(nombre_ffaa);
        
        Antecedant_obstri antecedant = new Antecedant_obstri();
        antecedant.setNombre_gda(gda);
        antecedant.setNombre_ffa(ffa);
        antecedant.setNombre_evdm(evdm);
        antecedant.setNombre_ffaa(ffaa);
        antecedant.setFiche(fiche);
        
        if(forEditing){
            
        }
        else{
            if(!antecedant_obstris.contains(antecedant)){
                if(new Antecedant_obstriDao().add(antecedant)>0){
                    showAlert(Alert.AlertType.NONE,"Enrehistrement","L'Antécédant Obstrical est ajouté avec succès !",ButtonType.OK);
                    resetForm();
                    antecedant_obstris.add(antecedant);
                }
                else{
                    showAlert(Alert.AlertType.ERROR,"Erreur de l'ajout","Enregistrement a échoué !", ButtonType.OK);
                }
            }
            else{
                showAlert(Alert.AlertType.WARNING,"Existence !!","Cet Antécédant Obstrical existe déjà !", ButtonType.OK);
            }
        }
    }

    @FXML
    void txtFilter_TextChanged(ActionEvent event) {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Chargement
        loadFiches();
        loadAntecedants();
        lstAntecedantObstris.setCellFactory(new Callback<ListView<Antecedant_obstri>, ListCell<Antecedant_obstri>>() {
            @Override
            public ListCell<Antecedant_obstri> call(ListView<Antecedant_obstri> param) {
                return new AntecedantObstriItemViewAdapter(antecedant_obstris);
            }
        });
    }    

     boolean forEditing=false;
     
      void loadFiches(){
        cbFiches.setItems(FXCollections.observableArrayList(new FicheDao().getFiche()));
        if(cbFiches.getItems() != null && cbFiches.getItems().size()>0)
            cbFiches.getSelectionModel().select(0);
    }
    void loadAntecedants(){
        antecedant_obstris = FXCollections.observableArrayList(new Antecedant_obstriDao().getAntecedant_obstris());
        lstAntecedantObstris.setItems(antecedant_obstris);
    }
    
    void resetForm(){
        
        txtNombreGDA.setText("");
        txtNombreFFA.setText("");
        txtNombreEVDM.setText("");
        txtNombreFFAA.setText("");
        btnSave.setText("Enregistrer");
        forEditing=false; 
    }
   
    Optional<ButtonType> showAlert(Alert.AlertType type,String header,String content,ButtonType... buttons){
        Alert alert=new Alert(type, content, buttons);
        alert.setHeaderText(header);
        return alert.showAndWait();
    }
    
    @Override
    public Node getMenu() {
       
        return null;
    }

    @Override
    public String getTitle() {
       
        return "Antécédant Obstrical";
    }

    @Override
    public Node getInfoControl() {
        
        return null;
    }
    
}

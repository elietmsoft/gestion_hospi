/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.configuration.Views;

import DAO.CpnDao;
import DAO.FicheDao;
import Model.Cpn;
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
import modules.configuration.Views.templates.CpnItemViewAdapter;
import protocols.ISegmentable;
import util.DbUtilss;

/**
 * FXML Controller class
 *
 * @author Elie Tshibangu
 */
public class CpnViewFXMLController implements Initializable,ISegmentable {

    @FXML
    private TextField txtPoidsMere;

    @FXML
    private ComboBox<String> cbTensionMeres;

    @FXML
    private Button btnFreshTension;

    @FXML
    private ComboBox<String> cbPositionEnfants;

    @FXML
    private Button btnFreshPosition;

    @FXML
    private ComboBox<String> cbEtatSeriologiques;

    @FXML
    private Button btnFreshSeriologie;

    @FXML
    private ComboBox<String> cbGlycemies;

    @FXML
    private Button btnFreshGlycemie;

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
    private ListView<Cpn> lstCpns;
    ObservableList<Cpn> cpns;

    @FXML
    void btnCancel_Clicked(ActionEvent event) {

    }

    @FXML
    void btnSave_Clicked(ActionEvent event) {
        
        String poids_mere = txtPoidsMere.getText().trim();
        String tension_mere = cbTensionMeres.getSelectionModel().getSelectedItem();
        String position_enfant = cbPositionEnfants.getSelectionModel().getSelectedItem();
        String etat_seriologique = cbEtatSeriologiques.getSelectionModel().getSelectedItem();
        String glycemies = cbGlycemies.getSelectionModel().getSelectedItem();
        Fiche fiche = cbFiches.getSelectionModel().getSelectedItem();
        
        if("".equals(poids_mere)){
            showAlert(Alert.AlertType.ERROR,"Champs Vides!!","Veuillez saisir le poids de la mère", ButtonType.OK);
            return;
        }
        if(fiche.equals(null)){
            showAlert(Alert.AlertType.ERROR,"Choix de la Fiche","Veuillez choisir une fiche",ButtonType.OK);
            return;
        }
        float poids = 0 ;
        try{
          poids = Float.parseFloat(poids_mere);
        }
        catch(Exception ex){}
        
        if(poids<=0){
            showAlert(Alert.AlertType.ERROR,"Saisie Incorrecte","Veuillez saisir un poids > 0",ButtonType.OK);
            return;
        }
        
        Cpn cpn = new Cpn();
        cpn.setPoids_mere(poids);
        cpn.setTension_mere(tension_mere);
        cpn.setPosition_enfant(position_enfant);
        cpn.setEtat_serologique_mere(etat_seriologique);
        cpn.setGlycemie_mere(glycemies);
        cpn.setFiche(fiche);
        
        if(forEditing){
            
        }
        else{
            if(!cpns.contains(cpn)){
                if(new CpnDao().add(cpn)>0){
                    showAlert(Alert.AlertType.NONE,"Enrehistrement","Le CPN est ajouté avec succès !",ButtonType.OK);
                    resetForm();
                    cpns.add(cpn);
                }
                else{
                    showAlert(Alert.AlertType.ERROR,"Erreur de l'ajout","Enregistrement a échoué !", ButtonType.OK);
                }
            }
            else{
                showAlert(Alert.AlertType.WARNING,"Existence !!","Ce CPN existe déjà !", ButtonType.OK);
            }
        }
        
    }

    @FXML
    void txtFilter_TextChanged(ActionEvent event) {

    }

    @FXML
    private Button btnFreshSection;

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
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadTensionMeres();
        loadPositionEnfants();
        loadEtatSeriologiques();
        loadGlycemies();
        loadFiches();
        loadCpns();
        lstCpns.setCellFactory(new Callback<ListView<Cpn>, ListCell<Cpn>>() {  
            @Override
            public ListCell<Cpn> call(ListView<Cpn> param) {
                return new CpnItemViewAdapter(cpns);
            }
        });
    }    

    void loadTensionMeres(){
        cbTensionMeres.setItems(FXCollections.observableArrayList(DbUtilss.getTensionMeres()));
        if(cbTensionMeres.getItems() != null && cbTensionMeres.getItems().size()>0)
               cbTensionMeres.getSelectionModel().select(0);
    }
     void loadPositionEnfants(){
        cbPositionEnfants.setItems(FXCollections.observableArrayList(DbUtilss.getPositionEnfants()));
        if(cbPositionEnfants.getItems() != null && cbPositionEnfants.getItems().size()>0)
               cbPositionEnfants.getSelectionModel().select(0);
    }
    void loadEtatSeriologiques(){
        cbEtatSeriologiques.setItems(FXCollections.observableArrayList(DbUtilss.getEtatSeriologiques()));
        if(cbEtatSeriologiques.getItems() != null && cbEtatSeriologiques.getItems().size()>0)
               cbEtatSeriologiques.getSelectionModel().select(0);
    }
    void loadGlycemies(){
        cbGlycemies.setItems(FXCollections.observableArrayList(DbUtilss.getGlycemies()));
        if(cbGlycemies.getItems() != null && cbGlycemies.getItems().size()>0)
               cbGlycemies.getSelectionModel().select(0);
    }
    void loadFiches(){
        cbFiches.setItems(FXCollections.observableArrayList(new FicheDao().getFiche()));
        if(cbFiches.getItems() != null && cbFiches.getItems().size()>0)
            cbFiches.getSelectionModel().select(0);
    }
    void loadCpns(){
        cpns = FXCollections.observableArrayList(new CpnDao().getCpns());
        lstCpns.setItems(cpns);
    }
    void resetForm(){
        
        txtPoidsMere.setText("");
        btnSave.setText("Enregistrer");
        forEditing=false;
        
    }
    
    boolean forEditing=false;
    
    
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
        
        return "Info CPN";
    }

    @Override
    public Node getInfoControl() {
        
        return null;
    }
    
}

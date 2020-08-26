/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.configuration.Views;

import DAO.AccouchementDao;
import DAO.FicheDao;
import Model.Accouchement;
import Model.Fiche;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import modules.configuration.Views.templates.AccouchementItemViewAdapter;
import protocols.ISegmentable;
import util.DbUtilss;

/**
 * FXML Controller class
 *
 * @author Elie Tshibangu
 */
public class AccouchementViewFXMLController implements Initializable,ISegmentable {

    @FXML
    private TextField txtPoidsEnfant;

    @FXML
    private ComboBox<String> cbSexes;

    @FXML
    private Button btnFreshSexe;

    @FXML
    private Button btnFreshColoration;

    @FXML
    private TextField txtColoration;

    @FXML
    private TextField txtCri;

    @FXML
    private Button btnFreshCri;

    @FXML
    private ComboBox<String> cbEtatEnfants;

    @FXML
    private Button btnFreshEtat;

    @FXML
    private ComboBox<String> cbVoies;

    @FXML
    private Button btnFreshVoie;

    @FXML
    private DatePicker dpAccouchement;

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
    private ListView<Accouchement> lstAccouchements;
    ObservableList<Accouchement> accouchements;

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
    void txtFilter_TextChanged(ActionEvent event) {

    }

    @FXML
    void btnCancel_Clicked(ActionEvent event) {
       resetForm();
    }

    @FXML
    void btnSave_Clicked(ActionEvent event) {
        String poids_enfant = txtPoidsEnfant.getText().trim();
        String sexe = cbSexes.getSelectionModel().getSelectedItem();
        String coloration = txtColoration.getText().trim();
        String cri = txtCri.getText().trim();
        String etat_enfant = cbEtatEnfants.getSelectionModel().getSelectedItem();
        String voie = cbVoies.getSelectionModel().getSelectedItem();
        String date = dpAccouchement.getValue().toString();
        Fiche fiche = cbFiches.getSelectionModel().getSelectedItem();
        
        if("".equals(poids_enfant) || "".equals(cri) || "".equals(coloration)){
            showAlert(Alert.AlertType.ERROR,"Champs Vides !!","Veuillez saisir tous les champs !",ButtonType.OK);
            return;
        }
        if(fiche.equals(null)){
            showAlert(Alert.AlertType.ERROR,"Choix de la Fiche","Veuillez choisir une fiche",ButtonType.OK);
            return;
        }
        float poids = 0 ;
        try{
          poids = Float.parseFloat(poids_enfant);
        }
        catch(Exception ex){}
        
        if(poids<=0){
            showAlert(Alert.AlertType.ERROR,"Saisie Incorrecte","Veuillez saisir un poids > 0",ButtonType.OK);
            return;
        }
        Accouchement accouchement = new Accouchement();
        accouchement.setPoids_enfant(poids);
        accouchement.setSexe_enfant(sexe);
        accouchement.setColoration_enfant(coloration);
        accouchement.setPremier_cri_enfant(cri);
        accouchement.setEtat_enfant(etat_enfant);
        accouchement.setVoie_accouchement(voie);
        accouchement.setDate(date);
        accouchement.setFiche(fiche);
        
        if(forEditing){
            
        }
        else{
            if(!accouchements.contains(accouchement)){
                if(new AccouchementDao().add(accouchement)>0){
                    showAlert(Alert.AlertType.NONE,"Enrehistrement","L'Accouchement est ajouté avec succès !",ButtonType.OK);
                    resetForm();
                    accouchements.add(accouchement);
                }
                else{
                    showAlert(Alert.AlertType.ERROR,"Erreur de l'ajout","Enregistrement a échoué !", ButtonType.OK);
                }
            }
            else{
                 showAlert(Alert.AlertType.WARNING,"Existence !!","Cet Accouchement existe déjà !", ButtonType.OK);
            }
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Chargements
       dpAccouchement.setValue(LocalDate.of(2020, Month.AUGUST, 23));
       loadSexes();
       loadEtatEnfants();
       loadVoies();
       loadFiches();
       loadAccouchements();
       lstAccouchements.setCellFactory(new Callback<ListView<Accouchement>, ListCell<Accouchement>>() {
           @Override
           public ListCell<Accouchement> call(ListView<Accouchement> param) {
               return new AccouchementItemViewAdapter(accouchements);
           }
       });
    }    

   void loadSexes(){
        cbSexes.setItems(FXCollections.observableArrayList(DbUtilss.getSexes()));
        if(cbSexes.getItems() != null && cbSexes.getItems().size()>0)
               cbSexes.getSelectionModel().select(0);
    }
   void loadEtatEnfants(){
        cbEtatEnfants.setItems(FXCollections.observableArrayList(DbUtilss.getEtatEnfants()));
        if(cbEtatEnfants.getItems() != null && cbEtatEnfants.getItems().size()>0)
               cbEtatEnfants.getSelectionModel().select(0);
   }
   void loadVoies(){
        cbVoies.setItems(FXCollections.observableArrayList(DbUtilss.getVoies()));
        if(cbVoies.getItems() != null && cbVoies.getItems().size()>0)
               cbVoies.getSelectionModel().select(0);
   }
    void loadFiches(){
        cbFiches.setItems(FXCollections.observableArrayList(new FicheDao().getFiche()));
        if(cbFiches.getItems() != null && cbFiches.getItems().size()>0)
            cbFiches.getSelectionModel().select(0);
    }
    void loadAccouchements(){
        accouchements = FXCollections.observableArrayList(new AccouchementDao().getAccouchements());
        lstAccouchements.setItems(accouchements);
    }
    void resetForm(){
        
        txtPoidsEnfant.setText("");
        txtColoration.setText("");
        txtCri.setText("");
        btnSave.setText("Enregistrer");
        forEditing=false;
        
    }
    boolean forEditing=false;
    
    Optional<ButtonType> showAlert(Alert.AlertType type,String header,String content,ButtonType... buttons){
        
        Alert alert=new Alert(type, content, buttons);
        alert.setTitle(header);
        
        return alert.showAndWait();
    }
     
    @Override
    public Node getMenu() {
        
        return null;
    }

    @Override
    public String getTitle() {
        
        return "Accouchement";
    }

    @Override
    public Node getInfoControl() {
        
        return null;
    }
    
}

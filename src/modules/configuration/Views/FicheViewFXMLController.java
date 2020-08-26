/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.configuration.Views;

import DAO.FicheDao;
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
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import modules.configuration.Views.templates.FicheItemViewAdapter;
import protocols.ISegmentable;
import util.DbUtilss;

/**
 * FXML Controller class
 *
 * @author Elie Tshibangu
 */
public class FicheViewFXMLController implements Initializable, ISegmentable{

    @FXML
    private TextField txtNomMere;

    @FXML
    private TextField txtPostnomMere;

    @FXML
    private TextField txtPrenomMere;

    @FXML
    private TextField txtAgeMere;

    @FXML
    private ComboBox<String> cbNiveauEtudes;

    @FXML
    private Button btnFreshCycle;

    @FXML
    private ComboBox<String> cbEtatCivils;

    @FXML
    private Button btnFreshOption;

    @FXML
    private TextField txtNomMari;

    @FXML
    private ComboBox<String> cbProfessionMaris;

    @FXML
    private Button btnFreshOption1;

    @FXML
    private TextField txtTelephone;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

    @FXML
    private ListView<Fiche> lstFiches;
    ObservableList<Fiche> fiches;

    @FXML
    private TextField txtFilter;

    @FXML
    private Button btnFreshClasse;

    @FXML
    void btnCancel_Clicked(ActionEvent event) {

    }

    @FXML
    void btnSave_Clicked(ActionEvent event) {
        String nom_mere = txtNomMere.getText().trim();
        String post_mere = txtPostnomMere.getText().trim();
        String pre_nom = txtPrenomMere.getText().trim();
        String age_mere = txtAgeMere.getText().trim();
        String niveau_etude = cbNiveauEtudes.getSelectionModel().getSelectedItem();
        String etat_civil = cbEtatCivils.getSelectionModel().getSelectedItem();
        String nom_mari = txtNomMari.getText().trim();
        String profession_mari = cbProfessionMaris.getSelectionModel().getSelectedItem();
        String telephone = txtTelephone.getText().trim();
        if("".equals(nom_mere) || "".equals(post_mere) || "".equals(pre_nom) || 
           "".equals(age_mere) || "".equals(telephone) || "".equals(nom_mari) ){
            
            showAlert(Alert.AlertType.ERROR,"Champs Vides","Veuillez saisir tous les champs!",ButtonType.OK);
            return;
        }
        Fiche fiche = new Fiche();
        fiche.setNom_marie(nom_mari);
        fiche.setNom_mere(nom_mere);
        fiche.setPostnom_mere(post_mere);
        fiche.setPrenom_mere(pre_nom);
        fiche.setAge(Integer.parseInt(age_mere));
        fiche.setNiveau_etude(niveau_etude);
        fiche.setEtat_civil(etat_civil);
        fiche.setProfession_marie(profession_mari);
        fiche.setTelephone(Integer.parseInt(telephone));
        
        if(forEditing){
            
        }
        else{
            if(!fiches.contains(fiche)){
                if(new FicheDao().add(fiche)>0){
                    showAlert(Alert.AlertType.NONE,"Enrehistrement","La fiche est ajoutée avec succès !",ButtonType.OK);
                    resetForm();
                    fiches.add(fiche);
                }
                else{
                    showAlert(Alert.AlertType.ERROR,"Erreur de l'ajout","Enregistrement a échoué !", ButtonType.OK);
                }
            }
            else{
                showAlert(Alert.AlertType.WARNING,"Existence !!","Cette fiche existe déjà !", ButtonType.OK);
            }
        }
    }

    @FXML
    void txtFilter_TextChanged(ActionEvent event) {

    }

    @FXML
    void txtIntitule_caretUpdate(MouseEvent event) {

    }


    @FXML
    void btnFresh_clicked(ActionEvent event) {

        Button btn=(Button)event.getSource();
        
        if(btn != null){
            
            switch(btn.getId()){
            case "btnFreshClasse":
                //loadClasses();
                break;
            case "btnFreshOption":
                //loadOptions();
                break;
            case "btnFreshCycle":
                //loadCycles();
                break;
             default:
                 break;
            }
        }
    }
  
    // Evenement lié au cbCycles
    @FXML
    void cbCycles_ItemStateChanged(ActionEvent event) {
            
    }
    // Evenement lié au txtIntitule de la classe
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Chargement
        loadNiveauEtudes();
        loadEtatCivils();
        loadProfessionMaris();
        loadFiches();
        lstFiches.setCellFactory(new Callback<ListView<Fiche>, ListCell<Fiche>>() {
            @Override
            public ListCell<Fiche> call(ListView<Fiche> param) {
                return new FicheItemViewAdapter(fiches);
            }
        });
    }    
    
    boolean forEditing=false;
    
    void resetForm(){ 
        txtNomMere.setText("");
        txtPostnomMere.setText("");
        txtPrenomMere.setText("");
        txtAgeMere.setText("");
        txtNomMari.setText("");
        txtTelephone.setText("");
        btnSave.setText("Enregistrer");
        forEditing=false; 
    }
    void loadNiveauEtudes(){
        cbNiveauEtudes.setItems(FXCollections.observableArrayList(DbUtilss.getNiveauEtudes()));
        if(cbNiveauEtudes.getItems() != null && cbNiveauEtudes.getItems().size()>0)
               cbNiveauEtudes.getSelectionModel().select(0);
        
    }
    void loadEtatCivils(){
        cbEtatCivils.setItems(FXCollections.observableArrayList(DbUtilss.getEtatCivils()));
        if(cbEtatCivils.getItems() != null && cbEtatCivils.getItems().size()>0)
               cbEtatCivils.getSelectionModel().select(0);
    }
    void loadProfessionMaris(){
        cbProfessionMaris.setItems(FXCollections.observableArrayList(DbUtilss.getProfessionMaries()));
        if(cbProfessionMaris.getItems() != null && cbProfessionMaris.getItems().size()>0)
               cbProfessionMaris.getSelectionModel().select(0);
    }
    void loadFiches(){    
        fiches=FXCollections.observableArrayList(new FicheDao().getFiche());
        lstFiches.setItems(fiches);
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
        return "Fiche de la mère";
    }

    @Override
    public Node getInfoControl() {
        return null;
    }
}

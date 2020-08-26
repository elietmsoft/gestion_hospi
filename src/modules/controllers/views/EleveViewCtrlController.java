/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.controllers.views;

import controls.OptionItem;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import modules.configuration.Views.templates.FicheItemViewAdapter;
import protocols.ISegmentable;

/**
 * FXML Controller class
 *
 * @author Elie Tshibangu
 */
public class EleveViewCtrlController implements Initializable,ISegmentable {

    Parent _eleve;
  
    @FXML
    private ListView<?> lstClasses;
     
    ObservableList<?> classes;
    //Classe classe;
    ObservableList<?> eleves;

    @FXML
    private AnchorPane containerLayout;

    @FXML
    private Label txtTitle;
    
    void optionChanged(OptionItem option) {
        
        if (option != null) {
            switch(option.getId()){
               
               case "tbEleve" :
                   //changeClasse(_eleve,classe);
                    break;
              
               default:
                   break;
            }
        }
        
    }
/*
    @FXML
    void pressing(MouseEvent event) {
        if(lstClasses.getSelectionModel().getSelectedItem()!=null){
                changeClasse(_eleve,lstClasses.getSelectionModel().getSelectedItem());
                //showAlert(Alert.AlertType.NONE, "",lstClasses.getSelectionModel().getSelectedItem().getIntitule(),ButtonType.OK);
            }   
    }
    void changeClasse(Parent option,Classe classe){
        containerLayout.getChildren().clear();
        
        if (option != null) {
            containerLayout.getChildren().add(option);
            AnchorPane.setBottomAnchor(option, 0.0);
            AnchorPane.setTopAnchor(option, 0.0);
            AnchorPane.setLeftAnchor(option, 0.0);
            AnchorPane.setRightAnchor(option, 0.0);
        }
        
        if (classe != null) {
            //changeListEleve(eleves);
            changeTitle(classe.getIntitule()+"è "+classe.getOption().getIntitule());
        }
        else{
            changeListEleve(null);
            changeTitle("");
        }
    }
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // chargement dans la liste view
        /*
        try {
            // Chargement des vues FXML et mappage aux variables correspondantes
            loadClasses();
        
            lstClasses.setCellFactory(new Callback<ListView<Classe>, ListCell<Classe>>() {
            @Override
            public ListCell<Classe> call(ListView<Classe> param) {
                return new FicheItemViewAdapter(classes);
            }
        });
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/modules/configuration/Views/EleveViewFXML.fxml"));
            _eleve = loader.load();
            //
           
        } catch (IOException ex) {
            Logger.getLogger(EleveViewCtrlController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }    
 /*
    void loadClasses(){    
        classes=FXCollections.observableArrayList(new ClasseDao().getClasses(true));
        lstClasses.setItems(classes);
    }
*/
     Optional<ButtonType> showAlert(Alert.AlertType type,String header,String content,ButtonType... buttons){
        Alert alert=new Alert(type, content, buttons);
        alert.setHeaderText(header);
        return alert.showAndWait();
    }
    
    void changeTitle(String title){
       txtTitle.setText(title);
    }
    
    

    @Override
    public Node getMenu() {
       return null;
    }

    @Override
    public String getTitle() {
       return "Elève";
    }

    @Override
    public Node getInfoControl() {
       return null;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tfe_hospi;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

/**
 *
 * @author Elie Tshibangu
 */
public class FXMLDocumentController implements Initializable {
    
     //Declaration des variables controllers
    Parent _configuration;
    //Parent _infos;
    Parent _accueil;
    
    @FXML
    private ToggleButton tbConfiguration;

    @FXML
    private ToggleGroup tbOptionsGroup;
    /*
    @FXML
    private ToggleButton tbInfos;*/

    @FXML
    private ToggleButton tbAccueil;

    @FXML
    private WebView wvBG;

    @FXML
    private AnchorPane containerLayout;
    
    @FXML
    void tbOption_Clicked(ActionEvent event) {

        ToggleButton btn=(ToggleButton)event.getSource();
        
        if(btn != null){
            
            switch(btn.getId()){
            case "tbConfiguration":
                changeOption(_configuration);
               break;
            /*
            case "tbInfos":
                changeOption(_infos);
                break;*/
            case "tbAccueil":
                changeOption(_accueil);
                break;
             default:
                 changeOption(wvBG);
                 break;
            }
        }
    }

    void changeOption(Parent option){
        
        containerLayout.getChildren().clear();
        containerLayout.getChildren().add(option);
        
        AnchorPane.setBottomAnchor(option,0.0);
        AnchorPane.setTopAnchor(option,0.0);
        AnchorPane.setRightAnchor(option,0.0);
        AnchorPane.setLeftAnchor(option,0.0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Chargement des views
      try {
            
            _configuration = FXMLLoader.load(getClass().getResource("/modules/configuration/controllers/CtrlConfigurationFXML.fxml"));
            //_infos = FXMLLoader.load(getClass().getResource("/modules/paiement/controllers/CtrlInfosFXML.fxml"));
            _accueil = FXMLLoader.load(getClass().getResource("/modules/accueil/controllers/CtrlAccueilFXML.fxml"));
             tbAccueil.setSelected(true);
             changeOption(_accueil);
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    
    Optional<ButtonType> showAlertOK(Alert.AlertType type,String header,String content,ButtonType... buttons){
        Alert alert=new Alert(type, content, buttons);
        alert.setHeaderText(header);
        alert.setGraphic(new ImageView("/images/BRB.png"));
        return alert.showAndWait();
    }
    Optional<ButtonType> showAlertKO(Alert.AlertType type,String header,String content,ButtonType... buttons){
        Alert alert=new Alert(type, content, buttons);
        alert.setHeaderText(header);
        alert.setGraphic(new ImageView("/images/elie.png"));
        return alert.showAndWait();
    }
   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.configuration.controllers;

import Model.Login;
import controls.OptionItem;
import global.templates.OptionItemViewAdapter;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.Duration;
import protocols.ISegmentable;

/**
 * FXML Controller class
 *
 * @author Elie Tshibangu
 */
public class CtrlConfigurationFXMLController implements Initializable {

    Parent _fiche;
    Parent _accouchement;
    Parent _antecedantObstri;
    Parent _cpn;
    
    ISegmentable _ficheCtrl;
    ISegmentable _antecedantObstriCtrl;
    ISegmentable _accouchementCtrl;
    ISegmentable _cpnCtrl;
  
    @FXML
    private ToggleButton tbFiche;

    @FXML
    private ToggleGroup tbOptionsGroup;

    @FXML
    private ToggleButton tbCpn;

    @FXML
    private ToggleButton tbAccouchement;
    
    @FXML
    private ToggleButton tbAntecedantObstri;
    
    @FXML
    private TextField txtUsername;
    
    @FXML
    private TextField txtPassword;


    @FXML
    private ListView<OptionItem> lstOptions;
     
    ObservableList<OptionItem> optionss;

    @FXML
    private AnchorPane containerLayout;
    
    @FXML
    private VBox gridViewLogin;
    
    @FXML
    private GridPane gridGen;
    
    @FXML
    private Button btnMenu;

    @FXML
    private Label txtTitle;
    
    @FXML
    private Button btnFreshCycle;

    @FXML
    private Button btnFreshOption;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;
    

    @FXML
    void btnCancel_Clicked(ActionEvent event) {
        txtUsername.setText("");
        txtPassword.setText("");
    }


    @FXML
    void btnSave_Clicked(ActionEvent event) {
        String login = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        if(login.equals("") || password.equals("")){
            showAlert(Alert.AlertType.ERROR,"Erreur!!!","Veuillez saisir tous les champs!!",ButtonType.OK);
            return;
        }
        if(login.equals("root") && password.equals("root")){
            showAlert(Alert.AlertType.NONE,"Bienvenue!!!","Bienvenue dans l'onglet de configurartion!",ButtonType.OK);
            active();
            gridViewLogin.setVisible(false);     
        }
    }

    void optionChanged(OptionItem option) {
        
        if (option != null) {
            switch(option.getId()){
                case "tbFiche" :
                    changeOption(_fiche, _ficheCtrl);
                    break;

                case "tbCpn" :
                    changeOption(_cpn, _cpnCtrl);
                    break;

               case "tbAntecedantObstri" :
                   changeOption(_antecedantObstri, _antecedantObstriCtrl);
                    break;
               case "tbAccouchement" :
                   changeOption(_accouchement, _accouchementCtrl);
                    break;
              
               default:
                   break;
            }
        }
        
    }

    void changeOption(Parent option, ISegmentable ctrl){
        containerLayout.getChildren().clear();
        
        if (option != null) {
            containerLayout.getChildren().add(option);
            AnchorPane.setBottomAnchor(option, 0.0);
            AnchorPane.setTopAnchor(option, 0.0);
            AnchorPane.setLeftAnchor(option, 0.0);
            AnchorPane.setRightAnchor(option, 0.0);
        }
        
        if (ctrl != null) {
            changeInfo(ctrl.getInfoControl());
            changeMenu(ctrl.getMenu());
            changeTitle(ctrl.getTitle());
        }
        else{
            changeInfo(null);
            changeMenu(null);
            changeTitle("");
        }
    }
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // chargement dans la liste view
        
        try {
            // Chargement des vues FXML et mappage aux variables correspondantes
            setupOptions();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/modules/configuration/Views/FicheViewFXML.fxml"));
            _fiche = loader.load();
            
            //Récupération du controller de Agence
            _ficheCtrl = (ISegmentable)loader.getController();
            
            loader = new FXMLLoader(getClass().getResource("/modules/configuration/Views/CpnViewFXML.fxml"));
            _cpn = loader.load();
            
            _cpnCtrl = (ISegmentable)loader.getController();
            
            loader = new FXMLLoader(getClass().getResource("/modules/configuration/Views/AccouchementViewFXML.fxml"));
            _accouchement = loader.load();
            
            _accouchementCtrl = (ISegmentable)loader.getController();
            
            loader = new FXMLLoader(getClass().getResource("/modules/configuration/Views/AntecedantObstriViewFXML.fxml"));
            _antecedantObstri = loader.load();
            
            _antecedantObstriCtrl = (ISegmentable)loader.getController();
            
            
            lstOptions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<OptionItem>() {
                @Override
                public void changed(ObservableValue<? extends OptionItem> observable, OptionItem oldValue, OptionItem newValue) {
                    optionChanged(newValue);
                }
            });
            
            lstOptions.setCellFactory(new Callback<ListView<OptionItem>, ListCell<OptionItem>>() {
            @Override
                public ListCell<OptionItem> call(ListView<OptionItem> param) {
                    return new OptionItemViewAdapter(optionss);
                }
            });
            
            lstOptions.setItems(optionss);
            lstOptions.setDisable(true);
            
        } catch (IOException ex) {
            Logger.getLogger(CtrlConfigurationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
   void active(){
       lstOptions.setDisable(false);
   }
    
    void setupOptions(){
        
        optionss = FXCollections.observableArrayList();
        
        OptionItem tbFiche = new OptionItem("tbFiche", "Fiche", "M0.87400818,24.487999L16.195007,24.487999C16.447006,25.778999,16.988998,26.965004,17.742996,27.986L0,27.986z M22.896011,20.618006L22.896011,22.191002 24.76001,22.191002 26.624008,22.191002 26.624008,20.618006 27.977997,21.715998 29.332001,22.814003 27.977997,23.911995 26.624008,25.010002 26.624008,23.297005 26.416,23.297005 23.104004,23.297005 22.896011,23.297005 22.896011,25.010002 21.542999,23.911995 20.189011,22.814003 21.542999,21.715998z M24.761002,16.915995C21.508011,16.915995 18.862,19.562006 18.862,22.814003 18.862,26.066002 21.508011,28.710989 24.761002,28.710989 28.013,28.710989 30.658005,26.066002 30.658005,22.814003 30.658005,19.562006 28.013,16.915995 24.761002,16.915995z M24.761002,15.574C28.751999,15.574 32,18.823008 32,22.814003 32,26.805992 28.751999,30.053 24.761002,30.053 20.768997,30.053 17.521011,26.805992 17.521011,22.814003 17.521011,18.823008 20.768997,15.574 24.761002,15.574z M21.863007,10.495008L27.111008,10.495008 26.236008,12.244 26.236008,14.217998C25.755997,14.135005 25.264999,14.083003 24.761002,14.083003 24.064011,14.083003 23.389008,14.174007 22.738007,14.329005L22.738007,12.244z M11.369003,10.495008L16.615997,10.495008 15.742004,12.244 15.742004,22.739006 12.242996,22.739006 12.242996,12.244z M0.87400818,10.495008L6.1220093,10.495008 5.2470093,12.244 5.2470093,22.739006 1.7490082,22.739006 1.7490082,12.244z M13.992996,3.4990073C13.026001,3.4990071 12.242996,4.2820116 12.242996,5.2479993 12.242996,6.2140027 13.026001,6.997007 13.992996,6.997007 14.959,6.997007 15.742004,6.2140027 15.742004,5.2479993 15.742004,4.2820116 14.959,3.4990071 13.992996,3.4990073z M13.992996,0L27.986008,8.7459994 0,8.7459994z");
        OptionItem tbCpn = new OptionItem("tbCpn", "CPN", "M4.6910016,25.130001L19.096998,25.130001 19.096998,27.341003 4.6910016,27.341003z M12.506999,14.244001L19.482001,14.244001 19.482001,22.621 12.445003,22.621 12.445003,22.134001C13.836002,21.892 14.598003,20.970999 14.598003,19.897001 14.598003,18.807001 14.018001,18.142 12.578002,17.635 11.549003,17.246 11.126002,16.993002 11.126002,16.593 11.126002,16.255999 11.381,15.917002 12.166,15.917002 13.037002,15.917002 13.593002,16.195002 13.908,16.327002L14.259002,14.960001C13.860999,14.768,13.317001,14.597002,12.506999,14.560999z M4.3070027,14.244001L11.318999,14.244001 11.318999,14.646002C10.025001,14.9 9.2740013,15.734999 9.2740013,16.800001 9.2740013,17.974001 10.159,18.579 11.454002,19.014002 12.348003,19.316999 12.735,19.607 12.735,20.065 12.735,20.550001 12.263999,20.816 11.574001,20.816 10.787002,20.816 10.073001,20.560999 9.5650016,20.283998L9.2030021,21.698999C9.6630012,21.965002,10.448001,22.182001,11.260001,22.219999L11.260001,22.622999 4.3070027,22.622999z M4.6910016,9.407998L19.096998,9.407998 19.096998,11.620001 4.6910016,11.620001z M6.5630027,4.6569981L17.226999,4.6569981 17.226999,6.8680009 6.5630027,6.8680009z M1.6410022,1.6399994L1.6410022,30.358002 22.147001,30.358002 22.149,30.358002 22.149,1.6399994z M0,0L23.789,0 23.789,32 0,32z");
        OptionItem tbAccouchement = new OptionItem("tbAccouchement", "Accouchement", "M4.6910016,25.130001L19.096998,25.130001 19.096998,27.341003 4.6910016,27.341003z M12.506999,14.244001L19.482001,14.244001 19.482001,22.621 12.445003,22.621 12.445003,22.134001C13.836002,21.892 14.598003,20.970999 14.598003,19.897001 14.598003,18.807001 14.018001,18.142 12.578002,17.635 11.549003,17.246 11.126002,16.993002 11.126002,16.593 11.126002,16.255999 11.381,15.917002 12.166,15.917002 13.037002,15.917002 13.593002,16.195002 13.908,16.327002L14.259002,14.960001C13.860999,14.768,13.317001,14.597002,12.506999,14.560999z M4.3070027,14.244001L11.318999,14.244001 11.318999,14.646002C10.025001,14.9 9.2740013,15.734999 9.2740013,16.800001 9.2740013,17.974001 10.159,18.579 11.454002,19.014002 12.348003,19.316999 12.735,19.607 12.735,20.065 12.735,20.550001 12.263999,20.816 11.574001,20.816 10.787002,20.816 10.073001,20.560999 9.5650016,20.283998L9.2030021,21.698999C9.6630012,21.965002,10.448001,22.182001,11.260001,22.219999L11.260001,22.622999 4.3070027,22.622999z M4.6910016,9.407998L19.096998,9.407998 19.096998,11.620001 4.6910016,11.620001z M6.5630027,4.6569981L17.226999,4.6569981 17.226999,6.8680009 6.5630027,6.8680009z M1.6410022,1.6399994L1.6410022,30.358002 22.147001,30.358002 22.149,30.358002 22.149,1.6399994z M0,0L23.789,0 23.789,32 0,32z");
        OptionItem tbAntecedantObstri = new OptionItem("tbAntecedantObstri", "Antecedant Obstrical", "M0,51.099999L63.999999,51.099999 63.999999,52.741002 0,52.741002z M1.46,46.770001L62.359,46.770001 62.359,48.41 1.46,48.41z M45.175013,19.966C43.135912,19.965998,41.482214,21.618329,41.482214,23.657463L41.482214,42.667 48.86761,42.667 48.86761,23.657463C48.86761,21.618329,47.214011,19.965998,45.175013,19.966z M31.909321,19.966C29.870322,19.965998,28.217824,21.618329,28.217824,23.657463L28.217824,42.667 35.602019,42.667 35.602019,23.657463C35.602019,21.618329,33.949719,19.965998,31.909321,19.966z M18.644928,19.966C16.60593,19.965998,14.952331,21.618329,14.952331,23.657463L14.952331,42.667 22.337727,42.667 22.337727,23.657463C22.337727,21.618329,20.684027,19.965998,18.644928,19.966z M5.9271201,18.052L57.983,18.052 57.983,19.995182 57.877917,20.008522C56.104823,20.27921,54.747805,21.809496,54.747805,23.657463L54.747805,42.667 57.983,42.667 58.484,42.667 58.484,44.307 5.3330001,44.307 5.3330001,42.667 5.8363351,42.667 9.0722151,42.667 9.0722151,23.657463C9.0722151,21.809496,7.7150607,20.27921,5.9429368,20.008522L5.919916,20.005598z M32.251193,0L59.737998,9.5725607 59.737998,15.316 4.0809993,15.316 4.0809993,9.7093367z");
        //OptionItem tbEnseignant= new OptionItem("tbEnseignant", "Enseignant", "M26.078001,40.18L55.097,40.18 55.097,62.500999 54.200001,62.500999 54.200001,63.885 51.987001,63.885 51.987001,62.500999 29.305001,62.500999 29.305001,63.885 27.092001,63.885 27.092001,62.500999 26.078001,62.500999z M56.7267,19.253L60.189001,20.665775 53.532001,36.966172 53.532001,37.178 41.399001,37.178 41.399001,33.439 50.933931,33.439z M12.135898,18.216002L16.829016,18.216002C19.009546,18.216002,20.893766,19.486588,21.781157,21.327805L21.822484,21.419197 29.075794,30.8056 36.264131,30.8056 36.602052,30.787999C38.514013,30.787999 40.064002,32.386877 40.064002,34.359449 40.064002,36.332223 38.514013,37.931 36.602052,37.931001 36.482548,37.931 36.364458,37.924755 36.248073,37.912564L36.180956,37.901999 25.642373,37.901999 22.323002,33.661173 22.323002,46.144285C22.323002,46.618318,22.262938,47.078339,22.150007,47.517155L22.138823,47.556536 23.112047,47.555 23.154001,47.556049 23.154001,51.903837 23.118547,51.904806 13.624001,51.91979 13.624001,61.559 17.592001,61.559 19.805001,61.559 19.805002,62.779001 19.805001,64 17.592001,64 17.592001,62.779001 7.4050007,62.779001 7.4050007,64 5.1920013,64 5.1920013,61.559 5.2300014,61.559 7.4050007,61.559 11.106001,61.559 11.106001,51.923764 3.3540916,51.935999C2.19067,51.9373,1.2460041,50.964713,1.2440033,49.76435L1.2461033,49.720014 1.2299843,49.558844 0.0040073395,27.043149C-0.068264008,25.717649 0.84581375,24.59005 2.0451107,24.52495 3.2450695,24.459849 4.2758255,25.48065 4.3481007,26.806149L5.4793701,47.582832 6.8330402,47.580695 6.8149929,47.517155C6.7020626,47.078339,6.6420002,46.618318,6.6420002,46.144285L6.6420002,23.709417C6.6420002,20.675508,9.1021748,18.216002,12.135898,18.216002z M14.402536,0C18.575128,0 21.958002,3.3829765 21.958002,7.5549088 21.958002,11.727955 18.575128,15.111 14.402536,15.111 10.229845,15.111 6.847002,11.727955 6.847002,7.5549088 6.847002,3.3829765 10.229845,0 14.402536,0z");
        //OptionItem tbEleve = new OptionItem("tbEleve", "Elève", "M48.600503,28.509L64.000001,32.38648 50.041783,49.004999 41.621392,46.56731C44.390957,40.917639,40.956002,37.704254,40.956002,37.704254z M49.265824,16.544001L63.336003,29.1742 47.825738,26.182 38.962613,35.931999 29.102,35.710699 30.099391,31.6117 36.746434,31.6117z M19.81399,15.755215C28.042414,15.860268 28.325161,22.748956 28.325161,22.748957 28.601172,24.491516 26.749335,35.048578 26.104613,38.626091L26.079096,38.767469 36.451928,38.48049C38.571667,38.421988 40.338601,40.093811 40.398503,42.213541 40.457203,44.334669 38.785271,46.100294 36.665333,46.158895L20.876341,46.597002 20.706107,46.609953C20.640011,46.613304 20.573479,46.615 20.50655,46.615 20.305737,46.615 20.108494,46.599733 19.915921,46.570301L19.805991,46.550663 19.794226,46.548895 19.779851,46.545994 19.724944,46.536184 19.680874,46.526011 19.607293,46.511156C17.939385,46.130789 16.672989,44.661039 16.624556,42.872349 16.622687,42.806063 16.62251,42.740124 16.623984,42.674572L16.629,42.569584 16.629,33.849871 14.144331,33.384476 13.701736,40.031746 0,44.981001 0,25.038082 17.579291,15.879079C18.230158,15.80291 18.841643,15.763097 19.416125,15.755394 19.550768,15.753589 19.683381,15.753547 19.81399,15.755215z M32.7198,0C37.5337,0 41.436001,3.9011641 41.436001,8.7150707 41.436001,13.528855 37.5337,17.43 32.7198,17.43 27.907401,17.43 24.005002,13.528855 24.005002,8.7150707 24.005002,3.9011641 27.907401,0 32.7198,0z");
        //OptionItem tbCours=new OptionItem("tbCours","Cours","M7.45900249481201,2.78899955749512C5.1750020980835,2.78899955749512,3.30100154876709,3.7810001373291,3.15800094604492,6.03300094604492L45.658016204834,6.03300094604492 45.658016204834,59.8599987030029 48.839017868042,57.2630023956299 48.8250179290771,2.78899955749512z M6.26200199127197,0L50.6100177764893,0C51.157018661499,0,51.6020183563232,0.440999984741211,51.605016708374,0.988000869750977L51.6310176849365,58.2020015716553C51.6310176849365,58.2020015716553 51.605016708374,59.439001083374 51.0040187835693,60.0189990997314 50.3610172271729,60.6660022735596 45.658016204834,64.0000019073486 45.658016204834,64.0000019073486L0,63.9899997711182 0,7.028000831604C0,2.53400039672852,2.78300094604492,0,6.26200199127197,0z");
        optionss.addAll(tbFiche, tbCpn,tbAntecedantObstri,tbAccouchement);
        
    }
    
    
    boolean isMenuOpen = false;
    
    
    @FXML
    void btnMenu_Click(ActionEvent event) {

        Timeline tl = new Timeline();
        tl.setAutoReverse(false);

        KeyValue k1 = new KeyValue(lstOptions.prefWidthProperty(), isMenuOpen ? 51.0 : 200.0);
        KeyFrame kf1 = new KeyFrame(Duration.millis(200), k1);
        tl.getKeyFrames().add(kf1);
        tl.play();
        
        isMenuOpen = !isMenuOpen;
    }

     Optional<ButtonType> showAlert(Alert.AlertType type,String header,String content,ButtonType... buttons){
        Alert alert=new Alert(type, content, buttons);
        alert.setHeaderText(header);
        return alert.showAndWait();
    }
    
    Optional<ButtonType> showAlertPerso(Alert.AlertType type,String header,String content,ButtonType... buttons){
        Alert alert=new Alert(type, content, buttons);
        alert.setHeaderText(header);
        alert.setGraphic(new ImageView("/images/elie.png"));
        return alert.showAndWait();
    }
    
    Optional<ButtonType> showAlertDetails(Alert.AlertType type,String header,String content,ButtonType... buttons){
        Alert alert=new Alert(type, content, buttons);
        alert.setHeaderText(header);
        alert.setGraphic(new ImageView("/images/student.png"));
        return alert.showAndWait();
    }
    
    
    void changeTitle(String title){
       txtTitle.setText(title);
    }
    
    void changeMenu(Node menu)
    {
        
    }
    
    void changeInfo(Node info)
    {
        
    }
}

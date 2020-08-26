/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package global.templates;

import controls.OptionItem;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.SVGPath;

/**
 *
 * @author Jonathan Mub Nice
 */
public class OptionItemViewAdapter extends ListCell<OptionItem> implements Initializable {
    
    FXMLLoader loader;
    
    @FXML
    private GridPane container;

    @FXML
    private SVGPath svgIcon;
     
    @FXML
    private Label content;
    
   ObservableList<OptionItem> options;
   
    OptionItem option;
    
    public OptionItemViewAdapter( ObservableList<OptionItem> options){
        
        this.options = options;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    
    @Override
    protected void updateItem(OptionItem item, boolean empty) {
        super.updateItem(item, empty); 
        
        if (item != null && !empty) {
            try {
                loader = new FXMLLoader(getClass().getResource("/global/templates/OptionItemView.fxml"));
                loader.setController(this);
                
                loader.load();
                
                option = item;
                setupViewHolder();
                
            } catch (IOException ex) {
                Logger.getLogger(OptionItemViewAdapter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            setGraphic(null);
            setText(null);
        }
        
    }

    private void setupViewHolder() {
        content.textProperty().bind(option.contentProperty());
        
//        option.pathProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//        });
        
        svgIcon.setContent(option.getPath());
        resize(svgIcon, 20, 20);
        
        this.setGraphic(container);
    }
    
    private void resize(SVGPath svg, double width, double height) {

        double originalWidth = svg.prefWidth(-1);
        double originalHeight = svg.prefHeight(originalWidth);

        double scaleX = width / originalWidth;
        double scaleY = height / originalHeight;

        svg.setScaleX(scaleX);
        svg.setScaleY(scaleY);
    }
}

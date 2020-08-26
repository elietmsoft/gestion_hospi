/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controls;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Elie Tshibangu
 */
public class OptionItem {
    private StringProperty id;
    private StringProperty content;
    private StringProperty path;

    public OptionItem(){
        content = new SimpleStringProperty();
        path = new SimpleStringProperty();
        id = new SimpleStringProperty();
    }
    
    public OptionItem(String id, String content, String path){
        this.content = new SimpleStringProperty(content);
        this.path = new SimpleStringProperty(path);
        this.id = new SimpleStringProperty(id);
    }
    
    public String getContent() {
        return content.get();
    }

    public void setContent(String content) {
        this.content.set(content);
    }

    public String getPath() {
        return path.get();
    }

    public void setPath(String path) {
        this.path.set(path);
    }
    
    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }
    
    public StringProperty contentProperty(){
        return this.content;
    }
    
    public StringProperty pathProperty(){
        return this.path;
    }
    
    public StringProperty idProperty(){
        return this.id;
    }
}

package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserData {
    private final StringProperty id;
    private final StringProperty STT;
    private final StringProperty Username;
    private final StringProperty Password;
    private final StringProperty Description;

    public UserData(String id, String STT, String Username, String Password, String Description){
        this.id = new SimpleStringProperty(id);
        this.STT = new SimpleStringProperty(STT);
        this.Username = new SimpleStringProperty(Username);
        this.Password = new SimpleStringProperty(Password);
        this.Description = new SimpleStringProperty(Description);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getSTT() {
        return STT.get();
    }

    public StringProperty STTProperty() {
        return STT;
    }

    public void setSTT(String STT) {
        this.STT.set(STT);
    }

    public String getUsername() {
        return Username.get();
    }

    public StringProperty usernameProperty() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username.set(username);
    }

    public String getPassword() {
        return Password.get();
    }

    public StringProperty passwordProperty() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password.set(password);
    }

    public String getDescription() {
        return Description.get();
    }

    public StringProperty descriptionProperty() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description.set(description);
    }
}

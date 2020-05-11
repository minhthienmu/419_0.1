package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginWindow {
    Client client = new Client("localhost", 3333);
    @FXML
    private TextField Username;
    @FXML
    private PasswordField Password;


    public void Login (ActionEvent event) throws IOException {
        client.connect();
        String username = Username.getText();
        String password = Password.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //if (username.equals("user") && password.equals("pass")){
        if (client.Check_Login(username, password)) {
            alert.setContentText("Login succesful");
        } else {
            alert.setContentText("Login failed");
        }
        alert.show();
    }
}

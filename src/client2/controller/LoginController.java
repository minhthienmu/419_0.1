package client2.controller;

import client2.MainClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    client2.Client client = new client2.Client("localhost", 3333);
    @FXML
    TextField Username = new TextField();
    @FXML
    PasswordField Password = new PasswordField();
    @FXML
    Button btnlogin = new Button();



    public void Login() throws IOException {
        client.connect();
        String username = Username.getText();
        String password = Password.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (client.Check_Login(username, password)) {
            client.setName(username);
            MainClient.showAppView(client);
        } else {
            alert.setContentText("Login failed");
            alert.show();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

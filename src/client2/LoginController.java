package client2;

import client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController{
    client2.Client client = new client2.Client("localhost", 3333);
    @FXML
    TextField Username = new TextField();
    @FXML
    PasswordField Password = new PasswordField();
    @FXML
    Button login = new Button();
    @FXML
    Button logout = new Button();


    public void Login() throws IOException {
        client.connect();
        String username = Username.getText();
        String password = Password.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (client.Check_Login(username, password)) {
            MainClient.showAppView(client);
        } else {
            alert.setContentText("Login failed");
            alert.show();
        }
    }



}

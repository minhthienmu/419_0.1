package client2.controller;

import client2.Client;
import client2.MainClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppViewController implements Initializable {
    private Client client;
    @FXML
    Button logout = new Button();
    @FXML
    TextArea msg = new TextArea();
    @FXML
    Button send = new Button();

    public void Logout(ActionEvent event) throws IOException {
        MainClient.showLoginView();
        client.Log_Out();
    }

    public void Sendmsg(ActionEvent event) throws IOException {
        client.Send_Msg("user2", msg.getText());
        msg.setText("");
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

package client2.controller;

import client2.Client;
import client2.MainClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppViewController implements Initializable {
    private Client client;
    @FXML
    Button btnlogout = new Button();
    @FXML
    TextArea msg = new TextArea();
    @FXML
    Button btnsend = new Button();
    @FXML
    ImageView homepageicon = new ImageView();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("src/client2/image/btnhomepage.png");
        Image image = new Image(file.toURI().toString());
        homepageicon.setImage(image);
    }


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


}

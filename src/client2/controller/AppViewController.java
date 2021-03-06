package client2.controller;

import client2.Client;
import client2.MainClient;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppViewController implements Initializable {
    private Client client;
    @FXML
    Button btnlogout = new Button();
    @FXML
    Button btnsend = new Button();
    @FXML
    ImageView homepageicon = new ImageView();
    @FXML
    ImageView addgroupicon = new ImageView();
    @FXML
    ImageView sendicon = new ImageView();
    @FXML
    private ImageView addfileicon;
    @FXML
    private JFXTextArea msg;
    @FXML
    private HBox msgpane;
    @FXML
    private JFXButton btnhomepage;
    @FXML
    private ImageView groupicon;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file1 = new File("src/client2/image/btnhomepage.png");
        Image image1 = new Image(file1.toURI().toString());
        homepageicon.setImage(image1);

        File file2 = new File("src/client2/image/btnaddgroup.png");
        Image image2 = new Image(file2.toURI().toString());
        addgroupicon.setImage(image2);

        File file3 = new File("src/client2/image/btnsend.png");
        Image image3 = new Image(file3.toURI().toString());
        sendicon.setImage(image3);

        File file4 = new File("src/client2/image/btnaddfile.png");
        Image image4 = new Image(file4.toURI().toString());
        addfileicon.setImage(image4);

        File file5 = new File("src/client2/image/btngroup.png");
        Image image5 = new Image(file5.toURI().toString());
        groupicon.setImage(image5);
    }


    public void Logout(ActionEvent event) throws IOException {
        MainClient.showLoginView();
        client.Log_Out();
    }

    public void Sendmsg(ActionEvent event) throws IOException {
        client.Send_Msg("user2", msg.getText());
        msg.setText("");
    }

    @FXML
    void handlebutton(ActionEvent event) {
        if (event.getSource() == btnhomepage){
            msgpane.setVisible(true);
        }
    }



    public void setClient(Client client) {
        this.client = client;
    }


}

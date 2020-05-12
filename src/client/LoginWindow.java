package client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginWindow extends Application {
    Client client = new Client("localhost", 3333);
    @FXML
    private TextField Username;
    @FXML
    private PasswordField Password;
    @FXML
    private Button login;


    public void Login(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        client.connect();
        String username = Username.getText();
        String password = Password.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //if (username.equals("user") && password.equals("pass")){
        if (client.Check_Login(username, password)) {
            stage = (Stage) login.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("ChatView.fxml"));
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } else {
            alert.setContentText("Login failed");
            alert.show();
        }
    }

    //public void
    public void Logout(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Log out");
        alert.show();
        client.Log_Out();

        /*Stage stage;
        Parent root;

        stage = (Stage) logout.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("LoginWindow.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        client.Log_Out();
        stage.show();*/
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginWindow.fxml"));
        primaryStage.setTitle("Chat 419");
        primaryStage.setScene(new Scene(root, 600,400));
        primaryStage.show();

    }



}

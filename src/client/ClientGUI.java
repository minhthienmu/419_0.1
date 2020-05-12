package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientGUI extends Application{
    //Client client = new Client("localhost", 3333);

    public static void main(String[] args) throws IOException {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        //client.connect();
        Parent root = FXMLLoader.load(LoginWindow.class.getResource("LoginWindow.fxml"));
        primaryStage.setTitle("Chat 419");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

    }


}

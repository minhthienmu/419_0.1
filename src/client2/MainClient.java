package client2;

import client2.controller.AppViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainClient extends Application {
    private static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        /*Parent root = FXMLLoader.load(getClass().getResource("view/LoginView.fxml"));
        primaryStage.setTitle("Chat 419");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();*/
        MainClient.primaryStage = primaryStage;
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        showLoginView();
    }

    public static void showLoginView() throws IOException {
        Parent root = FXMLLoader.load(MainClient.class.getResource("view/LoginView.fxml"));
        primaryStage.setTitle("Chat 419");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void showAppView(Client client) throws IOException {
        /*Parent root = FXMLLoader.load(MainClient.class.getResource("view/AppView.fxml"));
        primaryStage.setTitle("Chat 419");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();*/

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainClient.class.getResource("view/AppView.fxml"));
        Parent root = loader.load();

        AppViewController avc = loader.getController();
        avc.setClient(client);

        primaryStage.setTitle("Chat 419");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


}

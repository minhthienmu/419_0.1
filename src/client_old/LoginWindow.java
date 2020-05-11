package client_old;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginWindow extends Application {
    private Client client;
    @FXML
    private TextField Username;
    @FXML
    private PasswordField Password;


    Stage window;

    public static void main(String[] args) {
        launch(args);
    }


    public void Login (ActionEvent event) throws IOException {
        String username = Username.getText();
        String password = Password.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //if (Username.getText().equals("user") && Password.getText().equals("pass")){
        if (client.login(username, password)){
            alert.setContentText("Login succesful");
        }
        else {
            alert.setContentText("Login failed");
        }
        alert.show();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LoginWindow.fxml"));
        this.client = new Client("localhost", 3333);
        client.connect();

        /*GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        Text title = new Text("Log in");
        title.setFont(Font.font("Tahoma", FontWeight.BOLD,15));
        grid.add(title,0,0);

        Label userName = new Label("Username: ");
        grid.add(userName,0,1);

        TextField userTF = new TextField();
        grid.add(userTF, 1,1 );

        Label password = new Label("Password: ");
        grid.add(password,0,2);

        PasswordField passTF = new PasswordField();
        grid.add(passTF,1,2);

        Text actionmg = new Text();
        grid.add(actionmg,1,6);

        Button bt = new Button("Log in");
        HBox box = new HBox(10);
        box.setAlignment(Pos.TOP_RIGHT);
        box.getChildren().add(bt);
        grid.add(box,1,4);

        bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (client.login(userTF.getText(), passTF.getText())){
                        actionmg.setText("Log in successfully");
                    }
                    else {
                        actionmg.setText("Đăng nhập thất bại");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });*/ //Mau dang nhap cu


        primaryStage.setTitle("Chat 419");
        primaryStage.setScene(new Scene(root, 900,600));
        primaryStage.show();
    }



}


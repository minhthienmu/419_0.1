package client2;

import javafx.event.ActionEvent;

import java.io.IOException;

public class AppViewController {
    private Client client;

    public void Logout(ActionEvent event) throws IOException {
        MainClient.showLoginView();
        client.Log_Out();
    }

    public void setClient(Client client) {
        this.client = client;
    }
}

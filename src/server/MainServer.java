package server;
//Create: 21h-3/5/2020
//Version 0.1

public class MainServer{
    public static void main(String[] args) {
        int port = 3333;
        Server serverManager = new Server(port);
        serverManager.start();
    }
}

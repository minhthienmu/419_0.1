package client;

import java.io.*;
import java.net.Socket;

public class Client{
    private final String serverName;
    private final int serverPort;
    private Socket socket;
    private OutputStream serverOut;
    private InputStream serverIn;
    private BufferedReader bufferredIn;

    public Client(String serverName, int serverPort){
        this.serverName = serverName;
        this.serverPort = serverPort;
    }

    void connect(){
        try {
            this.socket = new Socket(serverName, serverPort);
            //this.socket = new Socket("localhost", 3333);
            System.out.println("Client port is " + socket.getLocalPort());
            this.serverOut = socket.getOutputStream();
            this.serverIn = socket.getInputStream();
            this.bufferredIn = new BufferedReader(new InputStreamReader(serverIn));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean Check_Login(String username, String password) throws IOException {
        String cmd = "login " + username + " " + password + "\n";
        serverOut.write(cmd.getBytes());

        String response = bufferredIn.readLine();

        return "OK Login".equalsIgnoreCase(response);
    }
}

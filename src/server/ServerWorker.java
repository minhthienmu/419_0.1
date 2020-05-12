package server;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServerWorker extends Thread{
    private final Server server;
    private final Socket clientSocket;
    private OutputStream outputStream;
    private String user = null;

    public ServerWorker(Server server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            handleClient();
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleClient() throws IOException, ClassNotFoundException, SQLException {
        InputStream inputStream = clientSocket.getInputStream(); //Luong nhan du lieu tu Client gui den (nhi phan)
        this.outputStream = clientSocket.getOutputStream(); //Luong tra du lieu cho Client

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)); //Luong doc ki tu

        String line;
        while ((line = reader.readLine()) != null){ //Doc du lieu theo dong
            String[] tokens = StringUtils.split(line);
            if (tokens != null && tokens.length > 0) {
                String tag = tokens[0];
                if ("login".equalsIgnoreCase(tag)) {
                    handleLogin(outputStream, tokens);
                } else if ("msg".equalsIgnoreCase(tag)) {
                    String[] tokenMsg = StringUtils.split(line, null, 3);
                    handleMessage(tokenMsg);
                } else if ("logout".equalsIgnoreCase(tag)){
                    handleLogout();
                    break;
                }
            }
        }
    }

    private void handleLogin(OutputStream outputStream, String[] tokens) throws ClassNotFoundException, SQLException, IOException {
        if (tokens.length == 3) {
            String username = tokens[1];
            String password = tokens[2];

            int result = 0;

            Connection connect = UserDB.getConnection();
            String query = "SELECT dbo.Check_Login(?,?) AS result";
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            rs.next();
            result = rs.getInt("result");
            if (result == 1){
                String msg = "OK Login\n";
                outputStream.write(msg.getBytes());
                this.user = username;
                System.out.println("User logged in succesfully: " + username);

                ArrayList<ServerWorker> workerList = server.getWorkerList();

                // send current user all other online logins
                for(ServerWorker worker: workerList){
                    if (!user.equals(worker.getLogin())) {
                        if(worker.getLogin() != null) {
                            String msg2 = "online " + worker.getLogin() + "\n";
                            send(msg2);
                        }
                    }
                }

                // send other online users current user's status
                String onlineMsg = "online " + user + "\n";
                for(ServerWorker worker: workerList){
                    if (!user.equals(worker.getLogin())) {
                        worker.send(onlineMsg);
                    }
                }
            } else {
                String msg = "Error Login\n";
                outputStream.write(msg.getBytes());
            }
        }
    }

    private void handleMessage(String[] tokens) throws IOException {//Xu li Message
        String sendTo = tokens[1]; //Gui toi
        String body = tokens[2]; //noi dung

        ArrayList<ServerWorker> workerList = server.getWorkerList();
        for(ServerWorker worker: workerList) {
            if (sendTo.equalsIgnoreCase(worker.getLogin())) {
                String outMsg = "msg " + sendTo + " " + body + "\n";
                worker.send(outMsg);
            }
        }

    }

    private String getLogin() {
        return user;
    }

    private void send(String msg) throws IOException {
        if (user != null) {
            outputStream.write(msg.getBytes());
        }
    }

    private void handleLogout() throws IOException {
        server.removeWorker(this);
        System.out.println("User log out: " + this.user);

        clientSocket.close();
    }
}
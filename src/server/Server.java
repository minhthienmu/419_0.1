package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread{
    private final int serverPort;

    private final ArrayList<ServerWorker> workerList = new ArrayList<>();

    private final ArrayList<GroupChat> listGroup = new ArrayList<>();

    public Server(int serverPort){
        this.serverPort = serverPort;
    }

    public ArrayList<ServerWorker> getWorkerList(){
        return workerList;
    }
    public ArrayList<GroupChat> getGroups(){
        return listGroup;
    }
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(serverPort);
            while (true) {
                System.out.println("Waiting for connection ...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accept connection from " + clientSocket);
                ServerWorker worker = new ServerWorker(this, clientSocket);
                workerList.add(worker);
                worker.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeWorker(ServerWorker serverWorker) {
        workerList.remove(serverWorker);
    }

    public GroupChat createNewGroup(int ID) throws ClassNotFoundException {
        GroupChat group = new GroupChat(ID);
        return group;
    }
}
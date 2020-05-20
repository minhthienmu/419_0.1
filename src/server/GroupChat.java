package server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class GroupChat{
    private final int groupID;
    private final ArrayList<ServerWorker> listUserOnl = new ArrayList<>();
    private Connection connection;

    public GroupChat(int groupID) throws ClassNotFoundException {
        this.groupID = groupID;
        connection = UserDB.getConnection();
    }

    public void addUserOnl(ServerWorker serverWorker){
        listUserOnl.add(serverWorker);
    }

    public int getID() {
        return this.groupID;
    }

    public void sendMess(String from, String mess) throws IOException, SQLException {
        addMessToDB(from,mess);
        for (ServerWorker worker: listUserOnl){
            worker.send(from, mess);
        }
    }

    private void addMessToDB(String from, String mess) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO message (groupID,fromUser,mess) VALUES (?,?,?)");
        ps.setString(1,Integer.toString(groupID));
        ps.setString(2,from);
        ps.setString(3,mess);
    }
}

package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Ket noi toi Database quan ly tai khoan User
public class UserDB {
    public static Connection getConnection() throws ClassNotFoundException {
        Connection connection = null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433; databaseName=DBUser";
            String user = "sa";
            String pass = "123";
            connection = DriverManager.getConnection(url,user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static void closeConnection(Connection con) {
        if(con != null){
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}

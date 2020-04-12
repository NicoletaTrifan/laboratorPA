import java.sql.*;

public class Database {
    private static Connection connection = null;

    private Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/MusicAlbums", "dba", "sql");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            new Database();
            return connection;
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }
}

import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class DB {
    final String url = "jdbc:mysql://sql7.freesqldatabase.com:3306/sql7572575";
    final String username = "sql7572575";
    final String password = "13a31ec2dd";
    String query = null;
    Connection connection;
    PreparedStatement pstmt;

    public DB() {

        dbConnect();
    }

    public void dbConnect(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(this.url, this.username, this.password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dbCloseConnection(){

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertUserIntoDB(String username, char[] password, int role){

        String hash = HashConverter.convertToHash(password);
        query = "INSERT INTO user(username, password, role_fk) VALUES(?, ?, ?)";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1,username);
            pstmt.setString(2,hash);
            pstmt.setInt(3,role+1);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbCloseConnection();
    }
}

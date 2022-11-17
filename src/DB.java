import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

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

    public void insertUserIntoDB(String username, char[] password, String fullName, String address, int role){

        String hash = HashConverter.convertToHash(password);
        query = "INSERT INTO user(username, password, full_name, address, role_fk) VALUES(?, ?, ?, ?, ?)";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1,username);
            pstmt.setString(2,hash);
            pstmt.setString(3,fullName);
            pstmt.setString(4,address);
            pstmt.setInt(5,role+1);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registration Successful!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        dbCloseConnection();
    }

    public void deleteUserFromDB(String username){

        query = "DELETE FROM user WHERE username = ?";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1,username);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "User successfully deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        dbCloseConnection();
    }

    public User getUser(String username){

        User user = null;
        query = "SELECT * FROM user WHERE username = ?";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1,username);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                user = new User(rs.getString(2), rs.getString(4), rs.getString(5), rs.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        dbCloseConnection();
        return user;
    }

    public ArrayList<User> getUsers(){

        ArrayList<User> users = new ArrayList<>();
        try {
            pstmt = connection.prepareStatement("select * from user");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                User currentUser = new User(rs.getString(2), rs.getString(4), rs.getString(5), rs.getString(6));
                users.add(currentUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbCloseConnection();
        return users;
    }
}

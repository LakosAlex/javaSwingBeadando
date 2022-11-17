public class User {

    final String username;
    final String fullName;
    final String address;
    final String role;

    public User(String username, String fullName, String address, String role){
        this.username = username;
        this.fullName = fullName;
        this.address = address;
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }
}

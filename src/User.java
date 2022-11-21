public class User {

    private final String username;
    private final String fullName;
    private final String address;
    private final String role;

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

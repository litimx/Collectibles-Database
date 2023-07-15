package collections.security;

public class Credentials {

    private String username;
    private String password;

//    private String role = "User"; implement this to assign admin

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package requests;

/**
 * request class for login functionality.

 */
public class LoginRequest {
    /**
     * username - username being passed in
     * password - password being passed in
     */
    private String username;
    private String password;

    public LoginRequest(){

    }

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

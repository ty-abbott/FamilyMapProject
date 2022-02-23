package requests;

/**
 * request class for the register service class
 */
public class registerRequest {
    /**
     * String username - username for the user that is passed in
     * String password - password for the user
     * String email - email of the user
     * String firstname - the first name of the user
     * String lastname - the lastname of the user
     * String gender - the gender of the user.
     */
    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private String gender;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

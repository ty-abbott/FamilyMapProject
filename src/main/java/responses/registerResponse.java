package responses;
import services.register;

/**
 * response of the information given when the person registers in the database with the app.
 */
public class registerResponse {
    private String authToken;
    private String username;
    private String personID;
    private Boolean success;
    private String message;

    /**
     * the constructor called if the service worked
     * @param authToken - String authToken - the authToken of the user
     * @param username - String username - the username of the user
     * @param personID - String personID - the ID of the person
     * @param success - boolean success - the boolean that states whether or not there was success
     */
    public registerResponse(String authToken, String username, String personID, boolean success) {
        this.authToken = authToken;
        this.username = username;
        this.personID = personID;
        this.success = success;
    }

    /**
     * the constructor called if the service did not work.
     * @param message - String message - the message that states whether or not the service worked. If not then an error message is included.
     * @param success - @param success - boolean success - the boolean that states whether or not there was success
     */

    public registerResponse(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthtoken() {
        return authToken;
    }

    public void setAuthtoken(String authtoken) {
        this.authToken = authtoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public registerResponse allValues(register body) {
        return this;
    }

    public registerResponse errorMessage(register body) {
        return this;
    }
}



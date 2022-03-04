package responses;

/**
 * return object of basic infomration for the user that just logged in
 */
public class loginResponse {
    private String username;
    private String authtoken;
    private String personID;
    private boolean success;
    private String message;

    /**
     * this constructor is called if the service worked
     * @param authtoken - String authToken - the authorization token for the user to make calls to the server and for identification
     * @param username - String username - a username for the user
     * @param personID - String personID - the ID of the user that is links people in the database
     * @param success - boolean success - the bool that states that the service worked or not
     */
    public loginResponse(String authtoken, String username, String personID, boolean success){
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
        this.success = success;
    }

    /**
     * this constructor is called if the service did not work.
     * @param message - String message - the message that states if the service worked, if not the error message is included
     * @param success - boolean success - the bool that states that the service worked or not
     */
    public loginResponse(String message, boolean success){
        this.message = message;
        this.success = success;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthToken() {
        return authtoken;
    }

    public void setAuthToken(String authToken) {
        this.authtoken = authtoken;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

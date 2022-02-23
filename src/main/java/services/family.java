package services;

import responses.familyResponse;

/**
 * authtoken is a string that determines the current user and is used to authenticate with the server.
 */
public class family {
    private String authToken;


    /**
     * get the total people of the current user.
     * @return the array of people related to the user and a success boolean. if there was an error then it returns a false boolean and the
     * error message
     */
    public familyResponse getFamily(){
        return null;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}

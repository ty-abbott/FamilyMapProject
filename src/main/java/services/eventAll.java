package services;

import responses.eventAllResponse;

/**
 * authToken - used to get the current user and to authenticate api calls.
 */
public class eventAll {
    private String authToken;

    /**
     * gets all the events of the users family.
     * @return returns an array of all the events if there are no errors. False and an error message if there is
     */
    public eventAllResponse getEventAll(){return null;}

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}

package services;

import responses.eventResponse;

/**
 * eventID - the id of the event we are looking for.
 * authToken - the authToken that we need to figure out who the user is and used to authenticate api calls.
 */
public class event {
    private String eventID;
    private String authToken;

    /**
     * get a specifc event based upon the eventID that we are given.
     * @return the event information and success if it works and false and the error message if not.
     */
    public eventResponse getEvent() {
        return null;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }
}

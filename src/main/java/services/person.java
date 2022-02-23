package services;

import responses.personResponse;

/**
 * personID - identifier for the person
 * authToken - the token that is used to authorize calls, also can be used to tell the user.
 * model - used to represent a single row of data in the person database.
 */
public class person {
    private String personID;
    private String authToken;
    private person model;

    /**
     * returns a specified person from the database based upon the personID and the currentuser which is determined from the authtoken
     * @return returns an object of the response of the calls and logic of the service.
     */
    public personResponse personInfo(){
        return null;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public person getModel() {
        return model;
    }

    public void setModel(person model) {
        this.model = model;
    }
}

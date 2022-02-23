package services;

import requests.loginRequest;
import responses.loginResponse;
import models.user;

/**
 * a service class to help login the user to the application
 */
public class login {


    /**
     * tries to log the person into the server. Checks if credentials are in the database.
     * @param body contains username and password
     * @return response of whether or not the service worked.
     */
    public loginResponse login(loginRequest body){
        return null;
    }

    /**
     * gets and returns authToken
     * @return a string of the authtoken
     */
    public String authToken() {

        return null;
    }
}

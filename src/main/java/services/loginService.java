package services;

import requests.loginRequest;
import responses.loginResponse;
import dao.UserDAO;

/**
 * a service class to help login the user to the application
 */
public class loginService {


    /**
     * tries to log the person into the server. Checks if credentials are in the database.
     * @param body contains username and password
     * @return response of whether or not the service worked.
     */
    public loginResponse login(loginRequest body){

        if(body.getUsername() == null || body.getPassword() == null) {
            loginResponse resp = new loginResponse("Error: there was no username or password passed provided", false);
            return resp;
        }
        else{

        }
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

package services;

import models.*;
import responses.*;
import requests.*;

/**
 * This class is used to register the user with the application.
 */
public class register {


    /**
     * this creates four generations of people in the databse under the people table. Called from create account after the creation of account
     */
    private void generateData() {
//this will generate four generations in the database for the person,
// this will be called from create account after the success of creating an account
// have a method in the dao to do this.
    }

    /**
     * this will call another class that will give the user their unique authtoken. This happens after the creation of
     * the account.
     * @return the authToken String
     */
    private String authToken() {
        //this will generate an auth token and then return it to the handler in the response object.
        // this will happen after the creation of the account
        return null;
    }

    /**
     * main method of the class that creates the account for the application and saves it in the database under the user table.
     * @return returns a registerResponse object. It will be different based upon whether or not register worked.
     */
    public registerResponse createAccount(registerRequest body) {
        return null;

    }
}

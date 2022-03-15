package services;

import dao.*;
import helpers.GenerateData;
import helpers.AuthTokenHelper;
import helpers.GenerateID;
import helpers.LoadJSON;
import models.*;
import responses.*;
import requests.*;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.Objects;
import java.util.Random;

/**
 * This class is used to register the user with the application.
 */
public class RegisterService {

    /**
     * main method of the class that creates the account for the application and saves it in the database under the user table.
     * @return returns a registerResponse object. It will be different based upon whether or not register worked.
     */
    public RegisterResponse createAccount(RegisterRequest body) throws DataAccessException, FileNotFoundException {

        Database db = new Database();
        Random random = new Random();
        Connection conn = db.getConnection();
        UserDAO userDAO = new UserDAO(conn);
        AuthTokenDAO authDao = new AuthTokenDAO(conn);
        AuthTokenHelper token = new AuthTokenHelper();
        GenerateID idObj = new GenerateID();
        LoadJSON location = new LoadJSON();
        location.load();
        Location area = location.locData.getData()[random.nextInt(100)];
        User user;

        try{
            if(body.getUsername() == null || body.getPassword() == null || body.getEmail() == null ||
                    body.getFirstName() == null || body.getLastName() == null || body.getGender() == null ){
                RegisterResponse resp = new RegisterResponse("One of the values was missing", false);
                return resp;
                //if any of the information is null return the error
            }
            String personID = idObj.getID();
             user = new User(body.getUsername(), body.getPassword(), body.getEmail(), body.getFirstName(), body.getLastName(),
                    body.getGender(), personID);
             //create user object


        }catch(DataAccessException e){
            RegisterResponse resp = new RegisterResponse("Error: There were not enough paramters to create the user", false);
            return resp;
        }
        System.out.println(user.getUsername());
        User user2 = userDAO.find(user.getUsername());
        //if there is a user already in the database.
        if(user2 != null){
            RegisterResponse resp = new RegisterResponse("Error: there is a user already in the database with this information", false);
            db.closeConnection(true);
            return resp;
        }
        //inserting the valid user
        userDAO.insert(user);
        user2 = userDAO.find(user.getUsername());
        if(Objects.equals(user2.getUsername(), user.getUsername())){
            //creating the person object associated with the user
            Person person = new Person(user2.getPersonID(), user2.getUsername(), user2.getFirstName(), user2.getLastName(), user2.getGender());
            Event birthEvent = new Event(idObj.getID(), user2.getUsername(), user2.getPersonID(), area.getLatitude(), area.getLongitude(),
                    area.getCountry(), area.getCity(), "Birth", 1997);
            EventDAO event = new EventDAO(conn);
            //creating and inserting birth object
            event.insert(birthEvent);
            String auth = token.getAuthToken();
            AuthToken tokenModel = new AuthToken(auth, user.getUsername());
            authDao.insert(tokenModel);
            //auth token created and inserted into the database
            db.closeConnection(true);
            GenerateData family = new GenerateData();
            //generate family information
            family.generatePerson(person, 4, 1997);
            RegisterResponse resp = new RegisterResponse(auth, user2.getUsername(), user2.getPersonID(), true);
            return resp;
            //return success
        }

        RegisterResponse resp = new RegisterResponse("Error: There was an internal error with the server", false);
        return resp;
        //internal error returned
    }
}

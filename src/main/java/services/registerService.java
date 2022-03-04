package services;

import dao.*;
import helpers.GenerateData;
import helpers.authToken;
import helpers.GenerateID;
import helpers.loadJSON;
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
public class registerService {

    /**
     * main method of the class that creates the account for the application and saves it in the database under the user table.
     * @return returns a registerResponse object. It will be different based upon whether or not register worked.
     */
    public registerResponse createAccount(registerRequest body) throws DataAccessException, FileNotFoundException {

        Database db = new Database();
        Random random = new Random();
        Connection conn = db.getConnection();
        UserDAO userDAO = new UserDAO(conn);
        AuthTokenDAO authDao = new AuthTokenDAO(conn);
        authToken token = new authToken();
        GenerateID idObj = new GenerateID();
        loadJSON location = new loadJSON();
        location.load();
        Location area = location.locData.getData()[random.nextInt(100)];
        User user;

        try{

             user = new User(body.getUsername(), body.getPassword(), body.getEmail(), body.getFirstName(), body.getLastName(),
                    body.getGender(), idObj.getID());


        }catch(DataAccessException e){
            registerResponse resp = new registerResponse("Error: There were not enough paramters to create the user", false);
            return resp;
        }
        System.out.println(user.getUsername());
        User user2 = userDAO.find(user.getUsername());
        if(user2 != null){
            registerResponse resp = new registerResponse("Error: there is a user already in the database with this information", false);
            db.closeConnection(true);
            return resp;
        }

        userDAO.insert(user);
        user2 = userDAO.find(user.getUsername());
        if(Objects.equals(user2.getUsername(), user.getUsername())){

            String id = idObj.getID();
            Person person = new Person(id, user2.getUsername(), user2.getFirstname(), user2.getLastname(), user2.getGender());
            Event birthEvent = new Event(idObj.getID(), user2.getUsername(), id, area.getLatitude(), area.getLongitude(),
                    area.getCountry(), area.getCity(), "Birth", 1997);
            EventDAO event = new EventDAO(conn);
            event.insert(birthEvent);
            String auth = token.getAuthToken();
            AuthToken tokenModel = new AuthToken(auth, user.getUsername());
            authDao.insert(tokenModel);
            db.closeConnection(true);
            GenerateData family = new GenerateData();

            family.generatePerson(person, 4, 1997);
            registerResponse resp = new registerResponse(auth, user2.getUsername(), user2.getPersonID(), true);
            return resp;
        }

        registerResponse resp = new registerResponse("Error: There was an internal error with the server", false);
        return resp;

    }
}

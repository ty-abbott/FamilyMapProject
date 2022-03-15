package services;

import dao.DataAccessException;
import dao.Database;
import dao.PersonDAO;
import helpers.AuthTokenHelper;
import models.Person;
import responses.PersonResponse;

import java.sql.Connection;

/**
 * personID - identifier for the person
 * authToken - the token that is used to authorize calls, also can be used to tell the user.
 * model - used to represent a single row of data in the person database.
 */
public class PersonService {


    /**
     * returns a specified person from the database based upon the personID and the currentuser which is determined from the authtoken
     * @return returns an object of the response of the calls and logic of the service.
     */
    public PersonResponse personInfo(String authToken, String personID) throws DataAccessException {
        AuthTokenHelper token = new AuthTokenHelper();
        String username = token.checkAuthToken(authToken);
        Database db = new Database();
        Connection conn = db.getConnection();
        PersonDAO pDAO = new PersonDAO(conn);
        Person person;
        //getting the username from the authtoken and creating database connections
        if(username == null) {
            PersonResponse resp = new PersonResponse("Error: the authtoken is not valid", false);
            return resp;
            //if username is null then we return that error. That means there is no user associated with the passed token
        }
        //check to see if personID is valid string
        if(!(personID instanceof String)) {
            PersonResponse resp = new PersonResponse("Error: the personID is not a string", false);
            return resp;
        }
        //find the person by the ID and the user
        person = pDAO.findByUser(personID, username);
        db.closeConnection(false);
        if(person == null) {
            PersonResponse resp = new PersonResponse("Error: the person does not exist or is not linked with the user", false);
            return resp;
            //no person found
        }
        else{
            if(person.getSpouseID()==null || person.getFatherID() == null || person.getMotherID() == null){
                PersonResponse resp = new PersonResponse(person.getAssociatedUsername(), person.getPersonID(), person.getFirstName(),
                        person.getLastName(), person.getGender(), true);
                return resp;
                //return response if one of the optional values are null
            }
            else{
                PersonResponse resp = new PersonResponse(person.getAssociatedUsername(), person.getPersonID(), person.getFirstName(),
                        person.getLastName(), person.getGender(), person.getFatherID(), person.getMotherID(), person.getSpouseID(), true);
                return resp;
                //response if theyre all filled out
            }
        }
    }

}

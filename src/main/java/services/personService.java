package services;

import dao.DataAccessException;
import dao.Database;
import dao.PersonDAO;
import helpers.AuthTokenHelper;
import models.Person;
import responses.personResponse;

import java.sql.Connection;

/**
 * personID - identifier for the person
 * authToken - the token that is used to authorize calls, also can be used to tell the user.
 * model - used to represent a single row of data in the person database.
 */
public class personService {


    /**
     * returns a specified person from the database based upon the personID and the currentuser which is determined from the authtoken
     * @return returns an object of the response of the calls and logic of the service.
     */
    public personResponse personInfo(String authToken, String personID) throws DataAccessException {
        AuthTokenHelper token = new AuthTokenHelper();
        String username = token.checkAuthToken(authToken);
        Database db = new Database();
        Connection conn = db.getConnection();
        PersonDAO pDAO = new PersonDAO(conn);
        Person person;

        if(username == null) {
            personResponse resp = new personResponse("Error: the authtoken is not valid", false);
            return resp;
        }
        if(!(personID instanceof String)) {
            personResponse resp = new personResponse("Error: the personID is not a string", false);
            return resp;
        }
        person = pDAO.findByUser(personID, username);
        db.closeConnection(false);
        if(person == null) {
            personResponse resp = new personResponse("Error: the person does not exist or is not linked with the user", false);
            return resp;
        }
        else{
            if(person.getSpouseID()==null || person.getFatherID() == null || person.getMotherID() == null){
                personResponse resp = new personResponse(person.getAssociatedUsername(), person.getPersonID(), person.getFirstName(),
                        person.getLastName(), person.getGender(), true);
                return resp;
            }
            else{
                personResponse resp = new personResponse(person.getAssociatedUsername(), person.getPersonID(), person.getFirstName(),
                        person.getLastName(), person.getGender(), person.getFatherID(), person.getMotherID(), person.getSpouseID(), true);
                return resp;
            }
        }
    }

}

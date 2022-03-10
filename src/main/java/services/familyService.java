package services;

import dao.DataAccessException;
import dao.Database;
import dao.PersonDAO;
import helpers.AuthTokenHelper;
import models.Person;
import responses.FamilyResponse;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * authtoken is a string that determines the current user and is used to authenticate with the server.
 */
public class FamilyService {


    /**
     * get the total people of the current user.
     *
     * @return the array of people related to the user and a success boolean. if there was an error then it returns a false boolean and the
     * error message
     */
    public FamilyResponse getFamily(String authToken) throws DataAccessException, SQLException {
        AuthTokenHelper token = new AuthTokenHelper();
        String username = token.checkAuthToken(authToken);
        Database db = new Database();
        Connection conn = db.getConnection();
        PersonDAO pDAO = new PersonDAO(conn);
        ArrayList<Person> people = new ArrayList<>();
        Person[] retArray;

        if(username == null) {
            FamilyResponse resp = new FamilyResponse("Error: there is no username in the database",false);
            return resp;
        }
        people = pDAO.findFamily(username);
        db.closeConnection(false);
        if(people == null){
            FamilyResponse resp = new FamilyResponse("Error: there was no one in the database for the user", false);
            return resp;
        }
        retArray = people.toArray(new Person[0]);
        System.out.println(retArray.length);
        FamilyResponse resp = new FamilyResponse(retArray, true);
        return resp;
    }
}


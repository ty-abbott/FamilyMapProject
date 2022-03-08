package services;

import dao.DataAccessException;
import dao.Database;
import dao.EventDAO;
import helpers.AuthTokenHelper;
import models.Event;
import responses.eventAllResponse;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * authToken - used to get the current user and to authenticate api calls.
 */
public class eventAllService {


    /**
     * gets all the events of the users family.
     *
     * @return returns an array of all the events if there are no errors. False and an error message if there is
     */
    public eventAllResponse getEventAll(String authToken) throws DataAccessException, SQLException {
        AuthTokenHelper token = new AuthTokenHelper();
        String username = token.checkAuthToken(authToken);
        Database db = new Database();
        Connection conn = db.getConnection();
        EventDAO eDAO = new EventDAO(conn);
        ArrayList<Event> events = new ArrayList<>();
        Event[] retArray;

        if(username == null){
            eventAllResponse resp = new eventAllResponse( false, "Error: the authtoken is invalid");
            return resp;
        }

        events = eDAO.findAll(username);
        db.closeConnection(false);
        if(events == null){
            eventAllResponse resp = new eventAllResponse(false, "Error: there were not events in the database");
            return resp;
        }
        else{
            retArray = events.toArray(new Event[0]);
            eventAllResponse resp = new eventAllResponse(retArray, true);
            return resp;
        }
    }
}

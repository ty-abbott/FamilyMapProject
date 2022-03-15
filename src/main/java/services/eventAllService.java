package services;

import dao.DataAccessException;
import dao.Database;
import dao.EventDAO;
import helpers.AuthTokenHelper;
import models.Event;
import responses.EventAllResponse;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * authToken - used to get the current user and to authenticate api calls.
 */
public class EventAllService {


    /**
     * gets all the events of the users family.
     *
     * @return returns an array of all the events if there are no errors. False and an error message if there is
     */
    public EventAllResponse getEventAll(String authToken) throws DataAccessException, SQLException {
        AuthTokenHelper token = new AuthTokenHelper();
        String username = token.checkAuthToken(authToken);
        Database db = new Database();
        Connection conn = db.getConnection();
        EventDAO eDAO = new EventDAO(conn);
        ArrayList<Event> events = new ArrayList<>();
        Event[] retArray;
        //data fields that are necessary for functionality
        if(username == null){
            EventAllResponse resp = new EventAllResponse( false, "Error: the authtoken is invalid");
            return resp;
            //if the username is null then respond with that
        }
        //fill the array list with all the event objects based on the username
        events = eDAO.findAll(username);
        db.closeConnection(false);
        if(events == null){
            EventAllResponse resp = new EventAllResponse(false, "Error: there were not events in the database");
            return resp;
            //no given events based on the username
        }
        else{
            retArray = events.toArray(new Event[0]);
            EventAllResponse resp = new EventAllResponse(retArray, true);
            return resp;
            //return the events
        }
    }
}

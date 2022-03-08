package services;

import dao.DataAccessException;
import dao.Database;
import dao.EventDAO;
import helpers.AuthTokenHelper;
import models.Event;
import responses.eventResponse;

import java.sql.Connection;

/**
 * eventID - the id of the event we are looking for.
 * authToken - the authToken that we need to figure out who the user is and used to authenticate api calls.
 */
public class eventService {


    /**
     * get a specifc event based upon the eventID that we are given.
     *
     * @return the event information and success if it works and false and the error message if not.
     */
    public eventResponse getEvent(String authToken, String eventID) throws DataAccessException {
        AuthTokenHelper token = new AuthTokenHelper();
        String username = token.checkAuthToken(authToken);
        Database db = new Database();
        Connection conn = db.getConnection();
        EventDAO eDAO = new EventDAO(conn);
        Event event;

        if(username ==  null) {
            eventResponse resp = new eventResponse("Error: the authtoken is not valid", false);
            return resp;
        }
        if(!(eventID instanceof String)){
            eventResponse resp = new eventResponse("Error: the event ID is not a string", false);
            return resp;
        }
        //TODO:create the DAO function to find a single event based on the username
        //create the rest of the functionality and then return that ish
        event = eDAO.findByUser(eventID, username);
        db.closeConnection(false);
        if(event == null){
            eventResponse resp = new eventResponse("Error: there was no event in the database with that username and ID", false);
            return resp;
        }
        else{
            eventResponse resp = new eventResponse(event.getAssociatedUsername(), event.getEventID(), event.getPersonID(),
                    event.getLatitude(), event.getLongitude(), event.getCountry(), event.getCity(), event.getEventType(),
                    event.getYear(), true);
            return resp;
        }
    }

}
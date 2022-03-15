package services;
import dao.*;
import models.Event;
import models.Person;
import models.User;
import requests.LoadRequest;
import responses.DefaultResponse;

import java.sql.Connection;

public class LoadService {
    /**
     *
     * @param body - contains arrays of people, users and events to load into the database
     * @return response on whether or not the functionality worked.
     */
    public DefaultResponse loadData(LoadRequest body) throws DataAccessException {
        ClearService clear = new ClearService();
        clear.clearDB();
        Database db = new Database();
        Connection conn = db.getConnection();
        EventDAO eDAO = new EventDAO(conn);
        PersonDAO pDAO = new PersonDAO(conn);
        UserDAO uDAO = new UserDAO(conn);
        //array of users is passed in to add to the database.
        for (User user : body.getUsers()){
            //if any of the values are null we stop and send the error
            if (user.getUsername() == null|| user.getPassword() == null || user.getEmail() == null || user.getFirstName() == null ||
                    user.getLastName() == null || user.getGender()==null||user.getPersonID() == null){
                db.closeConnection(false);
                DefaultResponse resp = new DefaultResponse("Error:there was a value missing in a users object", false);
                return resp;
            }
            else{
                uDAO.insert(user);
            }
        }
        //events to add to the database. If any values are null then same story
        Event[] events = body.getEvents();
        for (Event event : events) {
            if (event.getEventID() == null || event.getAssociatedUsername() == null || event.getPersonID() == null||
                    event.getLatitude() == 0 || event.getLongitude() == 0 || event.getCountry() == null || event.getCity() == null ||
            event.getEventType() == null || event.getYear() == 0){
                db.closeConnection(false);
                DefaultResponse resp = new DefaultResponse("Error: One of the fields for an event object was missing", false);
                return resp;
            }
            else{
                eDAO.insert(event);
            }
        }
        //person array to put into the database
        for (Person person : body.getPersons()){
            if(person.getPersonID() == null || person.getAssociatedUsername() == null || person.getFirstName() == null || person.getLastName() == null ||
            person.getGender() == null){
                db.closeConnection(false);
                DefaultResponse resp = new DefaultResponse("Error: there was a missing field in a Person object", false);
                return resp;
            }
            else{
                pDAO.insert(person);
            }
        }
        //close database and send the response with how many of each were added
        db.closeConnection(true);
        int numUsers = body.getUsers().length;
        int numPersons = body.getPersons().length;
        int numEvents = body.getEvents().length;
        return new DefaultResponse("Successfully added " + numUsers + " users, " + numPersons + " persons, and " + numEvents + " events to the database.", true);
    }
}

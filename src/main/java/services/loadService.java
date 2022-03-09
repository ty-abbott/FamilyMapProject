package services;
import dao.*;
import models.Event;
import models.Person;
import models.User;
import requests.loadRequest;
import responses.defaultResponse;

import java.sql.Connection;

public class loadService {
    /**
     *
     * @param body - contains arrays of people, users and events to load into the database
     * @return response on whether or not the functionality worked.
     */
    public defaultResponse loadData(loadRequest body) throws DataAccessException {
        clearService clear = new clearService();
        clear.clearDB();
        Database db = new Database();
        Connection conn = db.getConnection();
        EventDAO eDAO = new EventDAO(conn);
        PersonDAO pDAO = new PersonDAO(conn);
        UserDAO uDAO = new UserDAO(conn);

        for (User user : body.getUsers()){
            if (user.getUsername() == null|| user.getPassword() == null || user.getEmail() == null || user.getFirstName() == null ||
                    user.getLastName() == null || user.getGender()==null||user.getPersonID() == null){
                defaultResponse resp = new defaultResponse("Error:there was a value missing in a users object", false);
                return resp;
            }
            else{
                uDAO.insert(user);
            }
        }

        Event[] events = body.getEvents();
        for (Event event : events) {
            if (event.getEventID() == null || event.getAssociatedUsername() == null || event.getPersonID() == null||
                    event.getLatitude() == 0 || event.getLongitude() == 0 || event.getCountry() == null || event.getCity() == null ||
            event.getEventType() == null || event.getYear() == 0){
                defaultResponse resp = new defaultResponse("Error: One of the fields for an event object was missing", false);
                return resp;
            }
            else{
                eDAO.insert(event);
            }
        }

        for (Person person : body.getPersons()){
            if(person.getPersonID() == null || person.getAssociatedUsername() == null || person.getFirstName() == null || person.getLastName() == null ||
            person.getGender() == null){
                defaultResponse resp = new defaultResponse("Error: there was a missing field in a Person object", false);
                return resp;
            }
            else{
                pDAO.insert(person);
            }
        }

        db.closeConnection(true);
        int numUsers = body.getUsers().length;
        int numPersons = body.getPersons().length;
        int numEvents = body.getEvents().length;
        return new defaultResponse("Successfully added " + numUsers + " users, " + numPersons + " persons, and " + numEvents + " events to the database.", true);
    }
}

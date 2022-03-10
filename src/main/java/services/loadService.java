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

        for (User user : body.getUsers()){
            if (user.getUsername() == null|| user.getPassword() == null || user.getEmail() == null || user.getFirstName() == null ||
                    user.getLastName() == null || user.getGender()==null||user.getPersonID() == null){
                DefaultResponse resp = new DefaultResponse("Error:there was a value missing in a users object", false);
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
                DefaultResponse resp = new DefaultResponse("Error: One of the fields for an event object was missing", false);
                return resp;
            }
            else{
                eDAO.insert(event);
            }
        }

        for (Person person : body.getPersons()){
            if(person.getPersonID() == null || person.getAssociatedUsername() == null || person.getFirstName() == null || person.getLastName() == null ||
            person.getGender() == null){
                DefaultResponse resp = new DefaultResponse("Error: there was a missing field in a Person object", false);
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
        return new DefaultResponse("Successfully added " + numUsers + " users, " + numPersons + " persons, and " + numEvents + " events to the database.", true);
    }
}

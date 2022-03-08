package requests;

import models.Event;
import models.Person;
import models.User;

/**
 * request class for the load functionality

 */
public class loadRequest {
    /**
     * userArray - an array of users
     * person[] - array of people
     * events[] - array of events
     */
    private User[] users;
    private Person[] persons;
    private Event[] events;

    loadRequest(){

    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public Person[] getPersons() {
        return persons;
    }

    public void setPersons(Person[] persons) {
        this.persons = persons;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }
}

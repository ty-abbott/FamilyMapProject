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
    private User[] userArray;
    private Person[] personsArray;
    private Event[] eventsArray;

    public User[] getUserArray() {
        return userArray;
    }

    public void setUserArray(User[] userArray) {
        this.userArray = userArray;
    }

    public Person[] getPersonsArray() {
        return personsArray;
    }

    public void setPersonsArray(Person[] personsArray) {
        this.personsArray = personsArray;
    }

    public Event[] getEventsArray() {
        return eventsArray;
    }

    public void setEventsArray(Event[] eventsArray) {
        this.eventsArray = eventsArray;
    }
}

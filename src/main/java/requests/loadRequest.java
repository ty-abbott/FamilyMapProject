package requests;

import models.Event;
import models.person;
import models.user;

/**
 * request class for the load functionality

 */
public class loadRequest {
    /**
     * userArray - an array of users
     * person[] - array of people
     * events[] - array of events
     */
    private user[] userArray;
    private person[] personsArray;
    private Event[] eventsArray;

    public user[] getUserArray() {
        return userArray;
    }

    public void setUserArray(user[] userArray) {
        this.userArray = userArray;
    }

    public person[] getPersonsArray() {
        return personsArray;
    }

    public void setPersonsArray(person[] personsArray) {
        this.personsArray = personsArray;
    }

    public Event[] getEventsArray() {
        return eventsArray;
    }

    public void setEventsArray(Event[] eventsArray) {
        this.eventsArray = eventsArray;
    }
}

package responses;
import models.Event;

/**
 * a response class for getting all the events of a users family members
 */
public class eventAllResponse {
    private Event[] eventArray;
    private boolean success;
    private String message;

    /**
     * the constructor called if the service worked
     * @param eventArray - eventarray - the array of the events
     * @param success - success - whether or not there was an error
     */

    public eventAllResponse(Event[] eventArray, boolean success) {
        this.eventArray = eventArray;
        this.success = success;
    }

    /**
     * the constructor called if the service did not work
     * @param success - success - whether or not there was an error
     * @param message - message - either a message that it worked or the error message.
     */
    public eventAllResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Event[] getEventArray() {
        return eventArray;
    }

    public void setEventArray(Event[] eventArray) {
        this.eventArray = eventArray;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

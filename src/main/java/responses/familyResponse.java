package responses;


import models.Person;

/**
 * the response object of getting all the family information for a person or user.
 */
public class familyResponse {
    private Person[] family;
    private String message;
    private boolean success;

    /**
     * this constructor is called if the service worked
     * @param family - family - an array of people that represents all the generations of the person
     * @param success - success - bool on whether it worked or not
     */
    public familyResponse(Person[] family, boolean success) {
        this.family = family;
        this.success = success;
    }

    /**
     * this constructor is called if the service did not work.
     * @param message - message - message of success or error
     * @param success - success - bool on whether it worked or not
     */
    public familyResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public Person[] getFamily() {
        return family;
    }

    public void setFamily(Person[] family) {
        this.family = family;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

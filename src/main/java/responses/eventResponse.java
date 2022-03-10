package responses;

/**
 * a response class for the event service
 */
public class EventResponse {
    private String associatedUsername;
    private String eventID;
    private String personID;
    private String country;
    private String city;
    private String eventType;
    private boolean success;
    private String message;
    private Integer year;
    private Float latitude;
    private Float longitude;
    /**
     * returns information on a specific event. This constructor is called if the service worked
     * @param associatedUsername - username that is associated with the event
     * @param eventID - the actual id of the event
     * @param personID - event that this person is related to
     * @param latitude - latitude of the event
     * @param longitude - longitude of the event
     * @param country - country where this took place
     * @param city - city where this took place
     * @param eventType - type of event
     * @param year - year of event
     * @param success - whether or not there was an error.
     */
    public EventResponse(String associatedUsername, String eventID, String personID, float latitude, float longitude,
                         String country, String city, String eventType, int year, boolean success) {
        this.associatedUsername = associatedUsername;
        this.eventID = eventID;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
        this.success = success;
    }

    /**
     * this constructor is called if the service did not work.
     * @param success -  a bool for whether or not there was an error.
     * @param message - a message whether there was success or not. If there was an error then a error message is included.
     */
    public EventResponse(String message, boolean success) {
        this.success = success;
        this.message = message;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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

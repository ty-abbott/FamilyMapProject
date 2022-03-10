package responses;

/**
 * returns an object representing all the information on a person found in the database.
 */
public class PersonResponse {
    private String associatedUsername;
    private String personID;
    private String firstName;
    private String lastName;
    private String gender;
    private String fatherName;
    private String motherName;
    private String spouseID;
    private boolean success;
    private String message;

    /**
     *
     * @param associatedUsername - String associated username - the username of the user that is associated with that person
     * @param personID - String personID - the id of the person
     * @param firstName - String firstName - the first name of the person
     * @param lastName - String lastName - the last name of the person
     * @param gender - String gender - the gender of the person
     * @param fatherName - String fatherName - the fathers name of the person. OPTIONAL
     * @param motherName - String motherName - the name of the mother. OPTONAL
     * @param spouseID - String spouseID - the ID of the spouse
     * @param success - boolean success - a boolean that states if the service was successful
     * this constructor is called if the service was a success
     */
    public PersonResponse(String associatedUsername, String personID, String firstName, String lastName, String gender,
                          String fatherName, String motherName, String spouseID, boolean success) {
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.spouseID = spouseID;
        this.success = success;
    }

    public PersonResponse(String associatedUsername, String personID, String firstName, String lastName, String gender, boolean success) {
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.success = success;
    }

    /**
     * this constructor is called if the service was not successful
     * @param success - boolean success - a boolean that states if the service was successful
     * @param message - String message - the message of if the service worked or not. If it did not then the error is included.
     */
    public PersonResponse(String message, boolean success ) {
        this.success = success;
        this.message = message;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getSpouseID() {
        return spouseID;
    }

    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
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



package models;


/**
 * The model for a particular row in the person database
 */
public class person {
    /**
     * String personID - the ID of the person of this particular row
     * String associatedUsername - the associated username of the user to this person in this particular row
     * String firstName - the firstname of the person in this particular row.
     * String lastName - the lastname of the person in this particular row.
     * String gender - the gender of the person in this particular row.
     * String fatherID - default is null, the ID of the father of the person in this particular row
     * String motherID - default is null, the ID of the mother of the person in this particular row
     */
    private String personID;
    private String associatedUsername;
    private String firstName;
    private String lasName;
    private String gender;
    private String fatherID=null;
    private String motherID=null;

    public person(String personID, String associatedUsername, String firstName, String lasName, String gender,
                  String fatherID, String motherID, String spouseID) {
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lasName = lasName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLasName() {
        return lasName;
    }

    public void setLasName(String lasName) {
        this.lasName = lasName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFatherID() {
        return fatherID;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public String getMotherID() {
        return motherID;
    }

    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    public String getSpouseID() {
        return spouseID;
    }

    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
    }

    private String spouseID=null;

}

package dao;

import models.person;

/**
 * this class interacts with the person table of the database.
 */
public class PersonDAO {
    /**
     * inserts a person into the database.
     * @param body - has all the information necessary to input the person into the database
     * @return - if there was no problem then the person model is returned. If there was then null is returned.
     */
    public person insert(person body){return null;}

    /**
     * this reads the family of the person that is passed in
     * @param body - this is the person model, it will be used to find the associated family members.
     * @return - an array of persons, if there was a problem then it returns null.
     */
    public person[] readFamily(person body){return null;}

    /**
     * this looks for and reads a specific family member.
     * @param body - the information that will be used to find the specific person.
     * @return - returns the model of the person found. returns null if not found.
     */
    public person read(person body){return null;}

    /**
     * deletes the person table of the database.
     * @return returns true if successful and false if not.
     */
    public boolean delete(){
        return true;
    }
}

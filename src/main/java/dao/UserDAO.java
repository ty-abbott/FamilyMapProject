package dao;
import models.user;
/**
 * the data access object for the user table of the database. This is where the interaction with the user table of the
 * database actually happens.
 */
public class UserDAO {



    /**
     * this will insert into the user table.
     * @param body - this is the user model that is passed into the method. This has all the information necessary to
     *             insert the user into the database.
     * @return a user model of the user that was inserted, if there was a problem then null will be returned.
     */
    public user insert(user body) {
        //this will insert the user in the database
        return null;
    }

    /**
     * this will return a specific user if it is found in the database.
     * @param body - the information that will be used to try and find the row.
     * @return - the user model will be returned if the user is found. If the user is not found then null will be
     * returned
     */
    public user read(user body){
        //this will return the user if found in the database
        return null;
    }

    /**
     * this will delete the user table of the database.
     * @return - this will return true if it worked. Else it returns false.
     */
    public boolean delete() {
        return true;
    }

}



package dao;

import models.AuthToken;

/**
 * this is a class that works with the auth token table of the database.
 */
public class AuthTokenDAO {
    /**
     * returns the auth token of the specific user.
     * @param token - the information that will get the token and username
     * @return - the model of the authtoken, else null
     */
    public AuthToken read(AuthToken token){
        return null;
    }

    /**
     * inserts a new authtoken into the database
     * @param token - information of the token
     * @return - a model of the token else null
     */
    public AuthToken insert(AuthToken token){
        return null;
    }

    /**
     * deletes the authtoken table of the database.
     *
     * @return true if it worked and false if not.
     */
    public boolean delete(){
        return true;
    }
}

package services;

import responses.DefaultResponse;
import dao.*;

import java.sql.Connection;


/**
 * message that states whether or not the service worked
 * success - boolean of whether or not there was success
 */
public class ClearService {
    private String message;
    private boolean success;
    Database db = new Database();

    /**
     * clears the whole database.
     * @return a response on whether or not the service worked.
     */
    public DefaultResponse clearDB() throws DataAccessException {
        //create the database connection and then the DAO objects
        Connection conn = db.getConnection();
        AuthTokenDAO aDAO = new AuthTokenDAO(conn);
        EventDAO eDAO = new EventDAO(conn);
        PersonDAO pDAO = new PersonDAO(conn);
        UserDAO uDAO = new UserDAO(conn);
        try{
            //clear the tables of the database
            System.out.println("Clearing the database.");
            aDAO.clear();
            eDAO.clear();
            pDAO.clear();
            uDAO.clear();
            db.closeConnection(true);
        } catch (DataAccessException e) {
            db.closeConnection(false);
            DefaultResponse resp = new DefaultResponse("Error: There was a problem clearing from the database", false);
            e.printStackTrace();
            return resp;
        }

        return new DefaultResponse("Clear succeeded.", true);
    }
}

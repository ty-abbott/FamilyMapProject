package helpers;

import dao.AuthTokenDAO;
import dao.DataAccessException;
import dao.Database;
import models.AuthToken;

import java.sql.Connection;
import java.util.UUID;

/**
 * this is a helper class to create and return an authtoken.
 */
public class AuthTokenHelper {

    public String getAuthToken() {
        //returning a authtoken with a random string
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public String checkAuthToken(String authToken) throws DataAccessException {
        //check if the given authtoken is valid by seeing if there is a user associated with it
        Database db = new Database();
        Connection conn = db.getConnection();
        AuthTokenDAO aDAO = new AuthTokenDAO(conn);
        AuthToken token = aDAO.find(authToken);
        db.closeConnection(false);
        if(token == null) {
            return null;
        }
        else{
            return token.getUsername();
        }


    }
}

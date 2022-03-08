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

        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public String checkAuthToken(String authToken) throws DataAccessException {
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

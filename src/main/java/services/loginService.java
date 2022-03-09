package services;

import dao.AuthTokenDAO;
import dao.DataAccessException;
import requests.loginRequest;
import responses.loginResponse;
import dao.UserDAO;
import dao.Database;
import java.sql.Connection;
import java.util.Objects;
import helpers.AuthTokenHelper;

import models.User;
import models.AuthToken;

/**
 * a service class to help login the user to the application
 */
public class loginService {


    /**
     * tries to log the person into the server. Checks if credentials are in the database.
     * @param body contains username and password
     * @return response of whether or not the service worked.
     */
    public loginResponse login(loginRequest body) throws DataAccessException {
        Database db = new Database();
        Connection conn = db.getConnection();
        UserDAO uDao = new UserDAO(conn);
        AuthTokenDAO authDao = new AuthTokenDAO(conn);
        AuthTokenHelper authtoken = new AuthTokenHelper();


        if(body.getUsername() == null || body.getPassword() == null) {
            loginResponse resp = new loginResponse("Error: there was no username or password password provided", false);
            return resp;
        }
        else{
            User user = uDao.find(body.getUsername());

            if(user == null){
                db.closeConnection(false);
                loginResponse resp = new loginResponse("Error: there is no user in the database with this username", false);
                return resp;
            }
            if(!Objects.equals(user.getPassword(), body.getPassword())){
                db.closeConnection(false);
                loginResponse resp = new loginResponse("Error: the passwords do not match", false);
                return resp;
            }
            String auth = authtoken.getAuthToken();
            AuthToken tokenModel = new AuthToken(auth, user.getUsername());
            authDao.insert(tokenModel);
            db.closeConnection(true);
            loginResponse resp = new loginResponse(auth, user.getUsername(), user.getPersonID(), true);
            return resp;
        }
    }
}

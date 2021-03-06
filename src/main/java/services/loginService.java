package services;

import dao.AuthTokenDAO;
import dao.DataAccessException;
import requests.LoginRequest;
import responses.LoginResponse;
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
public class LoginService {


    /**
     * tries to log the person into the server. Checks if credentials are in the database.
     * @param body contains username and password
     * @return response of whether or not the service worked.
     */
    public LoginResponse login(LoginRequest body) throws DataAccessException {
        Database db = new Database();
        Connection conn = db.getConnection();
        UserDAO uDao = new UserDAO(conn);
        AuthTokenDAO authDao = new AuthTokenDAO(conn);
        AuthTokenHelper authtoken = new AuthTokenHelper();
        //^^getting the login information and setting up database connection with DAO objects
        //checking to see if any of the login info is null. If it is we send a error response
        if(body.getUsername() == null || body.getPassword() == null) {
            LoginResponse resp = new LoginResponse("Error: there was no username or password password provided", false);
            return resp;
        }
        //check if user name is in database
        else{
            User user = uDao.find(body.getUsername());
        //if not return error
            if(user == null){
                db.closeConnection(false);
                LoginResponse resp = new LoginResponse("Error: there is no user in the database with this username", false);
                return resp;
            }
            //if password is incorrect tell them in error response
            if(!Objects.equals(user.getPassword(), body.getPassword())){
                db.closeConnection(false);
                LoginResponse resp = new LoginResponse("Error: the passwords do not match", false);
                return resp;
            }
            //else log them in
            String auth = authtoken.getAuthToken();
            AuthToken tokenModel = new AuthToken(auth, user.getUsername());
            authDao.insert(tokenModel);
            db.closeConnection(true);
            LoginResponse resp = new LoginResponse(auth, user.getUsername(), user.getPersonID(), true);
            return resp;
        }
    }
}

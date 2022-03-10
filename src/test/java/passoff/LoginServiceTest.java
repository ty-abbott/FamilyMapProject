package passoff;

import dao.DataAccessException;
import dao.Database;
import dao.UserDAO;
import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requests.LoginRequest;
import responses.LoginResponse;
import services.*;

import javax.xml.crypto.Data;
import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.*;
public class LoginServiceTest {
    Database db;
    User user;
    UserDAO uDAO;
    LoginService service;
    LoginRequest request;
    LoginResponse resp;
    @BeforeEach
    public void setUp() throws DataAccessException{
        db = new Database();
        service = new LoginService();

        System.out.println("Creating login requests");
        request = new LoginRequest();
        request.setUsername("tyler");
        request.setPassword("password");

        System.out.println("Creating a user object");
        user = new User("tmag", "password", "t@google.com", "Tyler", "Abbott",
                "m", "789");

        Connection conn = db.getConnection();
        uDAO = new UserDAO(conn);
        uDAO.clear();
    }

    @AfterEach
    public void tearDown() {
        db.closeConnection(false);
    }

    @Test
    public void loginFail() {
        try{
            resp = service.login(request);
            assertEquals(false, resp.isSuccess());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}

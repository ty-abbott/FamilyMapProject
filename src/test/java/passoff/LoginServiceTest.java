package passoff;

import dao.DataAccessException;
import dao.Database;
import dao.UserDAO;
import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requests.LoginRequest;
import requests.RegisterRequest;
import responses.LoginResponse;
import services.*;

import javax.xml.crypto.Data;
import java.io.FileNotFoundException;
import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.*;
public class LoginServiceTest {
    Database db;
    User user;
    UserDAO uDAO;
    LoginService service;
    LoginRequest request;
    LoginResponse resp;
    ClearService clear;
    RegisterService register;
    RegisterRequest regReq;


    @BeforeEach
    public void setUp() throws DataAccessException{
        db = new Database();
        service = new LoginService();

        System.out.println("Creating login requests");
        request = new LoginRequest();
        request.setUsername("tyler");
        request.setPassword("password");

        System.out.println("Creating a user object");
        regReq = new RegisterRequest();
        regReq.setUsername("tyler");
        regReq.setPassword("password");
        regReq.setEmail("email");
        regReq.setFirstName("tyler");
        regReq.setLastName("abbott");
        regReq.setGender("m");

        register = new RegisterService();
        clear = new ClearService();
        clear.clearDB();
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        clear.clearDB();
    }

    @Test
    public void loginFail() {
        try{
            resp = service.login(request);
            assertFalse(resp.isSuccess());
        } catch (DataAccessException e) {
            fail(e.getMessage());
            System.out.println("Error:logged in when shouldnt have");
        }
    }

    @Test
    public void loginSuccess() {
        try{
            register.createAccount(regReq);
            resp = service.login(request);
            assertTrue(resp.isSuccess());
        } catch (DataAccessException | FileNotFoundException e) {
            e.printStackTrace();
            fail(e.getMessage());
            System.out.println("Error occured when logging in. Did not loggin when it should have");
        }
    }
}

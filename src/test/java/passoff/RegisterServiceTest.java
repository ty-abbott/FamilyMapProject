package passoff;
import dao.DataAccessException;
import dao.Database;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requests.RegisterRequest;
import responses.RegisterResponse;
import static org.junit.jupiter.api.Assertions.*;
import services.*;

import java.io.FileNotFoundException;

public class RegisterServiceTest {
    ClearService clear;
    RegisterService register;
    RegisterRequest regReq;
    RegisterRequest regReq2;
    RegisterResponse resp;

    @BeforeEach
    public void setUp() throws DataAccessException{
        register = new RegisterService();
        System.out.println("Creating register requests");
        regReq = new RegisterRequest();
        regReq.setUsername("tyler");
        regReq.setPassword("password");
        regReq.setEmail("email");
        regReq.setFirstName("tyler");
        regReq.setLastName("abbott");
        regReq.setGender("m");

        regReq2 = new RegisterRequest();
        regReq2.setUsername("tyler");
        regReq2.setPassword("password");
        regReq2.setEmail("email");
        regReq2.setFirstName("tyler");
        regReq2.setLastName("abbott");

        clear = new ClearService();
        clear.clearDB();
    }

    @AfterEach
    public void tearDown() throws DataAccessException{
        clear.clearDB();
    }

    @Test
    public void registerFail() {
        try{
            resp = register.createAccount(regReq2);
            assertFalse(resp.getSuccess());
        } catch (FileNotFoundException | DataAccessException e) {
            e.printStackTrace();
            fail(e.getMessage());
            System.out.println("Error: problem with the bad data");
        }
    }

    @Test
    public void registerPass(){
        try{
            resp = register.createAccount(regReq);
            assertTrue(resp.getSuccess());
        } catch (FileNotFoundException | DataAccessException e) {
            e.printStackTrace();
            fail(e.getMessage());
            System.out.println("Error: problem with registering ");
        }
    }
}


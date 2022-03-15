package passoff;
import dao.DataAccessException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requests.RegisterRequest;
import responses.PersonResponse;
import responses.RegisterResponse;
import services.ClearService;
import services.PersonService;
import services.RegisterService;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class PersonServiceTest {
    PersonService service;
    RegisterRequest request;
    PersonResponse resp;
    RegisterService registerService;
    RegisterResponse registerResponse;
    ClearService clear;

    @BeforeEach
    public void setUp() throws DataAccessException{

        System.out.println("Setting up for the person Test");
        request = new RegisterRequest();
        request.setUsername("tyler");
        request.setPassword("password");
        request.setEmail("email");
        request.setFirstName("tyler");
        request.setLastName("abbott");
        request.setGender("m");
        registerService = new RegisterService();
        service = new PersonService();
        clear = new ClearService();
        clear.clearDB();
    }

    @AfterEach
    public void tearDown() throws DataAccessException{
        clear.clearDB();
    }

    @Test
    public void personFail(){
        try{
            registerResponse = registerService.createAccount(request);
            resp = service.personInfo("not the auth token", "fake iD");
            assertFalse(resp.isSuccess());
        } catch (FileNotFoundException | DataAccessException e) {
            e.printStackTrace();
            fail(e.getMessage());
            System.out.println("Failed: in person fail");
        }
    }

    @Test
    public void personSuccess(){
        try{
            registerResponse = registerService.createAccount(request);
            resp = service.personInfo(registerResponse.getAuthtoken(), registerResponse.getPersonID());
            assertTrue(resp.isSuccess());
        } catch (FileNotFoundException | DataAccessException e) {
            e.printStackTrace();
        }
    }

}

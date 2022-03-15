package passoff;
import dao.DataAccessException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requests.RegisterRequest;
import responses.DefaultResponse;
import responses.RegisterResponse;
import services.*;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;


public class ClearServiceTest {
    ClearService service;
    RegisterRequest regReq;
    DefaultResponse resp;
    RegisterService registerService;


    @BeforeEach
    public void setUp() throws DataAccessException{
        service = new ClearService();
        System.out.println("Creating the clear service functionality");
        regReq = new RegisterRequest();
        regReq.setUsername("tyler");
        regReq.setPassword("password");
        regReq.setEmail("email");
        regReq.setFirstName("tyler");
        regReq.setLastName("abbott");
        regReq.setGender("m");
        service.clearDB();
        registerService = new RegisterService();
    }

    @AfterEach
    public void tearDown() throws DataAccessException{
        service.clearDB();
    }

    @Test
    public void clearSuccessEmptyDataBase(){
        try{
            resp = service.clearDB();
            assertTrue(resp.isSuccess());
        } catch (DataAccessException e) {
            fail(e.getMessage());
            System.out.println("Error: problem clearing the empty database");
        }
    }

    @Test
    public void clearSuccessFullDatabase(){
        try{
            registerService.createAccount(regReq);
            resp = service.clearDB();
            assertTrue(resp.isSuccess());
        } catch (FileNotFoundException | DataAccessException e) {
            e.printStackTrace();
        }
    }
}

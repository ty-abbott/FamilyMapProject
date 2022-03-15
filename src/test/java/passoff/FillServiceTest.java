package passoff;
import dao.DataAccessException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import passoffrequest.FillRequest;
import requests.RegisterRequest;
import responses.DefaultResponse;
import responses.RegisterResponse;
import services.ClearService;
import services.FillService;
import services.RegisterService;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class FillServiceTest {
    FillService service;
    DefaultResponse response;
    ClearService clear;
    RegisterService registerService;
    RegisterRequest request;
    RegisterResponse registerResponse;

    @BeforeEach
    public void setUp() throws DataAccessException{
        System.out.println("Setting up the fill service test");
        request = new RegisterRequest();
        request.setUsername("tyler");
        request.setPassword("password");
        request.setEmail("email");
        request.setFirstName("tyler");
        request.setLastName("abbott");
        request.setGender("m");
        registerService = new RegisterService();
        clear = new ClearService();
        clear.clearDB();
    }

    @AfterEach
    public void tearDown() throws DataAccessException{
        clear.clearDB();
    }

    @Test
    public void fillFail(){
        try {
            service = new FillService("fake", 4);
            response = service.fill();
            assertFalse(response.isSuccess());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("problem with fill fail");
        }
    }

    @Test
    public void fillSuccess() {
        try{
            registerResponse = registerService.createAccount(request);
            service = new FillService(registerResponse.getUsername(), 4);
            response = service.fill();
            assertTrue(response.isSuccess());

        } catch (FileNotFoundException | DataAccessException | SQLException e) {
            e.printStackTrace();
        }
    }
}

package passoff;
import dao.DataAccessException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requests.RegisterRequest;
import responses.EventAllResponse;
import responses.FamilyResponse;
import responses.PersonResponse;
import responses.RegisterResponse;
import services.*;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class EventAllServiceTest {
    EventAllService service;
    RegisterRequest request;
    EventAllResponse resp;
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
        service = new EventAllService();
        clear = new ClearService();
        clear.clearDB();
    }

    @AfterEach
    public void tearDown() throws DataAccessException{
        clear.clearDB();
    }

    @Test
    public void familyFail(){
        try{
            resp = service.getEventAll("fake news");
            assertFalse(resp.isSuccess());
        } catch (DataAccessException | SQLException e) {
            e.printStackTrace();
            fail(e.getMessage());
            System.out.println("Error: failed in family fail");
        }
    }

    @Test
    public void familyPass() throws FileNotFoundException, DataAccessException, SQLException {
        try {
            registerResponse = registerService.createAccount(request);
            resp = service.getEventAll(registerResponse.getAuthtoken());
            assertTrue(resp.isSuccess());
        } catch (DataAccessException | FileNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

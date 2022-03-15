package passoff;
import dao.DataAccessException;
import dao.Database;
import dao.EventDAO;
import models.Event;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requests.RegisterRequest;
import responses.EventResponse;
import responses.RegisterResponse;
import services.ClearService;
import services.EventService;
import services.PersonService;
import services.RegisterService;

import java.io.FileNotFoundException;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class EventServiceTest {
    EventService service;
    RegisterRequest request;
    EventResponse resp;
    RegisterService registerService;
    RegisterResponse registerResponse;
    ClearService clear;
    EventDAO eDAO;
    Database db;

    @BeforeEach
    public void setUp() throws DataAccessException {

        System.out.println("Setting up for the event Test");
        request = new RegisterRequest();
        request.setUsername("tyler");
        request.setPassword("password");
        request.setEmail("email");
        request.setFirstName("tyler");
        request.setLastName("abbott");
        request.setGender("m");
        registerService = new RegisterService();
        service = new EventService();
        clear = new ClearService();
        clear.clearDB();
    }

    @AfterEach
    public void tearDown() throws DataAccessException{
        clear.clearDB();
    }

    @Test
    public void eventFail(){
        try{
            registerResponse = registerService.createAccount(request);
            resp = service.getEvent("not the auth token", "fake iD");
            assertFalse(resp.isSuccess());
        } catch (FileNotFoundException | DataAccessException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void personSuccess(){
        try{
            registerResponse = registerService.createAccount(request);
            db = new Database();
            Connection conn = db.getConnection();
            eDAO = new EventDAO(conn);
            Event event = new Event("eventID", "tyler", "123", 12, 13, "America", "Monument", "beat up hulk hogan", 2020);
            eDAO.insert(event);
            db.closeConnection(true);
            resp = service.getEvent(registerResponse.getAuthtoken(), event.getEventID());
            assertTrue(resp.isSuccess());
        } catch (FileNotFoundException | DataAccessException e) {
            e.printStackTrace();
        }
    }
}

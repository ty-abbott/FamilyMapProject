package passoff;

import dao.DataAccessException;
import models.Event;
import models.Person;
import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requests.LoadRequest;
import responses.DefaultResponse;
import services.ClearService;
import services.LoadService;
import static org.junit.jupiter.api.Assertions.*;
import javax.xml.crypto.Data;

public class LoadServiceTest {
    Person[] people;
    Event[] events;
    User[] users;
    User[] fakeUsers;
    LoadService service;
    DefaultResponse resp;
    LoadRequest request;
    Person person;
    Event event;
    User user;
    User fakeUser;
    ClearService clear;

    @BeforeEach
    public void setUp() throws DataAccessException{
        System.out.println("Setting up for the load test");
        request = new LoadRequest();
        person = new Person("123", "tmag", "tyler", "abbott", "m", "456", "789", "1011");
        people = new Person[1];
        people[0] = person;
        events = new Event[1];
        event = new Event("eventID", "tmag", "123", 12, 13, "America", "Monument", "beat up hulk hogan", 2020);
        events[0] = event;
        users = new User[1];
        user = new User("tmag", "password", "email", "tyler", "abbott", "m", "123");
        users[0] = user;
        fakeUsers = new User[1];
        fakeUser = new User(null, "password", "email", "tyler", "abbott", "m", "123");
        fakeUsers[0] = fakeUser;
        service = new LoadService();
        clear = new ClearService();
    }

    @AfterEach
    public void tearDown() throws DataAccessException{

    }
    @Test
    public void LoadFailTest() throws DataAccessException {
        try {
            request.setUsers(fakeUsers);
            request.setEvents(events);
            request.setPersons(people);
            resp = service.loadData(request);
            assertFalse(resp.isSuccess());
        } catch (DataAccessException e) {
            e.printStackTrace();
            System.out.println("Error with load function in the fail test");
        }
    }

    @Test
    public void LoadSuccessTest() throws DataAccessException{
        try{
            request.setUsers(users);
            request.setEvents(events);
            request.setPersons(people);
            resp = service.loadData(request);
            assertTrue(resp.isSuccess());
        } catch (DataAccessException e) {
            e.printStackTrace();
            System.out.println("Error with load function in the success test");
        }
    }

}

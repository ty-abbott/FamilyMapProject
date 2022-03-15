package passoff;

import dao.DataAccessException;
import dao.Database;
import dao.EventDAO;
import models.Event;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//We will use this to test that our insert method is working and failing in the right ways
public class EventDAOTest {
    private Database db;
    private Event bestEvent;
    private EventDAO eDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        // Here we can set up any classes or variables we will need for each test
        // lets create a new instance of the Database class
        db = new Database();
        // and a new event with random data
        bestEvent = new Event("Biking_123A", "Gale", "Gale123A",
                35.9f, 140.1f, "Japan", "Ushiku",
                "Biking_Around", 2016);

        // Here, we'll open the connection in preparation for the test case to use it
        Connection conn = db.getConnection();
        //Then we pass that connection to the EventDAO, so it can access the database.
        eDao = new EventDAO(conn);
        //Let's clear the database as well so any lingering data doesn't affect our tests
        eDao.clear();
    }

    @AfterEach
    public void tearDown() {
        // Here we close the connection to the database file, so it can be opened again later.
        // We will set commit to false because we do not want to save the changes to the database
        // between test cases.
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        // Start by inserting an event into the database.
        eDao.insert(bestEvent);
        // Let's use a find method to get the event that we just put in back out.
        Event compareTest = eDao.find(bestEvent.getEventID());
        // First lets see if our find method found anything at all. If it did then we know that we got
        // something back from our database.
        assertNotNull(compareTest);
        // Now lets make sure that what we put in is the same as what we got out. If this
        // passes then we know that our insert did put something in, and that it didn't change the
        // data in any way.
        // This assertion works by calling the equals method in the Event class.
        assertEquals(bestEvent, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        // Let's do this test again, but this time lets try to make it fail.
        // If we call the method the first time the event will be inserted successfully.
        eDao.insert(bestEvent);

        // However, our sql table is set up so that the column "eventID" must be unique, so trying to insert
        // the same event again will cause the insert method to throw an exception, and we can verify this
        // behavior by using the assertThrows assertion as shown below.

        // Note: This call uses a lambda function. A lambda function runs the code that comes after
        // the "()->", and the assertThrows assertion expects the code that ran to throw an
        // instance of the class in the first parameter, which in this case is a DataAccessException.
        assertThrows(DataAccessException.class, () -> eDao.insert(bestEvent));
    }

    @Test
    public void findSuccess() throws DataAccessException {
        try {
            eDao.insert(bestEvent);
            Event compareTest = eDao.find(bestEvent.getEventID());
            assertNotNull(compareTest);
            assertEquals(compareTest.getEventType(), bestEvent.getEventType());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findFail() throws DataAccessException{
        try{
            Event compareTest = eDao.find(bestEvent.getEventID());
            assertNull(compareTest);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllSuccess(){
        try{
            eDao.insert(bestEvent);
            eDao.insert(new Event("123", "Gale", "doesnt matter", 2, 3,
                    "america", "city", "got rich", 2020));
            ArrayList<Event> events = eDao.findAll(bestEvent.getAssociatedUsername());
            assertEquals(2, events.size());
        } catch (DataAccessException | SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void findAllFail(){
        try{
            ArrayList<Event> events = eDao.findAll(bestEvent.getAssociatedUsername());
            assertNull(events);
        } catch (DataAccessException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findByUserSuccess(){
        try{
            eDao.insert(bestEvent);
            Event event = eDao.findByUser(bestEvent.getEventID(), bestEvent.getAssociatedUsername());
            assertEquals(bestEvent.getEventID(), event.getEventID());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void findByUserFailure(){
        try{
            Event event = eDao.findByUser(bestEvent.getEventID(), bestEvent.getAssociatedUsername());
            assertNull(event);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void clearSuccessFull(){
        try {
            eDao.insert(bestEvent);
            assertNotNull(eDao.find(bestEvent.getEventID()));
            eDao.clear();
            Event compareTest = eDao.find(bestEvent.getEventID());
            assertNull(compareTest);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void clearSuccessEmpty() {
        try {
            eDao.clear();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void clearEvents(){
        try{
            eDao.insert(bestEvent);
            eDao.clearEvents(bestEvent.getAssociatedUsername());
            Event compareTest = eDao.findByUser(bestEvent.getEventID(), bestEvent.getAssociatedUsername());
            assertNull(compareTest);
        } catch (DataAccessException | SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void clearEventsEmpty() {
        try {
            eDao.clearEvents(bestEvent.getAssociatedUsername());
        } catch (DataAccessException | SQLException e) {
            e.printStackTrace();
        }
    }

    }


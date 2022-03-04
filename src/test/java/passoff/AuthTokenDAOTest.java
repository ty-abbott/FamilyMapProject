package passoff;

import dao.DataAccessException;
import dao.Database;
import dao.AuthTokenDAO;
import models.AuthToken;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class AuthTokenDAOTest {
    private Database db;
    private AuthToken auth;
    private AuthToken auth2;
    private AuthTokenDAO aDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        System.out.println("Creating authtoken object for test");
        auth = new AuthToken("12345", "tyler");
        auth2 = new AuthToken("6789", "tyler2");
        Connection conn = db.getConnection();
        System.out.println("attempting to set connection to database");
        aDao = new AuthTokenDAO(conn);
        System.out.println("attempting to clear database");
        aDao.clear();

    }

    @AfterEach
    public void tearDown() {
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException{
        try {
            aDao.insert(auth);
            System.out.println("Starting the insert pass test");
            AuthToken compareTest = aDao.find(auth.getAuthtoken());
            assertNotNull(compareTest);
            assertEquals(auth, compareTest);
        } catch (DataAccessException e) {
            fail(e.getMessage());
            System.out.println("Error occured when inserting into data base");
        }
    }
    @Test
    public void insertFail() throws DataAccessException{
        System.out.println("Starting the insert fail test");
        aDao.insert(auth);
        assertThrows(DataAccessException.class, () -> aDao.insert(auth));
    }
    @Test
    public void findPass(){
        try {
            System.out.println("Starting the find pass test");
            aDao.insert(auth);
            AuthToken compareTest = aDao.find(auth.getAuthtoken());
            assertNotNull(compareTest);
            assertEquals(auth, compareTest);

        } catch (DataAccessException e){
            fail(e.getMessage());
            System.out.println("Error occured with finding in the database for the first value");
        }
        try {
            System.out.println("finding another authtoken in databse");
            aDao.insert(auth2);
            AuthToken compareTest2 = aDao.find(auth2.getAuthtoken());
            assertNotNull(compareTest2);
            assertEquals(auth2, compareTest2);
        } catch (DataAccessException e) {
            fail(e.getMessage());
            System.out.println("Error occured with finding in the database for the second value");
        }
    }

    @Test
    public void findFail() {
        try {
            System.out.println("Starting the find fail test");
            AuthToken compareTest = aDao.find(auth.getAuthtoken());
            assertNull(compareTest);
            aDao.insert(auth);
            AuthToken compareTest2 = aDao.find(auth2.getAuthtoken());
            assertNull(compareTest2);
        } catch (DataAccessException e) {
            fail(e.getMessage());
            System.out.println("The given authtoken was found when it should not have been");
        }
    }

    @Test
    public void clear() throws DataAccessException {
        try {
            System.out.println("Starting the clear test");
            aDao.insert(auth);
            assertNotNull(aDao.find(auth.getAuthtoken()));
            aDao.clear();
            AuthToken compareTest = aDao.find(auth.getAuthtoken());
            assertNull(compareTest);
        } catch (DataAccessException e){
            fail(e.getMessage());
            System.out.println("There was a problem with deleting from the table");
        }
    }
}

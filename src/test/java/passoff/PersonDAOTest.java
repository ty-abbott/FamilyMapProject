package passoff;

import dao.DataAccessException;
import dao.Database;
import dao.PersonDAO;
import models.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PersonDAOTest {
    private Database db;
    private Person person;
    private Person person2;
    private PersonDAO pDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();

        person = new Person("1234", "tymag", "tyler", "abbott", "m",
                "3", "5", "6");
        person2 = new Person("12342", "tymag", "tyler", "abbott", "m",
                "3", "5", "6");
        Connection conn = db.getConnection();
        pDao = new PersonDAO(conn);
        pDao.clear();
    }

    @AfterEach
    public void tearDown() {
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException{
        try {
            pDao.insert(person);
            System.out.println("Starting the insert pass test");
            Person compareTest = pDao.find(person.getPersonID());
            assertNotNull(compareTest);
            assertEquals(person, compareTest);
        } catch (DataAccessException e) {
            fail(e.getMessage());
            System.out.println("Error occured when inserting into data base");
        }
    }
    @Test
    public void insertFail() throws DataAccessException{
        System.out.println("Starting the insert fail test");
        pDao.insert(person);
        assertThrows(DataAccessException.class, () -> pDao.insert(person));
    }
    @Test
    public void findPass(){
        try {
            System.out.println("Starting the find pass test");
            pDao.insert(person);
            Person compareTest = pDao.find(person.getPersonID());
            assertNotNull(compareTest);
            assertEquals(person, compareTest);

        } catch (DataAccessException e){
            fail(e.getMessage());
            System.out.println("Error occured with finding in the database for the first value");
        }
        try {
            System.out.println("finding another user in databse");
            pDao.insert(person2);
            Person compareTest2 = pDao.find(person2.getPersonID());
            assertNotNull(compareTest2);
            assertEquals(person2, compareTest2);
        } catch (DataAccessException e) {
            fail(e.getMessage());
            System.out.println("Error occured with finding in the database for the second value");
        }
    }

    @Test
    public void findFail() {
        try {
            System.out.println("Starting the find fail test");
            Person compareTest = pDao.find(person.getPersonID());
            assertNull(compareTest);
            pDao.insert(person);
            Person compareTest2 = pDao.find(person2.getPersonID());
            assertNull(compareTest2);
        } catch (DataAccessException e) {
            fail(e.getMessage());
            System.out.println("The given user was found when it should not have been");
        }
    }

    @Test
    public void findByUserPass(){
        try{
            pDao.insert(person);
            Person compareTest = pDao.findByUser(person.getPersonID(), person.getAssociatedUsername());
            assertNotNull(compareTest);
            assertEquals(person.getPersonID(), compareTest.getPersonID());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void findByUserFail() throws DataAccessException {
        try {
            Person compareTest = pDao.findByUser(person.getPersonID(), person.getAssociatedUsername());
            assertNull(compareTest);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findFamilySuccess() throws DataAccessException{
        try{
            pDao.insert(person);
            ArrayList<Person> list = pDao.findFamily(person.getAssociatedUsername());
            assertNotNull(list);
            assertEquals(person.getPersonID(), list.get(0).getPersonID());
        } catch (DataAccessException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findFamilyFail() throws DataAccessException{
        try{
            ArrayList<Person> list = pDao.findFamily(person.getAssociatedUsername());
            assertNull(list);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void clear() throws DataAccessException {
        try {
            System.out.println("Starting the clear test");
            pDao.insert(person);
            assertNotNull(pDao.find(person.getPersonID()));
            pDao.clear();
            Person compareTest = pDao.find(person.getPersonID());
            assertNull(compareTest);
        } catch (DataAccessException e){
            fail(e.getMessage());
            System.out.println("There was a problem with deleting from the table");
        }
    }
    @Test
    public void clearEmpty(){
        try{
            pDao.clear();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void clearPerson(){
        try{
            pDao.insert(person);
            pDao.deletePerson(person.getAssociatedUsername());
            Person compareTest = pDao.findByUser(person.getPersonID(), person.getAssociatedUsername());
            assertNull(compareTest);
        } catch (SQLException | DataAccessException throwables) {
            throwables.printStackTrace();
        }
    }
    @Test
    public void clearPersonEmpty(){
        try{
            pDao.deletePerson(person.getAssociatedUsername());
        } catch (SQLException | DataAccessException throwables) {
            throwables.printStackTrace();
        }
    }

}


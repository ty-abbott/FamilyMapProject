package services;

import dao.*;
import helpers.GenerateID;
import helpers.loadJSON;
import models.Event;
import models.Location;
import models.Person;
import models.User;
import responses.defaultResponse;
import helpers.GenerateData;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

/**
 * username - the username that we will be generating the generations for
 * numGenerations - how many total generations to generate
 */
public class fillService {
    int generations;
    String username;

    public fillService( String username, int generations) {
        this.username = username;
        this.generations = generations;
    }

    public fillService(String username) {
        this.username = username;
        this.generations = 4;
    }

    /**
     *
     *creates generations of data in the database for the specified user.
     * @return a default response of whether there was success or not and the message of problem or success
     */
    public defaultResponse fill() throws DataAccessException, FileNotFoundException, SQLException {
        //fills the generations with specified number of generations of people
        Database db = new Database();
        Connection conn = db.getConnection();
        Random random = new Random();
        UserDAO uDAO = new UserDAO(conn);
        PersonDAO pDAO = new PersonDAO(conn);
        EventDAO eDAO = new EventDAO(conn);
        GenerateID idObj = new GenerateID();
        loadJSON location = new loadJSON();
        location.load();
        Location area = location.locData.getData()[random.nextInt(100)];
        User user;

        user = uDAO.find(username);
        if(user == null) {
            defaultResponse resp = new defaultResponse("Error: there is no user in the database with this username.",false);
            return resp;
        }

        else{
            pDAO.deletePerson(username);
            eDAO.clearEvents(username);
            Person person = new Person(idObj.getID(),user.getUsername(), user.getFirstname(), user.getLastname(),
                    user.getGender());
            Event birthEvent = new Event(idObj.getID(), username, person.getPersonID(), area.getLatitude(), area.getLongitude()
            , area.getCountry(), area.getCity(), "Birth", 1997);
            eDAO.insert(birthEvent);
            try {
                GenerateData generate = new GenerateData();
                generate.generatePerson(person, generations, 1997);
                double numGen = Math.pow(2, 4);
                int numEvents = (int) (numGen*3-2);
                defaultResponse resp = new defaultResponse("Successfullly added " + numGen + " and " + numEvents + " events to the database", true)
            }catch(DataAccessException | FileNotFoundException e){
                e.printStackTrace();
                defaultResponse resp = new defaultResponse("There was a problem generating data", false);
            }

        }
        //TODO: create functionality for fill. Check user, delete information for user in db
        //TODO: create person for username. call the helper function to populate the database with data for user


        return null;
    }


}

package services;

import dao.*;
import helpers.GenerateID;
import helpers.LoadJSON;
import models.Event;
import models.Location;
import models.Person;
import models.User;
import responses.DefaultResponse;
import helpers.GenerateData;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

/**
 * username - the username that we will be generating the generations for
 * numGenerations - how many total generations to generate
 */
public class FillService {
    int generations;
    String username;

    public FillService(String username, int generations) {
        this.username = username;
        this.generations = generations;
    }

    public FillService(String username) {
        this.username = username;
        this.generations = 4;
    }

    /**
     *
     *creates generations of data in the database for the specified user.
     * @return a default response of whether there was success or not and the message of problem or success
     */
    public DefaultResponse fill() throws DataAccessException, FileNotFoundException, SQLException {
        //fills the generations with specified number of generations of people
        Database db = new Database();
        Connection conn = db.getConnection();
        Random random = new Random();
        UserDAO uDAO = new UserDAO(conn);
        PersonDAO pDAO = new PersonDAO(conn);
        EventDAO eDAO = new EventDAO(conn);
        GenerateID idObj = new GenerateID();
        LoadJSON location = new LoadJSON();
        location.load();
        Location area = location.locData.getData()[random.nextInt(100)];
        User user;

        user = uDAO.find(username);
        //if there is no user we respond with this error
        if(user == null) {
            DefaultResponse resp = new DefaultResponse("Error: there is no user in the database with this username.",false);
            db.closeConnection(false);
            return resp;
        }

        else{
            //deletes associated people and events from the database
            pDAO.deletePerson(username);
            eDAO.clearEvents(username);
            //create the person for the user and birth event and insert it into the database.
            Person person = new Person(idObj.getID(),user.getUsername(), user.getFirstName(), user.getLastName(),
                    user.getGender());
            Event birthEvent = new Event(idObj.getID(), username, person.getPersonID(), area.getLatitude(), area.getLongitude()
            , area.getCountry(), area.getCity(), "Birth", 1997);
            eDAO.insert(birthEvent);
            db.closeConnection(true);
            try {
                System.out.println("going to create the data for fill");
                GenerateData generate = new GenerateData();
                generate.generatePerson(person, generations, 1997);
                Double numGen = Math.pow(2, generations + 1) - 1;
                int integer = numGen.intValue();
                int numEvents = (int) (numGen*3-2);
                DefaultResponse resp = new DefaultResponse("Successfully added " + integer + " persons and " + numEvents + " events to the database.", true);
                return resp;
            }catch(DataAccessException | FileNotFoundException e){
                e.printStackTrace();
                DefaultResponse resp = new DefaultResponse("There was a problem generating data", false);
            }

        }
        //TODO: create functionality for fill. Check user, delete information for user in db
        //TODO: create person for username. call the helper function to populate the database with data for user


        return null;
    }


}

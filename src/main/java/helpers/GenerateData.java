package helpers;
import dao.*;
import models.Person;
import models.Event;
import models.Location;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.Random;

public class GenerateData {
    private final LoadJSON familyData = new LoadJSON();
    private final GenerateID id = new GenerateID();
    private final int LOC_RANDOM = 100;
    private final int NAME_RANDOM = 143;
    private Location areaData;
    Database db = new Database();


    public GenerateData() {
    }
    //this is where we create the people and event objects that are related to the person.
    public void generatePerson(Person person, int generations, int baseYear) throws DataAccessException, FileNotFoundException {
        //a person object is passed in. this is key to recursion, it is the person in the family tree we are currently working on
        // generations is which generation we are currently on. It is decreased each round through.
        familyData.load();
        Connection conn = db.getConnection();
        Random random = new Random();
        String motherID = id.getID();
        String fatherID = id.getID();
        //generating ids for father and mother
        String surname = familyData.surnames.getData()[NAME_RANDOM];
        if(generations > 0) {
            person.setMotherID(motherID);
            person.setFatherID(fatherID);
        }//as long as generation is not 0 then we will add the ids for mother and father to the object
        baseYear = baseYear - 20;
        //making realistic years for birth
        PersonDAO personObj = new PersonDAO(conn);
        personObj.insert(person);//inserting the person into the database
        db.closeConnection(true);
        //creating the mother and father objects
        Person mother = new Person(motherID, person.getAssociatedUsername(), familyData.femaleNames.getData()[random.nextInt(NAME_RANDOM)],
                surname, "female");
        Person father = new Person(fatherID, person.getAssociatedUsername(), familyData.maleNames.getData()[random.nextInt(NAME_RANDOM)],
                surname, "male");
        //spouse ID for mother and father
        mother.setSpouseID(fatherID);
        father.setSpouseID(motherID);
        //creating birth events for mother and father
        String fatherEventID = id.getID();
        String motherEventID = id.getID();
        areaData = familyData.locData.getData()[random.nextInt(100)];

        Event motherBirthEvent = new Event(motherEventID, person.getAssociatedUsername(), motherID, areaData.getLatitude(),
                areaData.getLongitude(), areaData.getCountry(), areaData.getCity(), "Birth", baseYear);
        Event fatherBirthEvent = new Event(fatherEventID, person.getAssociatedUsername(), fatherID, areaData.getLatitude(),
                areaData.getLongitude(), areaData.getCountry(), areaData.getCity(), "Birth", baseYear);



        String motherMarriageID = id.getID();
        String fatherMarriageID = id.getID();
        //creating the marriage event for mom and dad
        Event motherMarriageEvent = new Event(motherMarriageID, person.getAssociatedUsername(), motherID, areaData.getLatitude(),
                areaData.getLongitude(), areaData.getCountry(), areaData.getCity(), "Marriage", baseYear+19);
        Event fatherMarriageEvent = new Event(fatherMarriageID, person.getAssociatedUsername(), fatherID, areaData.getLatitude(),
                areaData.getLongitude(), areaData.getCountry(), areaData.getCity(), "Marriage", baseYear+19);

        String motherDeathID = id.getID();
        String fatherDeathID = id.getID();
        //death event for mother and father
        Event motherDeathEvent = new Event(motherDeathID, person.getAssociatedUsername(), motherID, areaData.getLatitude(),
                areaData.getLongitude(), areaData.getCountry(), areaData.getCity(), "Death", baseYear+40);
        Event fatherDeathEvent = new Event(fatherDeathID, person.getAssociatedUsername(), fatherID, areaData.getLatitude(),
                areaData.getLongitude(), areaData.getCountry(), areaData.getCity(), "Death", baseYear+40);

        //inserting the events for the parents in the database.
        conn = db.getConnection();
        EventDAO eventObj = new EventDAO(conn);
        eventObj.insert(motherBirthEvent);
        eventObj.insert(fatherBirthEvent);
        eventObj.insert(motherMarriageEvent);
        eventObj.insert(fatherMarriageEvent);
        eventObj.insert(motherDeathEvent);
        eventObj.insert(fatherDeathEvent);
        db.closeConnection(true);
        //recursively call the function on each of the parents to generate their information and then keep the family tree going
        if(generations > 0){
            generatePerson(mother, generations-1, baseYear);
            generatePerson(father, generations-1, baseYear);
        }
    }

    public void familyTree() {

    }
}

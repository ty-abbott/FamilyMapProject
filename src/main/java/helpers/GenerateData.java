package helpers;
import dao.*;
import models.Person;
import models.Event;
import models.Location;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.Random;

public class GenerateData {
    private final loadJSON familyData = new loadJSON();
    private final GenerateID id = new GenerateID();
    private final int LOC_RANDOM = 100;
    private final int NAME_RANDOM = 143;
    private Location areaData;
    Database db = new Database();


    public GenerateData() {
    }

    public void generatePerson(Person person, int generations, int baseYear) throws DataAccessException, FileNotFoundException {
        familyData.load();
        Connection conn = db.getConnection();
        Random random = new Random();
        String motherID = id.getID();
        String fatherID = id.getID();
        String surname = familyData.surnames.getData()[NAME_RANDOM];
        person.setMotherID(motherID);
        person.setFatherID(fatherID);
        baseYear = baseYear - 20;

        if(person.getMotherID()!=null && person.getFatherID()!=null){
            PersonDAO personObj = new PersonDAO(conn);
            personObj.insert(person);
            db.closeConnection(true);
        }
        Person mother = new Person(motherID, person.getAssociatedUsername(), familyData.femaleNames.getData()[random.nextInt(NAME_RANDOM)],
                surname, "female");
        Person father = new Person(fatherID, person.getAssociatedUsername(), familyData.maleNames.getData()[random.nextInt(NAME_RANDOM)],
                surname, "male");
        mother.setSpouseID(fatherID);
        father.setSpouseID(motherID);

        String fatherEventID = id.getID();
        String motherEventID = id.getID();
        areaData = familyData.locData.getData()[random.nextInt(100)];

        Event motherBirthEvent = new Event(motherEventID, person.getAssociatedUsername(), motherID, areaData.getLatitude(),
                areaData.getLongitude(), areaData.getCountry(), areaData.getCity(), "Birth", baseYear);
        Event fatherBirthEvent = new Event(fatherEventID, person.getAssociatedUsername(), fatherID, areaData.getLatitude(),
                areaData.getLongitude(), areaData.getCountry(), areaData.getCity(), "Birth", baseYear);


//generate child
        String motherMarriageID = id.getID();
        String fatherMarriageID = id.getID();

        Event motherMarriageEvent = new Event(motherMarriageID, person.getAssociatedUsername(), motherID, areaData.getLatitude(),
                areaData.getLongitude(), areaData.getCountry(), areaData.getCity(), "Marriage", baseYear+19);
        Event fatherMarriageEvent = new Event(fatherMarriageID, person.getAssociatedUsername(), fatherID, areaData.getLatitude(),
                areaData.getLongitude(), areaData.getCountry(), areaData.getCity(), "Marriage", baseYear+19);

        String motherDeathID = id.getID();
        String fatherDeathID = id.getID();

        Event motherDeathEvent = new Event(motherDeathID, person.getAssociatedUsername(), motherID, areaData.getLatitude(),
                areaData.getLongitude(), areaData.getCountry(), areaData.getCity(), "Death", baseYear+40);
        Event fatherDeathEvent = new Event(fatherDeathID, person.getAssociatedUsername(), fatherID, areaData.getLatitude(),
                areaData.getLongitude(), areaData.getCountry(), areaData.getCity(), "Death", baseYear+40);


        conn = db.getConnection();
        EventDAO eventObj = new EventDAO(conn);
        eventObj.insert(motherBirthEvent);
        eventObj.insert(fatherBirthEvent);
        eventObj.insert(motherMarriageEvent);
        eventObj.insert(fatherMarriageEvent);
        eventObj.insert(motherDeathEvent);
        eventObj.insert(fatherDeathEvent);
        db.closeConnection(true);

        if(generations > 0){
            generatePerson(mother, generations-1, baseYear);
            generatePerson(father, generations-1, baseYear);
        }
    }

    public void familyTree() {

    }
}

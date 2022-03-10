package helpers;

import models.LocationData;
import models.NameData;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import com.google.gson.*;


public class LoadJSON {
    Gson gson = new Gson();
    public LocationData locData;
    public NameData femaleNames;
    public NameData maleNames;
    public NameData surnames;
    public void load() throws FileNotFoundException {
        Reader reader = new FileReader("json/locations.json");
        locData = (LocationData)gson.fromJson(reader, LocationData.class);
        System.out.println(locData.getData()[2].getLatitude());
        System.out.println(locData.getData()[2].getLongitude());

        Reader reader2 = new FileReader("json/fnames.json");
        femaleNames = (NameData)gson.fromJson(reader2, NameData.class);
        System.out.println(femaleNames.getData()[1]);

        Reader reader3 = new FileReader("json/mnames.json");
        maleNames = (NameData)gson.fromJson(reader3, NameData.class);
        System.out.println(maleNames.getData()[2]);

        Reader reader4 = new FileReader("json/snames.json");
        surnames = (NameData) gson.fromJson(reader4, NameData.class);
        System.out.println(surnames.getData()[3]);
    }
}

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
        //this loads the json into arrays of objects to be then used with family tree generation
        Reader reader = new FileReader("json/locations.json");
        locData = (LocationData)gson.fromJson(reader, LocationData.class);


        Reader reader2 = new FileReader("json/fnames.json");
        femaleNames = (NameData)gson.fromJson(reader2, NameData.class);


        Reader reader3 = new FileReader("json/mnames.json");
        maleNames = (NameData)gson.fromJson(reader3, NameData.class);


        Reader reader4 = new FileReader("json/snames.json");
        surnames = (NameData) gson.fromJson(reader4, NameData.class);

    }
}

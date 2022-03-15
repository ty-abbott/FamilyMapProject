package helpers;

import java.util.UUID;

public class GenerateID {
    public String getID(){
        //create an ID by random string
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}


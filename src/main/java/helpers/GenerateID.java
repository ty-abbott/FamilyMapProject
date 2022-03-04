package helpers;

import java.util.UUID;

public class GenerateID {
    public String getID(){

        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}


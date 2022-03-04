package helpers;

import java.util.UUID;

/**
 * this is a helper class to create and return an authtoken.
 */
public class authToken {

    public String getAuthToken() {

        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}

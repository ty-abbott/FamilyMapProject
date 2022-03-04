package models;

import java.util.Objects;

/**
 * this is the model for a row in the database of authtokens
 */
public class AuthToken {
    /**
     * String authToken - this is the authtoken that is found on the particular row of the database
     * String username - this is the username that is found on the particular row of the database
     */
    private String authtoken;
    private String username;

    public AuthToken(String authtoken, String username) {
        this.authtoken = authtoken;
        this.username = username;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthToken token = (AuthToken) o;
        return Objects.equals(authtoken, token.getAuthtoken()) && Objects.equals(username, token.getUsername());
    }
}

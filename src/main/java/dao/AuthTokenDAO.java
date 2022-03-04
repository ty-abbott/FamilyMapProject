package dao;

import models.AuthToken;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * this is a class that works with the auth token table of the database.
 */
public class AuthTokenDAO {
    private Connection conn;
    public AuthTokenDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * returns the auth token of the specific user.
     * @param token - the information that will get the token and username
     * @return - the model of the authtoken, else null
     */
    public AuthToken find(String token)throws DataAccessException {
        AuthToken authToken;
        ResultSet rs;
        String sql = "SELECT * FROM Authtoken WHERE authtoken = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, token);
            rs = stmt.executeQuery();
            if(rs.next()){
                authToken = new AuthToken(rs.getString("authtoken"), rs.getString("username"));
                return authToken;
            }
            else{
                return null;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DataAccessException("Error encountered when finding record from authtoken table");
        }
    }

    /**
     * inserts a new authtoken into the database
     * @param token - information of the token

     */
    public void insert(AuthToken token) throws DataAccessException{
        String sql = "Insert INTO Authtoken (authtoken, username) VALUES(?,?)";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, token.getAuthtoken());
            stmt.setString(2, token.getUsername());

            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DataAccessException("Error encountered inserting into token into the table");
        }
    }

    /**
     * deletes the authtoken table of the database.
     *
     */
    public void clear() throws DataAccessException {
        String sql = "DELETE FROM Authtoken";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DataAccessException("Error encountered when deleting from Authtoken table");
        }
    }
}

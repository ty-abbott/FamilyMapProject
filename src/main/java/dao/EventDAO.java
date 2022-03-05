package dao;
import models.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * this class is used to interact with the event table of the database
 */
public class EventDAO{

    private final Connection conn;

    public EventDAO(Connection conn) {
        this.conn = conn;
    }
    /**
     * inserts a particular event into the database
     * @param body - the information needed to insert an event in the database.
     * @return - returns an event model or null if it did not work.
     */
    public void insert(Event body) throws DataAccessException{
        //We can structure our string to be similar to a sql command, but if we insert question
        //marks we can change them later with help from the statement
        String sql = "INSERT INTO Events (EventID, AssociatedUsername, PersonID, Latitude, Longitude, " +
                "Country, City, EventType, Year) VALUES(?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, body.getEventID());
            stmt.setString(2, body.getAssociatedUsername());
            stmt.setString(3, body.getPersonID());
            stmt.setFloat(4, body.getLatitude());
            stmt.setFloat(5, body.getLongitude());
            stmt.setString(6, body.getCountry());
            stmt.setString(7, body.getCity());
            stmt.setString(8, body.getEventType());
            stmt.setInt(9, body.getYear());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting an event into the database");
        }

    }

    /**
     * finds and returns a specific event.
     * @param eventID - contains the event id string
     * @return - returns a model of the event if found and null if not found.
     */
    public Event find(String eventID) throws DataAccessException{
        Event event;
        ResultSet rs;
        String sql = "SELECT * FROM Events WHERE EventID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, eventID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                event = new Event(rs.getString("EventID"), rs.getString("AssociatedUsername"),
                        rs.getString("PersonID"), rs.getFloat("Latitude"), rs.getFloat("Longitude"),
                        rs.getString("Country"), rs.getString("City"), rs.getString("EventType"),
                        rs.getInt("Year"));
                return event;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding an event in the database");
        }
    }

    /**
     * returns all the events of all the family members of a person.
     *
     * @param body - contains the data that will be used to get the events of the family members.
     * @return - returns an array of models of the events. If it doesnt work then return null.
     */
    public Event[] findAll(Event body) {return null;}

    /**
     * deletes the table of events from the database.
     *
     * @return - true if it works, false if it doesn't.
     */
    public void clear() throws DataAccessException{
        String sql = "DELETE FROM Events";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the event table");
        }
    }

    public void clearEvents(String username) throws DataAccessException, SQLException {

        String sql = "DELETE FROM Events WHERE AssociatedUsername = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, username);
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered deleting user from datbase");
        }
    }
}

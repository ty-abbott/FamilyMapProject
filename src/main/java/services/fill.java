package services;

import responses.defaultResponse;

/**
 * username - the username that we will be generating the generations for
 * numGenerations - how many total generations to generate
 */
public class fill {
    private String username;
    private int numGenerations;

    /**
     *
     *creates generations of data in the database for the specified user.
     * @return a default response of whether there was success or not and the message of problem or success
     */
    public defaultResponse fill(){
        //fills the generations with specified number of generations of people
        return null;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNumGenerations() {
        return numGenerations;
    }

    public void setNumGenerations(int numGenerations) {
        this.numGenerations = numGenerations;
    }
}

package com.efemsepci.autocratvideogame.classes;

import java.sql.Date;

public class GameSession {
    private int id;
    private  int numOfPlayers;
    private String name;
    private Date startTime;
    private Date endTime;

    public GameSession(String name, Date startTime, Date endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        numOfPlayers = 0;
    }

    //for listView
    public GameSession(int id, String name, int numOfPlayers){
        this.id = id;
        this.name = name;
        this.numOfPlayers = numOfPlayers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return getName() + "\t\t\t\t\t\t" + getNumOfPlayers() + "/4";
    }
}

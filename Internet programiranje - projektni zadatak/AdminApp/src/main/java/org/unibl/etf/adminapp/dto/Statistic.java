package org.unibl.etf.adminapp.dto;

import java.sql.Timestamp;

public class Statistic {
    private int id;
    private String clientUsername;
    private String whatIsHappened;
    private int returnedId;
    private Timestamp date;


    public Statistic() {
    }

    public Statistic(int id, String clientUsername, String whatIsHappened, int returnedId, Timestamp date) {
        this.id = id;
        this.clientUsername = clientUsername;
        this.whatIsHappened = whatIsHappened;
        this.returnedId = returnedId;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public String getWhatIsHappened() {
        return whatIsHappened;
    }

    public void setWhatIsHappened(String whatIsHappened) {
        this.whatIsHappened = whatIsHappened;
    }

    public int getReturnedId() {
        return returnedId;
    }

    public void setReturnedId(int returnedId) {
        this.returnedId = returnedId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}

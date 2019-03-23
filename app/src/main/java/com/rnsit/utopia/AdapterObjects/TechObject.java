package com.rnsit.utopia.AdapterObjects;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TechObject implements Serializable{
    String eventName,firstName,secondName,thirdName,firstTeam,secondTeam,thirdTeam;
    long timeStamp;
    public TechObject (long timeStamp,String eventName,String firstName,String secondName,String thirdName,String firstTeam,String secondTeam,String thirdTeam){
        this.timeStamp = timeStamp;
        this.eventName = eventName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.thirdName = thirdName;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.thirdTeam = thirdTeam;
    }
    public TechObject(){}

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(String firstTeam) {
        this.firstTeam = firstTeam;
    }

    public String getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(String secondTeam) {
        this.secondTeam = secondTeam;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getThirdTeam() {
        return thirdTeam;
    }

    public void setThirdTeam(String thirdTeam) {
        this.thirdTeam = thirdTeam;
    }
}
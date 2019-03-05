package com.rnsit.utopia.AdapterObjects;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CFLObject implements Serializable{
    String eventName,firstName,secondName,firstTeam,secondTeam;
    public CFLObject (String eventName,String firstName,String secondName,String firstTeam,String secondTeam){
        this.eventName = eventName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }
    public CFLObject(){}

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
}
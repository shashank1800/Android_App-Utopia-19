package com.rnsit.utopia.AdapterObjects;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CoordinatorObject implements Serializable{
    String eventName,boyName,girlName,boyPhone,girlPhone;
    int boyPhoto,girlPhoto;
    public CoordinatorObject (String eventName,int boyPhoto,String boyName,String boyPhone,int girlPhoto,String girlName,String girlPhone){
        this.eventName = eventName;
        this.boyPhoto = boyPhoto;
        this.boyName = boyName;
        this.boyPhone = boyPhone;
        this.girlPhoto = girlPhoto;
        this.girlName = girlName;
        this.girlPhone = girlPhone;
    }
    public CoordinatorObject(){}

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getBoyName() {
        return boyName;
    }

    public void setBoyName(String boyName) {
        this.boyName = boyName;
    }

    public String getGirlName() {
        return girlName;
    }

    public void setGirlName(String girlName) {
        this.girlName = girlName;
    }

    public String getBoyPhone() {
        return boyPhone;
    }

    public void setBoyPhone(String boyPhone) {
        this.boyPhone = boyPhone;
    }

    public String getGirlPhone() {
        return girlPhone;
    }

    public void setGirlPhone(String girlPhone) {
        this.girlPhone = girlPhone;
    }

    public int getBoyPhoto() {
        return boyPhoto;
    }

    public void setBoyPhoto(int boyPhoto) {
        this.boyPhoto = boyPhoto;
    }

    public int getGirlPhoto() {
        return girlPhoto;
    }

    public void setGirlPhoto(int girlPhoto) {
        this.girlPhoto = girlPhoto;
    }
}

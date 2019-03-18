package com.rnsit.utopia.AdapterObjects;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RecentPostObject implements Serializable{
    String imageRecentPostURL;
    long timeStamp;
    public RecentPostObject (long timeStamp,String imageRecentPostURL){
        this.timeStamp = timeStamp;
        this.imageRecentPostURL = imageRecentPostURL;
    }
    public RecentPostObject(){}

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getImagePostURL() {
        return imageRecentPostURL;
    }

    public void setImagePostURL(String imagePostURL) {
        this.imageRecentPostURL = imagePostURL;
    }
}

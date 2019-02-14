package com.rnsit.utopia;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PostViewObject implements Serializable{
    String postDetail,imagePostURL;
    public PostViewObject (String postDetail,String imagePostURL){
        this.postDetail = postDetail;
        this.imagePostURL = imagePostURL;
    }
    public PostViewObject(){}

    public String getPostDetail() {
        return postDetail;
    }

    public void setPostDetail(String postDetail) {
        this.postDetail = postDetail;
    }

    public String getImagePostURL() {
        return imagePostURL;
    }

    public void setImagePostURL(String imagePostURL) {
        this.imagePostURL = imagePostURL;
    }
}

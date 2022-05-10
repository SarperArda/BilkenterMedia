package com.thenameless.bilkenter_media.BlogPart;

import com.google.firebase.firestore.FieldValue;

import java.io.Serializable;

public class Blog implements Serializable {
    public String user;
    public String blog;
    //public FieldValue date;
    public String blogName;

    public Blog(String blogName,String user, String blog) {
        this.blogName = blogName;
        this.user = user;
        this.blog = blog;
        //this.date = date;
    }
}


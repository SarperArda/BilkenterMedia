package com.thenameless.bilkenter_media;

import java.io.Serializable;

public class Blog implements Serializable {
    public String blogName;
    public Blog(String blogName) {
        this.blogName = blogName;
    }
}


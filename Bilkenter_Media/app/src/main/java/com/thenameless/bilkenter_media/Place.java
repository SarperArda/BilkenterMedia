package com.thenameless.bilkenter_media;

import java.io.Serializable;

public class Place implements Serializable {
    public String placeName;
    public Place(String placeName) {
        this.placeName = placeName;
    }
}

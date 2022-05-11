package com.thenameless.bilkenter_media.PlacePart;


import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.io.Serializable;
public class Place implements Serializable {
    public String placeName;
    public String openingTime;
    public String closingTime;
    public int image;
    public double enlem;
    public double boylam;

    public Place (String placeName, String openingTime, String closingTime, int image, double enlem, double boylam) {
        this.placeName = placeName;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.image = image;
        this.enlem = enlem;
        this.boylam = boylam;
    }


}

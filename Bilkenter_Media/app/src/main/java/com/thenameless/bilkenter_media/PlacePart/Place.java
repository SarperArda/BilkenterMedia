package com.thenameless.bilkenter_media.PlacePart;


import java.util.ArrayList;
import java.io.Serializable;
public class Place implements Serializable {
    public String placeName;
    public String openingTime;
    public String closingTime;
    public int image;

    public Place (String placeName, String openingTime, String closingTime, int image) {
        this.placeName = placeName;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.image = image;
    }
    /**
    public double getAverageRank(){
        int sum = 0;
        for(Rank rank: ranks ){
            sum = sum + rank.getRank();
        }
        return (sum/ranks.size());
    }


    public ArrayList<Rank> getRanks() {
        return ranks;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
     */
}

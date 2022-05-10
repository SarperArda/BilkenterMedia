package com.thenameless.bilkenter_media;


import java.util.ArrayList;
import java.io.Serializable;
public class Place implements Serializable {
    public String placeName;
    public String openingTime;
    public String closingTime;
    public ArrayList<Rank> ranks;
    public ArrayList<Comment> comments;

    public Place(String placeName, String openingTime, String closingTime, ArrayList<Rank> ranks, ArrayList<Comment> comments) {
        this.placeName = placeName;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.ranks = ranks;
        this.comments = comments;
    }
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
}

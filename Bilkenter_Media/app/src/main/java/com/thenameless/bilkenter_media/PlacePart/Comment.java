package com.thenameless.bilkenter_media.PlacePart;

public class Comment {
    public String comment;
    public float rank;

    public Comment(String comment, float rank) {
        this.rank = rank;
        this.comment = comment;
    }
    public float getRank() {
        return rank;
    }
}

package com.sallyjayz.gads2020leadershipboard.model;

import com.google.gson.annotations.SerializedName;

public class LearnerSkill {

    private String name;
    private int score;
    private String country;
    @SerializedName("badgeUrl")
    private String url;

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getCountry() {
        return country;
    }

    public String getUrl() {
        return url;
    }
}

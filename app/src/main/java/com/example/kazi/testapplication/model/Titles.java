package com.example.kazi.testapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Titles {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("wins")
    @Expose
    private Integer wins;
    @SerializedName("statid")
    @Expose
    private Integer statid;
    @SerializedName("losses")
    @Expose
    private Integer losses;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("weight_class")
    @Expose
    private String weightClass;
    @SerializedName("title_holder")
    @Expose
    private Boolean titleHolder;
    @SerializedName("draws")
    @Expose
    private Integer draws;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("fighter_status")
    @Expose
    private String fighterStatus;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("belt_thumbnail")
    @Expose
    private String beltThumbnail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getStatid() {
        return statid;
    }

    public void setStatid(Integer statid) {
        this.statid = statid;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getWeightClass() {
        return weightClass;
    }

    public void setWeightClass(String weightClass) {
        this.weightClass = weightClass;
    }

    public Boolean getTitleHolder() {
        return titleHolder;
    }

    public void setTitleHolder(Boolean titleHolder) {
        this.titleHolder = titleHolder;
    }

    public Integer getDraws() {
        return draws;
    }

    public void setDraws(Integer draws) {
        this.draws = draws;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFighterStatus() {
        return fighterStatus;
    }

    public void setFighterStatus(String fighterStatus) {
        this.fighterStatus = fighterStatus;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getBeltThumbnail() {
        return beltThumbnail;
    }

    public void setBeltThumbnail(String beltThumbnail) {
        this.beltThumbnail = beltThumbnail;
    }

}
package com.ltmb.fitness.uimodel;

public class RankingPersonUiModel {
    String id;
    String avt;
    String name;
    long experience;

    public RankingPersonUiModel() {
    }

    public RankingPersonUiModel(String id, String avt, String name, long experience) {
        this.id = id;
        this.avt = avt;
        this.name = name;
        this.experience = experience;
    }

    public String getId() { return this.id; }
    public void setId(String id) {
        this.id = id;
    }

    public String getAvt() {
        return avt;
    }

    public void setAvt(String avt) {
        this.avt = avt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getExperience() {
        return experience;
    }

    public void setExperience(long experience) {
        this.experience = experience;
    }
}

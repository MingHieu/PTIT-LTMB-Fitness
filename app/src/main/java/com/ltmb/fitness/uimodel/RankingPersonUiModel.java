package com.ltmb.fitness.uimodel;

public class RankingPersonUiModel {
    String avt;
    String name;
    int experience;

    public RankingPersonUiModel() {
    }

    public RankingPersonUiModel(String avt, String name, int experience) {
        this.avt = avt;
        this.name = name;
        this.experience = experience;
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

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}

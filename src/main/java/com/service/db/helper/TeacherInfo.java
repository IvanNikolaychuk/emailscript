package com.service.db.helper;

import java.util.List;

public class TeacherInfo {
    private int id;
    private String name;
    private int japLevel;
    private int japJessLevel;
    private String studyMajor;
    private String university;
    private String occupation;
    private double avgScore;
    private List<String> curriculum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(List<String> curriculum) {
        this.curriculum = curriculum;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getJapLevel() {
        return japLevel;
    }

    public void setJapLevel(int japLevel) {
        this.japLevel = japLevel;
    }

    public int getJapJessLevel() {
        return japJessLevel;
    }

    public void setJapJessLevel(int japJessLevel) {
        this.japJessLevel = japJessLevel;
    }

    public String getStudyMajor() {
        return studyMajor;
    }

    public void setStudyMajor(String studyMajor) {
        this.studyMajor = studyMajor;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}

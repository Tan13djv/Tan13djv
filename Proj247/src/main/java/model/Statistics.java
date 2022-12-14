package model;

import enums.StudyProfile;

import java.util.OptionalDouble;

public class Statistics {
    String mainProfile;
    double avgExamScore;
    int stdCount;
    int univCount;
    String uniName;

    public Statistics(String mainProfile, double avgExamScore, int stdCount, int univCount, String uniName) {
        this.mainProfile = mainProfile;
        this.avgExamScore = avgExamScore;
        this.stdCount = stdCount;
        this.univCount = univCount;
        this.uniName = uniName;
    }

    public String getMainProfile() {
        return mainProfile;
    }

    public double getAvgExamScore() {
        return avgExamScore;
    }

    public int getStdCount() {
        return stdCount;
    }

    public int getUnivCount() {
        return univCount;
    }

    public String getUniName() {
        return uniName;
    }

    public void setMainProfile(String mainProfile) {
        this.mainProfile = mainProfile;
    }

    public void setAvgExamScore(double avgExamScore) {
        this.avgExamScore = avgExamScore;
    }

    public void setStdCount(int stdCount) {
        this.stdCount = stdCount;
    }

    public void setUnivCount(int univCount) {
        this.univCount = univCount;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }
}

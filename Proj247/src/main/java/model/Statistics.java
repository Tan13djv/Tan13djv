package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import enums.StudyProfile;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.beans.Transient;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.OptionalDouble;

@XmlAccessorType(XmlAccessType.FIELD)
public class Statistics {
    @SerializedName(value = "universityProfile")
    @Expose(serialize = true,deserialize = true)
    @XmlElement(name = "universityProfile")
    StudyProfile mainProfile;
    //String mainProfile;
    @SerializedName(value = "avgScore")
    @Expose(serialize = true,deserialize = true)
    @XmlElement(name = "avgScore")
    double avgExamScore;
    @Expose(serialize = false,deserialize = true)
    @XmlTransient
    int stdCount;
    @Expose(serialize = false,deserialize = true)
    @XmlTransient
    int univCount;
    @Expose(serialize = false,deserialize = true)
    @XmlTransient
    String uniName;

    public Statistics(StudyProfile mainProfile, double avgExamScore, int stdCount, int univCount, String uniName) {
        this.mainProfile = mainProfile;
        this.avgExamScore = new BigDecimal(avgExamScore,new MathContext(3, RoundingMode.HALF_UP)).doubleValue();
        this.stdCount = stdCount;
        this.univCount = univCount;
        this.uniName = uniName;
    }

    public StudyProfile getMainProfile() {
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

    public void setMainProfile(StudyProfile mainProfile) {
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

package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import enums.StudyProfile;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
public class Student  {

    //@SerializedName(value = "ФИО")
    @SerializedName(value = "studentName")
    @Expose(serialize = true,deserialize = true)
    @XmlElement(name = "studentName",required = true)
    String fullName;
    //@SerializedName(value = "Код УЗ")
    @SerializedName(value = "universityId")
    @Expose(serialize = true,deserialize = true)
    @XmlElement(name = "universityId",required = true)
    String universityId;
    //@SerializedName(value = "Курс обучения")
    @Expose(serialize = false,deserialize = true)
    @XmlTransient
    int currentCourseNumber;
    //@SerializedName(value = "Средний балл")
    @SerializedName(value = "avgScore")
    @Expose(serialize = true,deserialize = true)
    @XmlElement(name = "avgScore",required = true)
    float avgExamScore;
    public Student(String universityId,String fullName, int currentCourseNumber,float avgExamScore) {
        this.setFullName(fullName);
        this.setUniversityId(universityId);
        this.setCurrentCourseNumber(currentCourseNumber);
        this.setAvgExamScore(avgExamScore);
    }

    public Student() {
    }


    public String getFullName() {
        return fullName;
    }

    public String getUniversityId() {
        return universityId;
    }

    public int getCurrentCourseNumber() {
        return currentCourseNumber;
    }

    public float getAvgExamScore() {
        return avgExamScore;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId;
    }

    public void setCurrentCourseNumber(int currentCourseNumber) {
        this.currentCourseNumber = currentCourseNumber;
    }

    public void setAvgExamScore(float avgExamScore) {
        this.avgExamScore = avgExamScore;
    }


    @Override
    public String toString() {
        return "Student{" +
                "fullName='" + fullName + '\'' +
                ", universityId='" + universityId + '\'' +
                ", currentCourseNumber=" + currentCourseNumber +
                ", avgExamScore=" + avgExamScore +
                '}';
    }
    public StudyProfile getStudProf(Student Stud, ArrayList<University> Univs){
        StudyProfile profile;
        University uni;
        uni = Univs.stream().filter((s)->s.getId() == Stud.getUniversityId()).findFirst().get();
        profile = uni.getMainProfile();
        return profile;
    }
 }

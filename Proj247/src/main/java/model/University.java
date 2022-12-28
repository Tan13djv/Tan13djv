package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import enums.StudyProfile;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class University {
    //@SerializedName(value = "Код УЗ")
    @SerializedName(value = "universityId")
    @Expose(serialize = true,deserialize = true)
    @XmlElement(name = "universityId", required = true)
    String id;
    //@SerializedName(value = "Полное название")
    @SerializedName(value = "universityName")
    @Expose(serialize = true,deserialize = true)
    @XmlElement(name = "universityName",required = true)
    String fullName;
    //@SerializedName(value = "Короткое название")
    @Expose(serialize = false,deserialize = true)
    @XmlTransient
    String shortName;
    //@SerializedName(value = "Год основания")
    @Expose(serialize = false,deserialize = true)
    @XmlTransient
    int yearOfFoundation;

    //@SerializedName(value = "Профиль обучения")
    @SerializedName(value = "universityProfile")
    @Expose(serialize = true,deserialize = true)
    @XmlElement(name = "universityProfile",required = false)
    StudyProfile mainProfile;

    public StudyProfile getMainProfile() {
        return mainProfile;
    }

    public void setMainProfile(StudyProfile mainProfile) {
        this.mainProfile = mainProfile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getYearOfFoundation() {
        return yearOfFoundation;
    }

    public void setYearOfFoundation(int yearOfFoundation) {
        this.yearOfFoundation = yearOfFoundation;
    }

    public University()
    {

    }

    public University(String id, String fullName, String shortName, int yearOfFoundation, StudyProfile mainProfile) {
        this.setId(id);
        this.setFullName(fullName);
        this.setShortName(shortName);
        this.setYearOfFoundation(yearOfFoundation);
        this.setMainProfile(mainProfile);
    }

    @Override
    public String toString() {
        return "University{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", yearOfFoundation=" + yearOfFoundation +
                ", MainProfile=" + mainProfile.getProfileName() +
                '}';
    }
}

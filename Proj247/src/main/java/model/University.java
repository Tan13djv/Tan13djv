package model;

import com.google.gson.annotations.SerializedName;

import enums.StudyProfile;
public class University {
    @SerializedName(value = "Код УЗ")
    String id;
    @SerializedName(value = "Полное название")
    String fullName;
    @SerializedName(value = "Короткое название")
    String shortName;
    @SerializedName(value = "Год основания")
    int yearOfFoundation;

    @SerializedName(value = "Профиль обучения")
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

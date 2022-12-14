package enums;

public enum StudyProfile {
    MEDICINE("Медицина"),
    MATHEMATICS ("Математика"),
    PHYSICS ("Инженерия"),
    LINGUISTICS ("Лингвистика");
    String profileName;
    StudyProfile(String rus) {
        profileName = rus;
    }

    public String getProfileName() {
        return profileName;
    }
}

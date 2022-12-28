package util;

import enums.StudyProfile;
import model.Statistics;
import model.Student;
import model.University;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.OptionalDouble;
import java.util.stream.Collectors;


public class MyStatistic {

    public static ArrayList<Statistics> CollectData(ArrayList<Student> Stud, ArrayList<University> Univs){

        String mainProfile;
        double avgExamScore;
        int stdCount;
        int univCount;
        String uniName;
        ArrayList<StudyProfile> arProf = new ArrayList<StudyProfile>();
        arProf.add(StudyProfile.LINGUISTICS);
        arProf.add(StudyProfile.MEDICINE);
        arProf.add(StudyProfile.MATHEMATICS);
        arProf.add(StudyProfile.PHYSICS);

        ArrayList<Statistics> Stat = new ArrayList<Statistics>();
        for (StudyProfile prof: arProf) {


            univCount = (int) Univs.stream().filter((s) -> s.getMainProfile() == prof).count();
            if (univCount > 0) {
                stdCount = (int) Stud.stream().filter((s) -> s.getStudProf(s, Univs) == prof).count();
                try {
                    avgExamScore = Stud.stream().filter((s) -> s.getStudProf(s, Univs) == prof).mapToDouble(Student::getAvgExamScore).average().getAsDouble();
                } catch (NoSuchElementException e) {
                    avgExamScore = 0;
                }


                uniName = Univs.stream().filter((s) -> s.getMainProfile() == prof).map(University::getShortName).collect(Collectors.toList()).toString();

                Stat.add(new Statistics(prof, avgExamScore, stdCount, univCount, uniName));
            }
        }
        return Stat;
    }
}

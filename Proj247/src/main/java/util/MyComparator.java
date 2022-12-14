package util;

import Comporators.*;

import java.util.Comparator;
import Comporators.*;
import enums.StudChoseCompare;
import enums.UnivChoseCompare;
public class MyComparator {

    public static Comparator getMyComparator(UnivChoseCompare myEnum){
        if(myEnum == UnivChoseCompare.FullNameComparator)
            return new UniversityFullNameComparator();
        if(myEnum == UnivChoseCompare.YearOfFoundationComparator)
            return new UniversityYearOfFoundationComparator();
        if(myEnum == UnivChoseCompare.ShortNameComparator)
            return new UniversityShortNameComparator();
        if(myEnum == UnivChoseCompare.IdComparator)
            return new UniversityIdComparator();
        if(myEnum == UnivChoseCompare.StudyProfileComparator)
            return new UniversityStudyProfileComparator();
        return null;
    };
    public static Comparator getMyComparator(StudChoseCompare myEnum){
        if(myEnum == StudChoseCompare.FullNameComparator)
            return new StudentFullNameComparator();
        if(myEnum == StudChoseCompare.UniverIdComparator)
            return new StudentUniverIdComparator();
        if(myEnum == StudChoseCompare.CourseNumComparator)
            return new StudentCourseNumComparator();
        if(myEnum == StudChoseCompare.ExamScoreComparator)
            return new StudentExamScoreComparator();
        return null;
    };
}

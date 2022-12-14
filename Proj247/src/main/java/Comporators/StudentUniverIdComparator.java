package Comporators;

import org.apache.commons.lang3.StringUtils;
import model.Student;
public class StudentUniverIdComparator implements IStudComparator{

    @Override
    public int compare(Student o1, Student o2) {
        return StringUtils.compare(o1.getUniversityId(),o2.getUniversityId());
    }
}
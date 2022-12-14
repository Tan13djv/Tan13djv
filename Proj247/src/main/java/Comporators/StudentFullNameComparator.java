package Comporators;

import org.apache.commons.lang3.StringUtils;
import model.Student;
public class StudentFullNameComparator implements IStudComparator{
        @Override
        public int compare(Student o1, Student o2) {
            return StringUtils.compare(o1.getFullName(),o2.getFullName());
        }

}

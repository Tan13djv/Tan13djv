package Comporators;
import model.Student;
public class StudentCourseNumComparator implements IStudComparator{

    @Override
    public int compare(Student o1, Student o2) {
        return Integer.compare(o1.getCurrentCourseNumber(), o2.getCurrentCourseNumber());
    }
}

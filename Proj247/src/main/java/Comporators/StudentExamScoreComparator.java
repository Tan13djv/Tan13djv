package Comporators;
import model.Student;
public class StudentExamScoreComparator implements IStudComparator{

    @Override
    public int compare(Student o1, Student o2) {
        return Float.compare(o2.getAvgExamScore(),o1.getAvgExamScore());
    }
}
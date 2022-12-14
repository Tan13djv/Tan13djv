package Comporators;
import model.University;
public class UniversityYearOfFoundationComparator implements IUniverComparator{
    @Override
    public int compare(University o1, University o2) {
        return Integer.compare(o1.getYearOfFoundation(),o2.getYearOfFoundation());
    }

}

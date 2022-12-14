package Comporators;

import org.apache.commons.lang3.StringUtils;
import model.University;
public class UniversityStudyProfileComparator implements IUniverComparator{
    @Override
    public int compare(University o1, University o2) {
        return StringUtils.compare(o1.getMainProfile().getProfileName(),o2.getMainProfile().getProfileName());
    }

}

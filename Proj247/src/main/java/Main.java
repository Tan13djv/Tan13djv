import enums.StudChoseCompare;
import enums.UnivChoseCompare;
import io.ImportData;
import io.XlsWriter;
import model.Student;
import model.University;
import util.MyComparator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import util.JsonUtil;
import util.MyStatistic;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

    Comparator UnComp = null;
    Comparator StComp = null;

    ImportData NewData = new ImportData(Main.class.getClassLoader().getResource("universityInfo.xlsx").getFile());

    NewData.ImportUniversity();
    NewData.ImportStudients();


/*
    Scanner sc= new Scanner(System.in);

    System.out.println("Choose field for sorting of University: \n1.Full name\n2.Year of foundation\n3.Short mame\n4.Id\n5.Study Profile");
    int uCh = sc.nextInt();
    System.out.println("Choose field for sorting of Students: \n1.Full name\n2.University id\n3.Current course number\n4.Average exam score");
    int sCh = sc.nextInt();
    switch (uCh){
        case 1:
            UnComp = MyComparator.getMyComparator(UnivChoseCompare.FullNameComparator);
            break;
        case 2:
            UnComp = MyComparator.getMyComparator(UnivChoseCompare.YearOfFoundationComparator);
            break;
        case 3:
            UnComp = MyComparator.getMyComparator(UnivChoseCompare.ShortNameComparator);
            break;
        case 4:
            UnComp = MyComparator.getMyComparator(UnivChoseCompare.IdComparator);
            break;
        case 5:
            UnComp = MyComparator.getMyComparator(UnivChoseCompare.StudyProfileComparator);
            break;
    }

        switch (sCh){
            case 1:
                StComp = MyComparator.getMyComparator(StudChoseCompare.FullNameComparator);
                break;
            case 2:
                StComp = MyComparator.getMyComparator(StudChoseCompare.UniverIdComparator);
                break;
            case 3:
                StComp = MyComparator.getMyComparator(StudChoseCompare.CourseNumComparator);
                break;
            case 4:
                StComp = MyComparator.getMyComparator(StudChoseCompare.ExamScoreComparator);
                break;
        }
     NewData.Univs.stream().sorted(UnComp).forEach(System.out::println);
     NewData.Stud.stream().sorted(StComp).forEach(System.out::println);
 */
/*
        System.out.println("NewData.Univs.stream().count() = " + NewData.Univs.stream().count());
        System.out.println("NewData.Stud.stream().count() = " + NewData.Stud.stream().count());

        String JsonUniver = JsonUtil.ConvertUniversArrTo(NewData.Univs);
        String JsonStuds =  JsonUtil.ConvertStudArrTo(NewData.Stud);

        System.out.println(JsonUniver);
        System.out.println(JsonStuds);

        ArrayList<University> Univs;
        ArrayList<Student> Stud;

        Univs = JsonUtil.ConvertUniversArrFrom(JsonUniver);
        Stud = JsonUtil.ConvertStudArrFrom(JsonStuds);

        Univs.stream().forEach(System.out::println);
        Stud.stream().forEach(System.out::println);


        System.out.println("Univs.stream().count() = " + Univs.stream().count());
        System.out.println("Stud.stream().count() = " + Stud.stream().count());



        NewData.Univs.stream().forEach(entry -> {
            System.out.println(JsonUtil.ConvertUniTo(entry));
            System.out.println(JsonUtil.ConvertUniFrom(JsonUtil.ConvertUniTo(entry)));
            }
        );
        NewData.Stud.stream().forEach(entry -> {
            System.out.println(JsonUtil.ConvertStudTo(entry));
            System.out.println(JsonUtil.ConvertStFrom(JsonUtil.ConvertStudTo(entry)));
            }
        );
*/
       XlsWriter.MakeTable(MyStatistic.CollectData(NewData.Stud,NewData.Univs),"statistic.xlsx");
        System.out.println("File statistic.xlsx was created");
    }


}

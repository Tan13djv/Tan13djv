import enums.StudChoseCompare;
import enums.UnivChoseCompare;
import io.ImportData;
import io.JsonWriter;
import io.XlsWriter;
import io.XmlWriter;
import model.DataStructure;
import model.Statistics;
import model.Student;
import model.University;
import util.MyComparator;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import util.JsonUtil;
import util.MyStatistic;
import util.XmlUtil;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;


public class Main {
    public static Logger log;

    public static void main(String[] args) throws IOException, ClassNotFoundException, JAXBException, TransformerConfigurationException, ParserConfigurationException {

    log = Logger.getLogger(Main.class.getName());

        try {
            if (Main.class.getClassLoader().getResource("loggin.properties") != null)
                LogManager.getLogManager().readConfiguration(Main.class.getClassLoader().getResource("loggin.properties").openStream());
            else
            {
                System.err.println("Файл logging.properties не найден: ");
            }
        } catch (Exception e) {
            System.err.println("Не удается открыть файл logging.properties" + e.toString());
        }



    Comparator UnComp = null;
    Comparator StComp = null;

    log.info("Начало импорта данных");
    ImportData NewData = null;
    if (Main.class.getClassLoader().getResource("universityInfo.xlsx") != null) {
        NewData = new ImportData(Main.class.getClassLoader().getResource("universityInfo.xlsx").getFile());
        NewData.ImportUniversity();
        NewData.ImportStudients();
        log.info("Данные импортированы");
    }
       else{
        log.severe("Данные не импортированы. Файл не найден");
       }




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
    if(NewData != null) {
        NewData.Univs.sort(MyComparator.getMyComparator(UnivChoseCompare.YearOfFoundationComparator));
        log.info("Сортировка университетов выполнена");
        NewData.Stud.sort(MyComparator.getMyComparator(StudChoseCompare.ExamScoreComparator));
        log.info("Сортировка студентов выполнена");
    }
    else {
        log.warning("Сортировка не выполнена, данные не найдены");
    }

/*

        System.out.println("NewData.Univs.stream().count() = " + NewData.Univs.stream().count());
        System.out.println("NewData.Stud.stream().count() = " + NewData.Stud.stream().count());


        String JsonUniver = JsonUtil.ConvertUniversArrTo(NewData.Univs);
        String JsonStuds = JsonUtil.ConvertStudArrTo(NewData.Stud);

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
        ArrayList<Statistics> ArrStat = new ArrayList<Statistics>();
        if(NewData != null) {

          ArrStat = MyStatistic.CollectData(NewData.Stud,NewData.Univs);

          XmlUtil xmlUtil = new XmlUtil();
          DataStructure dataStructure = new DataStructure().setData(NewData.Stud,NewData.Univs,ArrStat);

          XmlWriter.MakeFile(xmlUtil.marshal(dataStructure), "xmlfile_"+String.valueOf(LocalDate.now())+".xml");
          JsonWriter.MakeFile(JsonUtil.SerialToJson(dataStructure), "json_"+String.valueOf(LocalDate.now())+".json");

          XlsWriter.MakeTable(ArrStat,"statistic.xlsx");

        }
        else {
                log.warning("Файлы не созданы, данные не найдены");
        }

    }


}

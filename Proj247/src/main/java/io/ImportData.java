package io;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import model.University;
import model.Student;
import enums.StudyProfile;
public class ImportData {
    XSSFWorkbook workbook;
    public ArrayList<University> Univs;
    public ArrayList<Student> Stud;
    XSSFSheet sheet;
    ImportData(){}
    public static Logger log;
    public ImportData(String filename) throws IOException {

        log = Logger.getLogger(ImportData.class.getName());

        Univs = new ArrayList<University>();
        Stud = new ArrayList<Student>();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filename);
        }
        catch (IOException e){
            log.severe("Не удается открыть файл: "+filename+ e.toString());
        }


        workbook = new XSSFWorkbook(fileInputStream);
        sheet = null;

    }
    public void ImportUniversity()  {

        sheet = workbook.getSheet("Университеты");
        if (sheet != null) {
            log.info("Данные университетов найдены");
            for (Row row : sheet) {
                if (row.getRowNum() > 0) {
                    String id = row.getCell(0).getStringCellValue();
                    String fullName = row.getCell(1).getStringCellValue();
                    String shortName = row.getCell(2).getStringCellValue();
                    int yearOfFoundation = (int) row.getCell(3).getNumericCellValue();
                    String Profile = row.getCell(4).getStringCellValue();

                    Univs.add(new University(id, fullName, shortName, yearOfFoundation, StudyProfile.valueOf(Profile)));
                }
            }
            log.info("Университеты импортированы");
        }
        else {
            log.warning("Данные университетов не найдены");
        }
    }
    public void ImportStudients()  {

        sheet = workbook.getSheet("Студенты");
        if (sheet != null) {
            log.info("Данные студентов найдены");
            for (Row row : sheet) {

                if (row.getRowNum() > 0) {

                    String universityId = row.getCell(0).getStringCellValue();
                    String fullName = row.getCell(1).getStringCellValue();
                    int currentCourseNumber = (int) row.getCell(2).getNumericCellValue();
                    float avgExamScore = (float) row.getCell(3).getNumericCellValue();

                    Stud.add(new Student(universityId, fullName, currentCourseNumber, avgExamScore));
                }
            }
            log.info("Студенты импортированы");
        }
        else
        {
            log.warning("Данные студентов не найдены");
        }

    }
}

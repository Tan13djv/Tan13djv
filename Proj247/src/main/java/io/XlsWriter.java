package io;

import model.Statistics;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.OptionalDouble;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class XlsWriter {


    public static Logger log;

    public XlsWriter() {

    }

    public static void MakeTable(ArrayList<Statistics> stData, String filename) throws IOException {

        log = Logger.getLogger(XlsWriter.class.getName());

        FileOutputStream fileOutputStream = null;
        XSSFWorkbook workbook;
        workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        XSSFRow row = sheet.createRow((short) 0);
        XSSFCell cell = row.createCell(0);

        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short)14);
        font.setBold(true);


        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        //Заголовок
        cell = row.createCell(0);
        cell.setCellValue("Профиль обучения");
        cell.setCellStyle(cellStyle);
        cell = row.createCell(1);
        cell.setCellValue("Средний балл");
        cell.setCellStyle(cellStyle);
        cell = row.createCell(2);
        cell.setCellValue("Количество студентов по профилю");
        cell.setCellStyle(cellStyle);
        cell = row.createCell(3);
        cell.setCellValue("Количество университетов по профилю");
        cell.setCellStyle(cellStyle);
        cell = row.createCell(4);
        cell.setCellValue("Названия университетов");
        cell.setCellStyle(cellStyle);
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);

        int i = 1;
            for (Statistics st:stData)
                  {
                      row = sheet.createRow((short) i++);
                      cell = row.createCell(0);
                      cell.setCellValue(st.getMainProfile());
                      cell = row.createCell(1);
                      cell.setCellValue((new BigDecimal(st.getAvgExamScore(),new MathContext(3, RoundingMode.HALF_UP))).toString());
                      cell = row.createCell(2);
                      cell.setCellValue(st.getStdCount());
                      cell = row.createCell(3);
                      cell.setCellValue(st.getUnivCount());
                      cell = row.createCell(4);
                      cell.setCellValue(st.getUniName());
                  }
        try {
            fileOutputStream = new FileOutputStream(filename);
        }
        catch (Exception e){
            log.severe("Файл статистики не создан: "+ e.toString());
        }

        workbook.write(fileOutputStream);

    }
}

package com.adviserlogic.automation.common.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;


public class CommissionFilesReader {

    /**
     * Returns a LinkedList object with each of its element as a LinkedHashmap representing a row
     * LinkedHashMap: Key as the column heading, value as the cell data for that particular row
     *
     * @param fileName
     * @return
     */
    public static LinkedList<Map<String,String>> readExcel(File fileName) throws IOException {
        LinkedList<Map<String,String>> dataList = new LinkedList<>(); // represents a list of data rows
        LinkedList<String> headingList = new LinkedList<>(); // represents the heading row
        LinkedList<Map<String, String>> csvData = new LinkedList<>();
        FileInputStream fis = new FileInputStream(fileName);
        Workbook workBook= null;
        CSVParser csvParser = null;
        String fileExtensionName = fileName.toString().substring(fileName.toString().indexOf("."));
        if(fileExtensionName.equalsIgnoreCase(".xls"))
        {
            workBook = new HSSFWorkbook(fis);
        }
        else if(fileExtensionName.equalsIgnoreCase(".xlsx"))
        {
            workBook = new XSSFWorkbook(fis);
        }
        else if(fileExtensionName.equalsIgnoreCase(".csv"))
        {
                Map<String, String> value = new HashMap<>();
                csvParser = CSVParser.parse(fis, Charset.defaultCharset(), CSVFormat.EXCEL.withFirstRecordAsHeader());
                for (CSVRecord csvRecord : csvParser.getRecords()) {
                    value = csvRecord.toMap();
                    csvData.add(value);
            }
            return csvData;
        }

        try {
            boolean checkHeadingRow = true;
            for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
                Sheet sheet = workBook.getSheetAt(i);
                Iterator rows = sheet.rowIterator();
                while (rows.hasNext()) {
                    Row row = (Row) rows.next();
                    if (checkHeadingRow == true && isHeadingRow(row)) // Check if it's the heading row
                    {
                        headingList = getHeadingList(row);
                        checkHeadingRow = false; // Don't check for heading row more than once
                        continue;
                    }
                    if (checkHeadingRow == false) {
                        dataList.add(getRowData(headingList, row));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return dataList;
    }


    public static Map<String, String> getRowData(LinkedList<String> headingList, Row row) {
        Map<String, String> rowData = new HashMap<>();
        Iterator cells = row.cellIterator();
        while (cells.hasNext()) {
            for (String heading : headingList) {
                Cell cell = (Cell) cells.next();
                rowData.put(heading, cell.toString());
            }
        }
        return rowData;
    }

    public static LinkedList<String> getHeadingList(Row row) {
        LinkedList<String> headingList = new LinkedList<>();
        Iterator cells = row.cellIterator();
        while (cells.hasNext()) {
            Cell cell = (Cell) cells.next();
            headingList.add(cell.toString());
        }
        return headingList;
    }

    // The top row containing data in the first 4 cells will be recognized as heading row
    public static boolean isHeadingRow(Row row) {
        Iterator cells = row.cellIterator();
        int count = 0;
        while (cells.hasNext()) {
            Cell cell = (Cell) cells.next();
            if (!cell.toString().equals("")) {
                count += 1;
            }
            if (count == 4) {
                return true;
            }
        }
        return false;
    }
}

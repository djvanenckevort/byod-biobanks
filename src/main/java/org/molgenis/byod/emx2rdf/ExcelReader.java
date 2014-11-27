/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.molgenis.byod.emx2rdf;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author David van Enckevort <david@allthingsdigital.nl>
 */
public class ExcelReader implements Iterator<Map<String, String>> {

    private final Sheet dataset;
//    private final Sheet organisations;
//    private final Sheet persons;
    private final Workbook wb;
    private String[] headers;
    private int rowNum;

    public ExcelReader(final Path location) throws IOException, InvalidFormatException {

        wb = WorkbookFactory.create(location.toFile());
        dataset = wb.getSheet("decode_cocos_20140601");
//        organisations = wb.getSheet("decode_organisations");
//        persons = wb.getSheet("decode_persons");
        Row row = dataset.getRow(0);
        headers = new String[row.getLastCellNum()];
        rowNum = 1;
        for (int cellNum = row.getFirstCellNum(); cellNum <= row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell == null || cell.getCellType() != Cell.CELL_TYPE_STRING) {
                break;
            }
            headers[cellNum] = cell.getStringCellValue();
        }
    }

    @Override
    public boolean hasNext() {
        return rowNum <= dataset.getLastRowNum();
    }

    @Override
    public Map<String, String> next() {
        Map<String, String> result = new HashMap<>();
        Row row = dataset.getRow(rowNum);
        rowNum++;
        for (int cellNum = 0; cellNum < headers.length; cellNum++) {
            Cell cell = row.getCell(cellNum);
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    if ("organisation".equals(headers[cellNum])) {
                        addOrganisation(result);
                    } else if ("".equals(headers[cellNum])) {
                        addPerson(result);
                    } else {
                        result.put(headers[cellNum], row.getCell(cellNum).getStringCellValue());
                    }

                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    result.put(headers[cellNum], String.valueOf(cell.getNumericCellValue()));
            }
        }
        return result;
    }

    private void addOrganisation(Map<String,String> map) {
        map.put("organisationName", "VUmc");
        map.put("homepageUrl", "http://www.vumc.nl/");
    }

    private void addPerson(Map<String, String> map) {
        map.put("fullName", "Gerrit Meijer");
        map.put("emailAddress", "ga.meijer@vumc.nl");
        map.put("phone", "020-4444852");
    }
}

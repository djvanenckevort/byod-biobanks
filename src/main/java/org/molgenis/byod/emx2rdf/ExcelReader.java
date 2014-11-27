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
    private final Workbook wb;
    private String[] headers;
    private int rowNum;

    public ExcelReader(final Path location, String datasheet) throws IOException, InvalidFormatException {

        wb = WorkbookFactory.create(location.toFile());
        dataset = wb.getSheet(datasheet);
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
                    result.put(headers[cellNum], row.getCell(cellNum).getStringCellValue());

                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    String value = String.format("%1.0f", cell.getNumericCellValue());
                    result.put(headers[cellNum], value);
                    break;
            }
        }
        return result;
    }
}

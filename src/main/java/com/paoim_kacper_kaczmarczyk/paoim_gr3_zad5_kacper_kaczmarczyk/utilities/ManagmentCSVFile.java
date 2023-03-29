package com.paoim_kacper_kaczmarczyk.paoim_gr3_zad5_kacper_kaczmarczyk.utilities;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.paoim_kacper_kaczmarczyk.paoim_gr3_zad5_kacper_kaczmarczyk.students.StudentsEntity;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class ManagmentCSVFile {
    private String filename;
    private StatefulBeanToCsv<StudentsEntity> writer;
    private HttpServletResponse response;

    public ManagmentCSVFile(String filename, HttpServletResponse response) throws Exception {
        this.filename = filename;
        this.response = response;
        this.writer = new StatefulBeanToCsvBuilder<StudentsEntity>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();

    }

    public void saveToCSV(List<StudentsEntity> csvBody) throws Exception
    {
        writer.write(csvBody);
    }
}

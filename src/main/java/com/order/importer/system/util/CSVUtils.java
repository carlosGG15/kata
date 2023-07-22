package com.order.importer.system.util;


import com.opencsv.CSVWriter;
import com.opencsv.bean.MappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.Writer;
import java.util.List;

@Slf4j
@UtilityClass
public class CSVUtils {

    public static <T> String generateCSV(final List<T> entities, final MappingStrategy<T> mappingStrategy, final int importId) throws Exception {
        log.info("Generating csv file");
        final String fileName = "./reports/report_" + importId + ".csv";
        try (Writer writer = new FileWriter(fileName)) {
            final StatefulBeanToCsv<T> sbc = new StatefulBeanToCsvBuilder<T>(writer)
                    .withQuotechar('\'')
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .withMappingStrategy(mappingStrategy)
                    .build();

            sbc.write(entities);
        }
        log.info("csv file generated successfully");
        return fileName;
    }

}

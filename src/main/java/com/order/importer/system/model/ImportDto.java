package com.order.importer.system.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class ImportDto {

    private int id;
    private ImportStatus status;
    private int numRecords;
    private LocalDate data;
}

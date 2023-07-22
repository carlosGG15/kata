package com.order.importer.system.entity;

import com.order.importer.system.model.ImportStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "imports")
public class Imports {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "import_date")
    private LocalDate date;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "num_records")
    private int numRecords;
    @Column(name = "import_status")
    @Enumerated(EnumType.ORDINAL)
    private ImportStatus status;
    @Column(name = "csv_file")
    private String csvFile;
    private int retries;
    @Column(name = "page_fail")
    private int pageFail;

}

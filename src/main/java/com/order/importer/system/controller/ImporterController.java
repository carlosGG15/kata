package com.order.importer.system.controller;

import com.order.importer.system.constants.KataConstants;
import com.order.importer.system.entity.Imports;
import com.order.importer.system.model.ImportDto;
import com.order.importer.system.service.ImportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(KataConstants.IMPORTER_RESOURCE_ENDPOINT)
public class ImporterController {

    private final ImportService importService;

    public ImporterController(ImportService importService) {
        this.importService = importService;
    }

    @GetMapping("/{id}")
    public ImportDto getImport(@PathVariable final Integer id) throws Exception {
        return importService.getImport(id);
    }

    @PostMapping
    public ImportDto createImport(@RequestBody ImportDto importDto) {
        return importService.createImport(importDto);
    }

    @GetMapping("{id}/csv")
    public void downloadCSV(@RequestParam final Integer id) {
        // TODO: Not implemented
    }

}

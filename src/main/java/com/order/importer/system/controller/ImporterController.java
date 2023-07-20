package com.order.importer.system.controller;

import com.order.importer.system.constants.KataConstants;
import com.order.importer.system.service.ImportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(KataConstants.IMPORTER_RESOURCE_ENDPOINT)
public class ImporterController {

    private final ImportService importService;

    public ImporterController(ImportService importService) {
        this.importService = importService;
    }

    @GetMapping
    public void importOrders() {
        importService.importOrders();
    }

}

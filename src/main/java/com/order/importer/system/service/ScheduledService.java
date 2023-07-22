package com.order.importer.system.service;

import com.order.importer.system.entity.Imports;
import com.order.importer.system.model.ImportStatus;
import com.order.importer.system.repository.ImportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ScheduledService {

    private final ImportService importService;
    private final ImportRepository importRepository;
    private final OrderService orderService;
    private int test = 1;

    private static final int MAX_RETIES_IMPORTS = 3;

    public ScheduledService(ImportService importService, ImportRepository importRepository, OrderService orderService) {
        this.importService = importService;
        this.importRepository = importRepository;
        this.orderService = orderService;
    }


    //@Scheduled(cron = "0 */2 * * * *") // Every two minutes
   @Scheduled(cron = "* * * * * *") // Every second
    public void imports() throws Exception {
        final List<Imports> pendingImports = importService.findImportsByStatus(ImportStatus.PENDING);
        if (!pendingImports.isEmpty()) {
            pendingImports.forEach(imports -> {
                try {
                    importService.importOrders(imports);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    //@Scheduled(cron = "* */10 * * * *")
    @Scheduled(cron = "* * * * * *")
    public void retryImports() {
        List<Imports> failedImports = importService.findImportsByStatus(ImportStatus.FAILED);
        if (!failedImports.isEmpty()) {
            failedImports.forEach(imports -> {
                if (imports.getRetries() < MAX_RETIES_IMPORTS) {
                    try {
                        importService.importOrders(imports);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    imports.setStatus(ImportStatus.CANCELLED);
                    importService.updateImport(imports);
                }
            });
        }
    }
}

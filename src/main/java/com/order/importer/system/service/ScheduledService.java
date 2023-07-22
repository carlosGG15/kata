package com.order.importer.system.service;

import com.order.importer.system.entity.Imports;
import com.order.importer.system.model.ImportStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ScheduledService {

    private final ImportService importService;
    private static final int MAX_RETIES_IMPORTS = 3;

    @Scheduled(cron = "0 */2 * * * *") // Every two minutes
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

    @Scheduled(cron = "* */10 * * * *") // Every 10 minutes
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

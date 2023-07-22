package com.order.importer.system.service;

import com.order.importer.system.client.EsPublicoClient;
import com.order.importer.system.entity.*;
import com.order.importer.system.exception.ImportException;
import com.order.importer.system.mapper.ImportMapper;
import com.order.importer.system.mapper.OrderMapper;
import com.order.importer.system.model.ImportDto;
import com.order.importer.system.model.ImportStatus;
import com.order.importer.system.model.OrderDto;
import com.order.importer.system.model.OrdersResponse;
import com.order.importer.system.repository.ImportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class ImportService {

    private final EsPublicoClient esPublicoClient;
    private final OrderMapper orderMapper;
    private final OrderService orderService;
    private final CountryService countryService;
    private final OrderTypeService orderTypeService;
    private final ChanelService chanelService;
    private final OrderPriorityService orderPriorityService;
    //private final CSVService csvService;
    private final ImportRepository importRepository;
    private final ImportMapper importMapper;

    public ImportService(final EsPublicoClient esPublicoClient, final OrderMapper orderMapper, final OrderService orderService,
                         final CountryService countryService, final OrderTypeService orderTypeService, final ChanelService chanelService,
                         final OrderPriorityService orderPriorityService, /*final CSVService csvService,*/ final ImportRepository importRepository,
                         final ImportMapper importMapper) {

        this.esPublicoClient = esPublicoClient;
        this.orderMapper = orderMapper;
        this.orderService = orderService;
        this.countryService = countryService;
        this.orderTypeService = orderTypeService;
        this.chanelService = chanelService;
        this.orderPriorityService = orderPriorityService;
        // this.csvService = csvService;
        this.importRepository = importRepository;
        this.importMapper = importMapper;
    }

    public void importOrders(final Imports imports) {
        log.info("Start to import orders");
        final int importId = imports.getId();
        imports.setStatus(ImportStatus.ONGOING);
        imports.setDate(LocalDate.now());
        imports.setRetries(imports.getRetries() + 1);
        updateImport(imports);


        int page = imports.getPageFail();
        OrdersResponse orders = esPublicoClient.getOrders(page);
        int totalImported = 0;
        Map<String, Integer> regionSummary = new HashMap<>();
        Map<String, Integer> countrySummary = new HashMap<>();
        Map<String, Integer> orderTypesSummary = new HashMap<>();
        Map<String, Integer> chanelSummary = new HashMap<>();
        Map<String, Integer> orderPrioritySummary = new HashMap<>();

        try {

            while (orders.getLinks().getNext() != null) {
                log.info("Pagina - " + page);
                page++;
                final List<OrderDto> content = orders.getContent();
                for (OrderDto orderDto : content) {
                    Order orderEntity = orderMapper.map(orderDto);
                    final Country country = countryService.importCountry(orderDto.getCountry(), orderDto.getRegion());
                    orderEntity.setCountry(country);
                    final OrderType orderType = orderTypeService.importOrderType(orderDto.getItemType());
                    orderEntity.setOrderType(orderType);
                    final Chanel chanel = chanelService.importChanel(orderDto.getSalesChanel());
                    orderEntity.setChanel(chanel);
                    final OrderPriority orderPriority = orderPriorityService.importOrderPriority(orderDto.getPriority());
                    orderEntity.setOrderPriority(orderPriority);
                    orderEntity.setImportId(importId);

                    orderService.saveOrder(orderEntity);

                    totalImported++;
                    regionSummary.merge(country.getRegion().getName(), 1, Integer::sum);
                    countrySummary.merge(country.getName(), 1, Integer::sum);
                    orderTypesSummary.merge(orderType.getName(), 1, Integer::sum);
                    chanelSummary.merge(orderType.getName(), 1, Integer::sum);
                    orderPrioritySummary.merge(orderType.getName(), 1, Integer::sum);
                }
                orders = esPublicoClient.getOrders(page);
            }

            log.info("Finish import, orders imported: " + totalImported);
            printSummary("Region", regionSummary);
            printSummary("Country", countrySummary);
            printSummary("Order types", orderTypesSummary);
            printSummary("Sales channels", chanelSummary);
            printSummary("Order priority", orderPrioritySummary);

            generateReport(imports, totalImported);
        } catch (Exception e) {
            log.error("Error during import ", e);
            imports.setStatus(ImportStatus.FAILED);
            imports.setPageFail(page);
            imports.setRetries(imports.getRetries() + 1);
            updateImport(imports);
        }
    }

    @Async
    public void generateReport(final Imports imports, final int totalImported) throws Exception {
        final String csvFilePath = orderService.generateReport(imports.getId());
        imports.setStatus(ImportStatus.SUCCESS);
        imports.setNumRecords(totalImported);
        imports.setCsvFile(csvFilePath);
        imports.setEndDate(LocalDate.now());
        updateImport(imports);
    }

    private void printSummary(final String summaryName, final Map<String, Integer> summary) {
        log.info(summaryName + " summary");
        for (String key : summary.keySet()) {
            log.info(key + ":" + summary.get(key));
        }
    }

    public ImportDto createImport(final ImportDto importDto) {
        final Imports importEntity = importMapper.mapImportDto(importDto);
        importEntity.setStatus(ImportStatus.PENDING);
        return importMapper.mapImport(importRepository.save(importEntity));
    }

    public ImportDto getImport(final Integer importId) {
        final Optional<Imports> importValue = importRepository.findById(importId);
        if (importValue.isPresent()) {
            return importMapper.mapImport(importValue.get());
        }
        throw new ImportException("Import with id: " + importId + " not found");
    }

    public List<Imports> findImportsByStatus(final ImportStatus importStatus) {
        return importRepository.findByStatus(importStatus);
    }

    public void updateImport(final Imports imports) {
        importRepository.save(imports);
    }
}

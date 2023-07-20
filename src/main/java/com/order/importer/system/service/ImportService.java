package com.order.importer.system.service;

import com.order.importer.system.client.EsPublicoClient;
import com.order.importer.system.entity.Chanel;
import com.order.importer.system.entity.Country;
import com.order.importer.system.entity.Order;
import com.order.importer.system.entity.OrderPriority;
import com.order.importer.system.entity.OrderType;
import com.order.importer.system.mapper.OrderMapper;
import com.order.importer.system.model.OrderDto;
import com.order.importer.system.model.OrdersResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    private final CSVService csvService;

    public ImportService(final EsPublicoClient esPublicoClient, final OrderMapper orderMapper, final OrderService orderService,
        final CountryService countryService, final OrderTypeService orderTypeService, final ChanelService chanelService,
        final OrderPriorityService orderPriorityService, final CSVService csvService) {

        this.esPublicoClient = esPublicoClient;
        this.orderMapper = orderMapper;
        this.orderService = orderService;
        this.countryService = countryService;
        this.orderTypeService = orderTypeService;
        this.chanelService = chanelService;
        this.orderPriorityService = orderPriorityService;
        this.csvService = csvService;
    }

    public void importOrders() {
        log.info("Start to import orders");
        Integer page = 1;
        OrdersResponse orders = esPublicoClient.getOrders(page);
        int totalImported = 0;
        Map<String, Integer> regionSummary = new HashMap<>();
        Map<String, Integer> countrySummary = new HashMap<>();
        Map<String, Integer> orderTypesSummary = new HashMap<>();
        Map<String, Integer> chanelSummary = new HashMap<>();
        Map<String, Integer> orderPrioritySummary = new HashMap<>();

        while (orders.getLinks().getNext() != null) {
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

        System.out.println("Finish import, orders imported: " + totalImported);
        printSummary("Region", regionSummary);
        printSummary("Country", countrySummary);
        printSummary("Order types", orderTypesSummary);
        printSummary("Sales channels", chanelSummary);
        printSummary("Order priority", orderPrioritySummary);

        csvService.generateCSV();
    }

    private void printSummary(final String summaryName, final Map<String, Integer> summary) {
        System.out.println(summaryName + " summary");
        for (String key : summary.keySet()) {
            System.out.println(key + ":" + summary.get(key));
        }
    }
}

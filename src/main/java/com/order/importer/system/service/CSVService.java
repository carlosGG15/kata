package com.order.importer.system.service;

import com.order.importer.system.model.OrderView;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CSVService {

    private final OrderService orderService;

    public CSVService(final OrderService orderService) {
        this.orderService = orderService;
    }

    public void generateCSV() {
        List<OrderView> orders = orderService.getOrders();
        // TODO: Not implemented
    }
}

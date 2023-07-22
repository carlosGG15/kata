package com.order.importer.system.service;

import com.opencsv.bean.MappingStrategy;
import com.order.importer.system.config.HeaderColumnNameAndOrderMappingStrategy;
import com.order.importer.system.entity.Order;
import com.order.importer.system.model.OrderView;
import com.order.importer.system.repository.OrderRepository;
import com.order.importer.system.util.CSVUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void saveOrder(final Order order) {
        orderRepository.save(order);
    }

    public List<OrderView> getOrders(final int importId) {
        return orderRepository.getOrders(importId);
    }

    public String generateReport(final int importId) throws Exception {
        final List<OrderView> orders = getOrders(importId);
        final MappingStrategy<OrderView> strategy = new HeaderColumnNameAndOrderMappingStrategy<>(OrderView.class);
        return CSVUtils.generateCSV(orders, strategy, importId);
    }
}

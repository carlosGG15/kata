package com.order.importer.system.service;

import com.order.importer.system.entity.Order;
import com.order.importer.system.model.OrderView;
import com.order.importer.system.repository.OrderRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void saveOrder(final Order order) {
        orderRepository.save(order);
    }

    public List<OrderView> getOrders() {
        return orderRepository.getOrders();
    }
}

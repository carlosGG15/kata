package com.order.importer.system.service;

import com.order.importer.system.entity.OrderPriority;
import com.order.importer.system.repository.OrderPriorityRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderPriorityService {

    private final OrderPriorityRepository orderPriorityRepository;

    public OrderPriorityService(OrderPriorityRepository orderPriorityRepository) {
        this.orderPriorityRepository = orderPriorityRepository;
    }

    public OrderPriority importOrderPriority(final String orderPriorityName) {
        final List<OrderPriority> orderPriorities = orderPriorityRepository.findByName(orderPriorityName);

        if(orderPriorities.isEmpty()) {
            OrderPriority orderPriority = new OrderPriority();
            orderPriority.setName(orderPriorityName);
            return orderPriorityRepository.save(orderPriority);
        }

        return orderPriorities.stream().findFirst().get();
    }
}

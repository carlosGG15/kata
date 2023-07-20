package com.order.importer.system.service;

import com.order.importer.system.entity.OrderType;
import com.order.importer.system.repository.OrderTypeRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderTypeService {

    private final OrderTypeRepository orderTypeRepository;

    public OrderTypeService(OrderTypeRepository orderTypeRepository) {
        this.orderTypeRepository = orderTypeRepository;
    }

    public OrderType importOrderType(final String orderTypeName) {
        final List<OrderType> orderTypes = orderTypeRepository.findByName(orderTypeName);

        if (orderTypes.isEmpty()) {
            OrderType orderType = new OrderType();
            orderType.setName(orderTypeName);
            return orderTypeRepository.save(orderType);
        }
        return orderTypes.stream().findFirst().get();
    }

}

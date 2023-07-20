package com.order.importer.system.repository;

import static com.order.importer.system.constants.OrderQueryConstants.GET_ORDERS_QUERY;

import com.order.importer.system.entity.Order;
import com.order.importer.system.model.OrderView;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    @Query(nativeQuery = true, value = GET_ORDERS_QUERY)
    List<OrderView> getOrders();

}

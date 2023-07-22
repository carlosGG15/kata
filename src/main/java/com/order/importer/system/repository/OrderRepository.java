package com.order.importer.system.repository;

import com.order.importer.system.entity.Order;
import com.order.importer.system.model.OrderView;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    @Query(nativeQuery = true)
    List<OrderView> getOrders(final int importId);

//    @Query(nativeQuery = true)
    //  Page<OrderView> getOrders(Pageable pageable);

}

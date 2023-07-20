package com.order.importer.system.repository;

import com.order.importer.system.entity.OrderPriority;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderPriorityRepository extends JpaRepository<OrderPriority, Integer> {

    @Query("SELECT op FROM OrderPriority op WHERE op.name LIKE %:name%")
    List<OrderPriority> findByName(final String name);

}

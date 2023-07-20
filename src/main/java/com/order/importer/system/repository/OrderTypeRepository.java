package com.order.importer.system.repository;

import com.order.importer.system.entity.OrderType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderTypeRepository extends JpaRepository<OrderType, Integer> {

    @Query("SELECT ot from OrderType ot where ot.name like %:name%")
    List<OrderType> findByName(final String name);

}

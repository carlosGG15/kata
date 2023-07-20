package com.order.importer.system.repository;

import com.order.importer.system.entity.Chanel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChanelRepository extends JpaRepository<Chanel, Integer> {

    @Query("SELECT c FROM Chanel c WHERE c.name LIKE %:name%")
    List<Chanel> findByName(final String name);

}

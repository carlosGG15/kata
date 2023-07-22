package com.order.importer.system.repository;

import com.order.importer.system.entity.Imports;
import com.order.importer.system.model.ImportStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImportRepository extends JpaRepository<Imports, Integer> {
    List<Imports> findByStatus(final ImportStatus importStatus);
}

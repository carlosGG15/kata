package com.order.importer.system.service;

import com.order.importer.system.entity.Region;
import com.order.importer.system.repository.RegionRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RegionService {

    private final RegionRepository repository;

    public RegionService(RegionRepository repository) {
        this.repository = repository;
    }

    public Region importRegion(final String regionName) {
        List<Region> regions = repository.findByName(regionName);
        if(regions.isEmpty()) {
            Region region = new Region();
            region.setName(regionName);
            return repository.save(region);
        }
        return regions.stream().findFirst().get();
    }
}

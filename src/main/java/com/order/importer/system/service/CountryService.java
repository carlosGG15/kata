package com.order.importer.system.service;

import com.order.importer.system.entity.Country;
import com.order.importer.system.entity.Region;
import com.order.importer.system.repository.CountryRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CountryService {

    private final RegionService regionService;
    private final CountryRepository countryRepository;

    public CountryService(final RegionService regionService, final CountryRepository countryRepository) {
        this.regionService = regionService;
        this.countryRepository = countryRepository;
    }

    public Country importCountry(final String countryName, String regionName) {
        final List<Country> countries = countryRepository.findByName(countryName);

        if (countries.isEmpty()) {
            log.info("Pais no existe -> lo creamos");
            log.info("Buscamos la region y en caso de que no exist se crea");
            Country country = new Country();
            country.setRegion(regionService.importRegion(regionName));
            country.setName(countryName);
            return countryRepository.save(country);
        }

        return countries.stream().findFirst().get();
    }
}

package com.order.importer.system.mapper;

import com.order.importer.system.entity.Imports;
import com.order.importer.system.model.ImportDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImportMapper {

    Imports mapImportDto(ImportDto importDto);

    ImportDto mapImport(Imports imports);
}

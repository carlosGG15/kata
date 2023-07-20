package com.order.importer.system.mapper;

import com.order.importer.system.entity.Order;
import com.order.importer.system.model.OrderDto;
import com.order.importer.system.util.DateUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(imports = {DateUtils.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    @Mapping(target = "country", ignore = true)
    @Mapping(target = "date", expression = "java(DateUtils.mapDate(orderDto.getDate()))")
    @Mapping(target = "shipDate", expression = "java(DateUtils.mapDate(orderDto.getDate()))")
    Order map(OrderDto orderDto);


}

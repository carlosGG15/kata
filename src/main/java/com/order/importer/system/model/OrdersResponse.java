package com.order.importer.system.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdersResponse {

    @JsonProperty("page")
    private Integer page;
    @JsonProperty("links")
    private Links links;
    @JsonProperty("content")
    private List<OrderDto> content;

}

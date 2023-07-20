package com.order.importer.system.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class OrderDto {

    @JsonProperty("uuid")
    private String uuid;
    @JsonProperty("id")
    private String orderId;
    @JsonProperty("region")
    private String region;
    @JsonProperty("country")
    private String country;
    @JsonProperty("item_type")
    private String itemType;
    @JsonProperty("sales_channel")
    private String salesChanel;
    @JsonProperty("priority")
    private String priority;
    @JsonProperty("date")
    private String date;
    @JsonProperty("ship_date")
    private String shipDate;
    @JsonProperty("units_sold")
    private Integer unitsSold;
    @JsonProperty("unit_price")
    private BigDecimal unitPrice;
    @JsonProperty("unit_cost")
    private BigDecimal unitCost;
    @JsonProperty("total_revenue")
    private BigDecimal totalRevenue;
    @JsonProperty("total_cost")
    private BigDecimal totalCost;
    @JsonProperty("total_profit")
    private BigDecimal totalProfit;
    @JsonProperty("links")
    private Links links;

}



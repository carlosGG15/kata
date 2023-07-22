package com.order.importer.system.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import com.order.importer.system.anotation.CsvBindByNameOrder;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@CsvBindByNameOrder({"Order ID", "Order Priority", "Order Date", "Region", "Country", "OrderType", "Sales Channel",
        "Ship Date", "Units Sold", "Unit Price", "Total Revenue", "Total Cost", "Total Profit"})
public class OrderView {

    @CsvBindByName(column = "Order ID")
    private String orderId;
    @CsvBindByName(column = "Order Priority")
    private String orderPriority;
    @CsvBindByName(column = "Order Date")
    @CsvDate(value = "dd/MM/yyyy")
    private LocalDate orderDate;
    @CsvBindByName(column = "Region")
    private String region;
    @CsvBindByName(column = "Country")
    private String country;
    @CsvBindByName(column = "OrderType")
    private String orderType;
    @CsvBindByName(column = "Sales Channel")
    private String salesChannel;
    @CsvBindByName(column = "Ship Date")
    @CsvDate(value = "dd/MM/yyyy")
    private LocalDate shipDate;
    @CsvBindByName(column = "Units Sold")
    private BigDecimal unitsSold;
    @CsvBindByName(column = "Unit Price")
    private BigDecimal unitPrice;
    @CsvBindByName(column = "Total Revenue")
    private BigDecimal totalRevenue;
    @CsvBindByName(column = "Total Cost")
    private BigDecimal totalCost;
    @CsvBindByName(column = "Total Profit")
    private BigDecimal totalProfit;

}

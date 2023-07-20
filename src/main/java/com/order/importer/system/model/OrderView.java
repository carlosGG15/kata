package com.order.importer.system.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface OrderView {

    String getOrderId();
    String getOrderPriority();
    LocalDate getOrderDate();
    String getRegion();
    String getCountry();
    String getOrderType();
    String getSalesChannel();
    LocalDate getShipDate();
    BigDecimal getUnitsSold();
    BigDecimal getUnitPrice();
    BigDecimal getTotalRevenue();
    BigDecimal getTotalCost();
    BigDecimal getTotalProfit();

}

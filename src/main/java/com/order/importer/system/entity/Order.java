package com.order.importer.system.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_id")
    private String orderId;

    @ManyToOne
    private Country country;

    @ManyToOne
    @JoinColumn(name = "order_type_id")
    private OrderType orderType;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Chanel chanel;

    @ManyToOne
    @JoinColumn(name = "order_priority_id")
    private OrderPriority orderPriority;

    @Column(name = "units_sold")
    private Integer unitsSold;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "unit_cost")
    private BigDecimal unitCost;

    @Column(name = "total_revenue")
    private BigDecimal totalRevenue;

    @Column(name = "total_cost")
    private BigDecimal totalCost;

    @Column(name = "total_profit")
    private BigDecimal totalProfit;

    @Column(name = "order_date")
    private LocalDate date;

    @Column(name = "ship_date")
    private LocalDate shipDate;

}

package com.order.importer.system.entity;

import com.order.importer.system.model.OrderView;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import static com.order.importer.system.constants.OrderQueryConstants.*;

@Data
@Entity
@Table(name = "orders")
@SqlResultSetMapping(name = ORDER_VIEW, classes = @ConstructorResult(targetClass = OrderView.class,
        columns = {
                @ColumnResult(name = "orderId", type = String.class),
                @ColumnResult(name = "orderPriority", type = String.class),
                @ColumnResult(name = "orderDate", type = LocalDate.class),
                @ColumnResult(name = "region", type = String.class),
                @ColumnResult(name = "country", type = String.class),
                @ColumnResult(name = "orderType", type = String.class),
                @ColumnResult(name = "salesChannel", type = String.class),
                @ColumnResult(name = "shipDate", type = LocalDate.class),
                @ColumnResult(name = "unitsSold", type = BigDecimal.class),
                @ColumnResult(name = "unitPrice", type = BigDecimal.class),
                @ColumnResult(name = "totalRevenue", type = BigDecimal.class),
                @ColumnResult(name = "totalCost", type = BigDecimal.class),
                @ColumnResult(name = "totalProfit", type = BigDecimal.class)
        }))
@NamedNativeQuery(name = GET_ORDERS, query = GET_ORDERS_QUERY, resultSetMapping = ORDER_VIEW)
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
    @Column(name = "import_id")
    private Integer importId;

}

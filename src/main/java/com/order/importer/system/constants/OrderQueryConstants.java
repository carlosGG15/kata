package com.order.importer.system.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderQueryConstants {

    public static final String GET_ORDERS_QUERY = """
            SELECT o.order_id as orderId, op.name AS orderPriority, o.order_date AS orderDate, r.name as region, c.name as country, ot.name as orderType,
             ch.name as salesChannel, o.ship_date AS shipDate, o.units_sold AS unitsSold, o.unit_price AS unitPrice, o.total_revenue as totalRevenue, o.total_cost as totalCost, o.total_profit AS totalProfit
            FROM orders o
             LEFT JOIN order_priorities op ON o.order_priority_id = op.id
             LEFT JOIN countries c ON o.country_id = c.id
             LEFT JOIN regions r ON c.region_id =  r.id
             LEFT JOIN order_types ot ON o.order_type_id = ot.id
             LEFT JOIN channels ch ON o.channel_id = ch.id
            order by order_id asc;
        """;
}

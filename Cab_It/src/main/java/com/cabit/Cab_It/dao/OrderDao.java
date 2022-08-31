package com.cabit.Cab_It.dao;

import com.cabit.Cab_It.model.Order;
import java.util.List;

public interface OrderDao {

    Order addOrder(Order order);

    Order setOrder(Order preOrder, Order newOrder);

    Order deleteOrder(Order order);

    List<Order> getOrders(boolean dateTimeInDsc);

    <U> List<Order> getOrders(U entity, boolean dateTimeInDsc);

    Order reviewOrder(Order order, String review);

    Order acceptOrder(Order order);
}

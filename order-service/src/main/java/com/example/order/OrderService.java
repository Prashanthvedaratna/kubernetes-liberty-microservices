package com.example.order;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class OrderService {
    private ConcurrentHashMap<Long, Order> orderStore = new ConcurrentHashMap<>();
    private AtomicLong idGenerator = new AtomicLong(1);

    public List<Order> getAllOrders() {
        return new ArrayList<>(orderStore.values());
    }

    public Order getOrder(Long id) {
        return orderStore.get(id);
    }

    public Order createOrder(Order order) {
        Long id = idGenerator.getAndIncrement();
        order.setId(id);
        // Set current date if not provided
        if (order.getOrderDate() == null) {
            order.setOrderDate(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        }
        orderStore.put(id, order);
        return order;
    }

    public boolean deleteOrder(Long id) {
        return orderStore.remove(id) != null;
    }
}
package com.example.order;

public class Order {
    private Long id;
    private Long productId;
    private Long userId;
    private int quantity;
    private String orderDate; // ISO format, e.g., "2025-03-10T10:15:30"

    public Order() {}

    public Order(Long id, Long productId, Long userId, int quantity, String orderDate) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getOrderDate() { return orderDate; }
    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }
}
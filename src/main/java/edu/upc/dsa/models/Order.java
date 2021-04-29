package edu.upc.dsa.models;

public class Order {
    public String userId; 
    public String productId; 
    public int quantity; 

    public Order() {
    }

    public Order(String userId, String productId, int quantity) {
        this(); 
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order userId(String userId) {
        setUserId(userId);
        return this;
    }

    public Order productId(String productId) {
        setProductId(productId);
        return this;
    }

    public Order quantity(int quantity) {
        setQuantity(quantity);
        return this;
    }

  
    @Override
    public String toString() {
        return "{" +
            " userId='" + getUserId() + "'" +
            ", productId='" + getProductId() + "'" +
            ", quantity='" + getQuantity() + "'" +
            "}";
    }


}
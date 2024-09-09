package model;

import utils.Utils;

public class Product {
    private final String productId;
    private final String productName;
    private int quantity;
    private final Address address;

    public Product( String productName, int quantity, Address address) {
        this.productId = Utils.generateId();
        this.productName = productName;
        this.quantity = quantity;
        this.address = address;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public Address getAddress() {
        return address;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}

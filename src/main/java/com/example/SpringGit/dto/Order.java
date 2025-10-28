package com.example.SpringGit.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    private int productId;
    private String name;
    private String productType;
    private int qty;
    private double price;
    private String trackingId;

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getProductType() {
        return productType;
    }

    public int getQty() {
        return qty;
    }

    public double getPrice() {
        return price;
    }

    public String getTrackingId() {
        return trackingId;
    }
}

package com.imara.shipping.model;

public enum ShipmentBidStatus {
    PENDING(1, "PENDING"),
    REJECTED(2, "REJECTED"),
    ACCEPTED(3, "ACCEPTED");

    private final int id;
    private final String status;

    ShipmentBidStatus(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}

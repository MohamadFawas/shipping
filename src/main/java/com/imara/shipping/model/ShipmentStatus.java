package com.imara.shipping.model;

public enum ShipmentStatus {
    WAITING_ACCEPTANCE(1, "WAITING_ACCEPTANCE"),
    DRIVER_ASSIGNED(2, "DRIVER_ASSIGNED"),
    WAITING_PICKUP_CONFIRMATION(3, "WAITING_PICKUP_CONFIRMATION"),
    PICKUP_CONFIRMED(4, "PICKUP_CONFIRMED"),
    SHIPMENT_STARTED(5, "SHIPMENT_STARTED"),
    SHIPMENT_COMPLETED(6, "SHIPMENT_COMPLETED");

    private final int id;
    private final String status;

    ShipmentStatus(int id, String status) {
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

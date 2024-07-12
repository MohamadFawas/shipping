package com.imara.shipping.dto;

import lombok.Data;

@Data
public class CalculateDTO {
    private double pickupLatitude;
    private double pickupLongitude;
    private double dropOffLatitude;
    private double dropOffLongitude;
    private double weight;
    private double distance;
    private double price;
}

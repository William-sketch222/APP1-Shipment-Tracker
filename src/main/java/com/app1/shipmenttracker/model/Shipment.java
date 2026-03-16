package com.app1.shipmenttracker.model;

public class Shipment {

    private String id;
    private String trackingNumber;
    private String origin;
    private String destination;
    private double originLat;
    private double originLng;
    private double destLat;
    private double destLng;
    private String status;  // IN_TRANSIT, DELIVERED, PROCESSING
    private String carrier;
    private String estimatedDelivery;
    private String description;
    private String lastUpdate;

    public Shipment() {}

    public Shipment(String id, String trackingNumber, String origin, String destination,
                    double originLat, double originLng, double destLat, double destLng,
                    String status, String carrier, String estimatedDelivery,
                    String description, String lastUpdate) {
        this.id = id;
        this.trackingNumber = trackingNumber;
        this.origin = origin;
        this.destination = destination;
        this.originLat = originLat;
        this.originLng = originLng;
        this.destLat = destLat;
        this.destLng = destLng;
        this.status = status;
        this.carrier = carrier;
        this.estimatedDelivery = estimatedDelivery;
        this.description = description;
        this.lastUpdate = lastUpdate;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public double getOriginLat() { return originLat; }
    public void setOriginLat(double originLat) { this.originLat = originLat; }
    public double getOriginLng() { return originLng; }
    public void setOriginLng(double originLng) { this.originLng = originLng; }
    public double getDestLat() { return destLat; }
    public void setDestLat(double destLat) { this.destLat = destLat; }
    public double getDestLng() { return destLng; }
    public void setDestLng(double destLng) { this.destLng = destLng; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getCarrier() { return carrier; }
    public void setCarrier(String carrier) { this.carrier = carrier; }
    public String getEstimatedDelivery() { return estimatedDelivery; }
    public void setEstimatedDelivery(String estimatedDelivery) { this.estimatedDelivery = estimatedDelivery; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getLastUpdate() { return lastUpdate; }
    public void setLastUpdate(String lastUpdate) { this.lastUpdate = lastUpdate; }
}

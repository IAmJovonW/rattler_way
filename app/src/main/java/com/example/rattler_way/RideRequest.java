package com.example.rattler_way;

public class RideRequest {

    private String date;
    private String time;
    private String pickupLocation;
    private String destination;
    private String serviceType;
    private String status;
    private String customerId;
    private String driverId;

    private String rideId;




    public RideRequest(String date, String time, String pickupLocation, String destination, String serviceType, String status, String customerId,
                       String driverId, String rideId) {
        this.date = date;
        this.time = time;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
        this.serviceType = serviceType;
        this.status = status;
        this.customerId = customerId;
        this.driverId = driverId;
        this.rideId = rideId;


    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getRideId() {
        return rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }










    public RideRequest() {

    }


}
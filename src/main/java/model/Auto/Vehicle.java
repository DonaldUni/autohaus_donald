package model.Auto;

public class Vehicle {

    private long id = -1;
    private VehicleType vehicleType;
    private String vehicleDesignation;
    private String manufacturer;
    private String power;
    private double salesPrice;

    public Vehicle() {
    }

    public Vehicle(long id, VehicleType vehicleType, String vehicleDesignation, String manufacturer, String power, double salesPrice) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.vehicleDesignation = vehicleDesignation;
        this.manufacturer = manufacturer;
        this.power = power;
        this.salesPrice = salesPrice;
    }

    // getter und setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleDesignation() {
        return vehicleDesignation;
    }

    public void setVehicleDesignation(String vehicleDesignation) {
        this.vehicleDesignation = vehicleDesignation;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }
}

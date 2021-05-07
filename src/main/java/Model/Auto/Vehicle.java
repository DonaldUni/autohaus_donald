package Model.Auto;

public class Vehicle {

    private long id = 0;
    private String vehicleType;
    private String vehicleDesignation;
    private String manufacturer;
    private String power;
    private double salesPrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
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

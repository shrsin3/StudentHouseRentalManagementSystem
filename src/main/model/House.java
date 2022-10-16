package model;

public class House {

    private static Integer nextRegistrationNumber = 1;
    private Integer registrationNumber;
    private String address;
    private String city;
    private Double rentAmount;
    private String ownerName;
    private String ownerGender;
    private Boolean isRented;

    public House(String address,String city,Double rentAmount,
                      String ownerName,String ownerGender) {
        registrationNumber = nextRegistrationNumber++;
        this.address = address;
        this.city = city;
        this.rentAmount = rentAmount;
        this.ownerName = ownerName;
        this.ownerGender = ownerGender;
        this.isRented = false;
    }

    public Integer getRegistrationNumber() {
        return registrationNumber;
    }

    public String getAddress() {
        return this.address;
    }

    public String getCity() {
        return this.city;
    }

    public Double getRentAmount() {
        return this.rentAmount;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public String getOwnerGender() {
        return this.ownerGender;
    }

    public Boolean getIsRented() {
        return this.isRented;
    }

    public void changeIsRentedStatus() {
        if (this.isRented) {
            this.isRented = false;
        } else {
            this.isRented = true;
        }
    }

    public void modifyAddress(String newAddress) {
        this.address = newAddress;
    }

    public void modifyOwnerName(String newOwnerName) {
        this.ownerName = newOwnerName;
    }

    public void modifyRentAmount(Double newRentAmount) {
        this.rentAmount = newRentAmount;
    }

    public void modifyHouseCity(String newHouseCity) {
        this.city = newHouseCity;
    }

}

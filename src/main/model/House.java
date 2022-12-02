package model;

import org.json.JSONObject;
import persistence.Writable;

// This represents the details of the houses in the housing application
public class House implements Writable {

    private static Integer nextRegistrationNumber = 1;
    private Integer registrationNumber;
    private String address;
    private String city;
    private Double rentAmount;
    private String ownerName;
    private String ownerGender;
    private Boolean isRented;

    // REQUIRES: rentAmount > 0 and ownerGender should be one of Male, Female or Other
    // EFFECTS: creates a house with the given address, city, Rent Amount, Owner Name, Owner Gender.
    // The registration number is assigned automatically based on the number of houses in the system.
    public House(String address,String city,Double rentAmount,
                      String ownerName,String ownerGender, Boolean isRented) {
        registrationNumber = nextRegistrationNumber++;
        this.address = address;
        this.city = city;
        this.rentAmount = rentAmount;
        this.ownerName = ownerName;
        this.ownerGender = ownerGender;
        this.isRented = isRented;
        EventLog.getInstance().logEvent(new Event("House Created!"));
    }

    // EFFECTS: returns the registration number of the house.
    public Integer getRegistrationNumber() {
        return registrationNumber;
    }

    // EFFECTS: returns the address of the house.
    public String getAddress() {
        return this.address;
    }

    // EFFECTS: returns the city of the house.
    public String getCity() {
        return this.city;
    }

    // EFFECTS: returns the rent amount of the house.
    public Double getRentAmount() {
        return this.rentAmount;
    }

    // EFFECTS: returns the owner's name of the house.
    public String getOwnerName() {
        return this.ownerName;
    }

    // EFFECTS: returns the owner's gender of the house.
    public String getOwnerGender() {
        return this.ownerGender;
    }

    // EFFECTS: returns the rental status of the house.
    public Boolean getIsRented() {
        return this.isRented;
    }

    // MODIFIES: this
    // EFFECTS: If the rental status of the house is false then changes it to true otherwise false
    public void changeIsRentedStatus() {
        if (this.isRented) {
            this.isRented = false;
        } else {
            this.isRented = true;
        }
        EventLog.getInstance().logEvent(new Event("House Rental Status Changed!"));
    }


    //MODIFIES: this
    //EFFECTS: changes the address of the house to the given address
    public void modifyAddress(String newAddress) {
        this.address = newAddress;
        EventLog.getInstance().logEvent(new Event("House Address Modified!"));
    }

    //MODIFIES: this
    //EFFECTS: changes the owner's name of the house to the given name
    public void modifyOwnerName(String newOwnerName) {
        this.ownerName = newOwnerName;
        EventLog.getInstance().logEvent(new Event("House Owner Name Modified!"));
    }

    //REQUIRES: newRentAmount > 0
    //MODIFIES: this
    //EFFECTS: changes the rent amount of the house to the given rent amount
    public void modifyRentAmount(Double newRentAmount) {
        this.rentAmount = newRentAmount;
        EventLog.getInstance().logEvent(new Event("House Rent Amount Modified!"));
    }

    //MODIFIES: this
    //EFFECTS: changes the city of the house to the given city
    public void modifyHouseCity(String newHouseCity) {
        this.city = newHouseCity;
        EventLog.getInstance().logEvent(new Event("House City Modified!"));
    }

    // Method was taken and modified from toJson() in:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/model/Thingy.java
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Registration Number", Integer.toString(registrationNumber));
        json.put("Owner Name", ownerName);
        json.put("Owner Gender", ownerGender);
        json.put("Address", address);
        json.put("City of House", city);
        json.put("Rent Amount", Double.toString(rentAmount));
        json.put("Rental Status", Boolean.toString(isRented));
        return json;
    }

}

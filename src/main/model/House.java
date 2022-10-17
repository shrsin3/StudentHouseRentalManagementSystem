package model;

// This represents the details of the houses in the housing application
public class House {

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
    // The rental status for all houses is automatically assigned as false.
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
    }


    //MODIFIES: this
    //EFFECTS: changes the address of the house to the given address
    public void modifyAddress(String newAddress) {
        this.address = newAddress;
    }

    //MODIFIES: this
    //EFFECTS: changes the owner's name of the house to the given name
    public void modifyOwnerName(String newOwnerName) {
        this.ownerName = newOwnerName;
    }

    //REQUIRES: newRentAmount > 0
    //MODIFIES: this
    //EFFECTS: changes the rent amount of the house to the given rent amount
    public void modifyRentAmount(Double newRentAmount) {
        this.rentAmount = newRentAmount;
    }

    //MODIFIES: this
    //EFFECTS: changes the city of the house to the given city
    public void modifyHouseCity(String newHouseCity) {
        this.city = newHouseCity;
    }

}

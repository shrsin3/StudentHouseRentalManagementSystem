package ui;

import model.House;
import model.HouseList;
import persistence.JsonReader;
import persistence.JsonWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.lang.Iterable;
import java.util.Scanner;

// Housing Application
public class HousingApp {

    private static final String JSON_STORE = "./data/houseList.json";
    private HouseList myHouseList;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    //EFFECTS: runs the housing application
    public HousingApp() throws FileNotFoundException {

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runHousingApp();
    }

    //MODIFIES: this
    //EFFCETS: processes user input
    private void runHousingApp() {
        boolean keepRunning = true;
        Integer userInput;

        init();

        while (keepRunning) {

            printInstructions();
            userInput = input.nextInt();

            if (userInput == 7) {
                break;
            } else {
                performFunctions(userInput);
            }
        }

        System.out.print("Thank you!");

    }

    //MODIFIES: this
    //EFFECTS: initializes the house list
    private void init() {
        myHouseList = new HouseList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    //EFFECTS: display menu of options for the main housing application
    public void printInstructions() {
        System.out.print("You can perform the following operations using this system-:" + "\n");
        System.out.print("1. To register a new house into the system enter 1." + "\n");
        System.out.print("2. To view the list of registered houses enter 2" + "\n");
        System.out.print("3. To remove a house enter 3" + "\n");
        System.out.print("4. To modify details of existing house enter 4" + "\n");
        System.out.print("5. To save house list to file enter 5" + "\n");
        System.out.print("6. To load house list from file enter 6" + "\n");
        System.out.print("7. To quit enter 7" + "\n");
    }

    //MODIFIES: this
    //EFFECTS: processes user inputs
    private void performFunctions(Integer userInput) {

        if (userInput == 1) {
            addHouse();
        } else if (userInput == 2) {
            viewHouseList();
        } else if (userInput == 3) {
            removeHouse();
        } else if (userInput == 4) {
            modifyHouseList();
        } else if (userInput == 5) {
            saveHouseList();
        } else if (userInput == 6) {
            loadHouseList();
        }
    }

    //MODIFIES: this
    //EFFECTS: takes house information from user and adds house to myHouseList
    private void addHouse() {
        House userHouse = createHouse();
        myHouseList.addHouse(userHouse);

    }

    //EFFECTS: takes user input to create a house and returns the house
    private House createHouse() {
        String ownerName;
        String ownerGender;
        String address;
        Double rentAmount;
        String city;
        System.out.print("Enter Owner Name=");
        ownerName = input.next();
        System.out.print("Enter Owner Gender (F/M/Other)=");
        ownerGender = input.next();
        System.out.print("Enter Owner Address=");
        address = input.next();
        System.out.print("Enter Rent Amount=");
        rentAmount = input.nextDouble();
        System.out.print("Enter House City=");
        city = input.next();
        House userHouse = new House(address, city, rentAmount, ownerName, ownerGender, false);
        return userHouse;
    }


    //EFFECTS: displays information of all the houses in myHouseList
    private void viewHouseList() {

        String houseStatus;
        for (House house : myHouseList.getHouseList()) {
            System.out.print("House Registration Number=" + house.getRegistrationNumber() + "\n");
            System.out.print("House Owner Name=" + house.getOwnerName() + "\n");
            System.out.print("House Owner Gender=" + house.getOwnerGender() + "\n");
            System.out.print("House City=" + house.getCity() + "\n");
            System.out.print("House Address=" + house.getAddress() + "\n");
            System.out.print("House Rent Amount=" + house.getRentAmount() + "\n");
            if (house.getIsRented()) {
                System.out.print("House Rent Amount= Yes" + "\n");
            } else {
                System.out.print("House Rent Amount= No" + "\n");
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: takes user input for registration number and if the house with that registration number is present
    // in myHouseList, then remove that house
    public void removeHouse() {
        System.out.print("Enter House Registration Number= ");
        Integer regNumber = input.nextInt();
        Boolean houseFound = false;
        House houseToBeRemoved = null;

        for (House house : myHouseList.getHouseList()) {
            if (regNumber == house.getRegistrationNumber()) {
                houseToBeRemoved = house;
                houseFound = true;
                break;
            }
        }
        if (houseFound) {
            myHouseList.removeHouseByValue(houseToBeRemoved);
        } else {
            System.out.print("House Not Registered!");
        }
    }

    //MODIFIES: this
    //EFFECTS: takes user input to for modification of house in the myHouseList
    public void modifyHouseList() {

        boolean keepRunning = true;
        Integer userInput;


        while (keepRunning) {

            printInstructionsForModify();
            userInput = input.nextInt();
            if (userInput == 6) {
                break;
            } else {
                performFunctionsForModify(userInput);
            }
        }
    }


    //EFFECTS: displays menu of options for the modifications that are possible through this application
    public void printInstructionsForModify() {

        System.out.print("You can perform the following modification operations -:" + "\n");
        System.out.print("1. To change house address enter 1." + "\n");
        System.out.print("2. To change house owner name enter 2" + "\n");
        System.out.print("3. To change rent amount enter 3" + "\n");
        System.out.print("4. To change house city enter 4" + "\n");
        System.out.print("5. To change house rental status enter 5" + "\n");
        System.out.print("6. To quit enter 6" + "\n");

    }

    //MODIFIES: this
    //EFFECTS: process user input to perform modification function on houses if house found in myHouseList
    public void performFunctionsForModify(int userInput) {
        int registrationNumber;
        System.out.print("Enter your house Registration Number" + "\n");
        registrationNumber = input.nextInt();
        boolean houseFound = false;
        for (House house : myHouseList.getHouseList()) {
            if (house.getRegistrationNumber() == registrationNumber) {
                houseFound = true;
                if (userInput == 1) {
                    modifyHouseAddress(house);
                } else if (userInput == 2) {
                    modifyHouseOwnerName(house);
                } else if (userInput == 3) {
                    modifyHouseRentAmount(house);
                } else if (userInput == 4) {
                    modifyHouseCity(house);
                } else if (userInput == 5) {
                    house.changeIsRentedStatus();
                }
            }
        }
        if (!houseFound) {
            System.out.print("Incorrect Registration Number! House not found!!" + "\n");
        }
    }

    //MODIFIES: this
    //EFFECTS: takes the user input for new address and changes the given house address
    public void modifyHouseAddress(House house) {
        System.out.print("Enter your new address" + "\n");
        String newAddress = input.next();
        house.modifyAddress(newAddress);
        System.out.print("House Address Changed!" + "\n");
    }

    //MODIFIES: this
    //EFFECTS: takes the user input for new owner name and changes the given house owner's name
    public void modifyHouseOwnerName(House house) {
        System.out.print("Enter your new owner name" + "\n");
        String newOwnerName = input.next();
        house.modifyOwnerName(newOwnerName);
        System.out.print("House Owner Name Changed!" + "\n");
    }

    //MODIFIES: this
    //EFFECTS: takes the user input for new rent amount and changes the given house rent amount
    public void modifyHouseRentAmount(House house) {
        System.out.print("Enter your new rent amount" + "\n");
        Double newRentAmount = input.nextDouble();
        house.modifyRentAmount(newRentAmount);
        System.out.print("House Rent Amount Changed!" + "\n");
    }

    //MODIFIES: this
    //EFFECTS: takes the user input for new city and changes the given house city
    public void modifyHouseCity(House house) {
        System.out.print("Enter your new city" + "\n");
        String newHouseCity = input.next();
        house.modifyHouseCity(newHouseCity);
        System.out.print("House Rent Amount Changed!" + "\n");
    }

    // EFFECTS: saves the houseList to file
    private void saveHouseList() {
        try {
            jsonWriter.open();
            jsonWriter.write(myHouseList);
            jsonWriter.close();
            System.out.println("House List has been saved " + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads houseList from file
    private void loadHouseList() {
        try {
            myHouseList = jsonReader.read();
            System.out.println("House List has been Loaded" + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}

package ui;

import model.House;
import model.HouseList;

import java.util.ArrayList;
import java.lang.Iterable;
import java.util.Scanner;

public class HousingApp {

    private HouseList myHouseList;
    private Scanner input;


    public HousingApp() {

        runHousingApp();
    }

    private void runHousingApp() {
        boolean keepRunning = true;
        Integer userInput;

        init();

        while (keepRunning) {

            printInstructions();
            userInput = input.nextInt();

            if (userInput == 5)  {
                break;
            } else {
                performFunctions(userInput);
            }
        }

        System.out.print("Thank you!");

    }

    private void init() {
        myHouseList = new HouseList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    public void printInstructions() {
        System.out.print("You can perform the following operations using this system-:" + "\n");
        System.out.print("1. To register a new house into the system enter 1." + "\n");
        System.out.print("2. To view the list of registered houses enter 2" + "\n");
        System.out.print("3. To remove a house enter 3" + "\n");
        System.out.print("4. To modify details of existing house enter 4" + "\n");
        System.out.print("5. To quit enter 5" + "\n");
    }

    private void performFunctions(Integer userInput) {

        if (userInput == 1) {
            addHouse();
        } else if (userInput == 2) {
            viewHouseList();
        } else if (userInput == 3) {
            removeHouse();
        } else if (userInput == 4) {
            modifyHouseList();
        }
    }

    private void addHouse() {
        House userHouse = createHouse();
        myHouseList.addHouse(userHouse);

    }

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
        House userHouse = new House(address,city,rentAmount,ownerName,ownerGender);
        return userHouse;
    }


    private void viewHouseList() {

        String houseStatus;
        for (House house: myHouseList.getHouseList()) {
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

    public void removeHouse() {
        System.out.print("Enter House Registration Number= ");
        Integer regNumber = input.nextInt();
        Boolean houseFound = false;

        for (House house: myHouseList.getHouseList()) {
            if (regNumber == house.getRegistrationNumber()) {
                myHouseList.removeHouseByValue(house);
                houseFound = true;
            }
        }
        if (!houseFound) {
            System.out.print("House Not Registered!");
        }
    }

    public void modifyHouseList() {

        boolean keepRunning = true;
        Integer userInput;


        while (keepRunning) {

            printInstructionsForModify();
            userInput = input.nextInt();
            if (userInput == 6)  {
                break;
            } else {
                performFunctionsForModify(userInput);
            }
        }
    }


    public void printInstructionsForModify() {

        System.out.print("You can perform the following modification operations -:" + "\n");
        System.out.print("1. To change house address enter 1." + "\n");
        System.out.print("2. To change house owner name enter 2" + "\n");
        System.out.print("3. To change rent amount enter 3" + "\n");
        System.out.print("4. To change house city enter 4" + "\n");
        System.out.print("5. To change house rental status enter 5" + "\n");
        System.out.print("6. To quit enter 6" + "\n");

    }

    public void performFunctionsForModify(int userInput) {
        int registrationNumber;
        System.out.print("Enter your house Registration Number" + "\n");
        registrationNumber = input.nextInt();
        boolean houseFound = false;
        for (House house: myHouseList.getHouseList()) {
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

    public void modifyHouseAddress(House house) {
        System.out.print("Enter your new address" + "\n");
        String newAddress = input.next();
        house.modifyAddress(newAddress);
        System.out.print("House Address Changed!" + "\n");
    }

    public void modifyHouseOwnerName(House house) {
        System.out.print("Enter your new owner name" + "\n");
        String newOwnerName = input.next();
        house.modifyOwnerName(newOwnerName);
        System.out.print("House Owner Name Changed!" + "\n");
    }

    public void modifyHouseRentAmount(House house) {
        System.out.print("Enter your new rent amount" + "\n");
        Double newRentAmount = input.nextDouble();
        house.modifyRentAmount(newRentAmount);
        System.out.print("House Rent Amount Changed!" + "\n");
    }

    public void modifyHouseCity(House house) {
        System.out.print("Enter your new city" + "\n");
        String newHouseCity = input.next();
        house.modifyHouseCity(newHouseCity);
        System.out.print("House Rent Amount Changed!" + "\n");
    }

}

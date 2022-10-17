package model;

import java.util.ArrayList;

// this class the represents a collection of all the houses that are registered in the system
public class HouseList {

    private ArrayList<House> houseList;

    //EFFECTS: creates a list for house
    public HouseList() {
        houseList = new ArrayList<House>();
    }

    //EFFECTS: returns the list of houses i.e. houseList
    public ArrayList<House> getHouseList() {
        return houseList;
    }

    //MODIFIES: this
    //EFFECTS: adds house to the houseList
    public void addHouse(House house) {
        houseList.add(house);
    }

    //REQUIRES: house should be present in the list
    //MODIFIES: this
    //EFFECTS: removes house from the houseList. If house is present in the list multiple times then
    // removes the first occurrence of the house
    public void removeHouseByValue(House house) {
        houseList.remove(house);
    }
    
}

package model;

import java.util.ArrayList;

public class HouseList {

    private ArrayList<House> houseList;

    public HouseList() {
        houseList = new ArrayList<House>();
    }

    public ArrayList<House> getHouseList() {
        return houseList;
    }

    public void addHouse(House house) {
        houseList.add(house);
    }

    public void removeHouseByValue(House house) {
        houseList.remove(house);
    }
    
}

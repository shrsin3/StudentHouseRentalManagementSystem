package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// this class the represents a collection of all the houses that are registered in the system
public class HouseList implements Writable {

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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("houses", housesToJson());
        return json;
    }

    // EFFECTS: returns house in this houseList as a JSON array
    private JSONArray housesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (House house : this.getHouseList()) {
            jsonArray.put(house.toJson());
        }

        return jsonArray;
    }
    
}

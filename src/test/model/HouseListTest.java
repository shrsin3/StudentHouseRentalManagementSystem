package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HouseListTest {

    private House house;
    private House house2;
    private House house3;
    private House house4;
    private HouseList houseList;
    EventLog events;

    @BeforeEach
    public void createHouseList() {

        house = new House("1203 My Road", "Vancouver", 1000.0,
                "S Owner", "Male", false);
        house2 = new House("1000 The Road", "Burnaby", 765.8,
                "The Owner", "Female", false);
        house3 = new House("1000 The Road", "Toronto", 1500.0,
                "MILLI CO.", "Other", false);
        house3 = new House("457 Underground", "Montreal", 890.98,
                "Henry", "Male", false);

        houseList = new HouseList();
    }

    @Test
    public void HousingListEmptyTest() {

        assertEquals(0, houseList.getHouseList().size());

    }

    @Test
    public void HousingListNonEmptyTest() {

        assertEquals(0, houseList.getHouseList().size());
        houseList.addHouse(house4);
        houseList.addHouse(house3);

        assertEquals(2, houseList.getHouseList().size());

        assertTrue(houseList.getHouseList().contains(house4));
        assertTrue(houseList.getHouseList().contains(house3));

        assertEquals(house4, houseList.getHouseList().get(0));
        assertEquals(house3, houseList.getHouseList().get(1));

        List<Event> l = new ArrayList<Event>();

        EventLog el = events.getInstance();
        for (Event next : el) {
            l.add(next);
        }

        assertEquals("House Added to system!", l.get(l.size()-1).getDescription());
        assertEquals("House Added to system!", l.get(l.size()-2).getDescription());
        assertTrue(!l.get(l.size()-3).getDescription().equals("House Added to system!"));


    }

    @Test
    public void addHouseOneTest() {

        assertEquals(0, houseList.getHouseList().size());
        houseList.addHouse(house);
        assertEquals(1, houseList.getHouseList().size());
        assertEquals(house, houseList.getHouseList().get(0));

        List<Event> l = new ArrayList<Event>();

        EventLog el = events.getInstance();
        for (Event next : el) {
            l.add(next);
        }
        assertEquals("House Added to system!", l.get(l.size()-1).getDescription());
        assertTrue(!l.get(l.size()-2).getDescription().equals("House Added to system!"));


    }

    @Test
    public void addHouseMultipleDifferentHouseTest() {

        assertEquals(0, houseList.getHouseList().size());

        houseList.addHouse(house);
        houseList.addHouse(house2);
        houseList.addHouse(house3);

        assertEquals(3, houseList.getHouseList().size());

        assertTrue(houseList.getHouseList().contains(house));
        assertTrue(houseList.getHouseList().contains(house2));
        assertTrue(houseList.getHouseList().contains(house3));

        assertEquals(house, houseList.getHouseList().get(0));
        assertEquals(house2, houseList.getHouseList().get(1));
        assertEquals(house3, houseList.getHouseList().get(2));

        List<Event> l = new ArrayList<Event>();

        EventLog el = events.getInstance();
        for (Event next : el) {
            l.add(next);
        }
        assertEquals("House Added to system!", l.get(l.size()-1).getDescription());
        assertEquals("House Added to system!", l.get(l.size()-2).getDescription());
        assertEquals("House Added to system!", l.get(l.size()-3).getDescription());
        assertTrue(!l.get(l.size()-4).getDescription().equals("House Added to system!"));


    }

    @Test
    public void addHouseMultipleSameHouseTest() {

        assertEquals(0, houseList.getHouseList().size());

        houseList.addHouse(house);
        houseList.addHouse(house);
        houseList.addHouse(house);
        houseList.addHouse(house);

        assertEquals(4, houseList.getHouseList().size());

        assertTrue(houseList.getHouseList().contains(house));
        assertFalse(houseList.getHouseList().contains(house2));
        assertFalse(houseList.getHouseList().contains(house3));
        assertFalse(houseList.getHouseList().contains(house4));

        assertEquals(house, houseList.getHouseList().get(0));
        assertEquals(house, houseList.getHouseList().get(1));
        assertEquals(house, houseList.getHouseList().get(2));
        assertEquals(house, houseList.getHouseList().get(3));

        List<Event> l = new ArrayList<Event>();

        EventLog el = events.getInstance();
        for (Event next : el) {
            l.add(next);
        }
        assertEquals("House Added to system!", l.get(l.size()-1).getDescription());
        assertEquals("House Added to system!", l.get(l.size()-2).getDescription());
        assertEquals("House Added to system!", l.get(l.size()-3).getDescription());
        assertEquals("House Added to system!", l.get(l.size()-4).getDescription());
        assertTrue(!l.get(l.size()-5).getDescription().equals("House Added to system!"));

    }

    @Test
    public void removeHouseByValueOneTest() {

        assertEquals(0, houseList.getHouseList().size());
        houseList.addHouse(house);
        assertEquals(1, houseList.getHouseList().size());
        houseList.removeHouseByValue(house);
        assertEquals(0, houseList.getHouseList().size());

        List<Event> l = new ArrayList<Event>();

        EventLog el = events.getInstance();
        for (Event next : el) {
            l.add(next);
        }

        assertEquals("House Removed from system!", l.get(l.size()-1).getDescription());
        assertTrue(!l.get(l.size()-2).getDescription().equals("House Removed from system!"));
    }

    @Test
    public void removeHouseByValueOneInNonEmptyListTest() {

        assertEquals(0, houseList.getHouseList().size());
        houseList.addHouse(house);
        houseList.addHouse(house);
        houseList.addHouse(house2);
        assertEquals(3, houseList.getHouseList().size());

        houseList.removeHouseByValue(house);
        assertEquals(2, houseList.getHouseList().size());
        assertTrue(houseList.getHouseList().contains(house));
        assertTrue(houseList.getHouseList().contains(house2));
        assertFalse(houseList.getHouseList().contains(house3));
        assertFalse(houseList.getHouseList().contains(house4));

        assertEquals(house, houseList.getHouseList().get(0));
        assertEquals(house2, houseList.getHouseList().get(1));

        List<Event> l = new ArrayList<Event>();

        EventLog el = events.getInstance();
        for (Event next : el) {
            l.add(next);
        }

        assertEquals("House Removed from system!", l.get(l.size()-1).getDescription());
        assertTrue(!l.get(l.size()-2).getDescription().equals("House Removed from system!"));

    }

    @Test
    public void removeHouseByValueMultilpleTimesTest() {

        assertEquals(0, houseList.getHouseList().size());
        houseList.addHouse(house);
        houseList.addHouse(house2);
        houseList.addHouse(house3);
        houseList.addHouse(house);
        houseList.addHouse(house3);
        houseList.addHouse(house4);
        assertEquals(6, houseList.getHouseList().size());

        houseList.removeHouseByValue(house);
        houseList.removeHouseByValue(house2);
        houseList.removeHouseByValue(house4);

        assertEquals(3, houseList.getHouseList().size());
        assertTrue(houseList.getHouseList().contains(house));
        assertFalse(houseList.getHouseList().contains(house2));
        assertTrue(houseList.getHouseList().contains(house3));
        assertFalse(houseList.getHouseList().contains(house4));

        assertEquals(house3, houseList.getHouseList().get(0));
        assertEquals(house, houseList.getHouseList().get(1));
        assertEquals(house3, houseList.getHouseList().get(2));

        List<Event> l = new ArrayList<Event>();

        EventLog el = events.getInstance();
        for (Event next : el) {
            l.add(next);
        }

        assertEquals("House Removed from system!", l.get(l.size()-1).getDescription());
        assertEquals("House Removed from system!", l.get(l.size()-2).getDescription());
        assertEquals("House Removed from system!", l.get(l.size()-3).getDescription());
        assertTrue(!l.get(l.size()-4).getDescription().equals("House Removed from system!"));
    }

    @Test
    public void toJasonEmptyHouseList() {
        JSONObject jsonObject;

        jsonObject = houseList.toJson();
        assertTrue(jsonObject.has("houses"));
        JSONArray houses = jsonObject.getJSONArray("houses");
        assertTrue(houses.isEmpty());


    }

    @Test
    public void toJasonOneHouseList() {
        JSONObject jsonObject;

        houseList.addHouse(house);
        jsonObject = houseList.toJson();
        assertTrue(jsonObject.has("houses"));
        JSONArray houses = jsonObject.getJSONArray("houses");
        assertFalse(houses.isEmpty());
        assertEquals(1, houses.length());
        houses.get(0).equals(house.toJson());

    }

    @Test
    public void toJasonMultipleHouseList() {
        JSONObject jsonObject;

        houseList.addHouse(house);
        houseList.addHouse(house2);
        houseList.addHouse(house3);
        houseList.addHouse(house2);
        jsonObject = houseList.toJson();
        assertTrue(jsonObject.has("houses"));
        JSONArray houses = jsonObject.getJSONArray("houses");
        assertFalse(houses.isEmpty());
        assertEquals(4, houses.length());
        houses.get(0).equals(house.toJson());
        houses.get(1).equals(house2.toJson());
        houses.get(2).equals(house3.toJson());
        houses.get(3).equals(house2.toJson());
    }

}



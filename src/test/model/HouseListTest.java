package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HouseListTest {

    private House house;
    private House house2;
    private House house3;
    private House house4;
    private HouseList houseList;

    @BeforeEach
    public void createHouseList() {

        house = new House("1203 My Road", "Vancouver", 1000.0,
                "S Owner", "Male", false );
        house2 = new House("1000 The Road", "Burnaby", 765.8,
                "The Owner", "Female", false );
        house3 = new House("1000 The Road", "Toronto", 1500.0,
                "MILLI CO.", "Other" , false);
        house3 = new House("457 Underground", "Montreal", 890.98,
                "Henry", "Male" , false);

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

    }

    @Test
    public void addHouseOneTest() {

        assertEquals(0, houseList.getHouseList().size());
        houseList.addHouse(house);
        assertEquals(1, houseList.getHouseList().size());
        assertEquals(house, houseList.getHouseList().get(0));

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

    }

    @Test
    public void removeHouseByValueOneTest() {

        assertEquals(0, houseList.getHouseList().size());
        houseList.addHouse(house);
        assertEquals(1, houseList.getHouseList().size());
        houseList.removeHouseByValue(house);
        assertEquals(0, houseList.getHouseList().size());
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

    }
}


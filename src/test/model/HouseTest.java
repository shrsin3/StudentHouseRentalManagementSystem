package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class HouseTest {

    private House house;
    private House house2;
    EventLog events;

    @BeforeEach
    public void createHouse() {
        house = new House("1203 My Road", "Vancouver", 1000.0,
                "S Owner", "Male", false);
        house2 = new House("1000 The Road", "Burnaby", 765.8,
                "The Owner", "Female", false);

    }

    @Test
    public void houseTest() {
        assertEquals("1203 My Road", house.getAddress());
        assertEquals("Vancouver", house.getCity());
        assertEquals(1000.0, house.getRentAmount());
        assertEquals("S Owner", house.getOwnerName());
        assertEquals("Male", house.getOwnerGender());
        assertTrue( house.getRegistrationNumber()>0 &&
                house.getRegistrationNumber() != house2.getRegistrationNumber());
        assertFalse(house.getIsRented());

        assertEquals("1000 The Road", house2.getAddress());
        assertEquals("Burnaby", house2.getCity());
        assertEquals(765.8, house2.getRentAmount());
        assertEquals("The Owner", house2.getOwnerName());
        assertEquals("Female", house2.getOwnerGender());
        assertTrue( house2.getRegistrationNumber()>0 &&
                house.getRegistrationNumber() != house2.getRegistrationNumber());
        assertFalse(house2.getIsRented());

        List<Event> l = new ArrayList<Event>();

        EventLog el = events.getInstance();
        for (Event next : el) {
            l.add(next);
        }
        assertEquals("House Created!", l.get(0).getDescription());



    }

    @Test
    public void getRegistrationNumberTest() {

        assertTrue( house.getRegistrationNumber()>0 &&
                house.getRegistrationNumber() != house2.getRegistrationNumber());
        assertTrue( house2.getRegistrationNumber()>0 &&
                house.getRegistrationNumber() != house2.getRegistrationNumber());
    }

    @Test
    public void getAddressTest() {
        assertEquals("1203 My Road", house.getAddress());
        assertEquals("1000 The Road", house2.getAddress());
    }

    @Test
    public void getCityTest() {
        assertEquals("Vancouver", house.getCity());
        assertEquals("Burnaby", house2.getCity());
    }

    @Test
    public void getRentAmountTest() {
        assertEquals(1000.0, house.getRentAmount());
        assertEquals(765.8, house2.getRentAmount());
    }

    @Test
    public void getOwnerNameTest() {
        assertEquals("S Owner", house.getOwnerName());
        assertEquals("The Owner", house2.getOwnerName());
    }

    @Test
    public void getOwnerGenderTest() {
        assertEquals("Male", house.getOwnerGender());
        assertEquals("Female", house2.getOwnerGender());
    }

    @Test
    public void getIsRentedTest() {
        assertFalse(house.getIsRented());
        assertFalse(house2.getIsRented());
    }

    @Test
    public void changeIsRentedStatusToTrueTest() {
        house.changeIsRentedStatus();
        house2.changeIsRentedStatus();
        assertTrue(house.getIsRented());
        assertTrue(house2.getIsRented());

        List<Event> l = new ArrayList<Event>();

        EventLog el = events.getInstance();
        for (Event next : el) {
            l.add(next);
        }
        assertEquals("House Rental Status Changed!", l.get(l.size()-1).getDescription());
        assertEquals("House Rental Status Changed!", l.get(l.size()-2).getDescription());
        assertTrue(!l.get(l.size()-3).getDescription().equals("House Rental Status Changed!"));

    }


    @Test
    public void changeIsRentedStatusMultipleTimesTest() {
        house.changeIsRentedStatus();
        house.changeIsRentedStatus();
        house.changeIsRentedStatus();
        house2.changeIsRentedStatus();
        house2.changeIsRentedStatus();

        assertTrue(house.getIsRented());
        assertFalse(house2.getIsRented());
        List<Event> l = new ArrayList<Event>();

        EventLog el = events.getInstance();
        for (Event next : el) {
            l.add(next);
        }
        assertEquals("House Rental Status Changed!", l.get(l.size()-1).getDescription());
        assertEquals("House Rental Status Changed!", l.get(l.size()-2).getDescription());
        assertEquals("House Rental Status Changed!", l.get(l.size()-3).getDescription());
        assertEquals("House Rental Status Changed!", l.get(l.size()-4).getDescription());
        assertEquals("House Rental Status Changed!", l.get(l.size()-5).getDescription());
        assertTrue(!l.get(l.size()-6).getDescription().equals("House Rental Status Changed!"));
    }

    @Test
    public void modifyAddressOnceTest() {

        assertEquals("1203 My Road", house.getAddress());
        house.modifyAddress("600 R Road");
        assertEquals("600 R Road", house.getAddress());

        assertEquals("1000 The Road", house2.getAddress());
        house2.modifyAddress("9274 This Address");
        assertEquals("9274 This Address", house2.getAddress());
        List<Event> l = new ArrayList<Event>();

        EventLog el = events.getInstance();
        for (Event next : el) {
            l.add(next);
        }
        assertEquals("House Address Modified!", l.get(l.size()-1).getDescription());
        assertEquals("House Address Modified!", l.get(l.size()-2).getDescription());
        assertTrue(!l.get(l.size()-3).getDescription().equals("House Address Modified!"));
    }

    @Test
    public void modifyAddressMultipleTest() {

        assertEquals("1203 My Road", house.getAddress());
        house.modifyAddress("600 R Road");
        house.modifyAddress("600 RMi Road");
        house.modifyAddress("1203 My Road");
        assertEquals("1203 My Road", house.getAddress());

        assertEquals("1000 The Road", house2.getAddress());
        house2.modifyAddress("9274 This Address");
        house2.modifyAddress("9274 This Address");
        house2.modifyAddress("87 road");
        house2.modifyAddress("1000 The Road");
        assertEquals("1000 The Road", house2.getAddress());
        List<Event> l = new ArrayList<Event>();

        EventLog el = events.getInstance();
        for (Event next : el) {
            l.add(next);
        }
        assertEquals("House Address Modified!", l.get(l.size()-1).getDescription());
        assertEquals("House Address Modified!", l.get(l.size()-2).getDescription());
        assertEquals("House Address Modified!", l.get(l.size()-3).getDescription());
        assertEquals("House Address Modified!", l.get(l.size()-4).getDescription());
        assertEquals("House Address Modified!", l.get(l.size()-5).getDescription());
        assertEquals("House Address Modified!", l.get(l.size()-6).getDescription());
        assertEquals("House Address Modified!", l.get(l.size()-7).getDescription());
        assertTrue(!l.get(l.size()-8).getDescription().equals("House Address Modified!"));

    }

    @Test
    public void modifyOwnerNameOnceTest() {

        assertEquals("S Owner", house.getOwnerName());
        house.modifyOwnerName("Mr. S");
        assertEquals("Mr. S", house.getOwnerName());

        assertEquals("The Owner", house2.getOwnerName());
        house2.modifyOwnerName("Mrs. THE");
        assertEquals("Mrs. THE", house2.getOwnerName());

        List<Event> l = new ArrayList<Event>();

        EventLog el = events.getInstance();
        for (Event next : el) {
            l.add(next);
        }
        assertEquals("House Owner Name Modified!", l.get(l.size()-1).getDescription());
        assertEquals("House Owner Name Modified!", l.get(l.size()-2).getDescription());
        assertTrue(!l.get(l.size()-3).getDescription().equals("House Owner Name Modified!"));
    }

    @Test
    public void modifyOwnerNameMultipleTimesTest() {

        assertEquals("S Owner", house.getOwnerName());
        house.modifyOwnerName("Mr. S");
        house.modifyOwnerName( "V S Mary");
        house.modifyOwnerName( "V Sera");
        assertEquals("V Sera", house.getOwnerName());

        assertEquals("The Owner", house2.getOwnerName());
        house2.modifyOwnerName("Mrs. THE");
        house2.modifyOwnerName("Tia");
        assertEquals("Tia", house2.getOwnerName());

        List<Event> l = new ArrayList<Event>();

        EventLog el = events.getInstance();
        for (Event next : el) {
            l.add(next);
        }
        assertEquals("House Owner Name Modified!", l.get(l.size()-1).getDescription());
        assertEquals("House Owner Name Modified!", l.get(l.size()-2).getDescription());
        assertEquals("House Owner Name Modified!", l.get(l.size()-3).getDescription());
        assertEquals("House Owner Name Modified!", l.get(l.size()-4).getDescription());
        assertEquals("House Owner Name Modified!", l.get(l.size()-5).getDescription());
        assertTrue(!l.get(l.size()-6).getDescription().equals("House Owner Name Modified!"));

    }

    @Test
    public void modifyRentAmountOnceTest() {

        assertEquals(1000.0, house.getRentAmount());
        house.modifyRentAmount(0.12);
        assertEquals(0.12, house.getRentAmount());

        assertEquals(765.8, house2.getRentAmount());
        house2.modifyRentAmount(1980.0);
        assertEquals(1980.0, house2.getRentAmount());

        List<Event> l = new ArrayList<Event>();

        EventLog el = events.getInstance();
        for (Event next : el) {
            l.add(next);
        }
        assertEquals("House Rent Amount Modified!", l.get(l.size()-1).getDescription());
        assertEquals("House Rent Amount Modified!", l.get(l.size()-2).getDescription());
        assertTrue(!l.get(l.size()-3).getDescription().equals("House Rent Amount Modified!"));


    }

    @Test
    public void modifyRentAmountMultipleTimesTest() {

        assertEquals(1000.0, house.getRentAmount());
        house.modifyRentAmount(0.12);
        house.modifyRentAmount(1200.8);
        assertEquals(1200.8, house.getRentAmount());

        assertEquals(765.8, house2.getRentAmount());
        house2.modifyRentAmount(1980.0);
        house2.modifyRentAmount(980.87);
        assertEquals(980.87, house2.getRentAmount());

        List<Event> l = new ArrayList<Event>();

        EventLog el = events.getInstance();
        for (Event next : el) {
            l.add(next);
        }
        assertEquals("House Rent Amount Modified!", l.get(l.size()-1).getDescription());
        assertEquals("House Rent Amount Modified!", l.get(l.size()-2).getDescription());
        assertEquals("House Rent Amount Modified!", l.get(l.size()-3).getDescription());
        assertEquals("House Rent Amount Modified!", l.get(l.size()-4).getDescription());
        assertTrue(!l.get(l.size()-5).getDescription().equals("House Rent Amount Modified!"));


    }

    @Test
    public void modifyHouseCityOnceTest() {
        assertEquals("Vancouver", house.getCity());
        house.modifyHouseCity("Burnaby");
        assertEquals("Burnaby", house.getCity());

        assertEquals("Burnaby", house2.getCity());
        house2.modifyHouseCity("Vancouver");
        assertEquals("Vancouver", house2.getCity());

        List<Event> l = new ArrayList<Event>();

        EventLog el = events.getInstance();
        for (Event next : el) {
            l.add(next);
        }
        assertEquals("House City Modified!", l.get(l.size()-1).getDescription());
        assertEquals("House City Modified!", l.get(l.size()-2).getDescription());
        assertTrue(!l.get(l.size()-3).getDescription().equals("House City Modified!"));
    }

    @Test
    public void modifyHouseCityMultipleTimesTest() {
        assertEquals("Vancouver", house.getCity());
        house.modifyHouseCity("Burnaby");
        house.modifyHouseCity("Toronto");
        house.modifyHouseCity("Montreal");
        assertEquals("Montreal", house.getCity());

        assertEquals("Burnaby", house2.getCity());
        house2.modifyHouseCity("Vancouver");
        house2.modifyHouseCity("Toronto");
        assertEquals("Toronto", house2.getCity());

        List<Event> l = new ArrayList<Event>();

        EventLog el = events.getInstance();
        for (Event next : el) {
            l.add(next);
        }

        assertEquals("House City Modified!", l.get(l.size()-1).getDescription());
        assertEquals("House City Modified!", l.get(l.size()-2).getDescription());
        assertEquals("House City Modified!", l.get(l.size()-3).getDescription());
        assertEquals("House City Modified!", l.get(l.size()-4).getDescription());
        assertEquals("House City Modified!", l.get(l.size()-5).getDescription());
        assertTrue(!l.get(l.size()-6).getDescription().equals("House City Modified!"));

    }

    @Test
    public void toJasonTest() {

        JSONObject json;
        json = house.toJson();
        assertEquals("1203 My Road", json.getString("Address"));
        assertTrue(Integer.parseInt(json.getString("Registration Number"))>0);
        assertEquals("S Owner", json.getString("Owner Name"));
        assertEquals("Male", json.getString("Owner Gender"));
        assertEquals("Vancouver", json.getString("City of House"));
        assertEquals("1000.0", json.getString("Rent Amount"));
        assertEquals("false", json.getString("Rental Status"));

    }

}
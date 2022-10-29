package persistence;

import model.House;
import static org.junit.jupiter.api.Assertions.*;

// Class was taken and modified from JsonTest in:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/test/persistence/JsonTest.java
public class JsonTest {
    protected void checkHouse(String address,String city,Double rentAmount,
                              String ownerName,String ownerGender, Boolean isRented, House house) {
        assertEquals(address, house.getAddress());
        assertEquals(city, house.getCity());
        assertEquals(rentAmount, house.getRentAmount());
        assertEquals(ownerName, house.getOwnerName());
        assertEquals(ownerGender, house.getOwnerGender());
        assertEquals(isRented, house.getIsRented());

    }
}
package persistence;

import model.House;
import static org.junit.jupiter.api.Assertions.*;

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
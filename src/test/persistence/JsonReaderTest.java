package persistence;

import model.House;
import model.HouseList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


// Class was taken and modified from JsonReaderTest in:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/test/persistence/JsonReaderTest.java
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            HouseList houseList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyHouseList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyHouseList.json");
        try {
            HouseList houseList = reader.read();
            assertEquals(0, houseList.getHouseList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralHouseList() {
        JsonReader reader = new JsonReader("./data/testReaderTesthouseList.json");
        try {
            HouseList houseList = reader.read();
            List<House> houses = houseList.getHouseList();
            assertEquals(2, houses.size());
            House house = houses.get(0);
            House house1 = houses.get(1);
            checkHouse("1203 My Road", "Vancouver", 1000.0,
                    "S Owner", "Male", false, houses.get(0));
            checkHouse("1000 The Road", "Burnaby", 765.8,
                    "The Owner", "Female", true, houses.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
package persistence;

import model.House;
import model.HouseList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


// Class was taken and modified from JsonWriterTest in:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/test/persistence/JsonWriterTest.java
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            HouseList houseList = new HouseList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyHouseList() {
        try {
            HouseList houseList = new HouseList();
            JsonWriter writer = new JsonWriter("./data/testReaderEmptyHouseList.json");
            writer.open();
            writer.write(houseList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderEmptyHouseList.json");
            houseList = reader.read();
            assertEquals(0, houseList.getHouseList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralHouseList() {
        try {
            HouseList houseList = new HouseList();
            houseList.addHouse(new House("1203 My Road", "Vancouver", 1000.0,
                    "S Owner", "Male", false));
            houseList.addHouse(new House("1000 The Road", "Burnaby", 765.8,
                    "The Owner", "Female", true));

            JsonWriter writer = new JsonWriter("./data/testReaderTesthouseList.json");
            writer.open();
            writer.write(houseList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderTesthouseList.json");
            houseList = reader.read();
            List<House> houses = houseList.getHouseList();
            assertEquals(2, houses.size());
            checkHouse("1203 My Road", "Vancouver", 1000.0,
                    "S Owner", "Male", false, houses.get(0));
            checkHouse("1000 The Road", "Burnaby", 765.8,
                    "The Owner", "Female", true, houses.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
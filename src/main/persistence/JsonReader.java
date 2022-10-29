package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.House;
import model.HouseList;
import org.json.*;

// Class was taken and modified from JsonReader in:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/persistence/JsonReader.java
// Represents a reader that reads houseList from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads houseList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public HouseList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseHouseList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses houseList from JSON object and returns it
    private HouseList parseHouseList(JSONObject jsonObject) {
        HouseList houseList = new HouseList();
        addThingies(houseList, jsonObject);
        return houseList;
    }

    // MODIFIES: houseList
    // EFFECTS: parses house from JSON object and adds them to houseList
    private void addThingies(HouseList houseList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("houses");
        for (Object json : jsonArray) {
            JSONObject nextHouse = (JSONObject) json;
            addThingy(houseList, nextHouse);
        }
    }

    // MODIFIES: houseList
    // EFFECTS: parses house from JSON object and adds it to houseList
    private void addThingy(HouseList houseList, JSONObject jsonObject) {
        String ownerName = jsonObject.getString("Owner Name");
        String ownerGender = jsonObject.getString("Owner Gender");
        String address = jsonObject.getString("Address");
        String city = jsonObject.getString("City of House");
        Double rentAmount = Double.parseDouble(jsonObject.getString("Rent Amount"));
        Boolean isRented = Boolean.parseBoolean(jsonObject.getString("Rental Status"));
        House thingy = new House(address, city,rentAmount,ownerName,ownerGender, isRented);
        houseList.addHouse(thingy);
    }
}

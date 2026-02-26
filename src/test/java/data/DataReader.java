package data;

import org.apache.commons.io.FileUtils;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class DataReader {
    public List<HashMap<String, String>> getJsonDataToHashMap() throws IOException {
        //reading json and convert json to string
        String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+ "//src//test//java//data//PurchaseOrders.json"), StandardCharsets.UTF_8);
        //converting string to hashmap
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){
        });
        return data;

    }
}

package Company.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		
		String jsonContent=FileUtils.readFileToString(new File(System.getProperty("user.dir")+ "\\src\\test\\java\\Company\\data\\Purchase.json"),StandardCharsets.UTF_8);
		
		//String To HashMap- JackSon DataBind
		ObjectMapper objectMapper = new ObjectMapper();

        // Parse JSON string into a HashMap using TypeReference
        List<HashMap<String, String>> data = objectMapper.readValue(jsonContent, new TypeReference <List<HashMap<String,String>>>() {
        	
        });
        return data;

	}

}

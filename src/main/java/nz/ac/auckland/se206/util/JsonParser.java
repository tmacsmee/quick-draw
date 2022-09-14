package nz.ac.auckland.se206.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

public class JsonParser {

    private final ObjectMapper mapper = new ObjectMapper();

    public Map<String, Map<String, Object>> parseAllUserData() throws IOException {
        return mapper.readValue(Paths.get("user_files/user_data.json").toFile(), Map.class);
    }

    public Object getProperty(String user, String property) throws IOException {
        Map<String, Map<String, Object>> allUserData = parseAllUserData();
        return allUserData.get(user).get(property);
    }
}

package nz.ac.auckland.se206.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class JsonParser {

  private final ObjectMapper mapper = new ObjectMapper();
  private Map<String, Map<String, Object>> allUserData;

  public JsonParser() {
    try {
      allUserData = mapper.readValue(Paths.get("user_files/user_data.json").toFile(), Map.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Object getProperty(String username, String property) {
    return allUserData.get(username).get(property);
  }

  public void addWordEncountered(String username, String word) {
    ((List<String>) (allUserData.get(username).get("words"))).add(word);
  }

  public void addUser(
      String username,
      String age,
      List<String> wordsEncountered,
      String gamesWon,
      String gamesLost,
      String bestTime) {
    Map<String, Object> userData =
        Map.of(
            "age",
            age,
            "wordsEncountered",
            wordsEncountered,
            "gamesWon",
            gamesWon,
            "gamesLost",
            gamesLost,
            "bestTime",
            bestTime);
    allUserData.put(username, userData);
  }

  public void mapToJson() {
    try {
      mapper.writeValue(Paths.get("user_files/user_data.json").toFile(), allUserData);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

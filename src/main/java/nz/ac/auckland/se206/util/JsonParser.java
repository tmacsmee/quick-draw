package nz.ac.auckland.se206.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Paths;
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

  /**
   * Checks if the given password is correct for the given username
   *
   * @param username The username of the user
   * @param password The password to check
   * @return True if the password is correct, false otherwise
   */
  public boolean isCorrectPassword(String username, String password) {
    return getProperty(username, "password").equals(password);
  }
}

package nz.ac.auckland.se206.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A utility class for parsing JSON files.
 *
 * <p>Uses the Jackson library to parse JSON files.
 */
public class JsonParser {

  private final ObjectMapper mapper = new ObjectMapper();
  private Map<String, Map<String, Object>> allUserData;

  public JsonParser() {
    try {
      if (!Paths.get(".user_files/user_data.json").toFile().exists()) {
        Paths.get(".user_files").toFile().mkdir(); // Create the directory if it doesn't exist.
        File file =
            new File(".user_files/user_data.json"); // Create the file and populate with empty node
        ObjectNode node = mapper.createObjectNode();
        mapper.writeValue(file, node);
      }
      allUserData = mapper.readValue(Paths.get(".user_files/user_data.json").toFile(), Map.class);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Gets the value of a JSON property
   *
   * @param username the username of the user
   * @param property the property to get the value of
   * @return the value of the property
   */
  public Object getProperty(String username, String property) {
    return allUserData.get(username).get(property);
  }

  /**
   * Checks if the inputted password matches the password stored in the JSON file
   *
   * @param username the username of the user
   * @param password the password to check
   * @return true if the password matches, false otherwise
   */
  public boolean isCorrectPassword(String username, String password) {
    return getProperty(username, "password").equals(password);
  }

  /**
   * Checks if the inputted username is already in use
   *
   * @param username the username to check
   * @return true if the username is not in use, false otherwise
   */
  public boolean isCorrectUsername(String username) {
    return allUserData.containsKey(username);
  }

  /**
   * Adds a word to the user's list of encountered words
   *
   * @param username the username of the user
   * @param word the word to add
   */
  public void addWordEncountered(String username, String word) {
    // Add a word if it is not already in the list
    if (!((List<String>) (getProperty(username, "wordsEncountered"))).contains(word)) {
      ((List<String>) (getProperty(username, "wordsEncountered"))).add(word);
      mapToJson();
    }
  }

  /**
   * Adds a user to the JSON file on signup
   *
   * @param username the username of the user
   * @param password the password of the user
   */
  public void addUser(String username, String password) {
    // Create a new blank user
    Map<String, Object> userData =
        new HashMap<>(
            Map.of(
                "password",
                password,
                "wordsEncountered",
                new ArrayList<String>(),
                "gamesWon",
                "0",
                "gamesLost",
                "0",
                "fastestTime",
                "0"));
    // Update the map with user data
    allUserData.put(username, userData);
    // Write the map to the JSON file
    mapToJson();
  }

  /**
   * Increments the number of games won by the user
   *
   * @param username the username of the user
   */
  public void incrementWins(String username) {
    int wins =
        Integer.parseInt((String) getProperty(username, "gamesWon")); // Get the number of wins
    allUserData.get(username).replace("gamesWon", Integer.toString(wins + 1));
    mapToJson();
  }

  /**
   * Increments the number of games lost by the user
   *
   * @param username the username of the user
   */
  public void incrementLosses(String username) {
    int losses =
        Integer.parseInt((String) getProperty(username, "gamesLost")); // Get the number of losses
    allUserData.get(username).replace("gamesLost", Integer.toString(losses + 1));
    mapToJson();
  }

  /** Maps the user data to the JSON file */
  public void mapToJson() {
    try {
      mapper.writeValue(
          Paths.get(".user_files/user_data.json").toFile(),
          allUserData); // Write the map to the JSON file
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void setFastestTime(String username, String time) {
    if (getProperty(username, "fastestTime").equals("0")) {
      allUserData.get(username).replace("fastestTime", time);
    } else {
      String fastestTime = (String) getProperty(username, "fastestTime");
      if (Integer.parseInt(time) < Integer.parseInt(fastestTime)) {
        allUserData.get(username).replace("fastestTime", time);
      }
    }
  }
}

package nz.ac.auckland.se206.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class DictionaryLookup {

  public static final String API_URL = "https://api.dictionaryapi.dev/api/v2/entries/en/";

  /**
   * Returns the definition of the given word.
   *
   * @param word The word to look up.
   * @return The definition of the word.
   * @throws IOException If the API call fails.
   */
  public String getDefinition(String word, String difficulty) throws IOException {

    ObjectMapper mapper = new ObjectMapper();

    // Retrieve the information as JSON string
    HashMap<String, HashMap<String, String>> definitions =
        mapper.readValue(new File("src/main/resources/definitions.json"), HashMap.class);

    return definitions.get(difficulty).get(word);
  }
}

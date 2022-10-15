package nz.ac.auckland.se206.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class DictionaryLookup {

  public static final String API_URL = "https://api.dictionaryapi.dev/api/v2/entries/en/";

  /**
   * Returns the definition of the given word.
   *
   * @param word The word to look up.
   * @return The definition of the word.
   * @throws IOException If the API call fails.
   */
  public String getDefinition(String word) throws IOException {
    // Setup the HTTP client for request
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder().url(API_URL + word).build();
    Response response = client.newCall(request).execute();
    ResponseBody responseBody = response.body();

    // Retrieve the information as JSON string
    String jsonString = responseBody.string();

    // Parse the JSON string, if there is no definition, return word not found
    try {
      JsonNode jsonNode = new ObjectMapper().readTree(jsonString);
      String definition =
          jsonNode
              .get(0)
              .get("meanings")
              .get(0)
              .get("definitions")
              .get(0)
              .get("definition")
              .asText();
      return definition;
    } catch (Exception e) {
      return "Word not found";
    }
  }
}

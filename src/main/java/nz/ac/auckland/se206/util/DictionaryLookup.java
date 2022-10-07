package nz.ac.auckland.se206.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class DictionaryLookup {

  private static final String API_URL = "https://api.dictionaryapi.dev/api/v2/entries/en/";

  // function that returns the definition of a word
  public String getDefinition(String word) throws IOException {
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder().url(API_URL + word).build();
    Response response = client.newCall(request).execute();
    ResponseBody responseBody = response.body();

    String jsonString = responseBody.string();

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

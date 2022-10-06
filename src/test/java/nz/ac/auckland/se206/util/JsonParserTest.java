package nz.ac.auckland.se206.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class JsonParserTest {

  private JsonParser parser;

  @Test
  void testGetProperty() {
    parser = new JsonParser();
    assertEquals("5", parser.getProperty("username1", "age"));
    assertEquals(
        "test1", ((List<String>) parser.getProperty("username2", "wordsEncountered")).get(0));
  }

  @Test
  void testIsCorrectUsername() {
    parser = new JsonParser();
    assertTrue(parser.isCorrectUsername("username1"));
    assertFalse(parser.isCorrectUsername("username3"));
  }
}

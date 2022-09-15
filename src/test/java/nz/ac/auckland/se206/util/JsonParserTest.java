package nz.ac.auckland.se206.util;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

class JsonParserTest {

  private JsonParser parser;

  @Test
  void testGetUsers() throws IOException {
    parser = new JsonParser();
    assertEquals("123", parser.getProperty("username1", "password"));
    assertEquals(
        "test1", ((List<String>) parser.getProperty("username2", "wordsEncountered")).get(0));
  }

  @Test
  void testIsCorrectPassword() throws IOException {
    parser = new JsonParser();
    assertTrue(parser.isCorrectPassword("username1", "123"));
    assertFalse(parser.isCorrectPassword("username1", "1234"));
  }
}

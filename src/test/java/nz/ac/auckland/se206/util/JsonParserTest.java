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
  void testIsCorrectPassword() {
    parser = new JsonParser();
    assertTrue(parser.isCorrectPassword("username1", "password1"));
    assertFalse(parser.isCorrectPassword("username1", "password2"));
  }

  @Test
  void testIsCorrectUsername() {
    parser = new JsonParser();
    assertTrue(parser.isCorrectUsername("username1"));
    assertFalse(parser.isCorrectUsername("username3"));
  }

  @Test
  void testAddUser() {
    parser = new JsonParser();
    parser.addUser("username3", "password3");
    assertEquals("password3", parser.getProperty("username3", "password"));
  }

  @Test
  void testMapToJson() {
    parser = new JsonParser();
    parser.addUser("username4", "password4");
    parser.mapToJson();
    parser = new JsonParser();
    assertEquals("password4", parser.getProperty("username4", "password"));
  }
}

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
    parser.addUser(
        "username3", "password3", "7", List.of("test1", "test2", "test3"), "1", "0", "1");
    assertEquals("password3", parser.getProperty("username3", "password"));
    assertEquals("7", parser.getProperty("username3", "age"));
    assertEquals(
        "test1", ((List<String>) parser.getProperty("username3", "wordsEncountered")).get(0));
    assertEquals("1", parser.getProperty("username3", "gamesWon"));
    assertEquals("0", parser.getProperty("username3", "gamesLost"));
    assertEquals("1", parser.getProperty("username3", "bestTime"));
  }

  @Test
  void testMapToJson() {
    parser = new JsonParser();
    parser.addUser(
        "username4", "password4", "8", List.of("test1", "test2", "test3"), "1", "0", "1");
    parser.mapToJson();
    parser = new JsonParser();
    assertEquals("password4", parser.getProperty("username4", "password"));
    assertEquals("8", parser.getProperty("username4", "age"));
    assertEquals(
        "test1", ((List<String>) parser.getProperty("username4", "wordsEncountered")).get(0));
    assertEquals("1", parser.getProperty("username4", "gamesWon"));
    assertEquals("0", parser.getProperty("username4", "gamesLost"));
    assertEquals("1", parser.getProperty("username4", "bestTime"));
  }
}

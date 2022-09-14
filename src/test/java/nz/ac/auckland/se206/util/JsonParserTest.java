package nz.ac.auckland.se206.util;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonParserTest {

    @Test
    void testGetUsers() throws IOException {
        JsonParser parser = new JsonParser();
        assertEquals("123", parser.getProperty("username1", "password"));
        assertEquals("test1", ((List<String>) parser.getProperty("username2", "wordsEncountered")).get(0));
    }
}

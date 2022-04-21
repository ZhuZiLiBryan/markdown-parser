import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Files;


public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void TestOne() {
        String finalContents = null;

        try {
            finalContents = Files.readString(Path.of("test-file.md"));
        } catch(IOException e) {
        }

        ArrayList<String> links = MarkdownParse.getLinks(finalContents);

        List<String> expected = List.of("https://something.com", "some-thing.html");

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), links.get(i));
        }
    }
}
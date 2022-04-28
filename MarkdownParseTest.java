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

    @Test
    public void TestTwo() {
        String finalContents = null;

        try {
            finalContents = Files.readString(Path.of("part_three.md"));
        } catch(IOException e) {
        }

        ArrayList<String> links = MarkdownParse.getLinks(finalContents);

        List<String> expected = List.of("https://docs.google.com/docu(ment/d/18ytCjxT_TptcXTZTdKBniBDvmdcseLP1zSUw2j6ZbPE/edit#", "https://ncia.wwnorton.com/ebookworldstogethe)r6-worldstogether6littleseagull4", "https://lego.com");

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), links.get(i));
        }
    }

    @Test
    public void TestThree() {
        String finalContents = null;

        try {
            finalContents = Files.readString(Path.of("no_brackets.md"));
        } catch(IOException e) {
        }

        ArrayList<String> links = MarkdownParse.getLinks(finalContents);

        assertEquals(0, links.size());
    }

    @Test
    public void TestFour() {
        String finalContents = null;

        try {
            finalContents = Files.readString(Path.of("image_test.md"));
        } catch(IOException e) {
        }

        ArrayList<String> links = MarkdownParse.getLinks(finalContents);

        List<String> expected = List.of("https://www.google.com/search?q=google+images&rlz=1C1VDKB_enUS957US957&oq=google&aqs=chrome.0.69i59j46i131i199i433i465i512j69i60l3j69i65j69i60l2.560j0j7&sourceid=chrome&ie=UTF-8");
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), links.get(i));
        }
    }

    //--------------USING PROVIDED TEST INPUTS-------------------------------
    @Test
    public void TestFive() {
        String finalContents = null;

        try {
            finalContents = Files.readString(Path.of("test-file2.md"));
        } catch(IOException e) {
        }

        ArrayList<String> links = MarkdownParse.getLinks(finalContents);

        List<String> expected = List.of("https://something.com", "some-page.html");
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), links.get(i));
        }
    }

    @Test
    public void TestSix() {
        String finalContents = null;

        try {
            finalContents = Files.readString(Path.of("test-file3.md"));
        } catch(IOException e) {
        }

        ArrayList<String> links = MarkdownParse.getLinks(finalContents);

        assertEquals(0, links.size());
    }

    @Test
    public void TestSeven() {
        String finalContents = null;

        try {
            finalContents = Files.readString(Path.of("test-file4.md"));
        } catch(IOException e) {
        }

        ArrayList<String> links = MarkdownParse.getLinks(finalContents);

        assertEquals(0, links.size());
    }

    @Test
    public void TestEight() {
        String finalContents = null;

        try {
            finalContents = Files.readString(Path.of("test-file5.md"));
        } catch(IOException e) {
        }

        ArrayList<String> links = MarkdownParse.getLinks(finalContents);

        assertEquals(0, links.size());
    }

    @Test
    public void TestNine() {
        String finalContents = null;

        try {
            finalContents = Files.readString(Path.of("test-file6.md"));
        } catch(IOException e) {
        }

        ArrayList<String> links = MarkdownParse.getLinks(finalContents);

        assertEquals(0, links.size());
    }

    @Test
    public void TestTen() {
        String finalContents = null;

        try {
            finalContents = Files.readString(Path.of("test-file7.md"));
        } catch(IOException e) {
        }

        ArrayList<String> links = MarkdownParse.getLinks(finalContents);

        assertEquals(0, links.size());
    }

    @Test
    public void TestEleven() {
        String finalContents = null;

        try {
            finalContents = Files.readString(Path.of("test-file8.md"));
        } catch(IOException e) {
        }

        ArrayList<String> links = MarkdownParse.getLinks(finalContents);

        List<String> expected = List.of("a link on the first line");
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), links.get(i));
        }
    }

    @Test
    public void Week5Task2() {
        String finalContents = null;

        try {
            finalContents = Files.readString(Path.of("test-file8.md"));
        } catch(IOException e) {
        }

        ArrayList<String> links = MarkdownParse.getLinks(finalContents);

        List<String> expected = List.of("bop"); //should be "a link on the first line"
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), links.get(i));
        }
    }




}
//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int openBracket = markdown.indexOf("[", currentIndex);
            int closeBracket = markdown.indexOf("]", openBracket);
            int openParen = markdown.indexOf("(", closeBracket);
            int closeParen = markdown.indexOf(")", openParen);

            //find the opening to next link
            int nextOpenBracket = markdown.indexOf("[", closeParen);
            //reached end of file
            if (nextOpenBracket == -1) {
                toReturn.add(markdown.substring(openParen + 1, closeParen));
                currentIndex = closeParen + 1;

                return toReturn;

            }
            String textBetween = markdown.substring(closeParen + 1, nextOpenBracket);
        
            //as long as there is a ")" to be found, keep shortening the string 
            //and keeping track of where that last close parenthesis is
            while (textBetween.indexOf(")") != -1) {
                closeParen = closeParen + textBetween.indexOf(")");
                System.out.println(closeParen);
            
                //last parentheses
                if (closeParen + 1 >= textBetween.length()) {
                    break;
                }
                textBetween = markdown.substring(closeParen + 1, nextOpenBracket);
                
            }

            
            toReturn.add(markdown.substring(openParen + 1, closeParen));
            currentIndex = closeParen + 1;
        }

        return toReturn;
    }


    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
    }
}

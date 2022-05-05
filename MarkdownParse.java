//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        boolean hadToShorten = false;
        
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int imageMarker = markdown.indexOf("!", currentIndex);

            int openBracket = markdown.indexOf("[", currentIndex);
            int closeBracket = markdown.indexOf("]", openBracket);

            int openParen = markdown.indexOf("(", closeBracket);
            int closeParen = markdown.indexOf(")", openParen);

            if (closeBracket == -1 || openParen <= 0 || (markdown.charAt(openParen - 1) != ']')) {
                currentIndex++;
                continue;
            }

            //find the opening to next link
            int nextOpenBracket = markdown.indexOf("[", closeParen);

            //not at end of file yet
            if (nextOpenBracket != -1) {
                String textBetween = markdown.substring(closeParen + 1, nextOpenBracket);
        
                //as long as there is a ")" to be found, keep shortening the string 
                //and keeping track of where that last close parenthesis is
                while (textBetween.indexOf(")") != -1) {
                    hadToShorten = true;
                    closeParen = closeParen + textBetween.indexOf(")");
                
                    //last parentheses
                    if (closeParen + 1 >= textBetween.length()) {
                        break;
                    }
                    textBetween = markdown.substring(closeParen + 1, nextOpenBracket);
                }

                if (hadToShorten) {
                    closeParen++;
                }

                currentIndex = closeParen + 1;
            } else {
                currentIndex = markdown.length();
            }

            if (openParen == -1) {
                currentIndex = markdown.length();
            }
            else if ((imageMarker == -1 || imageMarker > openBracket) && openBracket != -1) {
                toReturn.add(markdown.substring(openParen + 1, closeParen));
            }
        }

        return toReturn;
    }


    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
        System.out.println("change");
    }
}

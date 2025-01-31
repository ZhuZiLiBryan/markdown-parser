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

            int openTick = markdown.indexOf("`", currentIndex);

            int openBracket = markdown.indexOf("[", currentIndex);
            int closeBracket = markdown.indexOf("]", openBracket);

            int closeTick = markdown.indexOf("`", openTick + 1);

            int openParen = markdown.indexOf("(", closeBracket);
            int closeParen = markdown.indexOf(")", openParen);

            if (closeBracket == -1 || openParen <= 0 || (markdown.charAt(openParen - 1) != ']')) {
                currentIndex++;
                continue;
            }

            if ((openBracket > openTick && openBracket < closeTick)) {
                currentIndex = closeTick + 1;
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
                    if (textBetween.indexOf(")") == 0) {
                        closeParen = closeParen + textBetween.indexOf(")") + 1;
                    } else {
                        closeParen = closeParen + textBetween.indexOf(")");
                    }
                
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
            else if (((imageMarker == -1 || imageMarker > openBracket) && openBracket != -1)) {
                String returnString = markdown.substring(openParen + 1, closeParen);
                returnString = returnString.trim();

                int nestedOpenBracket = returnString.indexOf("[");
                int nestedCloseBracket = returnString.indexOf("]");

                if (nestedCloseBracket != -1 && returnString.charAt(nestedCloseBracket - 1) != '\\' &&
                    returnString.indexOf(")") != -1) {
                    returnString = returnString.substring(0, returnString.indexOf(")"));
                }

                if (nestedOpenBracket != -1 && returnString.charAt(nestedOpenBracket - 1) != '\\') {
                    returnString = returnString.substring(nestedCloseBracket + 1);
                    int nestedOpenParen = returnString.indexOf("(");
                    returnString = returnString.substring(nestedOpenParen + 1);
                }

                    
                toReturn.add(returnString);
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

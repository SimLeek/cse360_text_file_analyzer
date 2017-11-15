import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.floor;

public class Analysis {

    private int numOfLines = 0; //a
    private int numOfBlankLines = 0; //b
    private int numOfSpaces = 0; //c
    private int numOfWords = 0; //d
    private int numTotalChar = 0; //e1, f1

    Analysis(String fileName){
        //myFile = new File(fileName);
        //System.out.println("File name to be analyzed: " + myFile.toString()); //testing for correct filename

        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader buff = new BufferedReader(reader);

            String line;
            List<String> contentList = new ArrayList<String>();

            while ((line = buff.readLine()) != null) {

                contentList.add(line);

                //Computing required file reports:

                //a. # Lines:
                numOfLines++;

                //b. # Blank Lines:
                if (line.length() == 0){
                    numOfBlankLines++;
                }

                //c. # Spaces:
                for (int i = 0; i < line.length(); i++){
                    char currChar = line.charAt(i);
                    if (currChar == ' '){
                        numOfSpaces++;
                    }

                    numTotalChar++;
                }

                //d. # Words:
                String trim = line.trim();
                numOfWords = trim.isEmpty() ? 0 : trim.split("\\s+").length;

            }

            buff.close();
        }
        catch (FileNotFoundException e){
            System.out.println("Unable to open " + fileName + "\n" + e.getMessage());
        }
        catch (IOException e){
            System.out.println("Error reading " + fileName);
            e.printStackTrace();
        }
    }

    public int numLines(){ //returns number of lines in the text file
        System.out.println("Number of lines: " + numOfLines);
        return numOfLines;
    }

    public int numBlankLines() {
        System.out.println("Number of blank lines: " + numOfBlankLines);
        return numOfBlankLines;
    }

    public int numSpaces(){
        System.out.println("Number of spaces: " + numOfSpaces);
        return numOfSpaces;
    }

    public int numWords() {
        System.out.println("Number of words: " + numOfWords);
        return numOfWords;
    }

    public int avgCharPerLine(){
        int avgChar = (int) floor(numTotalChar / numOfLines);
        System.out.println("Average number of characters per line: " + avgChar);
        return avgChar;
    }

    public int avgWordLength(){
        int avgWord;
        if (numOfWords == 0){
            avgWord = 0;
        }
        else {
            avgWord = (numTotalChar - numOfSpaces) / numOfWords;
        }

        return avgWord;
    }

    public String mostCommonWords() {
        return " ";
    }

}

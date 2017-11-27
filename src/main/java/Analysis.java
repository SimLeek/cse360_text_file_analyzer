import java.io.*;
import java.nio.Buffer;
import java.util.*;

import static java.lang.Math.floor;

public class Analysis {

    private int numOfLines; //a
    private int numOfBlankLines; //b
    private int numOfSpaces; //c
    private int numOfWords; //d
    private int numTotalChar; //e1, f1
    private List<String> contentList;
    private String mostCommonWord; //g
    private File file;

    Analysis(String filename){
        // Initializing all the instance variables to empty.
        this.file = new File(filename);
        this.numOfLines = 0;
        this.numOfBlankLines = 0;
        this.numOfSpaces = 0;
        this.numOfWords = 0;
        this.numTotalChar = 0;
        mostCommonWord = "";

        readFile();
    }

    // Reading all the file line by line to extract file statistics.
    public void readFile() {
        String line;
        this.contentList = new ArrayList<String>();
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(this.file));
            while ((line = buffer.readLine()) != null) {
                this.contentList.add(line); // Adding the current line into our content list.
                this.numOfLines++; // Incrementing the count of lines in the files.
                if (line.length() == 0) { // Found a blank line.
                    this.numOfBlankLines++;
                }
                // Reading number of space within each individual line.
                for (int i = 0; i < line.length(); i++){
                    char currChar = line.charAt(i);
                    if (currChar == ' '){
                        this.numOfSpaces++;
                    }
                    this.numTotalChar++;
                }
                String trim = line.trim();
                numOfWords += trim.isEmpty() ? 0 : trim.split("\\s+").length;
            }
            buffer.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public int NumLines(){
        return numOfLines;
    }

    public int NumBlankLines() {
        return numOfBlankLines;
    }

    public int NumSpaces(){
        return numOfSpaces;
    }

    public int NumWords() {
        return numOfWords;
    }

    public int getTotalChars() {
        return this.numTotalChar;
    }

    public int AvgCharPerLine(){
        int avgChar = 0;
        if (numOfLines != 0){
            avgChar = (int) floor(numTotalChar / numOfLines);
        }
        return avgChar;
    }

    public int AvgWordLength(){
        int avgWord = 0;
        if (numOfWords != 0){
            avgWord = (numTotalChar - numOfSpaces) / numOfWords;
        }

        return avgWord;
    }

    public String MostCommonWords() {
        String entireFile = "";

        for (int i = 0; i < contentList.size(); i++){
            entireFile += contentList.get(i);
            entireFile += " ";
        }

        String[] words = entireFile.split("[\\n\\r\\s]+");
        for (int i = 0; i < words.length; i++){
            words[i] = words[i].toLowerCase();
        }


        int max = 0;

        Map<String, Integer> frequencies = new LinkedHashMap<String, Integer>();
        for (String word : words){
            if (!word.isEmpty()){
                Integer frequency = frequencies.get(word);

                if (frequency == null){
                    frequency = 0;
                }

                ++frequency;
                if (frequency > max){
                    max = frequency;
                    mostCommonWord = word;
                }
                else if (frequency == max){
                    mostCommonWord += " " + word;
                }
                frequencies.put(word, frequency);
            }
        }



        return mostCommonWord;
    }

}

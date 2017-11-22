import java.io.*;
import java.util.*;

import static java.lang.Math.floor;

public class Analysis {

    private int numOfLines = 0; //a
    private int numOfBlankLines = 0; //b
    private int numOfSpaces = 0; //c
    private int numOfWords = 0; //d
    private int numTotalChar = 0; //e1, f1
    List<String> contentList;
    private String mostCommonWord = ""; //g

    Analysis(File fileName){
        //myFile = new File(fileName);
        //System.out.println("File name to be analyzed: " + myFile.toString()); //testing for correct filename

        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader buff = new BufferedReader(reader);

            String line;
            contentList = new ArrayList<String>();

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
                numOfWords += trim.isEmpty() ? 0 : trim.split("\\s+").length;

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

        String[] words = entireFile.split(" ");

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

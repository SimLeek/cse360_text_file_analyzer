import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class app {
    public static void main(String[] args) {
//        TextFileAnalyzer dialog = new TextFileAnalyzer();
//        dialog.pack();
//        dialog.setVisible(true);

        String fileName = "test.txt";
        Analysis fileToAnalyze =  new Analysis(fileName);

        int lines = fileToAnalyze.numLines();
        System.out.println("Number of lines in file " + fileName + ": " + lines);

        int blankLines = fileToAnalyze.numBlankLines();
        System.out.println("Number of blank lines in file " + fileName + ": " + blankLines);

        int spaces = fileToAnalyze.numSpaces();
        System.out.println("Number of spaces in file " + fileName + ": " + spaces);

        int words = fileToAnalyze.numWords();
        System.out.println("Number of words in file " + fileName + ": " + words);

        int charsAvg = fileToAnalyze.avgCharPerLine();
        System.out.println("Average number of characters per line in file " + fileName + ": " + charsAvg);

        int wordsAvg = fileToAnalyze.avgWordLength();
        System.out.println("Average length of words in file " + fileName + ": " + wordsAvg);

        System.exit(0);
    }
}

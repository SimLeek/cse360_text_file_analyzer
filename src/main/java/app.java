import java.io.*;

public class app {
    public static void main(String[] args) {
//        TextFileAnalyzer dialog = new TextFileAnalyzer();
//        dialog.pack();
//        dialog.setVisible(true);

        File fileName = new File("test.txt");
        Analysis fileToAnalyze =  new Analysis(fileName);

        String currentPath = fileName.getAbsolutePath();
        System.out.println("The current path is: " + currentPath);

        int lines = fileToAnalyze.NumLines();
        System.out.println("Number of lines in file " + fileName + ": " + lines);

        int blankLines = fileToAnalyze.NumBlankLines();
        System.out.println("Number of blank lines in file " + fileName + ": " + blankLines);

        int spaces = fileToAnalyze.NumSpaces();
        System.out.println("Number of spaces in file " + fileName + ": " + spaces);

        int words = fileToAnalyze.NumWords();
        System.out.println("Number of words in file " + fileName + ": " + words);

        int charsAvg = fileToAnalyze.AvgCharPerLine();
        System.out.println("Average number of characters per line in file " + fileName + ": " + charsAvg);

        int wordsAvg = fileToAnalyze.AvgWordLength();
        System.out.println("Average length of words in file " + fileName + ": " + wordsAvg);

        String mostCommon = fileToAnalyze.MostCommonWords();
        System.out.println("Most common word in file: " + mostCommon);

        System.exit(0);
    }
}

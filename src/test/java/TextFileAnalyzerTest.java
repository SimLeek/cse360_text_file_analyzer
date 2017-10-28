import static org.junit.jupiter.api.Assertions.*;

class TextFileAnalyzerTest {
    @org.junit.jupiter.api.Test
    void testMain() {
        TextFileAnalyzer dialog = new TextFileAnalyzer();
        dialog.pack();
        dialog.setVisible(true);
    }

}
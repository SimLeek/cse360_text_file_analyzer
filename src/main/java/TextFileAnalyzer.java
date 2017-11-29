import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Vector;

import static java.lang.Math.floor;

public class TextFileAnalyzer extends JDialog implements ActionListener, ListSelectionListener {
    private JPanel contentPane;
    private JButton newButton;
    private JButton openButton;
    private JButton analyzeButton;
    private JButton helpButton;
    private JButton averageButton;
    private JEditorPane editorPane1;
    private JList fileList;
    private DefaultListModel fileListModel;
    private File currentFile;
    private boolean moddedFile;

    private Vector<File> getAllFilesInFolder(String folder_str){
        //from: https://stackoverflow.com/a/5694398/782170
        File folder = new File(folder_str);
        File[] listOfFiles = folder.listFiles();
        Vector<File> file_vector = new Vector<File>();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                file_vector.add(file);
            } else if (file.isDirectory()) {
                //System.out.println("Directory " + file.getName());
            }
        }
        return file_vector;
    }

    private void addFilesToListModel(DefaultListModel model, Vector<File> file_list){//this 'model' is an object reference passed by value
        for (File file: file_list){
            model.addElement(file.getName());
        }

    }

    private void updateFileList(String folder_name){
        fileListModel.removeAllElements();
        addFilesToListModel(fileListModel, getAllFilesInFolder(folder_name));
    }

    TextFileAnalyzer() {
        moddedFile = false;
        openButton.addActionListener(this);
        newButton.addActionListener(this);
        analyzeButton.addActionListener(this);
        helpButton.addActionListener(this);
        averageButton.addActionListener(this);
        fileList.addListSelectionListener(this);
        setContentPane(contentPane);
        setModal(true);
        fileList.removeAll();

        fileListModel = new DefaultListModel();
        fileList.setModel(fileListModel);
        updateFileList("files/");
        //fileListModel.addElement("test");

        openButton.createToolTip();
        openButton.setToolTipText("Click to Open a File.");

        newButton.createToolTip();
        newButton.setToolTipText("Click to Create a New File.");
        
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        editorPane1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                try {
                    moddedFile = true;
                    writeToCurrentFile(editorPane1.getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private void writeToCurrentFile(String newText) throws IOException {
        if (currentFile!=null){
            FileWriter fw = new FileWriter(currentFile);
            fw.write(newText);
            fw.flush();
            fw.close();
        }
    }

    public void actionPerformed(ActionEvent e) {
//        TODO: TRY AND GET THE SWITCH WORKING.It would look nicer =)
//        switch (e.getSource()) {
//            case openButton:
//                System.out.println("Open Pressed");
//                break;
//            case analyzeButton:
//                System.out.println("Analyze Pressed");
//        }
        if (e.getSource() == openButton) {
            try {
                File[] files = openButtonHandler();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == analyzeButton) {
            try {
                analyzeButtonHandler();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == newButton) {
            newButtonHandler();
        } else if (e.getSource() == helpButton) {
            helpButtonHandler();
        }
    }

    private void helpButtonHandler() {
        BufferedReader msg = null;
        try {
            File file = new File("help.txt");
            msg = new BufferedReader(new FileReader(file));
            String st;
            String sb = "";
            while ((st = msg.readLine()) != null) {
                sb += st +"\n";
                System.out.println(st);
            }
            JOptionPane.showMessageDialog(null, sb);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void analyzeButtonHandler() throws IOException {
        File[] files = openButtonHandler();
        String output;
        if (files.length == 0) {
            Analysis text = new Analysis(files[0].getAbsolutePath()); // Can Create multiple text Analysis by indexing through array
            // TODO: Instead of Printing to the console we should display in a EditorPane.
            output = "Number of lines: " + text.NumLines() + "\n" +
                    "Number of blank lines: " + text.NumBlankLines() + "\n" +
                    "Number of spaces: " + text.NumSpaces() + "\n" +
                    "Number of words: " + text.NumWords() + "\n" +
                    "Average characters per line: " + text.AvgCharPerLine() + "\n" +
                    "Average word length: " + text.AvgWordLength() + "\n" +
                    "Most common word(s): " + text.MostCommonWords() + "\n";

        } else {
            int lines = 0,
                    blanks = 0,
                    spaces = 0,
                    words = 0,
                    averageWordLength = 0,
                    averageCharsPerLine = 0,
                    totalChars = 0;
            String mostCommonWords = "";
            for (int i = 0; i < files.length; i++) {
                Analysis text = new Analysis(files[i].getAbsolutePath());
                lines += text.NumLines();
                blanks += text.NumBlankLines();
                spaces += text.NumSpaces();
                words += text.NumWords();
                totalChars += text.getTotalChars();
                averageWordLength += text.AvgWordLength();
                mostCommonWords += text.MostCommonWords() + " ";
            }
            if (lines != 0) {
                averageCharsPerLine = (int) floor(totalChars / lines);
            }
            output = "Number of lines: " + lines + "\n" +
                    "Number of blank lines: " + blanks + "\n" +
                    "Number of spaces: " + spaces + "\n" +
                    "Number of words: " + words + "\n" +
                    "Average characters per line: " + averageCharsPerLine + "\n" +
                    "Average word length: " + averageWordLength + "\n" +
                    "Most common word(s): " + mostCommonWords + "\n";

        }

        JOptionPane.showMessageDialog(null, output);
    }

    public void getAverage(int[] stats) {

    }

    private void newButtonHandler() {
        String path = JOptionPane.showInputDialog("Filename: ");
        try {
            File newFile = new File("files/"+path);
            newFile.createNewFile();
            updateFileList("files/");
            System.out.println("Created File at: " + newFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyFileUsingStream(File source, File dest) throws IOException {
        //https://www.journaldev.com/861/java-copy-file
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

    public File[] openButtonHandler() throws IOException {
        JFileChooser chooser = new JFileChooser();
        File wd = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(wd);
        chooser.setMultiSelectionEnabled(true);

        // Show the dialog; wait until dialog is closed
        int returnVal = chooser.showOpenDialog(openButton);
        File[] files = {};
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            // Retrieve the selected files.
            editorPane1.setText("");
            files = chooser.getSelectedFiles();
            for (File file : files) {
                File newFile = new File("files/" + file.getName());
                boolean del = newFile.delete();

                boolean success = newFile.createNewFile();
                if(success){
                    copyFileUsingStream(file, newFile);
                    editorPane1.setText(editorPane1.getText() + "\n" + file.toString());
                }

            }
            updateFileList("files/");
        }
        return files;
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void setCurrentFile(String filename){
        currentFile = new File("files/"+filename);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()){
        try {
            if(moddedFile){
                writeToCurrentFile(editorPane1.getText());
            }
        } catch (IOException e1) {
            //e1.printStackTrace();
        }finally {
            String next_str = "";
            if (currentFile!=null){
                String current_str = currentFile.toString();
                String first_str = fileListModel.elementAt(e.getFirstIndex()).toString();
                String last_str = fileListModel.elementAt(e.getLastIndex()).toString();

                if (!current_str.equals("files"+ File.separator + first_str)){
                    next_str = first_str;
                }else{
                    next_str = last_str;
                }
            }else{
                next_str = fileListModel.elementAt(e.getFirstIndex()).toString();
            }

            setCurrentFile(next_str);
            try {
                FileReader fis = new FileReader(currentFile);
                char[] data = new char[(int) currentFile.length()];

                SimpleDateFormat form = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

                this.setTitle(currentFile.toString() + " : " +form.format(currentFile.lastModified()));
                fis.read(data);
                fis.close();
                String d = new String(data);
                editorPane1.setText(d);
                moddedFile = false;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }
}
}

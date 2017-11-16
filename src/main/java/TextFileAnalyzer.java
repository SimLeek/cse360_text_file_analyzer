
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class TextFileAnalyzer extends JDialog implements ActionListener {
    private JPanel contentPane;
    private JButton newButton;
    private JButton openButton;
    private JButton editButton;
    private JButton analyzeButton;
    private JButton helpButton;
    private JButton averageButton;
    private JEditorPane editorPane1;
    private String filename;



    TextFileAnalyzer() {
        openButton.addActionListener(this);
        newButton.addActionListener(this);
        editButton.addActionListener(this);
        analyzeButton.addActionListener(this);
        helpButton.addActionListener(this);
        averageButton.addActionListener(this);
        setContentPane(contentPane);
        setModal(true);

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
           File[] files = openButtonHandler();
        } else if (e.getSource() == analyzeButton) {
            analyzeButtonHandler();
        } else if (e.getSource() == newButton) {
            newButtonHandler();
        } else if (e.getSource() == helpButton) {
            helpButtonHandler();
        }
    }

    private void helpButtonHandler() {
        File msg = new File("help.txt");
        if (msg.exists()) {
            JOptionPane.showInputDialog(msg.getPath());
        }
    }

    private void analyzeButtonHandler() {
        File[] filename = openButtonHandler();
        Analysis text = new Analysis(filename[0]);
        // TODO: Instead of Printing to the console we should display in a EditorPane.
        System.out.println("Number of Words: " + text.NumWords());
        System.out.println("Number of Spaces: " + text.NumSpaces());
        System.out.println("Most Common Word: " + text.MostCommonWords());
        System.out.println("Average Character per line: " + text.AvgCharPerLine());
    }

    private void newButtonHandler() {
        String path = JOptionPane.showInputDialog("Filename: ");
        try {
            File newFile = new File("files/"+path);
            newFile.createNewFile();
            System.out.println("Created File at: " + newFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File[] openButtonHandler() {
        JFileChooser chooser = new JFileChooser();
        File wd = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(wd);
        chooser.setMultiSelectionEnabled(true);

        // Show the dialog; wait until dialog is closed
        int returnVal = chooser.showOpenDialog(openButton);
        File[] files = {};
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            // Retrieve the selected files.
            files = chooser.getSelectedFiles();
            editorPane1.setText(files[0].toString());
        }
        return files;
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }



}

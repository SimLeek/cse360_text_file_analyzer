
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.io.File;

public class TextFileAnalyzer extends JDialog implements ActionListener {
    private JPanel contentPane;
    private JButton newButton;
    private JButton browseButton;
    private JButton editButton;
    private JButton analyzeButton;
    private JButton helpButton;
    private JButton averageButton;
    private JEditorPane editorPane1;
    private String filename;



    TextFileAnalyzer() {
        browseButton.addActionListener(this);
        newButton.addActionListener(this);
        editButton.addActionListener(this);
        analyzeButton.addActionListener(this);
        helpButton.addActionListener(this);
        averageButton.addActionListener(this);
        setContentPane(contentPane);
        setModal(true);

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
//        TODO: TRY AND GET THE SWITCH WORKING.
//        switch (e.getSource()) {
//            case browseButton:
//                System.out.println("Browse Pressed");
//                break;
//            case analyzeButton:
//                System.out.println("Analyze Pressed");
//        }
        if (e.getSource() == browseButton) {
            // Open the pop up window for the file browser.
            JFileChooser chooser = new JFileChooser();
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);
            int returnVal = chooser.showOpenDialog(browseButton);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                filename = chooser.getSelectedFile().getName();
                System.out.println("You chose to open this file: " + filename);
            }
        } else if (e.getSource() == analyzeButton) {
            // Nicholas Part
            //
            System.out.println("ANALYZE PRESSED");
        }
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

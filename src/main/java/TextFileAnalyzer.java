import Browsing.FileTree;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class TextFileAnalyzer extends JDialog{
    private JPanel contentPane;
    private JButton newButton;
    private JButton browseButton;
    private JButton editButton;
    private JButton analyzeButton;
    private JButton helpButton;
    private JButton averageButton;
    private FileTree tree1;
    private JEditorPane editorPane1;


    TextFileAnalyzer() {
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

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        TextFileAnalyzer dialog = new TextFileAnalyzer();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void createUIComponents() {
        File fileRoot = new File(".");
        this.tree1 = new FileTree(fileRoot);
        this.tree1.updateUI();

    }

}

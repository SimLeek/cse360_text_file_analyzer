package Browsing;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import java.io.File;

public class FileTree extends JTree {
    //https://stackoverflow.com/a/23808238/782170

    public FileTree(TreeModel mod, File fileRoot, DefaultMutableTreeNode root){
        super(mod);

        setShowsRootHandles(true);

        CreateChildren ccd =
                new CreateChildren(fileRoot, root);
        new Thread(ccd).start();
    }
}

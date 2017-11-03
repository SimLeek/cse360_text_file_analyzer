package Browsing;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.File;

public class FileTree extends JTree {
    //https://stackoverflow.com/a/23808238/782170

    private DefaultMutableTreeNode root;

    public FileTree(File fileRoot){
        //super();

        setShowsRootHandles(true);

        this.root = new DefaultMutableTreeNode(".");


        this.treeModel = new DefaultTreeModel(this.root);

        CreateChildren ccd =
                new CreateChildren(fileRoot, this.root);
        new Thread(ccd).start();

    }

    public FileTree(){
        super();

        setShowsRootHandles(true);

        File fileRoot = new File(".");

        this.root = new DefaultMutableTreeNode(".");

        this.treeModel = new DefaultTreeModel(this.root);

        CreateChildren ccd =
                new CreateChildren(fileRoot, this.root);
        new Thread(ccd).start();
    }
}

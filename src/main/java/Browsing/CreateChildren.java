package Browsing;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;

public class CreateChildren implements Runnable{
    //https://stackoverflow.com/a/23808238/782170

    private DefaultMutableTreeNode root;

    private File fileRoot;

    public CreateChildren(File fileRoot, DefaultMutableTreeNode root){
        this.fileRoot = fileRoot;
        this.root = root;
    }

    @Override
    public  void run(){
        createChildren(fileRoot, root);
    }

    private void createChildren(File fileRoot, DefaultMutableTreeNode node){
        File[] files = fileRoot.listFiles();
        if(files != null){
            for(File file: files){
                DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(new FileLeaf(file));
                node.add(childNode);
                if(file.isDirectory()){
                    createChildren(file, childNode);
                }
            }
        }

    }
}

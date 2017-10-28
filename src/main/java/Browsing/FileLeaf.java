package Browsing;

import java.io.File;

public class FileLeaf {
    //https://stackoverflow.com/a/23808238/782170

    private File file;

    FileLeaf(File file){
        this.file=file;
    }

    @Override
    public String toString(){
        String name = file.getName();
        if(name.equals("")){
            return file.getAbsolutePath();
        }else{
            return name;
        }
    }

}

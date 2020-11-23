package com.shawn.ss.tools;

import java.io.File;

public class DifferMain {
    public static void main(String[] args){
        if(args.length==2){
            differ(args[0],args[1]);
        }
    }

    private static void differ(String folder1, String folder2) {
        File f1=new File(folder1);
        File f2=new File(folder2);
        if(f1.exists() && f2.exists() && f1.canRead() && f2.canRead()){

        }

    }
}

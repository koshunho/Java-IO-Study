package com.huang.file;

import java.io.File;

public class TestRecursive {
    public static void main(String[] args) {
        listDir(new File("C:\\Users\\hjp960322\\Desktop\\***"));
    }

    //递归遍历文件夹（里的内容）
    public static void listDir(File dir) {
        File[] files = dir.listFiles(); //得到传过来的目录下的所有文件 和 文件夹

        System.out.println(dir.getAbsolutePath());


        if(files != null && files.length > 0){
            for (File file : files) {
                if(file.isDirectory()){
                    listDir(file);
                }else{
                    System.out.println(file.getAbsolutePath());
                }
            }
        }

    }

    //递归删除文件夹（里的内容）
    public static void deleteDir(File dir) {
        File[] files = dir.listFiles(); //得到传过来的目录下的所有文件 和 文件夹

        System.out.println(dir.getAbsolutePath());


        if(files != null && files.length > 0){
            for (File file : files) {
                if(file.isDirectory()){
                    deleteDir(file);
                }else{
                    System.out.println(file.getAbsolutePath() + "删除: " + file.delete()); //把文件夹里面所有内容都删了
                }
            }
        }

        System.out.println(dir.getAbsolutePath() + "删除: " + dir.delete());  //把自己也删了
    }
}


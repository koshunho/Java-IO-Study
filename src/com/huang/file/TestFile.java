package com.huang.file;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

/*
* File类的操作
* （1）分隔符  -->去看环境变量。每个路径都是通过路径分隔符;隔开  用名称分隔符\隔开每个路径的每一级
* （2）文件操作
* （3）文件夹操作*/
public class TestFile {
    public static void main(String[] args) throws IOException, InterruptedException {
        separator();

        //fileOperation();

        directoryOperation();
    }

    public static void separator(){
        System.out.println("路径分隔符" + File.pathSeparator);
        System.out.println("名称分隔符" + File.separator);
    }

    public static void fileOperation() throws IOException, InterruptedException {
        //1.创建文件
        File file = new File("C:\\Users\\hjp960322\\Desktop\\file.txt"); //此时这个路径可能存在，可能不存在，不影响
        System.out.println(file.toString()); //打印出路径

        if(!file.exists()) {
            boolean b = file.createNewFile();
            System.out.println(b);
        }
/*        //如果（已经存在了并）再创建一次，就会返回false
        boolean b = file.createNewFile();
        System.out.println(b);*/

        //2.删除文件
/*        //2.1 直接删除
        System.out.println(file.delete());*/
        //2.2 使用JVM删除
        file.deleteOnExit();
        Thread.sleep(5000);


        //3.获取文件信息
        System.out.println("获取绝对路径 " + file.getAbsolutePath());  //如果new File("file.txt") -->直接创建在项目的根目录下
        System.out.println("获取路径 " + file.getPath()); //构造方法里写的什么路径，这里就返回什么路径
        System.out.println("获取文件名称" + file.getName());
        System.out.println("获取文件长度" + file.length()); //字节数


        //4.判断
        System.out.println("是否可写" + file.canRead()); //如果文件是 只读 的话，就返回false
        System.out.println("是否是文件" + file.isFile()); // 可能是文件，可能是文件夹
        System.out.println("是否隐藏" + file.isHidden());

    }

    public static void directoryOperation() throws InterruptedException {
        //1.创建文件夹
        File dir = new File("C:\\Users\\hjp960322\\Desktop\\aaa\\bbb\\ccc");
        if(!dir.exists()){
/*            dir.mkdir(); //只能创建单级目录*/
            System.out.println(dir.mkdirs());
        }
/*        //2.删除文件夹
        //2.1 直接删除(只能删除空目录)
        System.out.println("删除结果" + dir.delete()); //注意：只删除最底层的路径 -->ccc ，而且ccc 必须是空目录！！
        //2.2 使用JVM删除
        dir.deleteOnExit();
        Thread.sleep(5000);*/

        //3.获取文件夹信息
        System.out.println("获取绝对路径 " + dir.getAbsolutePath());
        System.out.println("获取路径 " + dir.getPath()); //构造方法里写的什么路径，这里就返回什么路径
        System.out.println("获取文件夹名称" + dir.getName());  //注意！！返回的是最底层的文件夹的名称！！！ -->ccc
        System.out.println("获取父目录" + dir.getParent()); //C:\Users\hjp960322\Desktop\aaa\bbb

        //4.判断
        System.out.println("是否是文件夹" + dir.isDirectory());
        System.out.println("是否隐藏" + dir.isHidden());

        //5.遍历文件夹(特殊！)
        File dir2 = new File("C:\\Users\\hjp960322\\Desktop\\history\\2015.1.12");
        String[] lists = dir2.list();
        System.out.println("-----------");
        for (String list : lists) {
            System.out.println(list);
        }
        System.out.println("-----------");

        //6.FileFilter
        File[] fileFilters = dir2.listFiles(pathname -> {  //只过滤出.jpg
            if (pathname.getName().endsWith(".jpg")) {
                return true;
            }
            return false;
        });
        for (File file : fileFilters) {
            System.out.println(file.getName());
        }
    }
}

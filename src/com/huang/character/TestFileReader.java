package com.huang.character;

import java.io.FileReader;
import java.io.IOException;

public class TestFileReader {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("C:\\Users\\hjp960322\\Desktop\\hello.txt");

        //一个中文字符是3个字节，如果用字节流FileInputStream读的话是一个字节一个字节的读，会输出乱码
        //所以用字符流fileReader来读，一个字符一个字符的读
/*
        int data = 0;
        while((data = fileReader.read()) != -1){
            System.out.println((char) data);
        }*/

        //不同点。char型数组
        char[] chars = new char[2];
        int count = 0;
        while((count = fileReader.read(chars)) != -1){
            System.out.println(new String(chars,0,count));
        }

        fileReader.close();
    }
}

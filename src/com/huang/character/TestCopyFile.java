package com.huang.character;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// 使用FileReader和FileWriter复制文本文件，不能复制图片or二进制文件（因为这些文件的基本单位是字节，而不是字符）
public class TestCopyFile {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("C:\\Users\\hjp960322\\Desktop\\hello.txt");
        FileWriter fileWriter = new FileWriter("C:\\Users\\hjp960322\\Desktop\\hello2.txt");

        int data = 0;
        while((data = fileReader.read()) != -1){
            fileWriter.write(data);
            fileWriter.flush();
        }

        fileReader.close();
        fileWriter.close();
    }
}

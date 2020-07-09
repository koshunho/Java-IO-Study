package com.huang.bridge;

import java.io.*;

// 转换流。将inputStream 字节流 转换成 reader 字符流
public class TestInputStreamReader {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\hjp960322\\Desktop\\BufferedWriter.txt");

        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"utf-8");

        int data = 0;
        while((data = inputStreamReader.read()) != -1){
            System.out.print((char) data);
        }

        inputStreamReader.close();
    }
}

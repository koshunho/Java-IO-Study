package com.huang.bridge;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

// 转换流。outputStream 转换成 Writer
public class TestOutputStreamWriter {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\hjp960322\\Desktop\\TestOutputStreamWriter.txt");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream,"utf-8");

        for (int i = 0; i < 10; i++) {
            outputStreamWriter.write("TestOutputStreamWriter" + i +"\r\n");
            outputStreamWriter.flush();
        }

        outputStreamWriter.close();
    }
}

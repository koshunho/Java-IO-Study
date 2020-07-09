package com.huang.character;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TestBufferedWriter {
    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter("C:\\Users\\hjp960322\\Desktop\\BufferedWriter.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (int i = 0; i < 10; i++) {
            String s = "好好学习天天向上才怪";
            bufferedWriter.write(s);
            bufferedWriter.newLine(); //写入一个换行符 \r\n
            bufferedWriter.flush();
        }

        bufferedWriter.close();
    }
}

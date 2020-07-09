package com.huang.character;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TestPrintWriter {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter("C:\\Users\\hjp960322\\Desktop\\FileWrite.txt");

        //打印到文件中
        printWriter.println(97); //文件中就显示97而不是a
        printWriter.println(true);
        printWriter.println(3.14);
        printWriter.println('a');

        printWriter.close();
    }
}


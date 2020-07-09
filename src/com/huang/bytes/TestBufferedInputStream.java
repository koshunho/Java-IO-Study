package com.huang.bytes;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class TestBufferedInputStream {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\hjp960322\\Desktop\\a.txt");
        BufferedInputStream bis = new BufferedInputStream(fis);  // 里面维护了一个8KB的缓冲区

        int data = 0;
        while((data = bis.read()) != -1){
            System.out.print((char) data);
        }

        //关bis就可以了
        bis.close();
    }
}

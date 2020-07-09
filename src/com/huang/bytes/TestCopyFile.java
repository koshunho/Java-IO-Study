package com.huang.bytes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestCopyFile {
    public static void main(String[] args) throws IOException {
        //1.创建流
        //1.1 文件字节输入流
        FileInputStream input = new FileInputStream("C:\\Users\\hjp960322\\Desktop\\shz.jpg");
        //1.2 文件字节输出流
        FileOutputStream output = new FileOutputStream("C:\\Users\\hjp960322\\Desktop\\bb.jpg");

        //2.一边读 一边写
        byte[] bytes = new byte[1024];
        int count = 0;
        while((count = input.read(bytes)) != -1){
            output.write(bytes,0,count);
        }

        //3.关闭
        input.close();
        output.close();
    }
}

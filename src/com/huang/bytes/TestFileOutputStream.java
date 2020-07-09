package com.huang.bytes;

import java.io.FileOutputStream;
import java.io.IOException;

public class TestFileOutputStream {
    public static void main(String[] args) throws IOException {
        //1.创建文件字节输出流
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\hjp960322\\Desktop\\b.txt",true);

        //2.写入文件
/*        fileOutputStream.write('f');
        fileOutputStream.write('u');
        fileOutputStream.write('c');
        fileOutputStream.write('k');
        fileOutputStream.write(5);*/


        String s = "NMSL";
        fileOutputStream.write(s.getBytes());  //会覆盖之前写过的内容。要想追加的话new的时候构造方法 append 为true

        //3.关闭
        fileOutputStream.close();
    }
}

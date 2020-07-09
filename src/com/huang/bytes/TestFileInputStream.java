package com.huang.bytes;

import java.io.FileInputStream;
import java.io.IOException;

public class TestFileInputStream {
    public static void main(String[] args) throws IOException {
        //1.创建FileInputStream
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\hjp960322\\Desktop\\a.txt");

/*        //2.1读取文件。read()读一个字节，读出来的是ASCII码
        int data = 0;

        while((data = fileInputStream.read()) != -1){
            System.out.print((char) data);
        }*/

/*        //2.2一次读取多个字节
        byte[] bytes = new byte[3];
        //这个方法返回实际读取的个数
        int count1 = fileInputStream.read(bytes);
        System.out.println(count1);
        System.out.println(new String(bytes));

        int count2 = fileInputStream.read(bytes);
        System.out.println(count2);
        System.out.println(new String(bytes));

        int count3 = fileInputStream.read(bytes);
        System.out.println(count3);

        //此处特别注意！！！！！文件中是abcdefg，最后一次输出的是gef。因为最后一次读取只读了g，ef并没有覆盖掉！！！
        System.out.println(new String(bytes));*/

        byte[] bytes = new byte[3];
        int count = 0;
        while((count = fileInputStream.read(bytes)) != -1){
            System.out.println(new String(bytes, 0, count));
        }
        //3.关闭
        fileInputStream.close();
    }
}


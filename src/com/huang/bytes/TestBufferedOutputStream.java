package com.huang.bytes;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestBufferedOutputStream {
    public static void main(String[] args) throws IOException {
        FileOutputStream fis = new FileOutputStream("C:\\Users\\hjp960322\\Desktop\\c.txt");
        BufferedOutputStream bos = new BufferedOutputStream(fis);

        for (int i = 0; i < 10; i++) {
            bos.write("NMSL".getBytes());
            //如果不立即flush的话，就会先保存到BufferedOutputStream的缓存区。硬盘是看不到的
            bos.flush();
        }

        //关闭的时候内部会调用flush!!!!
        bos.close();
    }
}

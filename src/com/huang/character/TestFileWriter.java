package com.huang.character;

import java.io.FileWriter;
import java.io.IOException;

public class TestFileWriter {
    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter("C:\\Users\\hjp960322\\Desktop\\FileWrite.txt",true);

/*        for (int i = 0; i < 10; i++) {
            fileWriter.write("NMSL 哈哈\r\n");
            fileWriter.flush();
        }*/


        for (int i = 0; i < 10; i++) {
            fileWriter.write(65);  //注意这里！！！ 这里实际是ASCII码！！！输入65 在文件中显示A。想正常显示数字就要用PrintWriter
            fileWriter.flush();
        }

        fileWriter.close();
    }
}

package com.huang.character;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestBufferedReader {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("C:\\Users\\hjp960322\\Desktop\\FileWrite.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

/*        char[] chars = new char[1024];
        int count = 0;
        while((count = bufferedReader.read(chars)) != -1){
            //System.out.println(count);
            System.out.println(new String(chars,0,count));
        }*/

        //一行一行读
        String line = null;
        while((line = bufferedReader.readLine()) != null){
            System.out.println(line);
        }

        bufferedReader.close();
    }
}

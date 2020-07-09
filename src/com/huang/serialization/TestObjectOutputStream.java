package com.huang.serialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

//序列化
public class TestObjectOutputStream {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("C:\\Users\\hjp960322\\Desktop\\stu.bean");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        Student student = new Student("你妈死了", 38);
        oos.writeObject(student);

        oos.close();
    }
}

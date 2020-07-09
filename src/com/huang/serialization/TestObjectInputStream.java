package com.huang.serialization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

// 反序列化
public class TestObjectInputStream {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("C:\\Users\\hjp960322\\Desktop\\stu.bean");//节点流。就是基本流
        ObjectInputStream ois = new ObjectInputStream(fis);//过滤流。过滤流保证节点流，添加特色功能

        Student student = (Student) ois.readObject();

        ois.close();
        System.out.println(student.toString());
    }
}

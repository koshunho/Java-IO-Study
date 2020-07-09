package com.huang.properties;

import java.io.*;
import java.util.Properties;
import java.util.Set;

public class TestProperties {
    public static void main(String[] args) throws IOException {
        //1.Properties集合:感觉有点像Redis？
        Properties properties = new Properties();

        properties.setProperty("username","zhangsan");
        properties.setProperty("age","30");

        //3.遍历
        //3.1 keySet
        //3.2 entrySet
        //3.3stringPropertyNames
        Set<String> fields = properties.stringPropertyNames();
        for (String field : fields) {
            System.out.println(field + " --> " + properties.getProperty(field));
        }

        //4.和流相关的方法
        //4.1 list方法
        PrintWriter pw = new PrintWriter("C:\\Users\\hjp960322\\Desktop\\TestProperties.txt");
        properties.list(pw);
        pw.close();
        /*文件内容：
        * -- listing properties --
          age=30
          username=zhangsan
         */

/*        //4.2 store方法
        FileOutputStream fos = new FileOutputStream("C:\\Users\\hjp960322\\Desktop\\store.properties");
        properties.store(fos,"注释！");  //就是添加注释
        fos.close();
        *//*文件内容：
        #\u6CE8\u91CA\uFF01
        #Thu Jul 09 17:22:26 JST 2020
        age=30
        username=zhangsan*/

        //4.3 load方法 常用
        Properties properties1 = new Properties();
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\hjp960322\\Desktop\\store.properties");
        properties1.load(fileInputStream);
        fileInputStream.close();
        System.out.println(properties1.toString());
    }
}

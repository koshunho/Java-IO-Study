# Java-IO-Study
Some examples to help understanding Java IO

## 概念

##### 什么是流？
内存与存储设备之间传输数据的通道。

![流](https://raw.githubusercontent.com/koshunho/koshunhopic/master/blog流.png)

##### 流的分类

按方向：

 - 输入流：将 存储设备 中的内容读入到 **内存** 中
 - 输出流：将 **内存** 中的内容写入到 存储设备 中
   
按单位：
 - 字节流：以字节为单位，可以读写所有数据  **(InputStream/OutputStream)**
 - 字符流：以字符为单位，只能读写文本数据 **(Reader/Writer)**

按功能：

 - 节点流：具有实际传输数据的读写功能
 - 过滤流：在节点流的基础之上增强功能

##### 字节流

字节流的父类（抽象类）
![InputStream/OutputStream](https://raw.githubusercontent.com/koshunho/koshunhopic/master/blogInputStream_OutputStream.png)

###### 文件字节流：FileInputStream/FileOutputStream

FileInputStream 

> **注意read(byte[] b) 和 read() 的返回值！！**
> 
> **read(byte[] b)返回实际读取的个数，读的内容已经放到数组中了！**

 - public int read(byte[] b) //从流中读取多个字节，将读到内容存入b数组，**返回实际读到的字节数**；如果达到文件的尾部，则返回-1
 - public int read() //从此输入流中**读取一个数据字节**
  
  FileOutputStream

 - public void write (byte[] b) //一次写多个字节，将b数组中所有字节，写入输出流

特别注意，**read()读一个字节，读出来的是ASCII码**！
```java
public class TestFileInputStream {
    public static void main(String[] args) throws IOException {
        //1.创建FileInputStream
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\a.txt");

/*        //2.1读取文件。read()读一个字节，读出来的是ASCII码
        int data = 0;

        while((data = fileInputStream.read()) != -1){
            System.out.print((char) data);
        }*/

       //2.2一次读取多个字节
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
        System.out.println(new String(bytes));

        byte[] bytes = new byte[3];
        int count = 0;
        while((count = fileInputStream.read(bytes)) != -1){
            System.out.println(new String(bytes, 0, count));
        }
        //3.关闭
        fileInputStream.close();
    }
}
```
注意！fileOutputStream.write(97);   //--> txt中会显示**a**(因为存的是ASCII码)
**将一个字符串写入文件中，需要调用s.getBytes()方法！**
```java
public class TestFileOutputStream {
    public static void main(String[] args) throws IOException {
        //1.创建文件字节输出流
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\hjp960322\\Desktop\\b.txt",true);

        //2.写入文件
/*        fileOutputStream.write('f');
        fileOutputStream.write('u');
        fileOutputStream.write('c');
        fileOutputStream.write('k');
		fileOutputStream.write(97);   //--> txt中会显示**a**
        fileOutputStream.write(5);*/


        String s = "NMSL";
        fileOutputStream.write(s.getBytes());  //会覆盖之前写过的内容。要想追加的话new的时候构造方法 append 为true

        //3.关闭
        fileOutputStream.close();
    }
}
```
###### 字节缓冲流：BufferedInputStream/BufferedOutputStream

 - 提高IO效率，减少访问磁盘的次数
 - 数据存储在缓冲区中，flush是将缓存区的内容写入文件中，也可以直接close
   
   字节缓冲流就是过滤流，**需要**一个节点流。
```java
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
```
**注意flush方法！**
```java
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
```

###### 对象流：ObjectOutputStream/ObjectInputStream

- 增强了缓冲区功能
- 增强了读写8种基本类型数据和字符串功能
- 增加了读写的功能
   1.readObject() //从流中读取一个对象（反序列化）
   2.writeObject(Object obj) //向流中写入一个对象（序列化）
 
 对象流也是一个过滤流，**需要**一个节点流！
 
 序列化。注意！需要序列化的类必须实现Serializable接口。序列化类中对象属性也要求实现Serializable接口。
 
 **当用transient修饰属性，这个属性就不能序列化了。**
 
 静态属性不能序列化。
 ```java
 //序列化
public class TestObjectOutputStream {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("C:\\Users\\Desktop\\stu.bean");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        Student student = new Student("你妈死了", 38);
        oos.writeObject(student);

        oos.close();
    }
}
 ```

反序列化。假设读完一次之后，再调用一次Student student1 = (Student) ois.readObject();会报错java.IO.EOFException。因为已经读完了，不能读多个。

最好序列化版本号ID，保证序列化的类和反序列化的类是同一个类。

当序列化多个对象时，可以用一个集合如List装起来。反序列的时候读到的就是这个List。
```java
// 反序列化
public class TestObjectInputStream {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("C:\\Users\\Desktop\\stu.bean");//节点流。就是基本流
        ObjectInputStream ois = new ObjectInputStream(fis);//过滤流。过滤流保证节点流，添加特色功能

        Student student = (Student) ois.readObject();

        ois.close();
        System.out.println(student.toString());
    }
}

```

##### 字符流
字符流的父类（抽象类）
![Reader/Writer](https://raw.githubusercontent.com/koshunho/koshunhopic/master/blogReader_Writer.png)

###### 文件字符流：FileReader/FileWriter
FileReader
- public int read(char[] c) //从流中读取多个字符，将读到内容存入c数组，返回实际读到的字符数；如果达到文件的尾部，则返回-1

FileWriter
- public void write(String str) //一次写多个字符

```java
public class TestFileReader {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("C:\\Users\\Desktop\\hello.txt"); //里面是一串中文“好好学习天天向上”

        //一个中文字符是3个字节，如果用字节流FileInputStream读的话是一个字节一个字节的读，会输出乱码
        //所以用字符流fileReader来读，一个字符一个字符的读
/*
        int data = 0;
        while((data = fileReader.read()) != -1){  //读一个字符，这里是读中文，一个字符是3个字节
            System.out.println((char) data);
        }*/

        //不同点。char型数组
        char[] chars = new char[2];
        int count = 0;
        while((count = fileReader.read(chars)) != -1){
            System.out.println(new String(chars,0,count));
        }

        fileReader.close();
    }
}
```

注意flush方法
```java
public class TestFileWriter {
    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter("C:\\Users\\Desktop\\FileWrite.txt",true);

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

```
###### 字符缓冲流：BufferedReader/BufferedWriter
- 高效读写
- 支持输入换行符
- 可一次写一行、读一行
  
  
字符缓冲流就是过滤流，**需要**一个节点流。

注意readLine()方法，一行一行读
```java
public class TestBufferedReader {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("C:\\Users\\Desktop\\FileWrite.txt");
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
```
注意newLine()方法
```java
public class TestBufferedWriter {
    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter("C:\\Users\\hjp960322\\Desktop\\BufferedWriter.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (int i = 0; i < 10; i++) {
            String s = "好好学习天天向上才怪";
            bufferedWriter.write(s);
            bufferedWriter.newLine(); //写入一个换行符 \r\n
            bufferedWriter.flush();
        }

        bufferedWriter.close();
    }
}
```
###### PrintWriter
- 封装了print() / println()，支持写入后换行
- 支持数据原样打印
 ```java
public class TestPrintWriter {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter("C:\\Users\\Desktop\\FileWrite.txt");

        //打印到文件中
        printWriter.println(97); //文件中就显示97而不是a
        printWriter.println(true);
        printWriter.println(3.14);
        printWriter.println('a');

        printWriter.close();
    }
}
```

##### 转换流

InputStreamReader/OutputStreamWriter
- 可将字节流转换为字符流
- 可设置字符的编码方式

就是 **字节流的抽象类 + 字符流的抽象类 = 转换流**
```java
  // 转换流。将inputStream 字节流 转换成 reader 字符流
public class TestInputStreamReader {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Desktop\\BufferedWriter.txt");

        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"utf-8");

        int data = 0;
        while((data = inputStreamReader.read()) != -1){
            System.out.print((char) data);
        }

        inputStreamReader.close();
    }
}
 ```
 
 ```java
 // 转换流。outputStream 转换成 Writer 字符流
public class TestOutputStreamWriter {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Desktop\\TestOutputStreamWriter.txt");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream,"utf-8");

        for (int i = 0; i < 10; i++) {
            outputStreamWriter.write("TestOutputStreamWriter" + i +"\r\n");
            outputStreamWriter.flush();
        }

        outputStreamWriter.close();
    }
}
 ```
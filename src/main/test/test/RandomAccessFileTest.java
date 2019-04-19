package main.test.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest {
    public static void main(String[] args) {
                 RandomAccessFile raf = null;
                 try {
                     raf = new RandomAccessFile("f:/data.txt","rw");
                 } catch (FileNotFoundException e) {
                     e.printStackTrace();
                 }
                 Person p = new Person(1001,"xiaoming",1.80d);
            try {
                p.write(raf);
            } catch (IOException e) {
                e.printStackTrace();
            }



    }
}

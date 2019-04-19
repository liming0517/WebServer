package main.test.test;

import java.io.IOException;
import java.io.RandomAccessFile;

class Person {
    int id;
    String name;
    double height;

    public Person() {
    }

    public Person(int id, String name, double height) {
        this.id = id;
        this.name = name;
        this.height = height;
    }

    public void write(RandomAccessFile raf) throws IOException {
        raf.write(id);
        raf.writeUTF(name);
        raf.writeDouble(height);
    }
}
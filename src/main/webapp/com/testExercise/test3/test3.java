package main.webapp.com.testExercise.test3;

public class test3 {
    public static void main(String[] args) {
        Pig pig = new Pig();
        Thread tr1 =new Thread(pig);
        Thread tr2 =new Thread(pig);
        Thread tr3 =new Thread(pig);
        Thread tr4 =new Thread(pig);
        tr1.start();
        tr2.start();
        tr3.start();
        tr4.start();
//        Monkey monkey =new Monkey();
//        Thread tr1=new Thread(monkey);
//        tr1.start();


    }
}


class Pig implements Runnable {
    @Override
    public void run() {
synchronized (this){
    for (int i = 0; i < 100; i++) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("我是猪，我正在写第" + i + "个HelloWorld");
    }
}


    }
}


class Monkey implements Runnable {
    private int num = 0;

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            num++;
            System.out.println("我是猴子，现在数字是：" + num);
        }
    }
}

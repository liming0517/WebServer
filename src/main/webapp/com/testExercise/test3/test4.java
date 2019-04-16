package main.webapp.com.testExercise.test3;

/**
 * 卖票
 */
public class test4 {
    public static void main(String[] args) {
        TicketWindows ticketWindows = new TicketWindows();
        Thread tr1 = new Thread(ticketWindows);
        Thread tr2 = new Thread(ticketWindows);
        Thread tr3 = new Thread(ticketWindows);
        Thread tr4 = new Thread(ticketWindows);
        tr1.setPriority(4);
        tr2.setPriority(2);
        tr1.start();
        tr2.start();
        tr3.start();
        tr4.start();

    }
}

class TicketWindows implements Runnable {
    int nums = 0;

    @Override
    public void run() {
        synchronized (this) {
            while (true) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (nums <= 100) {
                    nums++;
                    System.out.println(Thread.currentThread().getName() + "正在售出第" + nums + "张票");
                } else {
                    break;
                }
            }
        }

    }
}

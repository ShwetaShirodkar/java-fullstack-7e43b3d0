package com.ani.thread;

import com.ani.thread.basics.ExcelReaderTask;
import com.ani.thread.basics.ThreadOne;
import com.ani.thread.basics.ThreadTwo;
import com.ani.thread.itc.GrammerChecker;
import com.ani.thread.itc.ImpData;
import com.ani.thread.itc.SpellingChecker;

public class App {

    public static void demo1() {
        ThreadOne to = new ThreadOne();
        to.start();

        Runnable task1 = new ThreadTwo();
        Thread t1 = new Thread(task1);
        t1.start();

        Runnable task2 = new ExcelReaderTask();
        Thread t2 = new Thread(task2);
        t2.start();
    }

    public static void demo2() {

        ImpData dt = new ImpData();

        Runnable task1 = new SpellingChecker(dt);
        Thread t1 = new Thread(task1);
        t1.setPriority(Thread.NORM_PRIORITY);
      
        Runnable task2 = new GrammerChecker(dt);
        Thread t2 = new Thread(task2);
        t2.setPriority(Thread.MAX_PRIORITY);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
           System.out.println("There is a problem");
        }

        dt.currentCount();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("🔥 Threading 🔥");
        demo2();
    }
}

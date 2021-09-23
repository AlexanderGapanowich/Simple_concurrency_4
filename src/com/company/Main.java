package com.company;

public class Main {

    public static void main(String[] args) {
        MyInterruptedThreadOne threadOne = new MyInterruptedThreadOne("ThreadOne");
        Thread threadTwo = new MyInterruptedThreadTwo("ThreadTwo");

        threadOne.start();
        threadTwo.start();

        try {
            Thread.sleep(10000);
            threadOne.setNeedThreadWorking(false);
            threadTwo.interrupt();
        } catch (Throwable e) {
            System.out.println("Thread has been interrupted");
        }

        System.out.println("ThreadMain is finished");
    }
}

class MyInterruptedThreadOne extends Thread {
    Boolean needThreadWorking = true;

    MyInterruptedThreadOne(String name) {
        super(name);
    }

    public void setNeedThreadWorking(Boolean value) {
        needThreadWorking = value;
    }

    @Override
    public void run() {
        int x = 1;
        while (needThreadWorking) {
            try {
                Thread.sleep(1000);
                System.out.println("ThreadOne is working" + x++);
            } catch (Throwable e) {
            }

        }
        System.out.println("ThreadOne is interrupted");
    }
}


class MyInterruptedThreadTwo extends Thread {

    MyInterruptedThreadTwo(String name) {
        super(name);
    }

    @Override
    public void run() {
        int x = 1;
        while (!isInterrupted()) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
            System.out.println("ThreadTwo is working" + x++ );

        }
        System.out.println("ThreadTwo is interrupted");
    }
}
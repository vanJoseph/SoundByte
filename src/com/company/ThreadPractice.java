package com.company;

public class ThreadPractice implements Runnable {

    @Override
    public void run() {
        try {

            for (int i = 0; i < 20; i++) {
                print(String.valueOf(i));
                Thread.sleep(1000);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThreadPractice practice = new ThreadPractice();
        PitchGenerator generator = new PitchGenerator();
        Thread thread = new Thread(practice, "practice");
        thread.
        thread.start();

    }

    public static void print(String message) {
        System.out.println(message);
    }
}

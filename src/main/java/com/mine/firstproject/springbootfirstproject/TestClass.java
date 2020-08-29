package com.mine.firstproject.springbootfirstproject;

import java.util.ArrayList;
import java.util.List;

public class TestClass  {

    public static List arrays = new ArrayList<>();

   
    
    public static void main(String[] args) throws Exception {

        List routesList = new ArrayList<>();
        for (int i = 1; i < 1000; i++) {
            routesList.add(i);
        }    

        /*
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i < 10; i++) {
                    arrays.add(i);
                    try {
                    Thread.sleep(1);
                    }catch(Exception e) {e.printStackTrace();}
                }
            }
        });
 
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 11; i < 20; i++) {
                    arrays.add(i);
                    try {
                        Thread.sleep(1);
                        }catch(Exception e) {e.printStackTrace();}
                }
            }
        });
 
        thread2.start();

        thread1.join();
        thread2.join();
        */

        routesList.parallelStream().forEach(i -> {
            for (int itr = 11; itr < 20; itr++) {
                arrays.add(itr);
            }
        });
    

    System.out.println(arrays);

    }

}
package SmartWatch.MainThreads;

import java.util.Random;

public class DebugSleep {       //Only for debug purposes

    private final static int maxTimer=10;     //In seconds


    public static void sleep(String thread){
        try{
            System.out.println("["+thread+"] Sleep...");
            int nsec=new Random().nextInt(maxTimer)+1;
            Thread.sleep(nsec*1000);
        }catch(InterruptedException ie){
            System.out.println(ie.getMessage());
            ie.printStackTrace();
        }
    }

    public static void sleep(int nsec,String thread){
        try{
            System.out.println("["+thread+"] Sleep...");
            //int nsec=new Random().nextInt(maxTimer)+1;
            Thread.sleep(nsec*1000);
        }catch(InterruptedException ie){
            System.out.println(ie.getMessage());
            ie.printStackTrace();
        }
    }
}



//DebugSleep.sleep(getClass().getName());
//DebugSleep.sleep(12,getClass().getName());

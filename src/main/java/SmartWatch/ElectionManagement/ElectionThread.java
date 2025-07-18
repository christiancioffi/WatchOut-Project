package SmartWatch.ElectionManagement;

import SmartWatch.MainThreads.DebugSleep;

import java.util.Random;

public class ElectionThread implements Runnable{

    public ElectionThread(){

    }

    public void run(){
        Election.getInstance().executeElection();
    }

}

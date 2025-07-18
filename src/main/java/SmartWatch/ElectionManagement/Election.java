package SmartWatch.ElectionManagement;

import SmartWatch.Game.Player;
import SmartWatch.GlobalDataStructures.GameSession;
import SmartWatch.GlobalDataStructures.PlayerInfo;

import java.util.ArrayList;

public class Election {

    private final ElectionStatus status;


    private OkQueue okQueue;

    private static Election instance;


    private Election(){
        this.okQueue=null;
        status=new ElectionStatus();
    }

    public synchronized static Election getInstance(){
        if(instance==null){
            instance=new Election();
        }
        return instance;
    }


    public void seekerDiscoveredDuringPresentation(int seekerId){     //Simultaneous (received and sent) presentations  require synchronized
        status.seekerDiscoveredDuringPresentation(seekerId);
    }

    public void startElection(){      //Used only by the PlayerThread
        status.newElection();
    }

    public void waitSeekerElected(){
        status.waitSeekerElected();
    }

    public synchronized void coordinatorMessageReceived(int seekerId){
        status.coordinatorMessageArrived(seekerId);
        notify();
    }

    public void electionMessageReceived(){
        //status.startNewElection();
        status.newElection();
    }

    public int getSeekerId(){
        return status.getSeekerId();
    }

    public synchronized void executeElection(){
        System.out.println("[Election] Election thread "+Thread.currentThread().getId()+" started its job.");
        if(status.startElection()){
            if(status.amITheSeeker()){
                iAmTheSeeker();
            }else{
                if(checkIfBulliesAreAlive()){
                    waitForCoordinator();
                }else{
                    status.allPeersOffline();
                    iAmTheSeeker();
                }
            }
        }
        System.out.println("[Election] Election thread "+Thread.currentThread().getId()+" finished its job.");
    }

    private boolean checkIfBulliesAreAlive(){       //Lock on the election object is maintained even when wait for Ok messages
        boolean bulliesOnline=false;
        ArrayList<Player> bullies= GameSession.getInstance().getBullies();
        this.okQueue=new OkQueue(bullies.size());
        for(Player bully: bullies){
            Thread electionMessageThread=new Thread(new ElectionMessageThread(bully,okQueue));
            electionMessageThread.start();
        }
        bulliesOnline=okQueue.waitOks();
        if(!bulliesOnline){
            System.out.println("[Election] All the other peers (bullies) are offline. I am the new seeker.");
        }
        return bulliesOnline;
    }

    private void waitForCoordinator(){
        System.out.println("[Election] Waiting for a new coordinator...");
        try {
            wait(10000);     //Only 1 thread in wait (electionThread)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        status.endWaitCoordinator();
    }


    private void iAmTheSeeker(){
        ArrayList<Player> bullied= GameSession.getInstance().getBullied();
        ArrayList<Thread> threads=new ArrayList<Thread>();
        for(Player peer: bullied){
            Thread coordinatorMessageThread=new Thread(new CoordinatorMessageThread(peer, PlayerInfo.getInstance().getPlayer().getId()));
            threads.add(coordinatorMessageThread);
            coordinatorMessageThread.start();
        }
        for(Thread thread: threads){
            try{
                thread.join();
            }catch(InterruptedException ie){
                System.out.println(ie.getMessage());
            }
        }
        status.terminateElection();
    }


}

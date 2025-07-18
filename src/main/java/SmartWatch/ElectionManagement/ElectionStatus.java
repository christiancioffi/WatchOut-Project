package SmartWatch.ElectionManagement;

import SmartWatch.Game.Player;
import SmartWatch.GlobalDataStructures.GameSession;
import SmartWatch.GlobalDataStructures.PlayerInfo;

import java.util.ArrayList;

public class ElectionStatus {
    private Status status;

    private int lastSeekerElected;

    public ElectionStatus(){
        status=Status.Not_Started;
        lastSeekerElected= -1;
    }

    public synchronized void seekerDiscoveredDuringPresentation(int seekerId){
        if(status==Status.Not_Started){ //This means that the peer will believe in the first peer I receive a valid response from
            setSeekerId(seekerId);
            terminateElection();
            System.out.println("[Election] An election has been already executed by the other peers");
        }
    }

    public synchronized void coordinatorMessageArrived(int seekerId){
        if(status==Status.Not_Started || status==Status.Terminated){
            setSeekerId(seekerId);
            terminateElection();
        }else{          //Because a coordinator message, during an election, can be received only during the wait for a coordinator
            setSeekerId(seekerId);          //When we receive a Coordinator message while waiting for a coordinator message
        }
    }

    public synchronized void endWaitCoordinator(){
        if(status==Status.Coordinator_Elected){
            terminateElection();
        }else{
            System.out.println("[Election] No coordinator messages arrived while waiting");
            changeState(Status.Not_Started);
            newElection();
        }
    }

    public synchronized boolean amITheSeeker(){
        System.out.println("[Election] Am I the seeker?");
        ArrayList<Player> bullies= GameSession.getInstance().getBullies();
        boolean amITheSeeker=false;
        if(bullies.isEmpty()){
            System.out.println("[Election] I am the seeker!");
            setSeekerId(PlayerInfo.getInstance().getPlayer().getId());
            amITheSeeker=true;
        }else{
            System.out.println("[Election] I am not the seeker!");
        }

        return amITheSeeker;
    }

    public synchronized void allPeersOffline(){
        setSeekerId(PlayerInfo.getInstance().getPlayer().getId());
    }

    public synchronized int getSeekerId(){
        return this.lastSeekerElected;
    }

    public synchronized void terminateElection(){       //Callable only from synchronized methods (or synchronized code blocks)
        changeState(Status.Terminated);
        notify();
    }

    public synchronized void newElection(){
        Thread electionThread=new Thread(new ElectionThread());
        electionThread.start();
    }

    public synchronized boolean startElection(){
        if(status==Status.Not_Started){
            changeState(Status.Started);
            return true;
        }else{
            System.out.println("[Election] An election has been already executed or is executing...");
            return false;
        }
    }

    public synchronized void waitSeekerElected(){
        while(lastSeekerElected==-1){
            System.out.println("[Player] Waiting for the start of phase 1...");
            try{
                wait();
            }catch(InterruptedException ie){
                ie.printStackTrace();
            }
        }
    }

    private void setSeekerId(int seekerId){
        lastSeekerElected=seekerId;
        System.out.println("[Election] The new seeker is "+seekerId);
        changeState(Status.Coordinator_Elected);
    }

    private void changeState(Status status){
        this.status=status;
        System.out.println("[Election] State: "+status);
    }




}

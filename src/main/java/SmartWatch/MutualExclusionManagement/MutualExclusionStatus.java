package SmartWatch.MutualExclusionManagement;

import SmartWatch.Game.Player;
import SmartWatch.ElectionManagement.Election;
import SmartWatch.GlobalDataStructures.GameSession;
import SmartWatch.GlobalDataStructures.PlayerInfo;

import java.time.LocalTime;

import java.util.ArrayList;

public class MutualExclusionStatus {

    private Status status;

    private long playerTimestamp;


    private static MutualExclusionStatus instance;

    private MutualExclusionStatus(){
        status=Status.Not_Interested;
        playerTimestamp=Long.MAX_VALUE;
    }

    public static synchronized MutualExclusionStatus getInstance(){
        if(instance==null){
            instance=new MutualExclusionStatus();
        }
        return instance;
    }

    public synchronized void letAccess(int peerId, long timestamp){
        System.out.println("[Mutual Exclusion] My timestamp: "+playerTimestamp+". Peer "+peerId+"'s timestamp: "+timestamp);
        if(status==Status.In_Use || (status==Status.Interested && (timestamp>playerTimestamp || (timestamp==playerTimestamp && peerId<PlayerInfo.getInstance().getPlayer().getId())))){
            System.out.println("[Mutual Exclusion] Peer "+peerId+" must wait...");
            /*if(timestamp==playerTimestamp){
                System.out.println("[Mutual Exclusion] ***** Equal timestamps! *****");
            }*/
            try{
                wait();
            }catch(InterruptedException ie){
                System.out.println(ie.getMessage());
                ie.printStackTrace();
            }
        }
    }

    public boolean accessBase(){
        synchronized(this){
            if(status==Status.Not_Interested){
                System.out.println("[Mutual Exclusion] Trying to access the base...");
                status=Status.Interested;
                playerTimestamp=System.currentTimeMillis();
            }else{
                return false;
            }
        }
        askTheOtherPeers();
        synchronized(this){
            if(status==Status.Interested){
                status=Status.In_Use;
                return true;
            }else{
                return false;
            }
        }
    }

    public synchronized void releaseBase(){
        if(status==Status.In_Use){                      //Useless if
            status=Status.Already_Accessed;
            System.out.println("[Mutual Exclusion] Releasing the base... "+LocalTime.now());
            this.playerTimestamp=Long.MAX_VALUE;
            notifyAll();
        }
    }

    public synchronized boolean accessDenied(){
        if(status==Status.In_Use || status==Status.Already_Accessed){
            return false;
        }else{
            status=Status.Access_Denied;
            //System.out.println("[Mutual Exclusion] Seeker tagged me, so I must leave the game");
            this.playerTimestamp=Long.MAX_VALUE;
            notifyAll();
            return true;
        }
    }

    private void askTheOtherPeers(){
        int seekerId=Election.getInstance().getSeekerId();
        ArrayList<Player> hiders= GameSession.getInstance().getAllHiders(seekerId);
        System.out.println("[MutualExclusion] Contacting the following hiders: "+hiders);
        ArrayList<Thread> threads=new ArrayList<Thread>();
        for(Player p: hiders){
            Thread requestThread=new Thread(new MutualExclusionThread(p,playerTimestamp));
            threads.add(requestThread);
            requestThread.start();
        }
        for(Thread thread: threads){
            try{
                thread.join();
            }catch(InterruptedException ie){
                System.out.println(ie.getMessage());
            }
        }
    }




}

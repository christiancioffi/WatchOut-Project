package SmartWatch.Game;

public class PlayerStatus {
    private Status status;


    public PlayerStatus(){
        status= Status.Not_Ready;
    }

    public synchronized void setStatus(String s){
        try{
            this.status=Status.valueOf(s);
        }catch(IllegalArgumentException iae){
            System.out.println("Illegal Status");
        }
    }

    public synchronized String getStatus(){
        return this.status.name();
    }

    //---------------------------------------------------------
    //Ready=Phase0

    public synchronized void waitPlayerReady(){     //Used by playerThread (before election)
        while(status==Status.Not_Ready){
            System.out.println("[Player] Waiting for the start of the game...");
            try{
                wait();
            }catch(InterruptedException ie){
                ie.printStackTrace();
            }
        }
    }

    public synchronized void setPlayerReady(){      //Used by SubscribeMQTTThread
        status=Status.Ready;
        System.out.println("[Player] Player can play now!");
        notify();
    }

    public synchronized boolean isPlayerReady(){        //Used in the Election service
        return status.ordinal()>=Status.Ready.ordinal();
    }   //Used in the Election

    //---------------------------------------------------------

    public synchronized boolean hasWon(){
        return status==Status.Winner;
    }

    public synchronized boolean hasLost(){
        return status==Status.Loser;
    }
    public synchronized boolean hasFinished(){
        return status.ordinal()>Status.Ready.ordinal();
    }

}

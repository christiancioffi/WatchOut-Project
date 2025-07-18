package SmartWatch.Game;

import SmartWatch.ElectionManagement.Election;
import SmartWatch.GlobalDataStructures.GameSession;
import SmartWatch.GlobalDataStructures.PlayerInfo;
import SmartWatch.MutualExclusionManagement.MutualExclusionStatus;

import java.time.LocalTime;
import java.util.ArrayList;

public class PlayerThread implements Runnable{

    private final long baseTimer=10000;     //10 seconds

    public PlayerThread(){

    }

    public void run(){
        PlayerInfo.getInstance().getPlayer().getStatus().waitPlayerReady();     //Waiting for Admin to start the game
        phase0();
        play();
        System.out.println("[Player] Game over (still online).");
    }

    private void phase0(){       //start an election if needed (Phase 0)
        //DebugSleep.sleep(getClass().getName());
        Election.getInstance().startElection();
        Election.getInstance().waitSeekerElected();
    }

    private void play(){                                    //Phase 1
        if(!GameSession.getInstance().isGameOver()){
            //accessTheGame();
            if(PlayerInfo.getInstance().getPlayer().getId()==Election.getInstance().getSeekerId()){
                tagOthers();
            }else{
                hide();
            }
        }else{
            System.out.println("[Player] Game already ended.");
            notPlayed();
        }
    }

    //------------------SEEKER-----------------------


    private void tagOthers(){
        System.out.println("[Player] I am the seeker, so I must tag other players!");
        Player playerToTag= GameSession.getInstance().findNearestAvailablePlayer();

        while(playerToTag!=null){
            System.out.println("[Seeker] The nearest player is "+playerToTag);
            moveToThePlayer(playerToTag);
            Thread tagThread=new Thread(new TagThread(playerToTag));
            tagThread.start();
            try{
                tagThread.join();
            }catch(InterruptedException ie){
                System.out.println(ie.getMessage());
                ie.printStackTrace();
            }
            playerToTag= GameSession.getInstance().findNearestAvailablePlayer();
        }

        System.out.println("[Seeker] No more players available");
        //DebugSleep.sleep(30,getClass().getName());
        int tagged= GameSession.getInstance().getSummaryOfTheSeekerGame();
        if(tagged>0){       //Seeker has tagged at least a player
            win();
        }else{
            lose();
        }
        GameSession.getInstance().getSummaryOfTheGame();
    }

    private void moveToThePlayer(Player playerToTag){
        System.out.println("[Seeker] Going to the position of the player...");
        long speed=2;    //2 m/s
        Position seekerPosition=PlayerInfo.getInstance().getCurrentPosition();
        Position hiderPosition=playerToTag.getInitialPosition();
        long distance = (long) calculateDistance(seekerPosition,hiderPosition);
        long time = (distance / speed) * 1000;  //ms
        System.out.println("[Seeker] Walking for "+time+" ms ("+distance+" m)");
        try{
            Thread.sleep(time);
        }catch(InterruptedException ie){
            System.out.println(ie.getMessage());
            ie.printStackTrace();
        }
        PlayerInfo.getInstance().setCurrentPosition(playerToTag.getInitialPosition());
        System.out.println("[Seeker] Position reached ("+PlayerInfo.getInstance().getCurrentPosition()+") !");
    }

    private double calculateDistance(Position a, Position b) {
        double distance = 0;
        distance = Math.sqrt(Math.pow(a.getX() * 10 - b.getX() * 10, 2) + Math.pow(a.getY() * 10 - b.getY() * 10, 2));
        return distance;
    }

    //------------------HIDER------------------------


    private void hide(){
        System.out.println("[Player] I am a hider, so I must hide!");
        if(MutualExclusionStatus.getInstance().accessBase()){
            moveToTheBase();
            stayInTheBase();
            MutualExclusionStatus.getInstance().releaseBase();
            win();
        }else{
            lose();
        }
        gameOver();
        GameSession.getInstance().getSummaryOfTheGame();
    }

    private void moveToTheBase(){
        System.out.println("[Player] Permission acquired! Going to the base... "+ LocalTime.now());
        long speed=2;    //2 m/s
        long distance=(long) PlayerInfo.getInstance().getPlayer().getDistance();
        long time = (distance / speed)*1000;
        System.out.println("[Seeker] Walking for "+time+" ms ("+distance+" m)");
        try{
            Thread.sleep(time);
        }catch(InterruptedException ie){
            System.out.println(ie.getMessage());
            ie.printStackTrace();
        }
    }

    private void stayInTheBase(){
        System.out.println("[Player] Base reached! Now i will stay here for 10 seconds...");
        try{
            Thread.sleep(this.baseTimer);        //10 seconds
        }catch(InterruptedException ie){
            System.out.println(ie.getMessage());
            ie.printStackTrace();
        }
    }

    //-----------------BOTH---------------------

    /*private void accessTheGame(){
        PlayerInfo.getInstance().getPlayer().getStatus().setPlayerToPlay();
        broadcastMyStatus();
    }*/

    private void win(){
        PlayerInfo.getInstance().getPlayer().getStatus().setStatus(Status.Winner.name());
        System.out.println("[Player] I win! Must tell the others...");
        broadcastMyStatus();
    }

    private void lose(){
        PlayerInfo.getInstance().getPlayer().getStatus().setStatus(Status.Loser.name());
        System.out.println("[Player] I've lost...");
        broadcastMyStatus();
    }

    private void notPlayed(){
        PlayerInfo.getInstance().getPlayer().getStatus().setStatus(Status.Not_Played.name());
        System.out.println("[Player] I've not played...");
        broadcastMyStatus();
    }

    private void broadcastMyStatus(){
        ArrayList<Player> others= GameSession.getInstance().getOthers();
        //ArrayList<Thread> threads=new ArrayList<Thread>();
        for(Player p: others){
            Thread statusThread=new Thread(new StatusThread(p));
            //threads.add(statusThread);
            statusThread.start();
        }
        /*for(Thread t: threads){
            try{
                t.join();
            }catch(InterruptedException ie){
                System.out.println(ie.getMessage());
            }
        }*/
    }

    private void gameOver(){
        GameSession.getInstance().waitEndGame();
    }
}

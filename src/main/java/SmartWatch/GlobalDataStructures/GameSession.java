package SmartWatch.GlobalDataStructures;

import SmartWatch.Game.Player;
import SmartWatch.Game.Position;

import java.util.ArrayList;

public class GameSession {
    private final ArrayList<Player> others;
    private final GameOverBoolean gameOver;
    private static GameSession instance;

    private GameSession() {
        others = new ArrayList<Player>();
        gameOver=new GameOverBoolean();
    }

    public static synchronized GameSession getInstance() {
        if (instance == null) {
            instance = new GameSession();
        }
        return instance;
    }


    public synchronized void addOtherPlayer(Player player) {     //Both operations must be executed atomically
        player.setDistance(Base.getInstance().calculateDistanceFromBase(player.getInitialPosition()));           //Synchronized accesses to Player objects are not required.
        this.others.add(player);
    }

    public synchronized ArrayList<Player> getBullies() {
        ArrayList<Player> bullies;
        bullies = getPeersWithLessOrEqualDistance();
        return bullies;
    }

    public synchronized ArrayList<Player> getBullied() {
        ArrayList<Player> bullied;
        bullied = getPeersWithMoreDistance();
        return bullied;
    }

    private ArrayList<Player> getPeersWithLessOrEqualDistance() {
        Player player = PlayerInfo.getInstance().getPlayer();
        double distance = player.getDistance();
        ArrayList<Player> list = new ArrayList<Player>();
        for (Player p : this.others) {
            System.out.println("Player distance: " + distance + ", Other (" + p.getId() + ") distance: " + p.getDistance());
            if (p.getDistance() < distance || (p.getDistance() == distance && p.getId() > player.getId())) {     //The entry of the same player is automatically skipped
                list.add(p);
            }
        }
        return list;
    }

    private ArrayList<Player> getPeersWithMoreDistance() {
        Player player = PlayerInfo.getInstance().getPlayer();
        double distance = player.getDistance();
        ArrayList<Player> list = new ArrayList<Player>();
        for (Player p : this.others) {
            if (p.getDistance() > distance || (p.getDistance() == distance && p.getId() < player.getId())) {     //The entry of the same player is automatically skipped
                list.add(p);
            }
        }
        return list;
    }

    public synchronized ArrayList<Player> getOthers() {
        return new ArrayList<Player>(this.others);
    }

    public synchronized ArrayList<Player> getAllHiders(int seekerId) {       //SeekerId as a parameter to avoid deadlocks
        ArrayList<Player> hiders = new ArrayList<Player>();
        for (Player p : others) {
            if (p.getId() != seekerId) {
                hiders.add(p);
            }
        }
        return hiders;
    }

    public synchronized Player findNearestAvailablePlayer() {     //Only for the seeker
        Player nearest = null;
        Double distance;
        Double minDistance = Double.MAX_VALUE;
        ArrayList<Player> availablePlayers = getAvailablePlayers();
        for (Player p : availablePlayers) {
            distance = calculateDistance(PlayerInfo.getInstance().getCurrentPosition(), p.getInitialPosition());
            if (distance < minDistance) {
                minDistance = distance;
                nearest = p;
            }
        }
        if(nearest==null){
            gameOver();
        }
        return nearest;
    }

    private double calculateDistance(Position a, Position b) {
        double distance = 0;
        distance = Math.sqrt(Math.pow(a.getX() * 10 - b.getX() * 10, 2) + Math.pow(a.getY() * 10 - b.getY() * 10, 2));
        return distance;
    }

    private ArrayList<Player> getAvailablePlayers() {
        ArrayList<Player> availablePlayers = new ArrayList<Player>();
        for (Player p : others) {
            if (!p.getStatus().hasFinished()) {
                availablePlayers.add(p);
            }
        }
        return availablePlayers;
    }

    private boolean isThereAnyPlayerInGame() {      //Player that didn't end the game
        for (Player p : others) {
            if (!p.getStatus().hasFinished()) {
                return true;
            }
        }
        return false;
    }

    public synchronized void aPlayerFinishedToPlay(Player player, String status){
        player.getStatus().setStatus(status);
        notify();
    }


    public synchronized int getSummaryOfTheSeekerGame() {     //Only for the seeker
        int tagged=0;
        int safe=0;
        for (Player p : others) {
            if (p.getStatus().hasWon()) {
                safe++;
            }else if(p.getStatus().hasLost()){
                tagged++;
            }
        }
        System.out.println("[Seeker] Players that reached the base: "+safe);
        System.out.println("[Seeker] Players that were tagged: "+tagged);
        return tagged;
    }

    public synchronized void getSummaryOfTheGame() {
        System.out.println("--------------SUMMARY--------------");
        Player me=PlayerInfo.getInstance().getPlayer();
        System.out.println("[Player] Peer "+me.getId()+" status: "+me.getStatus().getStatus());
        for (Player p : others) {
            System.out.println("[Player] Peer "+p.getId()+" status: "+p.getStatus().getStatus());
        }
        System.out.println("------------------------------------");
    }

    public synchronized Player getPeerById(int id) {
        for (Player p : others) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public void gameOver(){     //Used only during presentation to other peers
        gameOver.gameOver();
    }
    public boolean isGameOver(){       //Used only at the beginning of the game and when a peer presents itself
        return gameOver.isGameOver();
    }

    public synchronized void waitEndGame(){     //Used by hiders only
        while(isThereAnyPlayerInGame()){
            System.out.println("[Player] Waiting for the end of the game...");
            try{
                wait();
            }catch(InterruptedException ie){
                ie.printStackTrace();
            }
        }
        gameOver();
    }
}

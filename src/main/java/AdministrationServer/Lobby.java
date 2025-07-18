package AdministrationServer;

import SmartWatch.Game.Player;
import SmartWatch.Game.Position;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;


public class Lobby {

    private HashMap<Integer, Player> players;
    private final int pitchDimension=10;            //Square pitch

    private final int maxPlayers=4*(pitchDimension-1);

    private int numPlayers=0;

    private ArrayList<Position> freePositions;
    private static Lobby instance=null;

    private Lobby(){
        this.players=new HashMap<Integer,Player>();
        this.freePositions=new ArrayList<Position>();
        //First column of the pitch
        for(int i=0;i<this.pitchDimension;i++){
            int j=0;
            freePositions.add(new Position(i,j));
        }
        //Last column of the pitch
        for(int i=0;i<this.pitchDimension;i++){
            int j=pitchDimension-1;
            freePositions.add(new Position(i,j));
        }
        //First row of the pitch
        for(int j=1;j<this.pitchDimension-1;j++){
            int i=0;
            freePositions.add(new Position(i,j));
        }
        //Last row of the pitch
        for(int j=1;j<this.pitchDimension-1;j++){
            int i=pitchDimension-1;
            freePositions.add(new Position(i,j));
        }
    }

    //Singleton pattern.
    public synchronized static Lobby getInstance(){
        if(instance==null){
            instance=new Lobby();
        }
        return instance;
    }

    //Add a player only if their id is not already used and if the number of maximum players is not exceeded.
    public Position addPlayer(Player player){
        Position position=null;
        int playerId=player.getId();
        try{
            if(checkPlayer(player)){
                //System.out.println("[Server] Adding a player...");
                synchronized(this){
                    if(this.numPlayers<=this.maxPlayers){
                        if(!this.players.containsKey(playerId)){
                            this.players.put(playerId,player);
                            this.numPlayers+=1;
                            position=generateRandomPosition();
                            System.out.println(position);
                            player.setInitialPosition(position);
                            StatisticsCollection.getInstance().addPlayer(player);
                        }
                    }
                }
            }
        }catch(Exception e){
            System.out.println("Addition of a player in the lobby failed.");
        }
        return position;
    }

    private Boolean checkPlayer(Player player) throws Exception{
        //System.out.println(player);
        int playerId=player.getId();
        String playerIP=player.getIpAddress();
        int playerPort=player.getPort();
        //System.out.println(player);
        return playerId>0 && playerIP.equals("localhost") && playerPort>0 && playerPort<=65535;
    }

    //Generate a random position on the perimeter
    //It is already synchronized by the fact that it is only called within a synchronized block (in addPlayer()).
    //Since this method is never called after maxPlayer is exceeded (and since freePositions.size()==maxPlayers), no check are peformed on the size of freePositions.
    private Position generateRandomPosition() {
        return freePositions.remove(new Random().nextInt(freePositions.size()-1));
    }

    public synchronized ArrayList<Player> getPlayersList() {
        ArrayList<Player> list=new ArrayList<Player>();
        for(Map.Entry<Integer,Player> entry: players.entrySet()) {
           list.add(entry.getValue());
        }
        return list;
    }

}

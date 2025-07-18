package SmartWatch.GlobalDataStructures;

import SmartWatch.ElectionManagement.Election;
import SmartWatch.Game.Player;
import SmartWatch.Game.Position;

public class PlayerInfo {           //Dummy class for the current player

    private Player player;

    private Position currentPosition;       //Only for the seeker


    private static PlayerInfo instance;


    private PlayerInfo(){

    }

    public static synchronized PlayerInfo getInstance(){
        if(instance==null){
            instance=new PlayerInfo();
        }
        return instance;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {  //Called only once, after registering to the REST Server.
        this.player = player;
        player.setDistance(Base.getInstance().calculateDistanceFromBase(player.getInitialPosition()));
        this.currentPosition=player.getInitialPosition();
    }

    //No need for synchronized for the following methods.
    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }


}

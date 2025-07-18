package AdministrationServer.Beans;
import AdministrationServer.Lobby;
import SmartWatch.Game.Player;
import SmartWatch.Game.Position;

import java.util.ArrayList;
import javax.xml.bind.annotation.*;

@XmlRootElement(name="response")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseAddPlayer {

    private Position startPosition;

    @XmlElementWrapper
    @XmlElement(name="player")
    private ArrayList<Player> players;

    public ResponseAddPlayer(){

    }


    public ResponseAddPlayer(Lobby lobby, Player player){
        this.startPosition=player.getInitialPosition();
        this.players=lobby.getPlayersList();
        this.players.remove(player);
    }

    public Position getStartPosition() {
        return startPosition;
    }


    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setStartPosition(Position startPosition) {
        this.startPosition = startPosition;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public String toString(){
        return "Players: "+this.players.toString()+"\nStarting Position: "+this.startPosition;
    }
}

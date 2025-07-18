package AdministrationServer.Beans;

import AdministrationServer.Lobby;
import SmartWatch.Game.Player;

import java.util.ArrayList;
import javax.xml.bind.annotation.*;

@XmlRootElement(name="response")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseGetAllPlayers {

    @XmlElementWrapper
    @XmlElement(name="player")
    private ArrayList<Player> players;

    public ResponseGetAllPlayers(){

    }


    public ResponseGetAllPlayers(Lobby lobby){
        this.players=lobby.getPlayersList();
    }


    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    @Override
    public String toString(){
        return "Players: "+players;
    }
}

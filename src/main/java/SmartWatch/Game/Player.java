package SmartWatch.Game;

import javax.xml.bind.annotation.*;

@XmlRootElement
public class Player {
    private int id;
    private String ipAddress;
    private int port;
    @XmlTransient
    private Position initialPosition;
    @XmlTransient
    private double distance;
    @XmlTransient
    private PlayerStatus status;

    public Player(){

    }
    public Player(int id,String ipAddress, int port){
        this.id=id;
        this.ipAddress=ipAddress;
        this.port=port;
        this.distance=Double.MAX_VALUE;
        this.status=new PlayerStatus();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Position getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(Position initialPosition) {
        this.initialPosition = initialPosition;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public PlayerStatus getStatus() {
        return status;
    }

    public void setStatus(PlayerStatus status) {    //Only for JABX
        this.status = status;
    }


    @Override
    public String toString(){
        return "\n{Player id: "+this.id+", IP Address: "+this.ipAddress+", Port Number: "+this.port+", Initial Position: "+this.initialPosition+"}";
    }

    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }
        if (!(o instanceof Player)) {
            return false;
        }
        Player p = (Player) o;
        if(this.id==p.getId() && this.ipAddress.equals(p.getIpAddress()) && this.port==p.getPort() && this.initialPosition.equals(p.getInitialPosition())){
            return true;
        }
        return false;
    }


}

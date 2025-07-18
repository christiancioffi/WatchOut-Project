package SmartWatch.GlobalDataStructures;

import SmartWatch.Game.Position;

public class Base {

    private final Position upSx=new Position(4,4);
    private final Position upDx=new Position(4,5);
    private final Position bottomSx=new Position(5,4);
    private final Position bottomDx=new Position(5,5);

    private static Base instance;

    private Base(){

    }

    public static synchronized Base getInstance(){
        if(instance==null){
            instance=new Base();
        }
        return instance;
    }
    public Position getUpSx() {
        return upSx;
    }

    public Position getUpDx() {
        return upDx;
    }

    public Position getBottomSx() {
        return bottomSx;
    }

    public Position getBottomDx() {
        return bottomDx;
    }

    public double calculateDistanceFromBase(Position playerPosition){
        Position basePosition;
        if(playerPosition.getX()<=getUpSx().getX() && playerPosition.getY()<=getUpSx().getY()){       //First quadrant
            basePosition=getUpSx();
        }else if(playerPosition.getX()<=getUpDx().getX() && playerPosition.getY()>=getUpDx().getY()){      //Second quadrant
            basePosition=getUpDx();
        }else if(playerPosition.getX()>=getBottomSx().getX() && playerPosition.getY()<=getBottomSx().getY()){     //Third quadrant
            basePosition=getBottomSx();
        }else{      //Fourth quadrant
            basePosition=getBottomDx();
        }
        return calculateDistance(playerPosition,basePosition);
    }

    private double calculateDistance(Position a, Position b){
        double distance=0;
        distance=Math.sqrt(Math.pow(a.getX()*10-b.getX()*10,2)+Math.pow(a.getY()*10-b.getY()*10,2));
        return distance;
    }

}

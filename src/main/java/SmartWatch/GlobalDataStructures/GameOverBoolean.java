package SmartWatch.GlobalDataStructures;

public class GameOverBoolean {

    private boolean gameEnded;

    public GameOverBoolean(){
        gameEnded=false;
    }

    public synchronized void gameOver(){
        gameEnded=true;
    }

    public synchronized boolean isGameOver(){
        return gameEnded;
    }

}

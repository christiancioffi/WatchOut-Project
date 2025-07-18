package SmartWatch.MainThreads;

import SmartWatch.Game.Player;
import SmartWatch.Game.PlayerThread;
import SmartWatch.GlobalDataStructures.GameSession;
import SmartWatch.GlobalDataStructures.PlayerInfo;
import SmartWatch.Initialization.PresentationThread;
import SmartWatch.Initialization.RegistrationToServerThread;
import SmartWatch.Initialization.SubscribeMQTTThread;
import SmartWatch.SensorManagement.AverageCalculator;
import SmartWatch.SensorManagement.AveragesList;
import SmartWatch.SensorManagement.BufferImpl;
import SmartWatch.SensorManagement.StatisticsManager;
import SmartWatch.Sensor.HRSimulator;

import java.util.ArrayList;

public class Main {

    private static PlayerInfo info;
    private static GameSession gameSession;
    public static void main(String[] args) {

        info=PlayerInfo.getInstance();
        gameSession = GameSession.getInstance();

        initializePlayer();

        Thread playerThread=new Thread(new PlayerThread());
        playerThread.start();

        System.out.println("[Initialization] Activating sensors...");
        BufferImpl buffer=BufferImpl.getInstance();
        AveragesList averages=AveragesList.getInstance();
        Thread sensorSimulator=new HRSimulator(buffer);
        Thread averageCalculatorThread = new Thread(new AverageCalculator(buffer,averages));
        Thread statisticsManagerThread = new Thread(new StatisticsManager(averages));
        sensorSimulator.start();
        averageCalculatorThread.start();
        statisticsManagerThread.start();
        System.out.println("[Initialization] Sensors activated");
    }

    private static void initializePlayer(){
        System.out.println("[Initialization] Registration to the REST Server...");
        Thread registrationToServerThread=new Thread(new RegistrationToServerThread());
        registrationToServerThread.start();
        try{
            registrationToServerThread.join();
        }catch(InterruptedException ie){
            System.out.println(ie.getMessage());
        }
        System.out.println("[Initialization] Registration to the REST Server completed");
        ArrayList<Player> peers= gameSession.getOthers();
        Thread waitNewMessagesThread=new Thread(new WaitNewMessagesThread(info.getPlayer().getPort()));
        waitNewMessagesThread.start();
        System.out.println("[Initialization] Presentation to the other peers...");
        for(Thread thread: presentingToPeers(peers)){
            try{
                thread.join();
            }catch(InterruptedException ie){
                System.out.println(ie.getMessage());
            }
        }
        System.out.println("[Initialization] Presentation to the other peers completed");
        System.out.println("[Initialization] Subscription to the MQTT Broker...");
        try{
            susbscribeToTopics().join();
        }catch(InterruptedException ie){
            System.out.println(ie.getMessage());
        }
        System.out.println("[Initialization] Subscription to the MQTT Broker completed");
        //GameStatus.getInstance().setPlayerReady();
    }



    private static ArrayList<Thread> presentingToPeers(ArrayList<Player> peers){
        ArrayList<Thread> threads=new ArrayList<Thread>();
        for(Player player : peers){
            Thread presentationThread=new Thread(new PresentationThread(info.getPlayer(), player));
            threads.add(presentationThread);
            presentationThread.start();
        }
        return threads;
    }

    private static Thread susbscribeToTopics(){
        Thread subscribeThread=new Thread(new SubscribeMQTTThread());
        subscribeThread.start();
        return subscribeThread;
    }
}

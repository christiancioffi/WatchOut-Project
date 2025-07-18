package SmartWatch.MainThreads;

import SmartWatch.ElectionManagement.ElectionServiceImpl;
import SmartWatch.Game.GameServiceImpl;
import SmartWatch.Initialization.PresentationServiceImpl;
import SmartWatch.MutualExclusionManagement.MutualExclusionServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class WaitNewMessagesThread implements Runnable{

    private final int port;

    public WaitNewMessagesThread(int port){
        this.port=port;
    }

    public void run(){
        try {

            Server server = ServerBuilder.forPort(port)
                    .addService(new PresentationServiceImpl())
                    .addService(new ElectionServiceImpl())
                    .addService(new MutualExclusionServiceImpl())
                    .addService(new GameServiceImpl())
                    .build();

            server.start();

            System.out.println("[WaitNewMessages Service] Waiting for new messages...");

            server.awaitTermination();

        } catch (IOException e) {

            //e.printStackTrace();
            //System.out.println(e.getMessage());

        } catch (InterruptedException e) {

            //e.printStackTrace();
            //System.out.println(e.getMessage());

        }
    }


}

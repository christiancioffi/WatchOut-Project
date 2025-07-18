package SmartWatch.Game;

import SmartWatch.GlobalDataStructures.PlayerInfo;
import grpc.game.GameGrpc;
import grpc.game.GameService.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class StatusThread implements Runnable{

    private Player peer;
    private String status;


    public StatusThread(Player peer){
        this.peer=peer;
        this.status= PlayerInfo.getInstance().getPlayer().getStatus().getStatus();
    }

    public void run(){

        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:"+peer.getPort()).usePlaintext().build();

        GameGrpc.GameBlockingStub stub = GameGrpc.newBlockingStub(channel);


        System.out.println("[Player] Telling peer "+peer.getId()+" my status: "+status);

        StatusMessage message = StatusMessage.newBuilder()
                .setPeerId(PlayerInfo.getInstance().getPlayer().getId())
                .setStatus(status)
                .build();

        try{
            stub.tellMeYourStatus(message);
        }catch(Exception e){
            System.out.println("[Player] Tried to tell peer "+peer.getId()+" my status, but it was offline");
        }



        channel.shutdown();
    }

}

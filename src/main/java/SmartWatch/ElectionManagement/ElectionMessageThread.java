package SmartWatch.ElectionManagement;

import SmartWatch.Game.Player;
import SmartWatch.GlobalDataStructures.PlayerInfo;
import grpc.election.ElectionGrpc;
import grpc.election.ElectionGrpc.*;
import grpc.election.ElectionService.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

public class ElectionMessageThread implements Runnable {

    private final Player bully;
    private final OkQueue okQueue;

    public ElectionMessageThread(Player bully, OkQueue okQueue){
        this.bully=bully;
        this.okQueue=okQueue;
    }

    public void run(){
        //Plaintext channel on the address (ip/port) which offers the Election service
        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:"+bully.getPort()).usePlaintext().build();

        //Creating a synchronous stub on the channel
        ElectionBlockingStub stub = ElectionGrpc.newBlockingStub(channel).withDeadlineAfter(10000, TimeUnit.MILLISECONDS);

        //Creating the ElectionMessage object which will be provided as input to the RPC method
        ElectionMessage message = ElectionMessage.newBuilder().setBulliedId(PlayerInfo.getInstance().getPlayer().getId()).build();

        String notReady="not ready";
        System.out.println("[Election] Sent election message to "+bully.getId());
        try {
            OkMessage response=stub.isOnline(message);
            if(response.getAck()){
                //The peer is online
                System.out.println("[Election] Received OK from "+bully.getId());
                this.okQueue.okMessageReceived();
            }else{
                throw new Exception(notReady);
            }

        } catch (Exception e) {
            String status="offline";
            //The peer is offline
            if(e.getMessage().equals(notReady)){
                status=e.getMessage();
            }
            System.out.println("[Election] Not received OK from "+bully.getId()+" because it was "+status);
            this.okQueue.okMessageNotReceived();
        }

        channel.shutdown();
    }
}

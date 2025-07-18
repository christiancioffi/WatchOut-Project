package SmartWatch.ElectionManagement;

import SmartWatch.Game.Player;
import grpc.election.ElectionGrpc;
import grpc.election.ElectionGrpc.*;
import grpc.election.ElectionService.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

public class CoordinatorMessageThread implements Runnable{

    private Player peer;
    private int seekerId;


    public CoordinatorMessageThread(Player peer, int seekerId){
        this.peer=peer;
        this.seekerId=seekerId;
    }

    public void run(){
        //Plaintext channel on the address (ip/port) which offers the Election service
        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:"+peer.getPort()).usePlaintext().build();

        //Creating a synchronous stub on the channel
        ElectionBlockingStub stub = ElectionGrpc.newBlockingStub(channel).withDeadlineAfter(10000, TimeUnit.MILLISECONDS);

        //Creating the CoordinatorMessage object which will be provided as input to the RPC method
        CoordinatorMessage message = CoordinatorMessage.newBuilder()
                .setSeekerId(seekerId)
                .build();

        try{
            /*if(PlayerInfo.getInstance().getPlayer().getId()==80){
                DebugSleep.sleep(12,getClass().getName());
            }*/
            OkMessage response=stub.setCoordinator(message);
            if(response.getAck()){
                //The peer is online
                System.out.println("[Election] Sent coordinator message to "+peer.getId());
            }else{
                throw new Exception();
            }
        }catch(Exception e){
            //The peer is offline
            System.out.println("[Election] Sent coordinator message to "+peer.getId()+", but it was offline.");
        }

        channel.shutdown();

    }
}

package SmartWatch.Game;

import grpc.game.GameGrpc;
import grpc.game.GameGrpc.*;
import grpc.game.GameService;
import grpc.game.GameService.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class TagThread implements Runnable{

    private Player peer;

    public TagThread(Player peer){
        this.peer=peer;
    }

    public void run(){
        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:"+peer.getPort()).usePlaintext().build();

        GameBlockingStub stub = GameGrpc.newBlockingStub(channel);

        System.out.println("[Seeker] Trying to tag peer "+peer.getId()+"...");

        GameService.TagMessage message = GameService.TagMessage.newBuilder().build();

        try{
            ResponseTagMessage response=stub.tag(message);
            if(response.getTagged()){
                System.out.println("[Seeker] Peer "+peer.getId()+" is tagged");
                peer.getStatus().setStatus(Status.Loser.name());
            }else{
                System.out.println("[Seeker] Peer "+peer.getId()+" is already safe");
                peer.getStatus().setStatus(Status.Winner.name());
            }
        }catch(Exception e){
            System.out.println("[Seeker] Peer "+peer.getId()+" was offline, so it is considered as tagged.");
            peer.getStatus().setStatus(Status.Loser.name());
        }


        channel.shutdown();
    }

}

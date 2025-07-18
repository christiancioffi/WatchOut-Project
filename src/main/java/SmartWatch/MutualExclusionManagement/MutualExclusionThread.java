package SmartWatch.MutualExclusionManagement;

import SmartWatch.Game.Player;
import SmartWatch.GlobalDataStructures.PlayerInfo;
import grpc.mutualExclusion.MutualExclusionGrpc;
import grpc.mutualExclusion.MutualExclusionGrpc.*;
import grpc.mutualExclusion.MutualExclusionService.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class MutualExclusionThread implements Runnable{

    private Player peer;
    private long timestamp;

    public MutualExclusionThread(Player peer, long timestamp){
        this.peer=peer;
        this.timestamp=timestamp;
    }

    public void run(){
        //Plaintext channel on the address (ip/port) which offers the MutualExclusion service
        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:"+peer.getPort()).usePlaintext().build();

        //Creating a synchronous stub on the channel
        MutualExclusionBlockingStub stub = MutualExclusionGrpc.newBlockingStub(channel);

        System.out.println("[Mutual Exclusion] Asking peer "+peer.getId()+" for permission...");

        //Creating the MERequest object which will be provided as input to the RPC method
        MERequest request = MERequest.newBuilder()
                .setPeerId(PlayerInfo.getInstance().getPlayer().getId())
                .setTimestamp(timestamp)
                .build();

        try{
            stub.letMeAccessTheBase(request);
            System.out.println("[Mutual Exclusion] OK received from Peer "+peer.getId());
        }catch(Exception e){
            System.out.println("[Mutual Exclusion] OK received from Peer "+peer.getId()+" (offline)");
        }


        channel.shutdown();
    }
}

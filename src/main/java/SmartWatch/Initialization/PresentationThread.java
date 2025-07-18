package SmartWatch.Initialization;

import SmartWatch.Game.Player;
import SmartWatch.Game.Position;
import SmartWatch.ElectionManagement.Election;
import SmartWatch.GlobalDataStructures.GameSession;
import SmartWatch.GlobalDataStructures.PlayerInfo;
import grpc.presentation.PresentationService.*;
import grpc.presentation.PresentationGrpc;
import grpc.presentation.PresentationGrpc.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class PresentationThread implements Runnable{

    private Player player;
    private Player peer;
    public PresentationThread(Player player,Player peer){this.player=player;this.peer=peer;}

    public void run(){
        presentingToPeer();
    }

    private void presentingToPeer(){

        //Plaintext channel on the address (ip/port) which offers the Presentation service
        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:"+peer.getPort()).usePlaintext().build();

        System.out.println("[Presentation Thread] Presenting to peer "+peer.getId()+". Waiting for a response...");
        //Creating a synchronous stub on the channel
        PresentationBlockingStub stub = PresentationGrpc.newBlockingStub(channel);
        Position position=player.getInitialPosition();

        //Creating the PresentationMessage object which will be provided as input to the RPC method
        PresentationMessage message = PresentationMessage.newBuilder()
                .setPlayerPar(PlayerPar.newBuilder().setId(player.getId()).setIp(player.getIpAddress()).setPort(player.getPort()))
                .setPositionPar(PositionPar.newBuilder().setX(position.getX()).setY(position.getY()))
                .build();


        try {
            //DebugSleep.sleep(new Random().nextInt(10)+1);
            PresentationResponse response=stub.presentation(message);
            //The peer is online
            int seekerId=response.getSeekerId();
            peer.getStatus().setStatus(response.getPeerStatus());
            System.out.println("[Presentation Thread] Presented to peer "+peer.getId()+". Its seeker is "+seekerId);
            if(seekerId!=-1){
                Election.getInstance().seekerDiscoveredDuringPresentation(seekerId);
            }
            if(response.getGameEnded()){
                GameSession.getInstance().gameOver();
            }
        } catch (Exception e) {         //The peer is offline
            //e.printStackTrace();
            //System.out.println(e.getMessage());
            System.out.println("[Presentation Thread] Presented to "+peer.getId()+", but it was offline.");
        }


        channel.shutdown();

    }
}

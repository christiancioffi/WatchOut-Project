package SmartWatch.Game;

import SmartWatch.GlobalDataStructures.GameSession;
import SmartWatch.GlobalDataStructures.PlayerInfo;
import SmartWatch.MutualExclusionManagement.MutualExclusionStatus;
import grpc.game.GameGrpc.*;
import grpc.game.GameService;
import grpc.game.GameService.*;
import io.grpc.stub.StreamObserver;

public class GameServiceImpl extends GameImplBase {

    @Override
    public void tag(GameService.TagMessage request, StreamObserver<GameService.ResponseTagMessage> responseObserver) {
        boolean tagged=false;
        if(MutualExclusionStatus.getInstance().accessDenied()){
            System.out.println("[Player] The seeker tagged me!");
            tagged=true;
        }else{
            System.out.println("[Player] The seeker tried to tag me, but I'm already safe...");
        }

        ResponseTagMessage response=ResponseTagMessage.newBuilder().setTagged(tagged).build();

        responseObserver.onNext(response);

        responseObserver.onCompleted();
    }

    @Override
    public void tellMeYourStatus(GameService.StatusMessage request, StreamObserver<GameService.ResponseStatusMessage> responseObserver) {
        int peerId=request.getPeerId();
        String status=request.getStatus();
        System.out.println("[Player] Peer "+peerId+" says that its status is: "+status);
        Player player= GameSession.getInstance().getPeerById(peerId);

        if(player!=null){
            GameSession.getInstance().aPlayerFinishedToPlay(player, status);
        }


        ResponseStatusMessage response=ResponseStatusMessage.newBuilder().build();

        responseObserver.onNext(response);

        responseObserver.onCompleted();
    }
}

package SmartWatch.Initialization;

import SmartWatch.ElectionManagement.Election;
import SmartWatch.Game.Status;
import SmartWatch.GlobalDataStructures.GameSession;
import SmartWatch.GlobalDataStructures.PlayerInfo;
import io.grpc.stub.StreamObserver;
import SmartWatch.Game.Player;
import SmartWatch.Game.Position;
import grpc.presentation.PresentationService.*;
import grpc.presentation.PresentationGrpc.*;

public class PresentationServiceImpl extends PresentationImplBase {
    @Override
    public void presentation(PresentationMessage message, StreamObserver<PresentationResponse> responseObserver){
        //System.out.println("-----New player!-----");
        PresentationResponse response;
        Player newPlayer=new Player(message.getPlayerPar().getId(),message.getPlayerPar().getIp(),message.getPlayerPar().getPort());
        Position position=new Position(message.getPositionPar().getX(),message.getPositionPar().getY());
        newPlayer.setInitialPosition(position);
        //Add to the others
        GameSession.getInstance().addOtherPlayer(newPlayer);

        System.out.println("[WaitNewPlayers Service] A new player just joined the game: "+newPlayer);
        response=PresentationResponse.newBuilder()
                .setSeekerId(Election.getInstance().getSeekerId())
                .setPeerStatus(PlayerInfo.getInstance().getPlayer().getStatus().getStatus())
                .setGameEnded(GameSession.getInstance().isGameOver())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
        //System.out.println("-------------------");
    }

}

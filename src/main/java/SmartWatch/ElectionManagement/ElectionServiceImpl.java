package SmartWatch.ElectionManagement;

import SmartWatch.GlobalDataStructures.PlayerInfo;
import io.grpc.stub.StreamObserver;
import grpc.election.ElectionService.*;
import grpc.election.ElectionGrpc.*;

public class ElectionServiceImpl extends ElectionImplBase {
    @Override
    public void isOnline(ElectionMessage message, StreamObserver<OkMessage> responseObserver){

        OkMessage response;
        boolean ack=true;

        if(PlayerInfo.getInstance().getPlayer().getStatus().isPlayerReady()){
            System.out.println("[Election] Election message received from "+message.getBulliedId());
            Election.getInstance().electionMessageReceived();
        }else{
            System.out.println("[Election] Election message received from "+message.getBulliedId()+", but player not ready");
        }
        /*if(PlayerInfo.getInstance().getPlayer().getId()==80){
            DebugSleep.sleep(12,getClass().getName());
        }*/
        response=OkMessage.newBuilder().setAck(ack).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void setCoordinator(CoordinatorMessage message, StreamObserver<OkMessage> responseObserver){

        OkMessage response;
        boolean ack=false;

        int seekerId=message.getSeekerId();
        System.out.println("[Election] Coordinator message received from "+seekerId);
        Election.getInstance().coordinatorMessageReceived(seekerId);

        ack=true;
        response=OkMessage.newBuilder().setAck(ack).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
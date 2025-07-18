package SmartWatch.MutualExclusionManagement;

import grpc.mutualExclusion.MutualExclusionGrpc;
import grpc.mutualExclusion.MutualExclusionService;
import grpc.mutualExclusion.MutualExclusionService.*;
import io.grpc.stub.StreamObserver;

public class MutualExclusionServiceImpl extends MutualExclusionGrpc.MutualExclusionImplBase {

    @Override
    public void letMeAccessTheBase(MutualExclusionService.MERequest request, StreamObserver<MutualExclusionService.OkResponse> responseObserver) {
        OkResponse response;
        int peerId=request.getPeerId();
        long timestamp=request.getTimestamp();

        MutualExclusionStatus status=MutualExclusionStatus.getInstance();

        System.out.println("[Mutual Exclusion] Request for accessing the base from peer "+peerId+" with timestamp "+timestamp);

        status.letAccess(peerId,timestamp);

        System.out.println("[Mutual Exclusion] Peer "+peerId+" can go to the base");

        response=OkResponse.newBuilder().build();

        responseObserver.onNext(response);

        responseObserver.onCompleted();


    }
}

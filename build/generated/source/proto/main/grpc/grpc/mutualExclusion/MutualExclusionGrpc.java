package grpc.mutualExclusion;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.25.0)",
    comments = "Source: MutualExclusionService.proto")
public final class MutualExclusionGrpc {

  private MutualExclusionGrpc() {}

  public static final String SERVICE_NAME = "grpc.mutualExclusion.MutualExclusion";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.mutualExclusion.MutualExclusionService.MERequest,
      grpc.mutualExclusion.MutualExclusionService.OkResponse> getLetMeAccessTheBaseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "letMeAccessTheBase",
      requestType = grpc.mutualExclusion.MutualExclusionService.MERequest.class,
      responseType = grpc.mutualExclusion.MutualExclusionService.OkResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.mutualExclusion.MutualExclusionService.MERequest,
      grpc.mutualExclusion.MutualExclusionService.OkResponse> getLetMeAccessTheBaseMethod() {
    io.grpc.MethodDescriptor<grpc.mutualExclusion.MutualExclusionService.MERequest, grpc.mutualExclusion.MutualExclusionService.OkResponse> getLetMeAccessTheBaseMethod;
    if ((getLetMeAccessTheBaseMethod = MutualExclusionGrpc.getLetMeAccessTheBaseMethod) == null) {
      synchronized (MutualExclusionGrpc.class) {
        if ((getLetMeAccessTheBaseMethod = MutualExclusionGrpc.getLetMeAccessTheBaseMethod) == null) {
          MutualExclusionGrpc.getLetMeAccessTheBaseMethod = getLetMeAccessTheBaseMethod =
              io.grpc.MethodDescriptor.<grpc.mutualExclusion.MutualExclusionService.MERequest, grpc.mutualExclusion.MutualExclusionService.OkResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "letMeAccessTheBase"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.mutualExclusion.MutualExclusionService.MERequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.mutualExclusion.MutualExclusionService.OkResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MutualExclusionMethodDescriptorSupplier("letMeAccessTheBase"))
              .build();
        }
      }
    }
    return getLetMeAccessTheBaseMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MutualExclusionStub newStub(io.grpc.Channel channel) {
    return new MutualExclusionStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MutualExclusionBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new MutualExclusionBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MutualExclusionFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new MutualExclusionFutureStub(channel);
  }

  /**
   */
  public static abstract class MutualExclusionImplBase implements io.grpc.BindableService {

    /**
     */
    public void letMeAccessTheBase(grpc.mutualExclusion.MutualExclusionService.MERequest request,
        io.grpc.stub.StreamObserver<grpc.mutualExclusion.MutualExclusionService.OkResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLetMeAccessTheBaseMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLetMeAccessTheBaseMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.mutualExclusion.MutualExclusionService.MERequest,
                grpc.mutualExclusion.MutualExclusionService.OkResponse>(
                  this, METHODID_LET_ME_ACCESS_THE_BASE)))
          .build();
    }
  }

  /**
   */
  public static final class MutualExclusionStub extends io.grpc.stub.AbstractStub<MutualExclusionStub> {
    private MutualExclusionStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MutualExclusionStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MutualExclusionStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MutualExclusionStub(channel, callOptions);
    }

    /**
     */
    public void letMeAccessTheBase(grpc.mutualExclusion.MutualExclusionService.MERequest request,
        io.grpc.stub.StreamObserver<grpc.mutualExclusion.MutualExclusionService.OkResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLetMeAccessTheBaseMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class MutualExclusionBlockingStub extends io.grpc.stub.AbstractStub<MutualExclusionBlockingStub> {
    private MutualExclusionBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MutualExclusionBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MutualExclusionBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MutualExclusionBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.mutualExclusion.MutualExclusionService.OkResponse letMeAccessTheBase(grpc.mutualExclusion.MutualExclusionService.MERequest request) {
      return blockingUnaryCall(
          getChannel(), getLetMeAccessTheBaseMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class MutualExclusionFutureStub extends io.grpc.stub.AbstractStub<MutualExclusionFutureStub> {
    private MutualExclusionFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MutualExclusionFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MutualExclusionFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MutualExclusionFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.mutualExclusion.MutualExclusionService.OkResponse> letMeAccessTheBase(
        grpc.mutualExclusion.MutualExclusionService.MERequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLetMeAccessTheBaseMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LET_ME_ACCESS_THE_BASE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MutualExclusionImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MutualExclusionImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LET_ME_ACCESS_THE_BASE:
          serviceImpl.letMeAccessTheBase((grpc.mutualExclusion.MutualExclusionService.MERequest) request,
              (io.grpc.stub.StreamObserver<grpc.mutualExclusion.MutualExclusionService.OkResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class MutualExclusionBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MutualExclusionBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.mutualExclusion.MutualExclusionService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MutualExclusion");
    }
  }

  private static final class MutualExclusionFileDescriptorSupplier
      extends MutualExclusionBaseDescriptorSupplier {
    MutualExclusionFileDescriptorSupplier() {}
  }

  private static final class MutualExclusionMethodDescriptorSupplier
      extends MutualExclusionBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MutualExclusionMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MutualExclusionGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MutualExclusionFileDescriptorSupplier())
              .addMethod(getLetMeAccessTheBaseMethod())
              .build();
        }
      }
    }
    return result;
  }
}

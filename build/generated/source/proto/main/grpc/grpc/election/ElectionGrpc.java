package grpc.election;

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
    comments = "Source: ElectionService.proto")
public final class ElectionGrpc {

  private ElectionGrpc() {}

  public static final String SERVICE_NAME = "grpc.election.Election";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.election.ElectionService.ElectionMessage,
      grpc.election.ElectionService.OkMessage> getIsOnlineMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "isOnline",
      requestType = grpc.election.ElectionService.ElectionMessage.class,
      responseType = grpc.election.ElectionService.OkMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.election.ElectionService.ElectionMessage,
      grpc.election.ElectionService.OkMessage> getIsOnlineMethod() {
    io.grpc.MethodDescriptor<grpc.election.ElectionService.ElectionMessage, grpc.election.ElectionService.OkMessage> getIsOnlineMethod;
    if ((getIsOnlineMethod = ElectionGrpc.getIsOnlineMethod) == null) {
      synchronized (ElectionGrpc.class) {
        if ((getIsOnlineMethod = ElectionGrpc.getIsOnlineMethod) == null) {
          ElectionGrpc.getIsOnlineMethod = getIsOnlineMethod =
              io.grpc.MethodDescriptor.<grpc.election.ElectionService.ElectionMessage, grpc.election.ElectionService.OkMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "isOnline"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.election.ElectionService.ElectionMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.election.ElectionService.OkMessage.getDefaultInstance()))
              .setSchemaDescriptor(new ElectionMethodDescriptorSupplier("isOnline"))
              .build();
        }
      }
    }
    return getIsOnlineMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.election.ElectionService.CoordinatorMessage,
      grpc.election.ElectionService.OkMessage> getSetCoordinatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "setCoordinator",
      requestType = grpc.election.ElectionService.CoordinatorMessage.class,
      responseType = grpc.election.ElectionService.OkMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.election.ElectionService.CoordinatorMessage,
      grpc.election.ElectionService.OkMessage> getSetCoordinatorMethod() {
    io.grpc.MethodDescriptor<grpc.election.ElectionService.CoordinatorMessage, grpc.election.ElectionService.OkMessage> getSetCoordinatorMethod;
    if ((getSetCoordinatorMethod = ElectionGrpc.getSetCoordinatorMethod) == null) {
      synchronized (ElectionGrpc.class) {
        if ((getSetCoordinatorMethod = ElectionGrpc.getSetCoordinatorMethod) == null) {
          ElectionGrpc.getSetCoordinatorMethod = getSetCoordinatorMethod =
              io.grpc.MethodDescriptor.<grpc.election.ElectionService.CoordinatorMessage, grpc.election.ElectionService.OkMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "setCoordinator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.election.ElectionService.CoordinatorMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.election.ElectionService.OkMessage.getDefaultInstance()))
              .setSchemaDescriptor(new ElectionMethodDescriptorSupplier("setCoordinator"))
              .build();
        }
      }
    }
    return getSetCoordinatorMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ElectionStub newStub(io.grpc.Channel channel) {
    return new ElectionStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ElectionBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ElectionBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ElectionFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ElectionFutureStub(channel);
  }

  /**
   */
  public static abstract class ElectionImplBase implements io.grpc.BindableService {

    /**
     */
    public void isOnline(grpc.election.ElectionService.ElectionMessage request,
        io.grpc.stub.StreamObserver<grpc.election.ElectionService.OkMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getIsOnlineMethod(), responseObserver);
    }

    /**
     */
    public void setCoordinator(grpc.election.ElectionService.CoordinatorMessage request,
        io.grpc.stub.StreamObserver<grpc.election.ElectionService.OkMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getSetCoordinatorMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getIsOnlineMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.election.ElectionService.ElectionMessage,
                grpc.election.ElectionService.OkMessage>(
                  this, METHODID_IS_ONLINE)))
          .addMethod(
            getSetCoordinatorMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.election.ElectionService.CoordinatorMessage,
                grpc.election.ElectionService.OkMessage>(
                  this, METHODID_SET_COORDINATOR)))
          .build();
    }
  }

  /**
   */
  public static final class ElectionStub extends io.grpc.stub.AbstractStub<ElectionStub> {
    private ElectionStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ElectionStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ElectionStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ElectionStub(channel, callOptions);
    }

    /**
     */
    public void isOnline(grpc.election.ElectionService.ElectionMessage request,
        io.grpc.stub.StreamObserver<grpc.election.ElectionService.OkMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getIsOnlineMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setCoordinator(grpc.election.ElectionService.CoordinatorMessage request,
        io.grpc.stub.StreamObserver<grpc.election.ElectionService.OkMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetCoordinatorMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ElectionBlockingStub extends io.grpc.stub.AbstractStub<ElectionBlockingStub> {
    private ElectionBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ElectionBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ElectionBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ElectionBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.election.ElectionService.OkMessage isOnline(grpc.election.ElectionService.ElectionMessage request) {
      return blockingUnaryCall(
          getChannel(), getIsOnlineMethod(), getCallOptions(), request);
    }

    /**
     */
    public grpc.election.ElectionService.OkMessage setCoordinator(grpc.election.ElectionService.CoordinatorMessage request) {
      return blockingUnaryCall(
          getChannel(), getSetCoordinatorMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ElectionFutureStub extends io.grpc.stub.AbstractStub<ElectionFutureStub> {
    private ElectionFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ElectionFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ElectionFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ElectionFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.election.ElectionService.OkMessage> isOnline(
        grpc.election.ElectionService.ElectionMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getIsOnlineMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.election.ElectionService.OkMessage> setCoordinator(
        grpc.election.ElectionService.CoordinatorMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getSetCoordinatorMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_IS_ONLINE = 0;
  private static final int METHODID_SET_COORDINATOR = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ElectionImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ElectionImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_IS_ONLINE:
          serviceImpl.isOnline((grpc.election.ElectionService.ElectionMessage) request,
              (io.grpc.stub.StreamObserver<grpc.election.ElectionService.OkMessage>) responseObserver);
          break;
        case METHODID_SET_COORDINATOR:
          serviceImpl.setCoordinator((grpc.election.ElectionService.CoordinatorMessage) request,
              (io.grpc.stub.StreamObserver<grpc.election.ElectionService.OkMessage>) responseObserver);
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

  private static abstract class ElectionBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ElectionBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.election.ElectionService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Election");
    }
  }

  private static final class ElectionFileDescriptorSupplier
      extends ElectionBaseDescriptorSupplier {
    ElectionFileDescriptorSupplier() {}
  }

  private static final class ElectionMethodDescriptorSupplier
      extends ElectionBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ElectionMethodDescriptorSupplier(String methodName) {
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
      synchronized (ElectionGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ElectionFileDescriptorSupplier())
              .addMethod(getIsOnlineMethod())
              .addMethod(getSetCoordinatorMethod())
              .build();
        }
      }
    }
    return result;
  }
}

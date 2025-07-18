package grpc.game;

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
    comments = "Source: GameService.proto")
public final class GameGrpc {

  private GameGrpc() {}

  public static final String SERVICE_NAME = "grpc.game.Game";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.game.GameService.TagMessage,
      grpc.game.GameService.ResponseTagMessage> getTagMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "tag",
      requestType = grpc.game.GameService.TagMessage.class,
      responseType = grpc.game.GameService.ResponseTagMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.game.GameService.TagMessage,
      grpc.game.GameService.ResponseTagMessage> getTagMethod() {
    io.grpc.MethodDescriptor<grpc.game.GameService.TagMessage, grpc.game.GameService.ResponseTagMessage> getTagMethod;
    if ((getTagMethod = GameGrpc.getTagMethod) == null) {
      synchronized (GameGrpc.class) {
        if ((getTagMethod = GameGrpc.getTagMethod) == null) {
          GameGrpc.getTagMethod = getTagMethod =
              io.grpc.MethodDescriptor.<grpc.game.GameService.TagMessage, grpc.game.GameService.ResponseTagMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "tag"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.game.GameService.TagMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.game.GameService.ResponseTagMessage.getDefaultInstance()))
              .setSchemaDescriptor(new GameMethodDescriptorSupplier("tag"))
              .build();
        }
      }
    }
    return getTagMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.game.GameService.StatusMessage,
      grpc.game.GameService.ResponseStatusMessage> getTellMeYourStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "tellMeYourStatus",
      requestType = grpc.game.GameService.StatusMessage.class,
      responseType = grpc.game.GameService.ResponseStatusMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.game.GameService.StatusMessage,
      grpc.game.GameService.ResponseStatusMessage> getTellMeYourStatusMethod() {
    io.grpc.MethodDescriptor<grpc.game.GameService.StatusMessage, grpc.game.GameService.ResponseStatusMessage> getTellMeYourStatusMethod;
    if ((getTellMeYourStatusMethod = GameGrpc.getTellMeYourStatusMethod) == null) {
      synchronized (GameGrpc.class) {
        if ((getTellMeYourStatusMethod = GameGrpc.getTellMeYourStatusMethod) == null) {
          GameGrpc.getTellMeYourStatusMethod = getTellMeYourStatusMethod =
              io.grpc.MethodDescriptor.<grpc.game.GameService.StatusMessage, grpc.game.GameService.ResponseStatusMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "tellMeYourStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.game.GameService.StatusMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.game.GameService.ResponseStatusMessage.getDefaultInstance()))
              .setSchemaDescriptor(new GameMethodDescriptorSupplier("tellMeYourStatus"))
              .build();
        }
      }
    }
    return getTellMeYourStatusMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GameStub newStub(io.grpc.Channel channel) {
    return new GameStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GameBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GameBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GameFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GameFutureStub(channel);
  }

  /**
   */
  public static abstract class GameImplBase implements io.grpc.BindableService {

    /**
     */
    public void tag(grpc.game.GameService.TagMessage request,
        io.grpc.stub.StreamObserver<grpc.game.GameService.ResponseTagMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getTagMethod(), responseObserver);
    }

    /**
     */
    public void tellMeYourStatus(grpc.game.GameService.StatusMessage request,
        io.grpc.stub.StreamObserver<grpc.game.GameService.ResponseStatusMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getTellMeYourStatusMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getTagMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.game.GameService.TagMessage,
                grpc.game.GameService.ResponseTagMessage>(
                  this, METHODID_TAG)))
          .addMethod(
            getTellMeYourStatusMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.game.GameService.StatusMessage,
                grpc.game.GameService.ResponseStatusMessage>(
                  this, METHODID_TELL_ME_YOUR_STATUS)))
          .build();
    }
  }

  /**
   */
  public static final class GameStub extends io.grpc.stub.AbstractStub<GameStub> {
    private GameStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GameStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GameStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GameStub(channel, callOptions);
    }

    /**
     */
    public void tag(grpc.game.GameService.TagMessage request,
        io.grpc.stub.StreamObserver<grpc.game.GameService.ResponseTagMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTagMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void tellMeYourStatus(grpc.game.GameService.StatusMessage request,
        io.grpc.stub.StreamObserver<grpc.game.GameService.ResponseStatusMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTellMeYourStatusMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class GameBlockingStub extends io.grpc.stub.AbstractStub<GameBlockingStub> {
    private GameBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GameBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GameBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GameBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.game.GameService.ResponseTagMessage tag(grpc.game.GameService.TagMessage request) {
      return blockingUnaryCall(
          getChannel(), getTagMethod(), getCallOptions(), request);
    }

    /**
     */
    public grpc.game.GameService.ResponseStatusMessage tellMeYourStatus(grpc.game.GameService.StatusMessage request) {
      return blockingUnaryCall(
          getChannel(), getTellMeYourStatusMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GameFutureStub extends io.grpc.stub.AbstractStub<GameFutureStub> {
    private GameFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GameFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GameFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GameFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.game.GameService.ResponseTagMessage> tag(
        grpc.game.GameService.TagMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getTagMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.game.GameService.ResponseStatusMessage> tellMeYourStatus(
        grpc.game.GameService.StatusMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getTellMeYourStatusMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_TAG = 0;
  private static final int METHODID_TELL_ME_YOUR_STATUS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GameImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GameImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_TAG:
          serviceImpl.tag((grpc.game.GameService.TagMessage) request,
              (io.grpc.stub.StreamObserver<grpc.game.GameService.ResponseTagMessage>) responseObserver);
          break;
        case METHODID_TELL_ME_YOUR_STATUS:
          serviceImpl.tellMeYourStatus((grpc.game.GameService.StatusMessage) request,
              (io.grpc.stub.StreamObserver<grpc.game.GameService.ResponseStatusMessage>) responseObserver);
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

  private static abstract class GameBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GameBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.game.GameService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Game");
    }
  }

  private static final class GameFileDescriptorSupplier
      extends GameBaseDescriptorSupplier {
    GameFileDescriptorSupplier() {}
  }

  private static final class GameMethodDescriptorSupplier
      extends GameBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GameMethodDescriptorSupplier(String methodName) {
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
      synchronized (GameGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GameFileDescriptorSupplier())
              .addMethod(getTagMethod())
              .addMethod(getTellMeYourStatusMethod())
              .build();
        }
      }
    }
    return result;
  }
}

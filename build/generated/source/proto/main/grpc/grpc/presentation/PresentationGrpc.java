package grpc.presentation;

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
    comments = "Source: PresentationService.proto")
public final class PresentationGrpc {

  private PresentationGrpc() {}

  public static final String SERVICE_NAME = "grpc.presentation.Presentation";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.presentation.PresentationService.PresentationMessage,
      grpc.presentation.PresentationService.PresentationResponse> getPresentationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "presentation",
      requestType = grpc.presentation.PresentationService.PresentationMessage.class,
      responseType = grpc.presentation.PresentationService.PresentationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.presentation.PresentationService.PresentationMessage,
      grpc.presentation.PresentationService.PresentationResponse> getPresentationMethod() {
    io.grpc.MethodDescriptor<grpc.presentation.PresentationService.PresentationMessage, grpc.presentation.PresentationService.PresentationResponse> getPresentationMethod;
    if ((getPresentationMethod = PresentationGrpc.getPresentationMethod) == null) {
      synchronized (PresentationGrpc.class) {
        if ((getPresentationMethod = PresentationGrpc.getPresentationMethod) == null) {
          PresentationGrpc.getPresentationMethod = getPresentationMethod =
              io.grpc.MethodDescriptor.<grpc.presentation.PresentationService.PresentationMessage, grpc.presentation.PresentationService.PresentationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "presentation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.presentation.PresentationService.PresentationMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.presentation.PresentationService.PresentationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new PresentationMethodDescriptorSupplier("presentation"))
              .build();
        }
      }
    }
    return getPresentationMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PresentationStub newStub(io.grpc.Channel channel) {
    return new PresentationStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PresentationBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new PresentationBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PresentationFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new PresentationFutureStub(channel);
  }

  /**
   */
  public static abstract class PresentationImplBase implements io.grpc.BindableService {

    /**
     */
    public void presentation(grpc.presentation.PresentationService.PresentationMessage request,
        io.grpc.stub.StreamObserver<grpc.presentation.PresentationService.PresentationResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPresentationMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getPresentationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.presentation.PresentationService.PresentationMessage,
                grpc.presentation.PresentationService.PresentationResponse>(
                  this, METHODID_PRESENTATION)))
          .build();
    }
  }

  /**
   */
  public static final class PresentationStub extends io.grpc.stub.AbstractStub<PresentationStub> {
    private PresentationStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PresentationStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PresentationStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PresentationStub(channel, callOptions);
    }

    /**
     */
    public void presentation(grpc.presentation.PresentationService.PresentationMessage request,
        io.grpc.stub.StreamObserver<grpc.presentation.PresentationService.PresentationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPresentationMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class PresentationBlockingStub extends io.grpc.stub.AbstractStub<PresentationBlockingStub> {
    private PresentationBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PresentationBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PresentationBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PresentationBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.presentation.PresentationService.PresentationResponse presentation(grpc.presentation.PresentationService.PresentationMessage request) {
      return blockingUnaryCall(
          getChannel(), getPresentationMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class PresentationFutureStub extends io.grpc.stub.AbstractStub<PresentationFutureStub> {
    private PresentationFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PresentationFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PresentationFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PresentationFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.presentation.PresentationService.PresentationResponse> presentation(
        grpc.presentation.PresentationService.PresentationMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getPresentationMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PRESENTATION = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PresentationImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(PresentationImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PRESENTATION:
          serviceImpl.presentation((grpc.presentation.PresentationService.PresentationMessage) request,
              (io.grpc.stub.StreamObserver<grpc.presentation.PresentationService.PresentationResponse>) responseObserver);
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

  private static abstract class PresentationBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PresentationBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.presentation.PresentationService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Presentation");
    }
  }

  private static final class PresentationFileDescriptorSupplier
      extends PresentationBaseDescriptorSupplier {
    PresentationFileDescriptorSupplier() {}
  }

  private static final class PresentationMethodDescriptorSupplier
      extends PresentationBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    PresentationMethodDescriptorSupplier(String methodName) {
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
      synchronized (PresentationGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PresentationFileDescriptorSupplier())
              .addMethod(getPresentationMethod())
              .build();
        }
      }
    }
    return result;
  }
}

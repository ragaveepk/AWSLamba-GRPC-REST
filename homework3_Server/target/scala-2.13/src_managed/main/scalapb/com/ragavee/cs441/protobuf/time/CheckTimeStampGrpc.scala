package com.ragavee.cs441.protobuf.time

object CheckTimeStampGrpc {
  val METHOD_IS_TIME_STAMPEXISTS: _root_.io.grpc.MethodDescriptor[com.ragavee.cs441.protobuf.time.Inputtime, com.ragavee.cs441.protobuf.time.Response] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("CheckTimeStamp", "isTimeStampexists"))
      .setSampledToLocalTracing(true)
      .setRequestMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[com.ragavee.cs441.protobuf.time.Inputtime])
      .setResponseMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[com.ragavee.cs441.protobuf.time.Response])
      .setSchemaDescriptor(_root_.scalapb.grpc.ConcreteProtoMethodDescriptorSupplier.fromMethodDescriptor(com.ragavee.cs441.protobuf.time.TimeProto.javaDescriptor.getServices().get(0).getMethods().get(0)))
      .build()
  
  val SERVICE: _root_.io.grpc.ServiceDescriptor =
    _root_.io.grpc.ServiceDescriptor.newBuilder("CheckTimeStamp")
      .setSchemaDescriptor(new _root_.scalapb.grpc.ConcreteProtoFileDescriptorSupplier(com.ragavee.cs441.protobuf.time.TimeProto.javaDescriptor))
      .addMethod(METHOD_IS_TIME_STAMPEXISTS)
      .build()
  
  trait CheckTimeStamp extends _root_.scalapb.grpc.AbstractService {
    override def serviceCompanion = CheckTimeStamp
    def isTimeStampexists(request: com.ragavee.cs441.protobuf.time.Inputtime): scala.concurrent.Future[com.ragavee.cs441.protobuf.time.Response]
  }
  
  object CheckTimeStamp extends _root_.scalapb.grpc.ServiceCompanion[CheckTimeStamp] {
    implicit def serviceCompanion: _root_.scalapb.grpc.ServiceCompanion[CheckTimeStamp] = this
    def javaDescriptor: _root_.com.google.protobuf.Descriptors.ServiceDescriptor = com.ragavee.cs441.protobuf.time.TimeProto.javaDescriptor.getServices().get(0)
    def scalaDescriptor: _root_.scalapb.descriptors.ServiceDescriptor = com.ragavee.cs441.protobuf.time.TimeProto.scalaDescriptor.services(0)
    def bindService(serviceImpl: CheckTimeStamp, executionContext: scala.concurrent.ExecutionContext): _root_.io.grpc.ServerServiceDefinition =
      _root_.io.grpc.ServerServiceDefinition.builder(SERVICE)
      .addMethod(
        METHOD_IS_TIME_STAMPEXISTS,
        _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[com.ragavee.cs441.protobuf.time.Inputtime, com.ragavee.cs441.protobuf.time.Response] {
          override def invoke(request: com.ragavee.cs441.protobuf.time.Inputtime, observer: _root_.io.grpc.stub.StreamObserver[com.ragavee.cs441.protobuf.time.Response]): _root_.scala.Unit =
            serviceImpl.isTimeStampexists(request).onComplete(scalapb.grpc.Grpc.completeObserver(observer))(
              executionContext)
        }))
      .build()
  }
  
  trait CheckTimeStampBlockingClient {
    def serviceCompanion = CheckTimeStamp
    def isTimeStampexists(request: com.ragavee.cs441.protobuf.time.Inputtime): com.ragavee.cs441.protobuf.time.Response
  }
  
  class CheckTimeStampBlockingStub(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions = _root_.io.grpc.CallOptions.DEFAULT) extends _root_.io.grpc.stub.AbstractStub[CheckTimeStampBlockingStub](channel, options) with CheckTimeStampBlockingClient {
    override def isTimeStampexists(request: com.ragavee.cs441.protobuf.time.Inputtime): com.ragavee.cs441.protobuf.time.Response = {
      _root_.scalapb.grpc.ClientCalls.blockingUnaryCall(channel, METHOD_IS_TIME_STAMPEXISTS, options, request)
    }
    
    override def build(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions): CheckTimeStampBlockingStub = new CheckTimeStampBlockingStub(channel, options)
  }
  
  class CheckTimeStampStub(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions = _root_.io.grpc.CallOptions.DEFAULT) extends _root_.io.grpc.stub.AbstractStub[CheckTimeStampStub](channel, options) with CheckTimeStamp {
    override def isTimeStampexists(request: com.ragavee.cs441.protobuf.time.Inputtime): scala.concurrent.Future[com.ragavee.cs441.protobuf.time.Response] = {
      _root_.scalapb.grpc.ClientCalls.asyncUnaryCall(channel, METHOD_IS_TIME_STAMPEXISTS, options, request)
    }
    
    override def build(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions): CheckTimeStampStub = new CheckTimeStampStub(channel, options)
  }
  
  def bindService(serviceImpl: CheckTimeStamp, executionContext: scala.concurrent.ExecutionContext): _root_.io.grpc.ServerServiceDefinition = CheckTimeStamp.bindService(serviceImpl, executionContext)
  
  def blockingStub(channel: _root_.io.grpc.Channel): CheckTimeStampBlockingStub = new CheckTimeStampBlockingStub(channel)
  
  def stub(channel: _root_.io.grpc.Channel): CheckTimeStampStub = new CheckTimeStampStub(channel)
  
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.ServiceDescriptor = com.ragavee.cs441.protobuf.time.TimeProto.javaDescriptor.getServices().get(0)
  
}
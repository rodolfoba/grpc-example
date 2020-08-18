package com.github.rodolfoba.grpcexample;

import com.github.rodolfoba.grpcexample.proto.LegacyGrpc;
import com.github.rodolfoba.grpcexample.proto.Transaction;
import io.grpc.stub.StreamObserver;

import javax.inject.Singleton;

@Singleton
public class LegacyService extends LegacyGrpc.LegacyImplBase {

    @Override
    public void sendTransaction(Transaction requestTransaction, StreamObserver<Transaction> responseObserver) {
        String data = "RESPONSE-FROM-ENTITY-TWO";
        responseObserver.onNext(Transaction.newBuilder().setData(data).build());
        responseObserver.onCompleted();
    }
}

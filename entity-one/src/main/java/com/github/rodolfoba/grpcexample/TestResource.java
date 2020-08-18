package com.github.rodolfoba.grpcexample;

import com.github.rodolfoba.grpcexample.proto.Transaction;
import io.quarkus.grpc.runtime.annotations.GrpcService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static com.github.rodolfoba.grpcexample.proto.LegacyGrpc.LegacyBlockingStub;

@Path("/test")
public class TestResource {

    @Inject
    @GrpcService("legacy")
    LegacyBlockingStub client;

    @GET
    @Path("/transaction")
    @Produces(MediaType.TEXT_PLAIN)
    public String sendTransaction() {
        return client.sendTransaction(Transaction.newBuilder().setData("REQUEST-FROM-ENTITY-ONE-TO-CENTRAL").build()).getData();
    }
}
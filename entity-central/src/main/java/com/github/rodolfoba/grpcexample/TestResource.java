package com.github.rodolfoba.grpcexample;

import com.github.rodolfoba.grpcexample.proto.LegacyGrpc;
import com.github.rodolfoba.grpcexample.proto.Transaction;
import io.quarkus.grpc.runtime.annotations.GrpcService;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class TestResource {

    @Inject
    @GrpcService("legacy-entity-one")
    Provider<LegacyGrpc.LegacyBlockingStub> clientEntityOne;

    @Inject
    @GrpcService("legacy-entity-two")
    Provider<LegacyGrpc.LegacyBlockingStub> clientEntityTwo;

    @Inject @Any
    Instance<LegacyGrpc.LegacyBlockingStub> clientInstance;

    @GET
    @Path("/transaction")
    @Produces(MediaType.TEXT_PLAIN)
    public String sendTransaction(@QueryParam("to") String destination) {
        //var stub = clientInstance.select(GrpcServiceLiteral.of("legacy-" + destination.toLowerCase())).get(); // not working
        var stub = destination.equalsIgnoreCase("entity-one") ? clientEntityOne.get() : clientEntityTwo.get();
        return stub.sendTransaction(Transaction.newBuilder().setData("REQUEST-FROM-CENTRAL-TO-" + destination.toUpperCase()).build()).getData();
    }
}
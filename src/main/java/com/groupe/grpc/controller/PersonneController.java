package com.groupe.grpc.controller;

import com.groupe.grpc.service.PersonneService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import com.groupe.grpc.config.PersonneServiceGrpc;
import com.groupe.grpc.config.PersonneRequest;
import com.groupe.grpc.config.PersonneResponse;
import com.groupe.grpc.config.PersonneListResponse;
import com.groupe.grpc.config.PersonneIdRequest;
import com.groupe.grpc.config.PersonneUpdateRequest;
import com.groupe.grpc.config.DeleteResponse;
import com.groupe.grpc.config.Empty;

import java.util.List;

@GrpcService
public class PersonneController extends PersonneServiceGrpc.PersonneServiceImplBase {
    @Autowired
    private PersonneService personneService;

    @Override
    public void ajouter(PersonneRequest request, StreamObserver<PersonneResponse> responseObserver) {
        PersonneResponse response = personneService.ajouter(request);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void lister(Empty request, StreamObserver<PersonneListResponse> responseObserver) {
        List<PersonneResponse> personnes = personneService.lister();
        PersonneListResponse.Builder builder = PersonneListResponse.newBuilder();
        builder.addAllPersonnes(personnes);
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void rechercher(PersonneIdRequest request, StreamObserver<PersonneResponse> responseObserver) {
        personneService.rechercher(request.getId())
            .ifPresentOrElse(
                response -> {
                    responseObserver.onNext(response);
                    responseObserver.onCompleted();
                },
                () -> responseObserver.onError(new Exception("Personne non trouvée"))
            );
    }

    @Override
    public void modifier(PersonneUpdateRequest request, StreamObserver<PersonneResponse> responseObserver) {
        // PersonneRequest req = new PersonneRequest(
        //     request.getNom(),
        //     request.getPrenom(),
        //     request.getDateDeNaissance(),
        //     request.getAdresse(),
        //     request.getTelephone()
        // );

        PersonneRequest req = PersonneRequest.newBuilder()
            .setNom(request.getNom())
            .setPrenom(request.getPrenom())
            .setDateDeNaissance(request.getDateDeNaissance())
            .setAdresse(request.getAdresse())
            .setTelephone(request.getTelephone())
            .build();

        personneService.modifier(request.getId(), req)
            .ifPresentOrElse(
                response -> {
                    responseObserver.onNext(response);
                    responseObserver.onCompleted();
                },
                () -> responseObserver.onError(new Exception("Personne non trouvée"))
            );
    }

    @Override
    public void supprimer(PersonneIdRequest request, StreamObserver<DeleteResponse> responseObserver) {
        boolean deleted = personneService.supprimer(request.getId());
        DeleteResponse response = DeleteResponse.newBuilder()
            .setSuccess(deleted)
            .setMessage(deleted ? "Suppression réussie" : "Personne non trouvée")
            .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

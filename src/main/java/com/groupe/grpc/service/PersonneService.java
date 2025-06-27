package com.groupe.grpc.service;

import com.groupe.grpc.model.Personne;
import com.groupe.grpc.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.groupe.grpc.config.PersonneRequest;
import com.groupe.grpc.config.PersonneResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonneService {
    @Autowired
    private PersonneRepository personneRepository;

    public PersonneResponse ajouter(PersonneRequest request) {
        Personne personne = Personne.builder()
                .nom(request.getNom())
                .prenom(request.getPrenom())
                .dateDeNaissance(LocalDate.parse(request.getDateDeNaissance()))
                .adresse(request.getAdresse())
                .telephone(request.getTelephone())
                .build();
        Personne saved = personneRepository.save(personne);
        return toResponse(saved);
    }

    public List<PersonneResponse> lister() {
        return personneRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public Optional<PersonneResponse> rechercher(Integer id) {
        return personneRepository.findById(id).map(this::toResponse);
    }

    public Optional<PersonneResponse> modifier(Integer id, PersonneRequest request) {
        return personneRepository.findById(id).map(personne -> {
            personne.setNom(request.getNom());
            personne.setPrenom(request.getPrenom());
            personne.setDateDeNaissance(LocalDate.parse(request.getDateDeNaissance()));
            personne.setAdresse(request.getAdresse());
            personne.setTelephone(request.getTelephone());
            Personne updated = personneRepository.save(personne);
            return toResponse(updated);
        });
    }

    public boolean supprimer(Integer id) {
        if (personneRepository.existsById(id)) {
            personneRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private PersonneResponse toResponse(Personne personne) {
        return PersonneResponse.newBuilder()
                .setId(personne.getId())
                .setNom(personne.getNom())
                .setPrenom(personne.getPrenom())
                .setDateDeNaissance(personne.getDateDeNaissance().toString())
                .setAdresse(personne.getAdresse())
                .setTelephone(personne.getTelephone())
                .build();
    }
}

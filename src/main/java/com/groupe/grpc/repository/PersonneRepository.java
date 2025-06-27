package com.groupe.grpc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupe.grpc.model.Personne;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Integer> {

    // Optional<Personne> findByEmail(String email);
    // boolean existsByEmail(String email);
}

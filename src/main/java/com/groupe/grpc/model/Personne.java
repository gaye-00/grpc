package com.groupe.grpc.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Personne {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Integer id;
    private String nom;
    private String prenom;
    private LocalDate dateDeNaissance;
    private String adresse;
    private String telephone;
}

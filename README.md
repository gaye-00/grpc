# 📦 PersonService gRPC

Service gRPC pour la gestion d'un répertoire de personnes (CRUD), développé dans le cadre d’un projet académique à l’UASZ.

---

## 🚀 Fonctionnalités

- ✅ Ajouter une personne
- 📋 Lister les personnes
- 🔍 Rechercher une personne par identifiant
- ✏️ Modifier une personne
- 🗑️ Supprimer une personne

---

## 🛠️ Technologies utilisées

- **Back-end** : Spring Boot 3.5.3
- **gRPC** : protoc + protoc-gen-grpc-java
- **Base de données** : MariaDB
- **Client de test** : [BloomRPC](https://github.com/bloomrpc/bloomrpc) ou `grpcurl`
- **Sérialisation** : Protocol Buffers (proto3)
- **Transport** : HTTP/2

---

## 📋 Prérequis

- Java 21+
- Maven 3+
- protoc (Protocol Buffers Compiler) `v3.21+`
- gRPC plugins :
  - `protoc-gen-grpc-java`

Vérifier l'installation de `protoc` :

```bash
protoc --version
# libprotoc 3.21.12
```

## 🔧 Installation & Compilation

# Cloner le projet

git clone https://github.com/gaye-00/grpc
cd grpc

### Compiler le projet avec Maven

./mvnw clean compile

### Lancer le serveur Spring Boot

./mvnw spring-boot:run

## 📂 Structure du projet

src/
├── main/
│ ├── java/
│ │ └── com/groupe/grpc/
│ │ ├── controller/ # Implémentation du service gRPC
│ │ ├── service/ # Logique métier
│ │ └── config/ # Fichiers générés depuis les .proto
│ ├── proto/ # Fichiers .proto
│ └── resources/
│ └── application.yml # Configuration Spring Boot & gRPC

## 👥 Auteurs

    Seydina Mouhamadou Al Hamine NDIAYE

    Abdoulaye GAYE

Encadrement : Pr. Camir Malack
Université : Université Assane Seck de Ziguinchor (UASZ)

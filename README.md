# Système de Gestion de Colis et Transporteurs

## 1. Contexte du projet
Cette application permet à une entreprise de logistique de gérer ses colis et transporteurs.  
Elle est développée avec **Spring Boot**, **MongoDB**, et sécurisée avec **JWT**.  
L'application supporte différents types de colis (STANDARD, FRAGILE, FRIGO) et des rôles utilisateurs (ADMIN, TRANSPORTEUR).

---

## 2. Fonctionnalités

### Gestion des colis
- Lister les colis avec pagination et filtres par type/statut
- Rechercher les colis par adresse de destination
- Créer, modifier et supprimer des colis (ADMIN uniquement)
- Assigner un colis à un transporteur (ADMIN uniquement)
- Mettre à jour le statut d’un colis (TRANSPORTEUR: ses colis, ADMIN: tous)

### Gestion des utilisateurs
- Authentification ADMIN / TRANSPORTEUR via JWT
- Créer, modifier, supprimer et lister les transporteurs (ADMIN uniquement)
- Activation / désactivation des utilisateurs

### Règles métier
- Un transporteur ne peut être assigné qu’à un colis correspondant à sa spécialité
- Les utilisateurs désactivés ne peuvent pas se connecter
- Les statuts sont gérés selon le type (colis ou transporteur)

<img width="896" height="693" alt="image" src="https://github.com/user-attachments/assets/b6cc16a3-1038-451d-827f-0eb418f755e8" />

<img width="793" height="693" alt="image" src="https://github.com/user-attachments/assets/4b4ede02-1bee-44a7-b5a7-c02dc6042948" />

---

## 3. Architecture

- **Controller / Service / Repository / DTO / Mapper**
- **MongoDB** avec schéma flexible pour les colis
- **Spring Security + JWT** pour authentification stateless
- **Docker / Docker Compose** pour conteneurisation
- **Tests unitaires** avec JUnit et Mockito

---

## 4. Installation

### Prérequis
- Java 17+
- Maven 3+
- Docker et Docker Compose
- MongoDB (via Docker Compose ou local)

### Cloner le projet
```bash
git clone https://github.com/votre-utilisateur/logistics-system.git
cd logistics-system


```
<img width="1867" height="912" alt="image" src="https://github.com/user-attachments/assets/2a7fbfea-d87e-4d71-be40-d948c51a2f44" />
<img width="1617" height="812" alt="image" src="https://github.com/user-attachments/assets/4aebefc0-396b-47f8-be89-192e970f7125" />
<img width="1731" height="824" alt="image" src="https://github.com/user-attachments/assets/092e04b0-70b3-47e9-989b-e50194d989e8" />



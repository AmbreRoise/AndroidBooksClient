# 📖 Android Books Client — Projet ABC

> **Projet académique en cours** — Client mobile Android pour parcourir, créer et supprimer des livres et des auteurs via une API

## À propos

Ce projet est une **application Android** développée dans le cadre du cours de développement mobile. Elle constitue le client mobile de l'API Books réalisée en W41, et permet à un utilisateur de consulter un catalogue de livres et d'auteurs, d'en créer de nouveaux et d'en supprimer, le tout depuis une interface moderne et fluide.

### Contexte du projet
- **Formation** : BUT2 Informatique — Module P42 — Développement mobile Android
- **Objectif** : Concevoir une application Android complète communiquant avec une API
- **Compétences** : Architecture MVVM, Retrofit, Navigation Component, RecyclerView, LiveData

---

## Fonctionnalités

### Livres
- Affichage de la liste des livres dans un `RecyclerView`
- Consultation des détails d'un livre (titre, description, tags)
- Création d'un livre via un formulaire (FAB)
- Suppression d'un livre depuis sa page de détail

### Auteurs
- Affichage de la liste des auteurs dans un `RecyclerView`
- Consultation des livres écrits par un auteur au clic
- Navigation vers le détail d'un livre depuis la liste de l'auteur
- Création d'un auteur via un formulaire (FAB)
- Suppression d'un auteur (et de ses livres associés)

---

## Technologies utilisées

| Composant | Technologie |
|-----------|-------------|
| **Langage** | Java |
| **IDE** | Android Studio |
| **Appels réseau** | Retrofit |
| **Architecture** | MVVM (ViewModel + LiveData) |
| **Navigation** | Navigation Component |
| **UI** | RecyclerView, FAB, BottomNavigationView (Material Design) |
| **API** | API W41 (Bun / TypeScript) |

---

## Installation et déploiement

### Prérequis
- Android Studio installé
- Un appareil Android ou un émulateur AVD
- L'API W41 lancée et accessible (voir [le mode d'emploi de l'API](api.md))

### Lancer l'application

**1. Cloner le dépôt**
```bash
git clone https://github.com/AmbreRoise/AndroidBooksClient.git
cd AndroidBooksClient
```

**2. Ouvrir dans Android Studio**  
Ouvrez le dossier `AndroidBooksClient/` dans Android Studio et laissez Gradle synchroniser le projet.

**3. Configurer l'URL de l'API**  
Dans `app/src/main/java/p42/androidbooksclient/db/Repository.java`, adaptez l'URL selon votre cas :

```java
// Sur émulateur
private static final String API_URL = "http://10.0.2.2:3000/";

// Sur téléphone physique (après adb reverse, voir api.md)
private static final String API_URL = "http://127.0.0.1:3000/";
```

**4. Lancer l'application**  
Sélectionnez votre appareil cible et cliquez sur **Run**.

---

## Architecture

L'application suit le pattern **MVVM** (Model-View-ViewModel) recommandé par Android Jetpack, avec une séparation stricte des responsabilités.

```
app/
├── model/                  # Modèles de données (Book, Author, Tag)
├── db/
│   └── Repository.java     # Point d'accès unique à l'API (Retrofit)
|   └── Services.java       # Divers services pour les requêtes
├── viewmodel/              # ViewModels exposant des LiveData
└── views/
    ├── fragments/          # BookListFragment, AuthorListFragment,
    │                       # BookDetailFragment, AuthorDetailFragment, ...
    └── adapters/           # Adapters RecyclerView (BookAdapter, AuthorAdapter)
```

### Flux de données

```
Fragment  ──observe──►  ViewModel  ──appelle──►  Repository  ──HTTP──►  API
   ▲                                                                      │
   └──────────────────── LiveData (mise à jour UI) ◄─────────────────────┘
```

- **Une seule activité** (`MainActivity`) contenant une `BottomNavigationView` avec deux onglets : Livres et Auteurs
- La navigation entre fragments est gérée par le **Navigation Component** (NavGraph)
- Le `Repository` centralise tous les appels Retrofit et est le seul à connaître l'API
- Les `ViewModel` ne font qu'exposer des `LiveData` : ils n'ont aucune connaissance de l'UI

---

## Auteurs

**Fouilleul Elora** — Développeuse  
**Léger Hugo** — Développeur

*Projet réalisé dans le cadre du module P42 — Développement mobile Android*

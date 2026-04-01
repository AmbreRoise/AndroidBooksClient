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
Projet ABC - Android Books Client
=================================

L'objectif de ce projet est de réaliser un client mobile pour l'API Books développée dans le module W41, dont [une correction minimale est disponible ici](API.md).

Binômes
-------

Vous devez vous mettre en binôme avec **un autre étudiant de votre groupe TP**.
En cas de nombre d'élèves impair dans le groupe, il y aura un monôme.

Vous devez déclarer vos binômes dans ce fichier : [https://seafile.unistra.fr/f/b0020cf2322a478aabb0/](https://seafile.unistra.fr/f/b0020cf2322a478aabb0/)

À noter que le **planning des soutenances** sera également ajouté dans ce fichier.

Préparation du dépôt
--------------------

1. Créez un groupe Gitlab nommé **`nom1-nom2`** où `nom1` et `nom2` sont les noms de famille des deux membres du binôme.
1. Forkez le dépôt https://git.unistra.fr/p42/p42-abc dans le groupe créé ci-dessus **en conservant l'URL p42-abc**.
1. **Passez ce dépôt en privé.**
1. Ajoutez votre enseignant de TP comme Reporter de votre dépôt.
1. Ajoutez votre enseignant de TP de P42 comme Reporter de votre dépôt de W41.

Rendu
-----

- Quand ? **le 5 avril à 0h00** (ou le 4 avril à 24h00, au choix)
- Où ? Sur votre dépôt Git.
- Quoi ?
  - Le code de l'application
  - Un rapport expliquant votre architecture, ce qui est censé fonctionner et ne pas fonctionner, les difficultés que vous avez rencontré.
  - Un mode d'emploi pour déployer votre API de W41. À défaut, votre application sera testée avec [la correction de l'API fournie en W41](API.md).

**Attention** : il faut que votre enseignant de TP de P42 soit **reporter de votre dépôts de P42 ET de celui de W41**.

Fonctionnalités
---------------

L'application doit proposer un certain nombre de fonctionnalités :

- **Afficher la liste des livres** : lorsqu'un livre est sélectionné, la description de ce livre doit s'afficher, dont les tags.
- **Afficher la liste des auteurs** : lorsqu'un auteur est sélectionné, la liste des titres des livres qu'il a écrit doit s'afficher. Un clic sur l'un des livres doit afficher ses détails.
- **Créer un livre** à partir d'un formulaire.
- **Supprimer un livre** depuis sa page de description.
- **Créer un auteur** à partir d'un formulaire.
- **Supprimer un auteur** (et les livres associés) depuis sa page de description.

Bonus : L'application peut permettre d'**associer une couverture à un livre**, mais uniquement localement. L'image est stockée sur le téléphone, pas sur le serveur.

Interface
---------

1. L'application est composée d'**une seule activité principale**. Cette activité contiendra une **`Bottom Navigation Activity`** avec deux menus proposants respectivement la liste des livres et la liste des auteurs.
1. L'écran d'accueil de l'application affiche la liste des livres.
1. Les listes des livres et des auteurs sont affichées dans des **`RecyclerView`**.
1. Lors d'un **clic sur un livre**, ses informations sont affichées dans un nouveau fragment.
1. Lors d'un **clic sur un auteur**, les livres de cet auteur sont affichés dans un nouveau fragment.
1. La **création des livres et des auteurs** doit être proposée à partir de [FABs](https://developer.android.com/develop/ui/views/components/floating-action-button) présents respectivement sur la liste des livres et des auteurs.

Pour gérer les **clics sur les items d'un `RecyclerView`**, vous pouvez vous référer à [ce site](https://dev.to/theplebdev/adding-onclicklistener-to-recyclerview-in-android-3amb).

Architecture
------------

Vous devez mettre en place l'architecture moderne en MVVM vue en cours et en TP, basée sur les `ViewModel` pour les données et un "repository" pour centraliser les requêtes à effectuer avec Retrofit.

Conseils
--------

- Vous pouvez travailler dans un premier temps avec **des données "en dur"**, sans communication avec l'API.
- C'est un projet assez conséquent, l'essentiel n'est pas d'implémenter toutes les fonctionnalités mais d'**implémenter "proprement"** celles que vous aurez le temps de faire.

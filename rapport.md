# Rapport

## Architecture

L'application suit strictement le pattern **MVVM** (Model-View-ViewModel).

```
app/src/main/java/p42/androidbooksclient/
├── db/
│   ├── Repository.java          # Centralise tous les appels réseau
│   ├── AuthorService.java       # Interface Retrofit — routes auteurs
│   ├── BookService.java         # Interface Retrofit — routes livres
│   └── TagService.java          # Interface Retrofit — routes tags
├── model/
│   ├── Author.java
│   ├── Book.java
│   └── Tag.java
├── view/
│   ├── MainActivity.java        # Activité unique, héberge la BottomNavigationView
│   ├── Book/                    # Fragments + Adapter RecyclerView pour les livres
│   └── Author/                  # Fragments + Adapter RecyclerView pour les auteurs
└── viewmodel/
    ├── AuthorViewModel.java
    ├── BookViewModel.java
    └── TagViewModel.java
```

### Flux de données :

Les fragments observent les données renvoyées par les ViewModel ou leurs envoient des données pour les créations ou les update.  
Les ViewModel interagissent avec le Repository qui communique avec l'API. C'est le Repository qui instancie ensuite les modèles pour les transmettre aux ViewModels.

```
Fragment → ViewModel → Repository → Service Retrofit → API
                 ↑
            LiveData (observe)
```

- Les **Services** (`AuthorService`, `BookService`, `TagService`) sont des interfaces Retrofit qui déclarent les routes de l'API avec les annotations `@GET`, `@POST`, `@DELETE`, `@PATCH`.
- Le **Repository** est le seul à instancier et utiliser ces services. Il centralise toute la logique réseau, ce qui évite que les ViewModels aient connaissance de Retrofit.
- Les **ViewModels** appellent le Repository et exposent les résultats via des `LiveData`, sans aucune logique réseau directe.
- Les **Fragments** observent ces `LiveData` et mettent à jour l'UI en conséquence. Ils n'ont aucune connaissance de Retrofit ni du Repository.


**Remarque :** Nous avons choisi de ne faire qu'un Repository afin de ne pas surcharger l'API de connexion au détriment de la longueur du fichier `Repository.java`. Afin de ne pas créer trois instances du Repository dans chacun des ViewModel (ce qui aurait eu pour effet de surcharger l'API et de gâcher notre logique), nous avons fait du Repository un singleton pour qu'il n'y ait qu'une seule instance. Ainsi, les ViewModel récupèrent l'instance courante pour effectuer les requêtes.

---

## Fonctionnalités 

Toutes les **fonctionnalités de base** ont été **implémentées** à savoir : 
- afficher la liste des auteurs
- afficher la liste des livres
- créer/supprimer un livre
- créer/supprimer un auteur

Nous avons également implémenté l'idée de la couverture pour un livre qui est géré en local et non grâce à une API.

### Rajouts

Puisqu'il nous restait du temps une fois ces fonctionnalités implémentées, nous avons décidé d'**enrichir l'application**. Voici la liste des autres possibilités qu'offre notre application : 
- **Création d'un Tag** : actuellement, nous étions limités aux tags présents dans l'API lors de la création d'un livre. L'utilisateur peut désormais créer un tag lorsqu'il ajoute un livre.
- **Mise à jour d'un livre** : l'utilisateur peut désormais modifier un livre en changeant son titre ou son année de publication lorsqu'il est affiché. Il peut aussi enlever ou ajouter des tags à un livre.
- **Filtrer sur le nom de l'auteur ou le titre du livre** : lors de l'affichage des listes, il peut être fastidieux de trouver un auteur ou un livre si le nombre de données est important. Nous avons donc rajouter des filtres sur le nom de l'auteur ou le titre du livre au moyen de champs de recherche en haut des listes.

---

## Difficultés

Au cours de ce projet, nous n'avons pas rencontrés de difficultés particulières. La SAE s'est bien déroulée et nous avons commencé rapidement sans nous faire prendre par le temps et les autres projets en parallèle. 

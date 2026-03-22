# Mode d'emploi API

Afin de récupérer notre API, rendez-vous sur le dépôt de notre [API](https://git.unistra.fr/leger-fouilleul/w41). Clonez ensuite le dépôt sur votre machine. Cette étape nécessite d'avoir une clef ssh (si vous n'en avez pas, utilisez le clonage par HTTPS) :  

```bash
git clone git@git.unistra.fr:leger-fouilleul/w41.git
```

*ou*  
```bash
git clone https://git.unistra.fr/leger-fouilleul/w41.git
```

Si vous ne souhaitez pas clôner le dépôt, la récupération par .zip est également possible.  

Une fois le dépôt récupéré, allez à l'intérieur et plus particulièrement dans le dossier `books` :  

```bash
cd w41/
cd books/
```

Exécutez ensuite la commande suivante afin de démarrer l'api :  

```bash
bun run start
```

L'api est désormais lancée. Vous devriez voir apparaître les lignes suivantes dans votre terminal :  

![Commande pour démarrer l'api](commande_api.png)

> ⚠️ Une fois que vous aurez fini d'utiliser l'application Android, faites Ctrl+C dans le terminal où vous avez lancé l'api afin de l'éteindre.

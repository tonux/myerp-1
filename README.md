# MYERP

## Organisation du répertoire

*   `doc` : documentation
*   `docker` : répertoire relatifs aux conteneurs _docker_ utiles pour le projet
    *   `dev` : environnement de développement
*   `racine` : code source de l'application

## Environnement de développement

Les composants nécessaires lors du développement sont disponibles via des conteneurs _docker_.
L'environnement de développement est assemblé grâce à _docker-compose_
(cf docker/dev/docker-compose.yml).

Il comporte :

*   une base de données _PostgreSQL_ contenant un jeu de données de démo (`postgresql://127.0.0.1:9032/db_myerp`)

### Déploiement Docker

#### Lancement

    cd docker/dev
    docker-compose up

#### Arrêt

    cd docker/dev
    docker-compose stop

#### Remise à zero

    cd docker/dev
    docker-compose stop
    docker-compose rm -v
    docker-compose up
    
#### Réinitialisation de la base de données
Le script `restart.sh` présent dans le dossier `docker/dev` permet de supprimer le container 
de la base de données et de le recréer avec le jeu de données de base.

    Important : Cette manipulation est à effectuer à chaque lancement de build Jenkins.

## Déploiement du projet


## Correctifs
- EcritureComptable, isEquilibree() : Erreur lors de la comparaison des données causée par les décimales. Ajout de la méthode stripTrailingZeros() pour suprimer les décimales inutiles. 
- SqlContext.xml, SQLinsertListLigneEcritureComptable : Ajout d'une virgule dans la requête SQL
- Création du fichier de configuration `bootstrapContext.xml` dans le module **Business**
- EcritureComptable, getTotalCredit() : Erreur provoquée par l'appel de la méthode getDebit() au lieu de getCredit()
- EcritureComptable : Modification de l'expression régulière


## Evolutions
- Implémentation de la méthode `addReference()` dans `ComptabiliteManagerImpl.class`
- Implémentation de la règle de gestion RG-Compta5 dans la méthode `CheckEcritureComptableUnit()`
- Ajout d'une base de données de test via le fichier `consumerContext.xml` dans le module **Consumer**

## Intégration continue via Jenkins
Un fichier `Jenkinsfile` permet d'automatiser les tests et leur agrégation :

Au lancement de build : 
1. Jenkins récupère le repository local
2. Une commande est lancée pour effectuer les tests avec les profils *consumer-test* et *business-test*

Le fichier `restart.sh` permet de relancer la base de données pour effectuer un build (voir **Déploiement du projet**)
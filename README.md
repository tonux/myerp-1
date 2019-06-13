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

 
## Déploiement Docker

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

### Agrégation des données de tests
Les données liées aux tests sont automatiquement intégrées, via la commande maven lancée depuis Jenkins, 
à **Sonarqube**.

## Correctifs et évolutions
*Se référer au fichier ``CHANGELOG.md``* 
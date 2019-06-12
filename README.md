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

## Intégration continue avec Jenkins
L'intégration continue du projet se fait via Jenkins. Un fichier `Jenkinsfile` permet de configurer 
*pipeline*. Ce pipeline va :
- Récupérer les sources depuis le repository
- Lancer une commande `mvn clean install` avec les profils de tests **consumer-test** et **business-test**

### Installation du pipeline Jenkins
L'instance de Jenkins installée doit être configurée avec JDK 8, Sonarqube et Maven 3.

Une fois le pipeline créé, il suffit d'y configurer le lien vers le repository et 
de spécifier que la configuration du pipeline se fera avec unfichier *Jenkinsfile*.

### Contenu du script Jenkinsfile 
Un fichier `Jenkinsfile` permet d'automatiser les tests et leur agrégation :

Au lancement de build : 
1. Jenkins récupère le repository local
2. Une commande est lancée pour effectuer les tests avec les profils *consumer-test* et *business-test*

Le fichier `restart.sh` permet de relancer la base de données pour effectuer un build (voir **Déploiement du projet**)

### Agrégation des données de tests
Les données liées aux tests sont automatiquement intégrées, via la commande maven lancée depuis Jenkins, 
à **Sonarqube**.

## Correctifs et évolutions
*Se référer au fichier ``CHANGELOG.md``* 
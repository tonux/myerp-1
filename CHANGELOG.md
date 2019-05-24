# CHANGELOG
Ce document regroupe l'ensemble des modifications intervenus dans le projet ``MyERP``
## [Unreleased]

## [1.1.0] - 2019-06-01

### Changement
- Implémentation de la méthode `addReference()` dans `ComptabiliteManagerImpl.class`
- Implémentation de la règle de gestion RG-Compta5 dans la méthode `CheckEcritureComptableUnit()`
- Ajout d'une base de données de test via le fichier `consumerContext.xml` dans le module **Consumer**

### Correctifs
- EcritureComptable, isEquilibree() : Erreur lors de la comparaison des données causée par les décimales. Ajout de la méthode stripTrailingZeros() pour suprimer les décimales inutiles. 
- SqlContext.xml, SQLinsertListLigneEcritureComptable : Ajout d'une virgule dans la requête SQL
- Création du fichier de configuration `bootstrapContext.xml` dans le module **Business**
- EcritureComptable, getTotalCredit() : Erreur provoquée par l'appel de la méthode getDebit() au lieu de getCredit()
- EcritureComptable : Modification de l'expression régulière

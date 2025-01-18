# Application : Online-shopping-site

## 1) Base de Données
Ce projet utilise une base de données **H2 en mémoire**, initialisée automatiquement au démarrage de l'application. Les scripts de création et de peuplement des tables sont disponibles dans le fichier `db_scripts.sql`.

## 2) Back-End
L'application back-end fonctionne sur le port **9090**.

## 3) Front-End
L'application front-end fonctionne également sur le port **3000**.

## 4) Documentation de l'API
Accédez à la documentation de l'API via **Swagger** à l'adresse suivante :  
[http://localhost:9090/swagger-ui/index.html#/](http://localhost:9090/swagger-ui/index.html#/)

## 5) Build de l'application Back-End
- **Build** :
```bash
  mvn clean install 
```
#### Exécution via Maven :

``` bash
mvn spring-boot:run
```
#### Exécution en JVM :

``` bash
java -jar target/online-shopping-site.jar
```
## 6) Build de l'application Front-End
Installation des dépendances :

```bash
npm install
```
#### Exécution :

``` bash
ng serve
```


## Problème Connu

### Description
Lors de l'ajout d'un nouveau produit, de son ajout au panier et de la visualisation du panier, le nouveau produit disparaît de la liste des produits lors du retour à celle-ci. Ce problème est dû à l'implémentation de la pagination avec `signal` :

```typescript
paginationProducts = computed(() => {
  const startIndex = this.pageNumber();
  const endIndex = startIndex + this.pageSize();
  return this.products().slice(startIndex, endIndex);
});

```
### Cause
La pagination ne prend pas en compte les nouveaux produits ajoutés tant que la liste complète (this.products()) n'est pas mise à jour ou que la pagination n'est pas recalculée.

### Statut
Le problème reste à résoudre. Une refactorisation de la logique de pagination est nécessaire pour garantir que les nouveaux produits soient toujours visibles après leur ajout.

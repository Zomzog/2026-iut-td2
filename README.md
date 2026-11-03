## TD2

Cloner [https://github.com/Zomzog/2026-iut-td2.git](https://github.com/Zomzog/2026-iut-td2.git)

Sur le réseau de l'IUT, ajouter dans le fichier `gradle.properties` le contenu suivant :

```properties
systemProp.http.proxyHost=srv-proxy-etu-2.iut-nantes.univ-nantes.prive
systemProp.http.proxyPort=3128
systemProp.https.proxyHost=srv-proxy-etu-2.iut-nantes.univ-nantes.prive
systemProp.https.proxyPort=3128

```

Importer le projet dans votre IDE en utilisant gradle.

Les modifications sont à faire dans le module `exo0` (exos 0 à 11), puis dans le module `exo12` (exos 12 à 14).

Les tests fournis ne doivent pas être modifiés, sauf si l'énoncé le demande.

Pour lancer un test seul depuis la ligne de commande :

```bash
./gradlew :exo0:test --tests 'iut.nantes.exo0.controller.PetControllerTest$Exo0a'
```

> **Remarque :** un `@RestControllerAdvice` (`ErrorHandler`) est déjà fourni dans le projet.
> Il étend `ResponseEntityExceptionHandler`, qui transforme déjà les erreurs de validation en réponse 400
> au format `ProblemDetail` : vous n'avez rien à écrire pour gérer ces erreurs.
> Ce n'est pas le comportement par défaut de Spring Boot : sans cette classe, les erreurs ne seraient pas des `ProblemDetail`.

## Exo 0 - Validations simples

Ajouter la dépendance `org.springframework.boot:spring-boot-starter-validation` dans `exo0/build.gradle.kts`
(puis recharger le projet Gradle).

Les contraintes standard (package `jakarta.validation.constraints`) sont listées dans la documentation de Hibernate Validator,
l'implémentation utilisée par Spring Boot :
[Jakarta Validation constraints](https://docs.hibernate.org/validator/9.1/reference/en-US/html_single/#validator-defineconstraints-spec).

### Exo 0a - Valider un paramètre d'URL

L'identifiant `petId` doit obligatoirement être un entier supérieur à 0 dans l'URL du `endpoint` GET /api/v1/pets/{petId}.

À l'aide d'une annotation, ajouter cette validation : un appel avec un `petId` invalide doit retourner un code 400.

La classe de test Exo0a de PetControllerTest doit passer sans y apporter de modifications.
La classe Base, qui teste le fonctionnement de départ de GET /api/v1/pets/{petId}, doit rester verte.

```bash
./gradlew :exo0:test --tests 'iut.nantes.exo0.controller.PetControllerTest$Exo0a'
```

### Exo 0b - Valider un `body`

À l'aide d'annotations, ajouter une validation pour que l'appel au `endpoint` POST /api/v1/pets
retourne un code 400 si le nom est vide ou si l'âge est inférieur à 0.

La classe de test Exo0b de PetControllerTest doit passer sans y apporter de modifications.

```bash
./gradlew :exo0:test --tests 'iut.nantes.exo0.controller.PetControllerTest$Exo0b'
```

### Exo 0c - Valider un objet imbriqué

Un pet peut avoir un propriétaire. Créer la classe `OwnerDto` et l'ajouter à `PetDto` :

```kotlin
data class OwnerDto(val name: String, val email: String)

data class PetDto(val id: Int?, val name: String, val age: Int, val kind: PetKind, val owner: OwnerDto? = null)
```

Le propriétaire est facultatif. S'il est fourni, l'appel au `endpoint` POST /api/v1/pets doit retourner un code 400
si son nom est vide ou si son email n'est pas une adresse valide.

<details>
<summary>Indice</summary>

Un objet imbriqué se valide de la même manière que le `body` de l'exo 0b.

</details>

La classe de test Exo0c de PetControllerTest doit passer sans y apporter de modifications, sans casser la classe Exo0b.

```bash
./gradlew :exo0:test --tests 'iut.nantes.exo0.controller.PetControllerTest$Exo0c'
```

## Exo 1 - Méta-annotation

Le `endpoint` PUT /api/v1/pets/{petId} doit appliquer la même règle que le GET de l'exo 0a :
`petId` est un entier supérieur à 0.

Plutôt que de recopier la contrainte, créer une méta-annotation `@PetId` qui porte la règle, et l'utiliser
sur `petId` dans les `endpoints` GET et PUT.
Même si ce n'est qu'une seule contrainte, la centraliser permet de n'avoir qu'un endroit à modifier si la règle change.
En plus, `@PetId` dit ce que représente le paramètre, là où `@Min(1)` dit seulement comment on le vérifie.

<details>
<summary>Indice</summary>

Voir la slide [« Meta annotation »](https://zomzog.fr/iut-spring-kotlin/c2/#/11).

</details>

La classe de test Exo1 de PetControllerTest doit passer sans y apporter de modifications, sans casser la classe Exo0a.

```bash
./gradlew :exo0:test --tests 'iut.nantes.exo0.controller.PetControllerTest$Exo1'
```

## Exo 2 (bonus) - Paramètres de requête dans un objet

> **Bonus :** cet exo est optionnel, aucun exo suivant n'en dépend. Faites-le si vous voulez découvrir cette technique.

Quand un `endpoint` a plusieurs paramètres de requête (query params), on peut les regrouper dans un objet.
Spring remplit chaque propriété avec le paramètre du même nom.
Ni l'objet ni le paramètre du `controller` n'ont besoin d'annotation : pas de `@RequestParam`, pas de `@RequestBody`.

```kotlin
data class Search(val text: String?, val limit: Int?)

@GetMapping("/api/v1/search")
fun search(search: Search) = "${search.text} ${search.limit}"
```

`GET /api/v1/search?text=abc&limit=10` appelle `search(Search("abc", 10))`.
Un paramètre absent de l'URL donne `null` : les propriétés doivent donc être nullables.

Créer une classe `AgeRange` qui regroupe `minAge` et `maxAge`, pour remplacer les deux paramètres de GET /api/v1/pets.

Le comportement ne change pas : la classe de test Exo2 de PetControllerTest passe déjà avant l'exo,
elle doit continuer à passer après sans y apporter de modifications.

```bash
./gradlew :exo0:test --tests 'iut.nantes.exo0.controller.PetControllerTest$Exo2'
```

## Exo 3 - Validateur personnalisé sur un champ

Les contraintes de Jakarta (`@NotBlank`, `@Min`...) ne couvrent pas tous les besoins : on peut créer les siennes.

Créer une annotation `@Capitalized` et son validateur, pour refuser un nom d'animal qui ne commence pas par une majuscule
("kraken" est refusé, "Kraken" est accepté). L'appliquer à la propriété `name` de `PetDto`.

Un nom vide est déjà refusé par `@NotBlank` : le validateur `Capitalized` doit accepter une valeur vide.

<details>
<summary>Indice</summary>

Le cours montre la création d'une annotation et de son `ConstraintValidator` (slides [« Constraint Annotation »](https://zomzog.fr/iut-spring-kotlin/c2/#/10) et [« Custom annotation »](https://zomzog.fr/iut-spring-kotlin/c2/#/12)).

Attention à la cible : dans le cours l'annotation est posée sur un paramètre de fonction (`VALUE_PARAMETER`).
Ici elle est posée sur une propriété de `PetDto`, que Bean Validation lit sur le champ :
l'annotation doit accepter la cible `AnnotationTarget.FIELD`.

</details>

La classe de test Exo3 de PetControllerTest doit passer sans y apporter de modifications.

```bash
./gradlew :exo0:test --tests 'iut.nantes.exo0.controller.PetControllerTest$Exo3'
```

## Exo 3b - Validateur personnalisé sur une classe

Certaines règles portent sur plusieurs champs à la fois : on valide alors l'objet dans son ensemble.

Le `endpoint` POST /api/v1/pets/search est fourni : il reçoit en `body` un `PetSearch(minAge, maxAge)` et renvoie
les animaux dont l'âge est dans cet intervalle.

Créer une annotation `@ValidAgeRange` et son validateur `ValidAgeRangeValidator`, pour valider un `PetSearch` :

- `minAge` est inférieur ou égal à `maxAge` s'ils sont fournis,
- `minAge` et `maxAge` sont supérieurs ou égaux à 0 s'ils sont fournis.

Poser l'annotation sur la classe `PetSearch`. Le `endpoint` doit répondre 400 pour un `body` invalide.

<details>
<summary>Indice</summary>

L'annotation se pose cette fois sur une classe : sa cible est `AnnotationTarget.CLASS`,
et le validateur est un `ConstraintValidator<ValidAgeRange, PetSearch>`.

</details>

<details>
<summary>Tableau de vérité de la validation</summary>

| minAge ↓ \ maxAge → | null | -1 | 0 | 1 | 10 |
|---|---|---|---|---|---|
| null | OK | ERR | OK | OK | OK |
| -1 | ERR | ERR | ERR | ERR | ERR |
| 0 | OK | ERR | OK | OK | OK |
| 1 | OK | ERR | ERR | OK | OK |
| 10 | OK | ERR | ERR | ERR | OK |

</details>

<details>
<summary>Pseudo-code</summary>

```text
(value.minAge == null || value.minAge >= 0) &&
(value.maxAge == null || value.maxAge >= 0) &&
(value.minAge == null || value.maxAge == null || value.minAge <= value.maxAge)
```

</details>

La classe de test Exo3b de PetControllerTest doit passer sans y apporter de modifications.

```bash
./gradlew :exo0:test --tests 'iut.nantes.exo0.controller.PetControllerTest$Exo3b'
```

## Exo 3c - Tester le validateur

Le tableau de vérité ci-dessus décrit toutes les règles de `ValidAgeRangeValidator`.

Créer une classe de tests unitaires `ValidAgeRangeValidatorTest` (dans `src/test/kotlin/iut/nantes/exo0/validation/`)
qui vérifie le validateur pour chaque case du tableau et couvre à 100% `ValidAgeRangeValidator`.
Ces tests n'ont pas besoin de Spring : on appelle directement `isValid`.

> **Astuce :** un `@ParameterizedTest` évite d'écrire un test par case. Avec `@CsvSource`, la valeur `null` s'écrit avec
> `nullValues = ["null"]`. Le second paramètre de `isValid` (le contexte) peut être `null` si votre validateur ne l'utilise pas.

> **Astuce :** pour mesurer la couverture dans IntelliJ, clic droit sur la classe de test puis **Run 'ValidAgeRangeValidatorTest' with Coverage**.

## Exo 4a - Gérer une exception dans un `@RestControllerAdvice`

Le `controller` `ExceptionController` est fourni : chacun de ses `endpoints` lance une exception du package `errors`.
Pour l'instant rien ne les intercepte : l'erreur remonte jusqu'à Spring.

Dans le `@RestControllerAdvice` `ErrorHandler`, gérer l'exception `MyFirstException`, lancée par GET /exception/MyFirstException :
la réponse a le statut 406, et son `body` est simplement le message de l'exception, sous forme de chaîne de caractères.

<details>
<summary>Indice</summary>

Voir la slide [« RestControllerAdvice »](https://zomzog.fr/iut-spring-kotlin/c2/#/19).

</details>

La classe de test Exo4a de PetControllerTest doit passer sans y apporter de modifications.

```bash
./gradlew :exo0:test --tests 'iut.nantes.exo0.controller.PetControllerTest$Exo4a'
```

## Exo 4b - Une erreur avec un `body` JSON

Une chaîne de caractères est difficile à exploiter pour le client de l'API : on veut maintenant une réponse en JSON.

Dans `ErrorHandler`, gérer l'exception `MyExceptionWithBody`, lancée par GET /exception/MyExceptionWithBody :
la réponse a le statut 500 et le `body` suivant, où `message` est le message de l'exception.

```json
{
  "message": "My exception with body",
  "status": 500
}
```

La classe de test Exo4b de PetControllerTest doit passer sans y apporter de modifications, sans casser la classe Exo4a.

```bash
./gradlew :exo0:test --tests 'iut.nantes.exo0.controller.PetControllerTest$Exo4b'
```

## Exo 4c - Une erreur au format `ProblemDetail`

Chaque API peut inventer son format d'erreur, comme à l'exo 4b, mais il existe un format standard : `ProblemDetail`
(RFC 9457, `application/problem+json`). C'est celui que l'application utilise déjà pour les erreurs de validation.

`ImATeapotException` est lancée par GET /exception/ImATeapotException,
et aussi par PUT /api/v1/pets/{petId} quand l'animal n'existe pas.

Dans `ErrorHandler`, gérer l'exception `ImATeapotException` : la réponse a le statut 418,
et c'est un `ProblemDetail` dont le `detail` est le message de l'exception.

> **Remarque :** l'API répond maintenant avec trois formats d'erreur différents (exos 4a, 4b et 4c).
> Dans une vraie application, on choisirait un seul format, `ProblemDetail`, pour toutes les erreurs.
> Ici on garde les handlers des exos 4a et 4b pour que leurs tests restent verts.

La classe de test Exo4c de PetControllerTest doit passer sans y apporter de modifications, sans casser les classes Exo4a et Exo4b.

```bash
./gradlew :exo0:test --tests 'iut.nantes.exo0.controller.PetControllerTest$Exo4c'
```

## Exo 5 - Surcharger la gestion d'une exception de Spring

Quand le `body` d'une requête ne peut pas être lu (valeur inconnue pour un enum, JSON mal formé...),
l'application répond déjà 400 avec un `ProblemDetail` : c'est `ResponseEntityExceptionHandler`, dont hérite `ErrorHandler`,
qui le construit. Mais son `detail` est trop vague pour le client de l'API : `Failed to read request`.

Dans `ErrorHandler`, on veut un message qui indique le champ en erreur et la valeur refusée.

Pour trouver la bonne exception à surcharger, lancer le test `unknown enum value` de la classe Exo5 de PetControllerTest :
il envoie un `kind` qui n'est pas dans l'enum (`PONY`) et affiche la requête et la réponse.
Chercher dans la sortie la ligne `Resolved Exception` : elle donne le type de l'exception.

> **Astuce :** une autre solution est de mettre un point d'arrêt dans la méthode `handleException` de `ResponseEntityExceptionHandler`
> et de lancer le test en debug.

<details>
<summary>Solution</summary>

Le type `PONY` n'est pas dans l'enum : la lecture du `body` échoue avec une `HttpMessageNotReadableException`,
et `ResponseEntityExceptionHandler` a une méthode `handleHttpMessageNotReadable` qu'on peut surcharger.

</details>

Surcharger la gestion de cette erreur pour que le `detail` du `ProblemDetail` indique le champ et la valeur refusée,
par exemple `Invalid value 'PONY' for field 'kind'`.

Les autres erreurs de lecture (JSON mal formé, par exemple) doivent continuer à être gérées comme avant.

<details>
<summary>Indice</summary>

La cause de l'`HttpMessageNotReadableException` (`ex.cause`) est ici une `tools.jackson.databind.exc.InvalidFormatException`.
Elle donne la valeur refusée (`value`) et le chemin du champ (`path`, une liste dont chaque élément a un `propertyName`).

Pour les autres causes, appeler la méthode du parent (`super`).

`ProblemDetail.forStatusAndDetail(status, detail)` crée le `ProblemDetail`,
et `handleExceptionInternal(ex, problemDetail, headers, status, request)` construit la réponse.

</details>

La classe de test Exo5 de PetControllerTest doit passer sans y apporter de modifications.

```bash
./gradlew :exo0:test --tests 'iut.nantes.exo0.controller.PetControllerTest$Exo5'
```

### Exo 5 - Bonus

Ajouter au `ProblemDetail` une propriété `field` qui contient le nom du champ en erreur (ici `kind`).

> **Astuce :** `ProblemDetail.setProperty(nom, valeur)` ajoute une propriété à la réponse JSON.

La classe de test Exo5Bonus de PetControllerTest doit passer sans y apporter de modifications.

## Exo 6 - Banner.txt pour afficher ses `properties`

Au démarrage, Spring Boot affiche une bannière dans la console. Elle peut être remplacée par un fichier `banner.txt`,
et ce fichier peut contenir des valeurs du fichier de configuration sous la forme `${nom.de.la.propriété}`.

Ajouter le fichier src/main/resources/application.yml

```yaml
custom:
    app-name: "mon application"
    app-version: "1.0.1"
    git:
        branch: "main"
        commit: "123456"
```

Ajouter un fichier banner.txt dans src/main/resources avec le contenu

```text
${custom.app-name} ${custom.app-version} ${custom.git.branch} ${custom.git.commit}
```

Pour vérifier, lancer l'application (`Exo0Application.kt`, ou `./gradlew :exo0:bootRun`) :
la première ligne affichée dans la console doit être

```text
mon application 1.0.1 main 123456
```

(Arrêter l'application avec `Ctrl+C` ou le bouton d'arrêt de l'IDE.)

## Exo 7 - Injection des `properties` avec des `@Value`

Créer un `endpoint` GET /api/v1/info qui renvoie la réponse suivante en JSON

```json
{
    "appName": "mon application",
    "appVersion": "1.0.1",
    "git": {
        "branch": "main",
        "commit": "123456"
    }
}
```

Les valeurs ne sont pas écrites dans le code : elles viennent du fichier `application.yml` créé à l'exo 6.
Elles doivent être injectées avec des `@Value`.

Un objet `Info` est fourni dans le package `domain` pour faciliter la sérialisation.

<details>
<summary>Indice</summary>

En Kotlin, `$` démarre un template de chaîne : pour que Spring reçoive `${custom.app-name}`,
il faut écrire `@Value("\${custom.app-name}")` (avec un `\` devant le `$`).

L'injection par le constructeur fonctionne aussi pour `@Value`.

</details>

La classe de test Exo7 de InfoTest doit passer sans y apporter de modifications.

```bash
./gradlew :exo0:test --tests 'iut.nantes.exo0.controller.InfoTest$Exo7'
```

## Exo 8 - ConfigurationProperties

Remplacer l'utilisation des `@Value` par l'approche avec un `@ConfigurationProperties`.

<details>
<summary>Indice</summary>

Voir les slides [« ConfigurationProperties »](https://zomzog.fr/iut-spring-kotlin/c2/#/38) et [« Utilisation »](https://zomzog.fr/iut-spring-kotlin/c2/#/39).

</details>

La classe de test Exo7 de InfoTest doit toujours passer sans y apporter de modifications.

```bash
./gradlew :exo0:test --tests 'iut.nantes.exo0.controller.InfoTest$Exo7'
```

## Exo 9 - `profiles`

Un profil permet de changer la configuration selon l'environnement : le fichier `application-dev.yml` n'est lu
que si le profil `dev` est actif, et ses valeurs remplacent celles de `application.yml`.

Ajouter le fichier src/main/resources/application-dev.yml

```yaml
custom:
    app-version: "1.0.2-SNAPSHOT"
    git:
        branch: "feature/TICKET"
        commit: "654321"
```

La classe de test Exo9 de InfoTest doit passer sans y apporter de modifications.

```bash
./gradlew :exo0:test --tests 'iut.nantes.exo0.controller.InfoTest$Exo9'
```

Pour le voir sur l'application, la lancer avec le profil `dev` :

```bash
SPRING_PROFILES_ACTIVE=dev ./gradlew :exo0:bootRun
curl localhost:8080/api/v1/info
```

La réponse contient `"appVersion":"1.0.2-SNAPSHOT"`, mais garde `"appName":"mon application"` qui n'est pas redéfini dans le profil.
Dans l'IDE, ajouter `--spring.profiles.active=dev` aux arguments du programme de la configuration de lancement.

## Exo 10 - Ajout d'un écart maximum (`maxRange`)

Pour ne pas surcharger la base de données, il est souvent utile de limiter l'écart entre deux valeurs.

Ajouter un paramètre `maxRange` à l'annotation @ValidAgeRange, avec `Int.MAX_VALUE` comme valeur par défaut (pas de limite).

Modifier la validation de `PetSearch` pour garantir que l'écart entre l'âge minimum et l'âge maximum
(quand les deux sont fournis) est inférieur ou égal à `maxRange`.

Utiliser `@ValidAgeRange(maxRange = 100)` sur `PetSearch`.

<details>
<summary>Indice</summary>

Le validateur reçoit l'annotation dans la méthode `initialize(constraintAnnotation)` de `ConstraintValidator` :
c'est là qu'on lit `maxRange` pour le garder dans le validateur.

</details>

Compléter aussi `ValidAgeRangeValidatorTest` avec des cas sur `maxRange`.

> **Astuce :** Kotlin permet de créer une instance d'annotation :
> `ValidAgeRangeValidator().apply { initialize(ValidAgeRange(maxRange = 5)) }`.

La classe de test Exo10 de PetControllerTest doit passer sans y apporter de modifications,
sans casser les classes Exo3b et ValidAgeRangeValidatorTest.

```bash
./gradlew :exo0:test --tests 'iut.nantes.exo0.controller.PetControllerTest$Exo10'
```

## Exo 11 - Validateur en tant que bean

> **Bonus :** cet exo est optionnel, aucun exo suivant n'en dépend.

Spring s'occupe de la création des validateurs (`ConstraintValidator`), ce qui permet d'y injecter des valeurs de Spring.
Ici, on veut que l'écart maximum vienne du fichier de configuration plutôt que de l'annotation.

- Ajouter `custom.api.pets.max-range: 100` dans le fichier `application.yml`.
- Supprimer le paramètre `maxRange` de l'annotation `@ValidAgeRange` (et son utilisation sur `PetSearch`).
- Dans `ValidAgeRangeValidator`, injecter la valeur par le constructeur avec un `@Value`.

Adapter `ValidAgeRangeValidatorTest` : le validateur reçoit maintenant sa valeur dans son constructeur.

La classe de test Exo11 de PetControllerTest doit passer sans y apporter de modifications
(elle fixe `custom.api.pets.max-range` à 5), sans casser les classes Exo3b et Exo10.

```bash
./gradlew :exo0:test --tests 'iut.nantes.exo0.controller.PetControllerTest$Exo11'
```

## Exo 12 - Base de données H2 en mémoire

À partir de cet exercice, les modifications sont à faire dans le module `exo12`.

H2 est une base de données écrite en Java, très pratique pour développer : elle démarre avec l'application, sans rien installer.

Dans `exo12/build.gradle.kts`, ajouter les dépendances suivantes (puis recharger le projet Gradle) :

```kotlin
implementation("org.springframework.boot:spring-boot-starter-data-jpa")
runtimeOnly("com.h2database:h2")
runtimeOnly("org.springframework.boot:spring-boot-h2console")
```

> **Remarque :** `spring-boot-h2console` apporte la console web de H2. Depuis Spring Boot 4, elle est dans un module séparé.

Ajouter dans le fichier src/main/resources/application.yml (sous la clé `spring` déjà présente) la configuration suivante

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driverClassName: org.h2.Driver
    username: sa
    password: password
  h2:
    console:
      enabled: true
```

La base de données H2 est une base de données en mémoire.
Pour y accéder, il faut se rendre sur l'URL [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

À chaque démarrage de l'application, la base de données est réinitialisée.

Pour le vérifier :

1. Lancer l'application (`Exo12Application.kt`, ou `./gradlew :exo12:bootRun`). La console affiche
   `H2 console available at '/h2-console'`.
2. Ouvrir la console H2 et se connecter avec l'URL JDBC `jdbc:h2:mem:testdb`, l'utilisateur `sa` et le mot de passe `password`.
3. Lancer la requête suivante, puis `SELECT * FROM TEST` : la requête réussit et ne renvoie aucune ligne.

   ```sql
   CREATE TABLE TEST(ID INT PRIMARY KEY, NAME VARCHAR(255));
   ```

4. Arrêter puis relancer l'application, se reconnecter à la console et relancer `SELECT * FROM TEST` :
   l'erreur `Table "TEST" not found` confirme que la base a été réinitialisée.

## Exo 13 - Base de données H2 persistante

Modifier le fichier src/main/resources/application.yml avec pour datasource.url jdbc:h2:file:./data/testdb

Les données sont stockées dans un fichier data/testdb.mv.db, dans le dossier du module (`exo12/data/`).

Entre deux démarrages de l'application, les données sont conservées.

Pour le vérifier :

1. Lancer l'application et ouvrir la console H2 avec l'URL JDBC `jdbc:h2:file:./data/testdb`
   (utilisateur `sa`, mot de passe `password`).
2. Lancer les requêtes suivantes :

   ```sql
   CREATE TABLE TEST(ID INT PRIMARY KEY, NAME VARCHAR(255));
   INSERT INTO TEST VALUES (1, 'Joe');
   ```

3. Arrêter puis relancer l'application, se reconnecter à la console et lancer `SELECT * FROM TEST` :
   la ligne `Joe` est toujours là.

La classe de test Exo13Test vérifie que la base est bien stockée dans un fichier. Elle doit passer sans y apporter de modifications.

```bash
./gradlew :exo12:test --tests 'iut.nantes.exo12.Exo13Test'
```

> **Attention :** H2 verrouille le fichier de la base tant que l'application tourne.
> Arrêter l'application avant de lancer les tests, sinon ils échouent avec `Database may be already in use`.

## Exo 14 - Un CRUD avec une BDD

Dans `exo12/build.gradle.kts`, ajouter le plugin `kotlin("plugin.jpa") version "2.4.20"` dans le bloc `plugins`.
JPA a besoin d'un constructeur sans argument sur les entités, que Kotlin ne génère pas : ce plugin le fait à votre place.

Créer une `entity` JPA `HumanEntity` qui correspond aux attributs de `HumanDto`.

Créer l'interface `HumanRepository` qui étend `JpaRepository`.

À l'aide de `HumanRepository`, dans `DatabaseProxy`, implémentez les fonctions suivantes :

- saveHuman
- findHumanById
- findAllHumans

Pour cela, il faut convertir un `HumanDto` en `HumanEntity` et inversement : à vous d'écrire ces conversions.
Les fonctions sur les animaux ne servent pas dans ce TD, les laisser telles quelles.

<details>
<summary>Indice</summary>

Pour une entité, préférer une `class` avec des `var` à une `data class`
(les méthodes `equals` et `hashCode` générées par `data class` posent problème avec JPA).

L'identifiant est nullable (`Int?`) : il vaut `null` tant que l'entité n'est pas enregistrée en base.
Il est annoté `@Id` et `@GeneratedValue`.

Ranger les entités et `HumanRepository` dans le package `iut.nantes.exo12.repository`.

</details>

La classe de test Exo14 de HumanControllerTest doit passer sans y apporter de modifications.

```bash
./gradlew :exo12:test --tests 'iut.nantes.exo12.controller.HumanControllerTest$Exo14'
```

## Exo Bonus 1 - Une exception qui porte sa réponse

À faire dans le module `exo0`, après l'exo 4c.

Plutôt que d'écrire un `@ExceptionHandler`, une exception peut porter elle-même son statut et son `ProblemDetail` :
il suffit qu'elle hérite de `ErrorResponseException` (package `org.springframework.web`).
`ResponseEntityExceptionHandler`, dont hérite `ErrorHandler`, gère déjà ces exceptions : il renvoie le `ProblemDetail` qu'elles portent.
Voir la documentation de Spring : [Error Responses](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-ann-rest-exceptions.html).

Faire hériter `ImATeapotException` de `ErrorResponseException`, puis supprimer son `@ExceptionHandler` dans `ErrorHandler`.
Les `controllers` ne doivent pas être modifiés : `ImATeapotException` se construit toujours avec un message.

<details>
<summary>Indice</summary>

Le `detail` ne vient pas du message de l'exception : avec `ErrorResponseException(status)`, la réponse n'a pas de `detail`.
Regarder les constructeurs de `ErrorResponseException` : l'un d'eux reçoit le `ProblemDetail` à renvoyer.

</details>

La classe de test Exo4c de PetControllerTest doit toujours passer sans y apporter de modifications.

```bash
./gradlew :exo0:test --tests 'iut.nantes.exo0.controller.PetControllerTest$Exo4c'
```

## Exo Bonus 2 - Profil Postgres

À faire dans le module `exo12`, après l'exo 14.

Créer une configuration application-postgres.yml où la base h2 est remplacée par une base postgresql.

Dans `exo12/build.gradle.kts`, ajouter le driver : `runtimeOnly("org.postgresql:postgresql")`.

Pour lancer une base postgres sur le port 5432, vous pouvez utiliser la commande suivante (avec `docker` ou `podman`) :

```bash
docker run --name postgres -e POSTGRES_USER=username -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres:18
```

Avec podman, l'image doit être donnée en entier : `docker.io/library/postgres:18`.

<details>
<summary>Avec docker compose</summary>

Dans un fichier `compose.yaml` :

```yaml
services:
  postgres:
    image: postgres:18
    environment:
      POSTGRES_USER: username
      POSTGRES_PASSWORD: password
    ports:
      - "5432:5432"
```

puis `docker compose up -d`.

</details>

Lancer l'application avec le profil `postgres` :

```bash
SPRING_PROFILES_ACTIVE=postgres ./gradlew :exo12:bootRun
```

Pour vérifier :

- la console contient `Database JDBC URL [jdbc:postgresql://localhost:5432/postgres]`,
- créer un humain avec POST /api/v1/humans (comme dans les tests), puis le relire avec GET /api/v1/humans,
- la table existe dans Postgres : `docker exec postgres psql -U username -d postgres -c '\dt'`
  (`human_entity`).

Pour arrêter et supprimer la base : `docker rm -f postgres`.

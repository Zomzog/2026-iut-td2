# TODO — préparation du TD2

Fichier de travail (à supprimer avant publication). On traite les étapes **une par une** : on coche
`- [x]` quand c'est fait, ou on écrit `**REPORTÉ :**` / `**ABANDONNÉ :**` avec la raison.

- **Numérotation** : exos 0 à 19 + Bonus (ancien exo = nouveau + 21, voir la table).
- **ID** : `G-nn` = transverse, `En-k` = exo n (`En-k` : k-ième étape de l'exo n).
- **Tags** : `[décidé]` = tranché pendant le grill du 2026-10-25 ; `[à trancher]` = question ouverte ;
  `[spike]` = à vérifier par un essai avant d'écrire l'énoncé.
- Notes de référence (hors dépôt) : `obsidian/Notes/iut/2026/TD/TD2.md` (étapes `T2-nn`),
  `obsidian/Notes/iut/2026/amphi/Cours 2.md` (étapes `C2-nn`). Dépôt 2025 : `../2025-iut-td`
  (corrections : branche `origin/td2-correction`, un commit par exo).

## Table des exos

| Exo | Ancien | Module | Sujet | Test de vérification | Socle |
|---|---|---|---|---|---|
| 0 | 21 | exo0 | annotations de validation | `PetControllerTest.Exo0` | socle |
| 1 | 22 | exo0 | méta-annotation sur `petId` | `Base` + `Exo1` | socle |
| 2 | 23 | exo0 | request params dans un objet | `Exo2` | ⭐ bonus `[décidé]` |
| 3 | 24 | exo0 | `@ValidAgeRange` | `Exo2` + `Exo3` | socle, **à réécrire / couper en 2** `[décidé]` |
| 4 | 25 | exo0 | `@ExceptionHandler` → 418 | `Exo4` | socle |
| 5 | 26 | exo0 | surcharge `HttpMessageNotReadableException` | `Exo5` | socle |
| 6 | 27 | exo0 | `banner.txt` | visuelle | socle (exo express) |
| 7 | 28 | exo0 | `@Value` | `InfoTest.Exo7` | socle |
| 8 | 29 | exo0 | `@ConfigurationProperties` | `InfoTest.Exo7` | socle |
| 9 | 30 | exo0 | profils | `InfoTest.Exo9` | socle |
| 10 | 31 | exo0 | paramètre `maxRange` | à écrire | socle |
| 11 | 32 | exo0 | validateur injecté comme bean | à écrire | ⭐ bonus |
| 12 | 33 | exo12 | H2 en mémoire + console | manuelle | socle |
| 13 | 34 | exo12 | H2 dans un fichier | manuelle + TU | socle |
| 14 | 35 | exo12 | Entity + `JpaRepository` | `exo 14` | socle |
| 15 | 36 | exo12 | OneToOne unidirectionnel | `exo 15` | socle |
| 16 | 37 | exo12 | OneToOne bidirectionnel | `exo 15` + test écrit par l'étudiant | socle |
| 17 | 38 | exo12 | OneToMany unidirectionnel | `exo 17` | socle |
| 18 | 39 | exo12 | OneToMany bidirectionnel | `exo 17` | socle |
| 19 | 40 | exo12 | DELETE d'un pet | `exo 19` (commenté) | ⭐ bonus `[à trancher]` |
| Bonus | Bonus | exo12 | profil Postgres | manuelle | ⭐ |

> Deux exos s'ajoutent (test écrit par l'étudiant, voir `E3-5` et `E16-3`) : la numérotation sera
> **refaite après insertion**. Le socle / bonus définitif se rediscute à la fin (`T2-38`).

## Transverse

- [x] **G-01** · **`README.md`** — remplacer `TD2.adoc` par un `README.md` en Markdown, sur le modèle du
  TD1 : clone, proxy (`gradle.properties` **du projet**, emplacement exact), import IDE, rappel « ne
  modifiez pas les tests fournis sauf consigne », commande Gradle par exo
  (`./gradlew :exo0:test --tests '…'`), puis une section par exo. Supprimer `TD2.adoc` à la fin.
  **FAIT (2026-10-25) :** conversion fidèle de `TD2.adoc` en `README.md` (entête TD1, détails repliables
  en `<details>`, commande de test seul vérifiée), `TD2.adoc` supprimé. Le contenu des exos n'a pas
  changé : il sera repris exo par exo. Une commande Gradle par exo reste à ajouter au fil des exos.
- [ ] **G-02** · **`build.gradle.kts` au plus juste** `[décidé]` — le squelette ne contient que ce dont il
  a besoin ; l'étudiant **ajoute lui-même** (coordonnées exactes dans l'énoncé) :
  `starter-validation` (exo 0) ; `starter-data-jpa`, `h2`, `kotlin("plugin.jpa")`, module console H2
  de Boot 4 (exos 12-14) ; `starter-data-jpa-test` (exo 16).
  Retirer `actuator`, `flyway-core` et `kotlin-logging` (TD4). Vérifier le groupe Jackson
  (`tools.jackson` vs `com.fasterxml`, `T0-13`). `@field:` : **inutile** en Kotlin 2.4.20
  (spike de `E0-2`), pas de flag `-Xannotation-default-target`.
- [ ] **G-03** · **Convention de tests** — `@Nested inner class ExoN` + `@DisplayName("Exo N — titre")`
  (comme TD1). Tests rouges sur `main` mais **qui compilent** ; quand un test ne compile pas avant
  l'exo, le coller depuis l'énoncé (mécanique `Exo8Test` du TD1). Un exo = un test lançable seul.
- [ ] **G-04** · **Branche `correction`** — un commit `ExoN` par exo (comme TD1), partant de `main` une
  fois les énoncés figés ; `git rebase main correction` avant publication. Reprendre les commits de
  `origin/td2-correction` du dépôt 2025 (`git cherry-pick -n`), adaptés à la nouvelle numérotation.
- [ ] **G-05** · **Nettoyage des restes de 2025** — `exo12/src/test/…/PetControllerTest.kt` : tests
  `exo 41` / `exo 44` (TD3) à retirer ; `application.yml` d'`exo0` : résidus `r2dbc` et `flyway`
  (`T2-02`) ; imports morts ; classe `PetControllerTest` d'`exo12` qui teste `/humans` (`T2-07`).
- [ ] **G-06** · **Relecture énoncé ↔ build (`T0-33`)** — coordonnées de dépendances identiques à celles
  de la correction, `curl` copiés-collés et exécutés, aucun paramètre de suivi dans les liens, noms de
  classes cohérents entre consigne et TIP, `rootProject.name = "td2"`.
- [ ] **G-07** · **Synchroniser les notes Obsidian** — `TD2.md` : numérotation à partir de 0, `T2-18`
  (la correction 2025 utilise `@Target(VALUE_PARAMETER)`, pas une contrainte de classe), décisions du
  grill. `Cours 2.md` : `C2-62` (slide `orphanRemoval`) déjà ajoutée.
- [ ] **G-08** · **CI** — reporté, voir `T2-39`.
- [ ] **G-09** · **Ordre et socle / bonus** — à repenser une fois le TD écrit, voir `T2-38`.

## Module `exo0` — validation, erreurs, configuration

### Exo 0 — annotations de validation (ancien 21)

- [x] **E0-1** · Retirer `@Validated` du `PetController` et le handler `ConstraintViolationException` de
  `ErrorHandler` : en 4.1 la validation est native (`T2-08`, `C2-01`). `[décidé]`
  **FAIT (2026-10-25) :** `@Validated` et le handler `ConstraintViolationException` retirés d'`exo0`, ainsi que
  les imports `jakarta.validation` et `ConditionalOnProperty` devenus inutiles (le module compile sans
  `starter-validation`, utile pour G-02). État de départ : 15 tests, 9 rouges (`Base.error case`, `Exo0` ×2,
  `Exo1`, `Exo3`, `Exo4`, `Exo5`, `InfoTest` ×2). `Exo2` est déjà vert, comme prévu pour un refactor.
- [x] **E0-2** · Énoncé : l'étudiant ajoute `spring-boot-starter-validation` (G-02) et pose des
  `@field:NotBlank` / `@field:Min(0)` sur `PetDto` ; préciser pourquoi `@field:` (slide « Annotation
  target »). Vérifier que `@Valid` est demandé sur le `@RequestBody` (slide « Validation d'objets »).
  **FAIT (2026-11-01) :** `starter-validation` retiré du `build.gradle.kts` d'`exo0` ; l'énoncé (README, exo 0)
  demande de l'ajouter, de poser `@NotBlank` / `@Min(0)` sur `PetDto` et `@Valid` sur le `@RequestBody`
  (indice repliable). **`@field:` n'est pas nécessaire en Kotlin 2.4.20** (vérifié par spike + `javap` : une
  annotation sans cible sur une propriété de constructeur est posée sur le champ **et** le paramètre), donc
  l'énoncé ne l'impose pas. Conséquence : la slide « Annotation target » (`validation.md`) et la note
  `T0-12` sont à réviser ; le flag `-Xannotation-default-target` est inutile (voir G-02).
- [x] **E0-3** · Retirer la phrase « Un controller advice est déjà fourni… » ou la réécrire : la réponse
  d'erreur 400 est un `ProblemDetail` automatique via `ResponseEntityExceptionHandler`. `[décidé]`
  **FAIT (2026-11-01) :** note d'en-tête du README réécrite ; vérifié par spike : un body invalide donne
  400 `application/problem+json` (`detail` = « Invalid request content. », sans détail par champ).
- [x] **E0-4** · Vérifier `PetControllerTest.Exo0` sans modification (`./gradlew :exo0:test --tests '*Exo0'`).
  **FAIT (2026-11-01) :** sur `main`, `Exo0` a 2 tests rouges (âge et nom invalides) et 1 vert (cas valide).
  Avec la solution (annotations + `@Valid`) dans une copie hors dépôt, les 3 tests sont verts, sans modifier
  le test. La solution n'est pas dans `main` : elle ira dans le commit `Exo0` de `correction`.

### Exo 1 — méta-annotation sur `petId` (ancien 22)

- [ ] **E1-1** · Énoncé : renvoyer à la slide « Meta annotation » (`MinMax`) ; GET et PUT partagent la
  même annotation sur `petId` (entier > 0).
- [ ] **E1-2** · Vérifier que `Base` + `Exo1` passent sans `@Validated` (`HandlerMethodValidationException`
  → 400 automatique). `[spike]`
- [ ] **E1-3** · Corriger la citation des classes de test dans l'énoncé (fait : « Base et Exo1 »).

### Exo 2 — request params dans un objet (ancien 23) ⭐

- [ ] **E2-1** · Passer en **bonus** : l'exo devient un mini-cours dans l'énoncé (comment grouper des
  query params dans une `data class` sans annotation, avec un exemple court) qui **propose** de le
  faire. `[décidé]`
- [ ] **E2-2** · Rester sur `AgeRange` (pas de `Page` : un vrai groupement de pagination passerait par
  `Pageable`). `[décidé]` Comme le refactor ne change pas le comportement, `Exo2` passe déjà au départ.
- [ ] **E2-3** · Réécrire la phrase illisible de 2025 (« même pas `@RequestParam` sans lui fournir
  d'annotation »).
- [ ] **E2-4** · Le squelette garde les deux `@RequestParam` sur `GET /pets` ; l'exo 3 ne doit plus en
  dépendre (voir E3-1).

### Exo 3 — `@ValidAgeRange` (ancien 24) — à réécrire, voire couper en 2

- [ ] **E3-1** · **Validation sur un body, pas sur des query params** `[décidé]` : d'abord une
  validation champ par champ, puis une validation globale (« MaxRange »). L'exo ne dépend plus de
  l'exo 2.
- [ ] **E3-2** · **Partie 1, champ par champ** : annotation personnalisée sur un champ de `PetDto`
  (`ConstraintValidator<…, String>`, mécanique de la slide « Custom annotation »). Proposition :
  `@Capitalized` sur `name`. `[à trancher]` (l'idée de l'utilisateur ?)
- [ ] **E3-3** · **Partie 2, validation globale** : contrainte de classe (`@Target(CLASS)`) sur un body
  `AgeRange(minAge, maxAge)`, avec le tableau de vérité de 2025 (min ≤ max, ≥ 0). La slide ne montre pas
  la cible de classe : prévoir un indice repliable de deux lignes. Exception attendue :
  `MethodArgumentNotValidException` (400). `[spike]`
- [ ] **E3-4** · **Endpoint qui porte le body** `[à trancher]` : proposition `POST /api/v1/pets/search`
  avec `{ "minAge": …, "maxAge": … }` (recherche par POST) ; alternatives : contrainte transversale sur
  `PetDto` (`kind` × `age`), ou nouveau body dédié.
- [ ] **E3-5** · **Nouvel exo : test du validateur écrit par l'étudiant** `[décidé : exo distinct]` —
  `@ParameterizedTest` sur le tableau de vérité, test unitaire pur (sans Spring).
- [ ] **E3-6** · Réécrire les tests `Exo2` / `Exo3` en conséquence (un test par cas du tableau, 400 sur
  body invalide).

### Exo 4 — gestion d'exception personnalisée (ancien 25)

- [ ] **E4-1** · L'étudiant écrit `@ExceptionHandler(ImATeapotException::class)` dans `ErrorHandler` et
  retourne un `ProblemDetail` de statut 418. `[décidé]`
- [ ] **E4-2** · Test : statut 418, content-type `application/problem+json`, `$.status`. Remplacer
  `isIAmATeapot()` (déprécié dans le test actuel).
- [ ] **E4-3** · Indice : `ImATeapotException` peut aussi hériter de `ErrorResponseException` (gérée sans
  handler).
- [ ] **E4-4** · Retirer du squelette le handler `Exception` qui répond « Failure: … » en 500. `[décidé]`

### Exo 5 — surcharge de `HttpMessageNotReadableException` (ancien 26)

- [ ] **E5-1** · Constat vérifié par spike : le 400 `ProblemDetail` est **déjà automatique**
  (`detail` = « Failed to read request »). L'exo n'est plus « changer le format » mais **enrichir le
  `detail`**. `[décidé]`
- [ ] **E5-2** · Garder la recherche de l'exception à surcharger (point d'arrêt dans `handleException`
  **ou** `logging.level.org.springframework.web=DEBUG`, alternative VS Code, `T2-27`).
- [ ] **E5-3** · L'étudiant surcharge `handleHttpMessageNotReadable` : le `detail` mentionne le champ
  (`kind`) et la valeur refusée (`PONY`). Piste : `ex.cause` est un
  `tools.jackson.databind.exc.InvalidFormatException`, `path` donne `[kind]`.
- [ ] **E5-4** · ⭐ Optionnel : propriété `field` dans le `ProblemDetail`.
- [ ] **E5-5** · Réécrire le test : plus de `$.message` ni `doesNotExist(title)` ; `startsWith` appliqué
  réellement (bug `T2-01`) ; rouge au départ car le `detail` par défaut ne contient pas `kind`.

### Exo 6 — `banner.txt` (ancien 27)

- [ ] **E6-1** · Garder comme **exo express** avec un indice. `[décidé]` Donner le `application.yml`
  (`custom.*`) et la ligne du `banner.txt`.
- [ ] **E6-2** · Décrire dans l'énoncé la vérification attendue (ce qu'on voit au démarrage).

### Exo 7 — `@Value` (ancien 28)

- [ ] **E7-1** · Aligner l'exemple JSON de l'énoncé sur le test : `appName`, `1.0.1`, `main` (`T2-05`).
- [ ] **E7-2** · Vérifier `InfoTest.Exo7` (ancien `Exo28`).

### Exo 8 — `@ConfigurationProperties` (ancien 29)

- [ ] **E8-1** · `@EnableConfigurationProperties` ou `@ConfigurationPropertiesScan` ? `[à trancher]`
  (`T2-23`, `C2-40` ; la slide montre `@EnableConfigurationProperties`).
- [ ] **E8-2** · `InfoTest.Exo7` doit toujours passer.

### Exo 9 — profils (ancien 30)

- [ ] **E9-1** · `application-dev.yml` ; `InfoTest.Exo9` (ancien `Exo30`) avec `@ActiveProfiles("dev")`.

### Exo 10 — `maxRange` (ancien 31)

- [ ] **E10-1** · `maxRange` est un **paramètre de l'annotation** (comme `@MinMax(0, 10)` dans la slide),
  pas une valeur en dur dans le validateur. `[décidé en principe]` À rattacher au nouveau design de
  l'exo 3 (validation globale sur body). `[à trancher]`
- [ ] **E10-2** · Écrire le test fourni : écart > max → 400. Réécrire l'énoncé (« valeur à 100 »).

### Exo 11 — validateur comme bean (ancien 32) ⭐

- [ ] **E11-1** · Bonus. La valeur vient de la config `custom.api.pets.max-range`, injectée par
  constructeur (plus de remarque sur `lateinit`, `T2-28`).
- [ ] **E11-2** · Test : `@SpringBootTest(properties = ["custom.api.pets.max-range=5"])`. Correction à
  écrire (aucun commit en 2025).

## Module `exo12` — JPA

### Exo 12 — H2 en mémoire + console (ancien 33)

- [ ] **E12-1** · L'étudiant ajoute `starter-data-jpa`, `h2` et le module console H2 de Boot 4. `[spike]`
  (artefact exact de la console).
- [ ] **E12-2** · Config `spring.datasource.*` donnée dans l'énoncé.
- [ ] **E12-3** · Vérification manuelle explicite : URL de la console, requête SQL à lancer, résultat
  attendu. `[décidé]`

### Exo 13 — H2 dans un fichier (ancien 34)

- [ ] **E13-1** · Passer à `jdbc:h2:file:./data/testdb`. Vérification manuelle + **test unitaire** qui
  vérifie que `./data/testdb.mv.db` existe après démarrage du contexte. `[décidé]`
- [ ] **E13-2** · `data/` dans `.gitignore` (déjà fait).

### Exo 14 — Entity + `JpaRepository` (ancien 35)

- [ ] **E14-1** · L'étudiant ajoute `kotlin("plugin.jpa")` (G-02) ; entité `HumanEntity`, repository,
  mapping DTO ↔ Entity **écrit par l'étudiant** `[décidé]`. Style d'entité (`class`, `id` nullable) à
  confirmer. `[à trancher]`
- [ ] **E14-2** · Corriger `HumanController` : `@GetMapping("/api/v1/humans/{humanId}")` (`T2-04`).
- [ ] **E14-3** · `findAllHumans` partout (`T2-06`) ; renommer `PetControllerTest` → `HumanControllerTest` ;
  isoler les tests (`deleteAll()` ou `@Transactional`, `T2-07`).

### Exo 15 — OneToOne unidirectionnel (ancien 36)

- [ ] **E15-1** · `ContactEntity`, `@OneToOne`. Test `exo 15`.

### Exo 16 — OneToOne bidirectionnel (ancien 37)

- [ ] **E16-1** · Passer en bidirectionnel (`mappedBy`).
- [ ] **E16-2** · Le test `exo 15` passe aussi en unidirectionnel (`T2-14`) : la bidirectionnalité est
  prouvée par le test de l'étape suivante.
- [ ] **E16-3** · **Nouvel exo : `@DataJpaTest` écrit par l'étudiant** `[décidé : exo distinct]` ;
  l'étudiant ajoute `starter-data-jpa-test` (`C2-57`).

### Exo 17 — OneToMany unidirectionnel (ancien 38)

- [ ] **E17-1** · `PetEntity`, `@OneToMany`, ajout de `pets` à `HumanDto`. Test `exo 17`.

### Exo 18 — OneToMany bidirectionnel (ancien 39)

- [ ] **E18-1** · Bidirectionnel (`@ManyToOne` + `mappedBy`) ; collections `var MutableList`.
- [ ] **E18-2** · Idem `E16-2` : le test `exo 17` ne prouve pas la bidirectionnalité.

### Exo 19 — DELETE d'un pet (ancien 40) ⭐ ?

- [ ] **E19-1** · `orphanRemoval = true` : **slide à ajouter au cours** (`C2-62`, déjà dans `Cours 2.md`) ;
  l'énoncé y renvoie. `[décidé]`
- [ ] **E19-2** · Garder le test commenté (décommenter après l'ajout de `pets` à `HumanDto`) ; corriger la
  phrase et l'indiquer dans l'énoncé (`T2-15`).
- [ ] **E19-3** · `[spike]` : le DELETE passe-t-il sans `@Transactional` (c3) ?
- [ ] **E19-4** · Socle ou bonus : `[à trancher]` (voir G-09).

### Bonus

- [ ] **EB-1** · Profil Postgres : `application-postgres.yml`, commande `podman run` **et** alternative
  `docker compose`, image figée (`postgres:18`) ; procédure testée (`T2-29`).
- [ ] **EB-2** · Requêtes dérivées (`findByNameContainingIgnoreCase`, `findByPetsKind`) avec un test
  fourni (`T2-31`). `[à trancher]`

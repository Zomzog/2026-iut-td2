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
| 0 | 21 | exo0 | validations simples : 0a chemin, 0b body, 0c objet imbriqué | `Base` + `Exo0a`, `Exo0b`, `Exo0c` | socle |
| 1 | 22 | exo0 | méta-annotation sur `petId` | `Exo0a` + `Exo1` | socle |
| 2 | 23 | exo0 | request params dans un objet | `Exo2` | ⭐ bonus `[décidé]` |
| 3 | 24 | exo0 | `@ValidAgeRange` | `Exo2` + `Exo3` | socle, **à réécrire / couper en 2** `[décidé]` |
| 4a | 25 | exo0 | `@ExceptionHandler` → 406, `body` chaîne | `Exo4a` | socle |
| 4b | nouveau | exo0 | `@ExceptionHandler` → 500, `body` JSON `{message, status}` | `Exo4a` + `Exo4b` | socle |
| 4c | 25 | exo0 | `@ExceptionHandler` → 418 en `ProblemDetail` | `Exo4a` + `Exo4b` + `Exo4c` | socle |
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
| Bonus 1 | nouveau | exo0 | `ImATeapotException` hérite de `ErrorResponseException` | `Exo4c` | ⭐ |
| Bonus 2 | nouveau | exo12 | requêtes dérivées | `ExoBonusQueries` | ⭐ |
| Bonus 3 | Bonus | exo12 | profil Postgres | manuelle | ⭐ |

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
  **FAIT à moitié (2026-11-01) :** `exo0` n'a plus `actuator`, `data-jpa`, `h2`, `plugin.jpa` ; `exo12` n'a plus
  `actuator`, `data-jpa`, `validation`, `flyway-core`, `h2`, `kotlin-logging`, `plugin.jpa` (l'étudiant les ajoute
  aux exos 12-14, voir les `E12-*` à `E14-*`) ; `ErrorHandler` d'`exo12` simplifié comme celui d'`exo0` ; Jackson
  passé à `tools.jackson.module:jackson-module-kotlin` (mêmes résultats de tests). **Reste** : cocher G-02 une fois
  les exos JPA écrits (les coordonnées exactes doivent être dans l'énoncé) ; `mockk` et `springmockk` sont dans
  `exo0` et `exo12`, mais aucun test ne s'en sert (relevé au grill de l'exo 8, 2026-11-03) : retirer ou garder pour un exo à écrire.
- [ ] **G-03** · **Convention de tests** — `@Nested inner class ExoN` + `@DisplayName("Exo N — titre")`
  (comme TD1). Tests rouges sur `main` mais **qui compilent** ; quand un test ne compile pas avant
  l'exo, le coller depuis l'énoncé (mécanique `Exo8Test` du TD1). Un exo = un test lançable seul.
- [ ] **G-04** · **Branche `correction`** — un commit `ExoN` par exo (comme TD1), partant de `main` une
  fois les énoncés figés ; `git rebase main correction` avant publication. Reprendre les commits de
  `origin/td2-correction` du dépôt 2025 (`git cherry-pick -n`), adaptés à la nouvelle numérotation.
- [ ] **G-05** · **Nettoyage des restes de 2025** — `exo12/src/test/…/PetControllerTest.kt` : tests
  `exo 41` / `exo 44` (TD3) à retirer ; `application.yml` d'`exo0` : résidus `r2dbc` et `flyway`
  (`T2-02`, **fait** à l'exo 6 : fichier supprimé) ; imports morts ; classe `PetControllerTest` d'`exo12` qui teste `/humans` (`T2-07`).
- [ ] **G-06** · **Relecture énoncé ↔ build (`T0-33`)** — coordonnées de dépendances identiques à celles
  de la correction, `curl` copiés-collés et exécutés, aucun paramètre de suivi dans les liens, noms de
  classes cohérents entre consigne et TIP, `rootProject.name = "td2"`.
- [x] **G-07** · **Synchroniser les notes Obsidian** — `TD2.md` : numérotation à partir de 0, `T2-18`
  (la correction 2025 utilise `@Target(VALUE_PARAMETER)`, pas une contrainte de classe), décisions du
  grill. `Cours 2.md` : `C2-64` (slide `orphanRemoval`) déjà ajoutée.
  **FAIT (2026-11-01) :** `TD2.md` (encadré d'avancement, table renumérotée, 31 étapes `T2-*` cochées avec FAIT / DÉCISION), `update.md`
  (`T0-08`, `T0-12`, `T0-13`, `T0-14`, `T0-16`, `T0-32`, `T0-34`), `Cours 2.md` (nouveaux `C2-67`, `C2-68`, `C2-69` ; la slide `orphanRemoval`
  est `C2-64`).
- [ ] **G-08** · **CI** — reporté, voir `T2-39`.
- [ ] **G-09** · **Ordre et socle / bonus** — à repenser une fois le TD écrit, voir `T2-38`.
- [x] **G-10** · **Liens vers les slides du cours** — chaque slide citée dans le README pointe vers
  `https://zomzog.fr/iut-spring-kotlin/c2/#/n`, avec le titre gardé dans le texte du lien.
  **FAIT (2026-11-03) :** exos 1 (11), 3 (10, 12), 15 (63), 16 (64), 17 (65), 18 (66, 65), 19 (69), Bonus 1 (52).
  Numéros calculés depuis `slides/c2.md` de `main` du dépôt `iut-spring-kotlin` (la branche `2026-C3` n'inclut
  pas `jpa2.md` / `jpa3.md`), pas vérifiés sur le site. Liens vers les slides de l'amphi (`jpa2.md`), pas vers
  celles de référence (`jpa3.md`, 73-84), qui donnent les solutions. **À refaire** si des slides sont ajoutées
  ou retirées du deck c2. **Reste (à voir à l'exo 15) :** `jpa2.md` / `jpa3.md` passent en tête de c3 (branche
  `2026-C3`, 2026-11-03) ; une fois déployé, les liens des exos 15-19 deviennent `c3/#/12` (15), `#/13` (16),
  `#/14` (17, 18), `#/15` (18), `#/18` (19). Ceux des exos 1, 3 et Bonus 1 ne bougent pas.
  **AJOUT (2026-11-03) :** exo 4a (19, « RestControllerAdvice »), même méthode (vérifiée : elle redonne 10, 11, 12, 52,
  63-69). Depuis la renumérotation des bonus (E4-8), le « Bonus 1 » ci-dessus est le Bonus 2 (requêtes dérivées).
  **AJOUT (2026-11-03) :** exo 8 (38 « ConfigurationProperties », 39 « Utilisation »), mêmes numéros sur `main` et `2026-C3`.
- [ ] **G-11** · **Réduire les indices** — exo 0 fait (E0-5), exo 1 fait (E1-4), 3b en partie (E3-7), exo 4 fait
  (E4-5 : seul le 4a garde un indice, le lien vers la slide), exo 8 fait (E8-3 : l'indice ne garde que les liens vers les
  slides). Autres candidats repérés : pseudo-code du 3b, bloc « Solution » + indice de l'exo 5, noms des méthodes du
  Bonus 2 (requêtes dérivées).

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
- [x] **E0-5** · **Exo 0 = validations simples, coupé en 0a / 0b / 0c** `[décidé]` — 0a : `@Min(1)` sur `petId`
  du GET ; 0b : la validation de body existante (`@NotBlank`, `@Min(0)`, `@Valid`) ; 0c : `OwnerDto` imbriqué
  dans `PetDto` (cascade `@Valid`). Le 3b n'est pas remonté : il demande un `ConstraintValidator`, vu à l'exo 3.
  **FAIT (2026-11-03) :** README : section « Exo 0 - Validations simples » (dépendance + lien vers la liste des
  contraintes de Hibernate Validator, qui remplace l'indice `@NotBlank` / `@Min` / `@Valid`), puis 0a, 0b, 0c.
  0c donne le code de `OwnerDto` et de `PetDto` (`owner: OwnerDto? = null`, pour que les tests qui construisent
  `PetDto` compilent toujours) ; indice : « un objet imbriqué se valide de la même manière que le `body` de
  l'exo 0b » (le lien vers « Object graphs » de Hibernate Validator donnait trop la réponse).
  Tests : `Exo0` renommé `Exo0b` ; `Exo0a` reprend `invalid path id on get` d'`Exo1` ; `Exo0c` nouveau (owner
  valide → 201, email invalide → 400, nom vide → 400). Vérifié par spike : tout est rouge au départ ; sans
  `@Valid` sur `owner`, les deux cas d'erreur d'`Exo0c` sont rouges (201) ; avec la solution, `Base`, `Exo0a`,
  `Exo0b`, `Exo0c` et `Exo1` sont verts. **Reste :** couper le commit `Exo0` de `correction` en 0a / 0b / 0c (G-04).

### Exo 1 — méta-annotation sur `petId` (ancien 22)

- [x] **E1-1** · Énoncé : renvoyer à la slide « Meta annotation » (`MinMax`) ; GET et PUT partagent la
  même annotation sur `petId` (entier > 0).
  **FAIT (2026-11-01) :** README exo 1 : indice repliable (renvoi à la slide « Meta annotation », annotation
  `PetId` à poser sur `petId` de GET et PUT) + note « pas de `@Validated` sur le controller ».
- [x] **E1-2** · Vérifier que `Base` + `Exo1` passent sans `@Validated` (`HandlerMethodValidationException`
  → 400 automatique). `[spike]`
  **FAIT (2026-11-01) :** spike hors dépôt avec la méta-annotation de la slide (`@Constraint(validatedBy = [])` +
  `@Min(1)`, cible `VALUE_PARAMETER`) : `Base` + `Exo1` verts **sans `@Validated`** ; sans l'annotation,
  `Exo1` est rouge. Le 400 vient de `HandlerMethodValidationException`, géré par `ResponseEntityExceptionHandler`.
- [x] **E1-3** · Corriger la citation des classes de test dans l'énoncé (fait : « Base et Exo1 »).
  **FAIT (2026-11-01) :** le test `Base > error case` (GET `/pets/0` → 400) dépendait de l'exo 1 : déplacé dans
  `Exo1` (`invalid path id on get`). `Base` ne garde que le cas nominal et est vert au départ ; l'énoncé ne cite
  plus que `Exo1` (+ « `Base` doit rester verte »), avec la commande Gradle de l'exo.
- [x] **E1-4** · **Expliquer pourquoi une méta-annotation pour une seule contrainte** `[décidé]` (grill du 2026-11-03).
  **FAIT (2026-11-03) :** l'énoncé part du GET de l'exo 0a et demande d'appliquer la même règle au PUT sans recopier
  la contrainte ; nom `@PetId` imposé dans l'énoncé ; deux phrases sur le pourquoi (un seul endroit à modifier si
  la règle change ; `@PetId` dit ce que représente le paramètre, `@Min(1)` seulement comment on le vérifie).
  L'indice ne garde que le lien vers la slide « Meta annotation ». Tests `Exo1` : `happy path` + deux PUT invalides,
  `zero path id on put` (`/pets/0`, body `"id": 0`) et `negative path id on put` (`/pets/-5`, body `"id": -5`).
  Les ids du body et du chemin sont égaux pour que le test ne dépende pas de la comparaison faite par le controller.
  `invalid path id on get` est passé dans `Exo0a`.

### Exo 2 — request params dans un objet (ancien 23) ⭐

- [x] **E2-1** · Passer en **bonus** : l'exo devient un mini-cours dans l'énoncé (comment grouper des
  query params dans une `data class` sans annotation, avec un exemple court) qui **propose** de le
  faire. `[décidé]`
  **FAIT (2026-11-01) :** README : titre « Exo 2 (bonus) », encadré « optionnel, aucun exo suivant n'en dépend »,
  mini-cours + exemple (`Search(text, limit)` sur `GET /api/v1/search`), puis la consigne. Exemple vérifié par
  spike : `?text=abc&limit=10` → `abc/10`, paramètre absent → `null`.
- [x] **E2-2** · Rester sur `AgeRange` (pas de `Page` : un vrai groupement de pagination passerait par
  `Pageable`). `[décidé]` Comme le refactor ne change pas le comportement, `Exo2` passe déjà au départ.
  **FAIT (2026-11-01) :** la consigne reste sur `AgeRange` ; vérifié avec une copie hors dépôt : `Exo2` vert avec
  `AgeRange` et déjà vert sur `main`.
- [x] **E2-3** · Réécrire la phrase illisible de 2025 (« même pas `@RequestParam` sans lui fournir
  d'annotation »).
  **FAIT (2026-11-01) :** phrase remplacée par une explication claire (Spring remplit chaque propriété avec le
  paramètre du même nom, ni objet ni paramètre n'ont d'annotation, propriétés nullables).
- [x] **E2-4** · Le squelette garde les deux `@RequestParam` sur `GET /pets` ; l'exo 3 ne doit plus en
  dépendre (voir E3-1).
  **FAIT (2026-11-01) :** le squelette garde `@RequestParam minAge` / `maxAge` ; rien à changer. **À garder en
  tête pour E3 :** un étudiant qui a fait l'exo 2 a déjà une classe `AgeRange` ; la validation globale de
  l'exo 3 (body) devra choisir un autre nom ou réutiliser cette classe.

### Exo 3 — `@ValidAgeRange` (ancien 24) — à réécrire, voire couper en 2

- [x] **E3-1** · **Validation sur un body, pas sur des query params** `[décidé]` : d'abord une
  validation champ par champ, puis une validation globale (« MaxRange »). L'exo ne dépend plus de
  l'exo 2.
  **FAIT (2026-11-01) :** l'ancien exo 24 est coupé en **Exo 3** (champ), **Exo 3b** (body global) et **Exo 3c**
  (test du validateur). Numérotation **provisoire** (3 / 3b / 3c) : à refaire en G-09 pour ne pas décaler
  tout le README maintenant. L'exo 3b ne dépend plus de l'exo 2.
- [x] **E3-2** · **Partie 1, champ par champ** : annotation personnalisée sur un champ de `PetDto`
  (`ConstraintValidator<…, String>`, mécanique de la slide « Custom annotation »). Proposition :
  `@Capitalized` sur `name`. `[à trancher]` (l'idée de l'utilisateur ?)
  **FAIT (2026-11-01) :** choix retenu `@Capitalized` sur `PetDto.name` (ma proposition, à valider). Vérifié par
  spike : le validateur accepte le vide (laisse `@NotBlank` décider) ; **la cible doit être `FIELD`** : avec
  `VALUE_PARAMETER` comme dans la slide `MinMax`, le test est rouge. Indice ajouté dans l'énoncé. Point à
  mettre dans `Cours 2.md` : la slide « Custom annotation » ne montre que la cible `VALUE_PARAMETER`.
- [x] **E3-3** · **Partie 2, validation globale** : contrainte de classe (`@Target(CLASS)`) sur un body
  `AgeRange(minAge, maxAge)`, avec le tableau de vérité de 2025 (min ≤ max, ≥ 0). La slide ne montre pas
  la cible de classe : prévoir un indice repliable de deux lignes. Exception attendue :
  `MethodArgumentNotValidException` (400). `[spike]`
  **FAIT (2026-11-01) :** contrainte `@Target(CLASS)` sur `PetSearch`, validateur
  `ConstraintValidator<ValidAgeRange, PetSearch>`, `@Valid` sur le `@RequestBody` → 400 vérifié par spike.
  Les deux règles (min ≤ max, ≥ 0) sont obligatoires (plus « optionnelles ») car les tests les exigent.
- [x] **E3-4** · **Endpoint qui porte le body** `[à trancher]` : proposition `POST /api/v1/pets/search`
  avec `{ "minAge": …, "maxAge": … }` (recherche par POST) ; alternatives : contrainte transversale sur
  `PetDto` (`kind` × `age`), ou nouveau body dédié.
  **FAIT (2026-11-01) :** option A retenue (ma recommandation, à valider) : `POST /api/v1/pets/search`,
  body `PetSearch(minAge, maxAge)`, fourni dans le squelette (endpoint + classe, sans `@Valid`). Nom
  `PetSearch` choisi pour ne pas entrer en conflit avec l'`AgeRange` de l'exo 2. `GET /pets` et le POST
  partagent `filterByAge`. **Impact exo 10 :** `maxRange` s'ajoutera à `@ValidAgeRange` sur `PetSearch`.
- [x] **E3-5** · **Nouvel exo : test du validateur écrit par l'étudiant** `[décidé : exo distinct]` —
  `@ParameterizedTest` sur le tableau de vérité, test unitaire pur (sans Spring).
  **FAIT (2026-11-01) :** exo 3c dans le README (`ValidAgeRangeValidatorTest`, 100% de couverture, `@ParameterizedTest`
  + `@CsvSource`, TIP couverture IntelliJ). Vérifié : `@ParameterizedTest` compile avec les dépendances de test
  actuelles et le validateur s'appelle avec un contexte `null`. Pas de test fourni (l'étudiant l'écrit).
- [x] **E3-6** · Réécrire les tests `Exo2` / `Exo3` en conséquence (un test par cas du tableau, 400 sur
  body invalide).
  **FAIT (2026-11-01) :** ancien `Exo3` (GET avec query params) remplacé par `Exo3` (2 tests : nom capitalisé /
  minuscule) et `Exo3b` (6 tests : sans bornes, max seul, min = max, min > max, min < 0, max < 0).
- [x] **E3-7** · Titre du 3b : « Validation globale d'un `body` » laissait croire à une validation classique.
  **FAIT (2026-11-03) :** renommé « Exo 3b - Validateur personnalisé sur une classe », en miroir de l'exo 3
  (« … sur un champ ») ; ligne « comme pour l'exo 0, `@Valid` » retirée de l'indice (c'est dans le cours).

### Exo 4 — gestion d'exception personnalisée (ancien 25), coupé en 4a / 4b / 4c

- [x] **E4-1** · L'étudiant écrit `@ExceptionHandler(ImATeapotException::class)` dans `ErrorHandler` et
  retourne un `ProblemDetail` de statut 418. `[décidé]`
  **FAIT (2026-11-01) :** README exo 4 : contexte (le controller lance l'exception, personne ne l'intercepte), consigne
  `ProblemDetail`, indice repliable (`@ExceptionHandler` + `ProblemDetail.forStatus(418)`). Solution vérifiée
  hors dépôt (`handleTeapot` retourne `ProblemDetail.forStatus(418)`).
- [x] **E4-2** · Test : statut 418, content-type `application/problem+json`, `$.status`. Remplacer
  `isIAmATeapot()` (déprécié dans le test actuel).
  **FAIT (2026-11-01) :** test `Exo4` : `status { isEqualTo(418) }` (fini `isIAmATeapot`, déprécié), content-type
  `application/problem+json`, `$.status` = 418. Rouge sur `main` (l'exception remonte en `ServletException`).
- [x] **E4-3** · Indice : `ImATeapotException` peut aussi hériter de `ErrorResponseException` (gérée sans
  handler).
  **FAIT (2026-11-01) :** variante vérifiée hors dépôt : `ImATeapotException : ErrorResponseException(HttpStatusCode.valueOf(418))`
  sans aucun handler → test vert. Non mise dans l'énoncé (reste une piste pour la correction ou un bonus).
- [x] **E4-4** · Retirer du squelette le handler `Exception` qui répond « Failure: … » en 500. `[décidé]`
  **FAIT (2026-11-01) :** handler `Exception` (« Failure: … » en 500) retiré ; `ErrorHandler` est une classe vide qui étend
  `ResponseEntityExceptionHandler`.
- [x] **E4-5** · **Exo 4 coupé en 4a / 4b / 4c** `[décidé]` (grill du 2026-11-03) — 4a : `body` = chaîne ; 4b : `body`
  `{ "message": <e.message>, "status": … }` ; 4c : `ProblemDetail`. Une exception par étape, pour que le test de
  l'étape précédente reste vert. Remplace E4-1 (le 4c en reprend la consigne) ; E4-2 devient le 2e test d'`Exo4c`.
  **FAIT (2026-11-03) :** squelette : `ExceptionController` (endpoint de démo, 3 GET) ; exceptions `(message: String) :
  RuntimeException(message)` dans `errors` :

  | Route | Exception | Statut | Message |
  |---|---|---|---|
  | GET `/exception/MyFirstException` | `MyFirstException` | 406 | `My first exception` |
  | GET `/exception/MyExceptionWithBody` | `MyExceptionWithBody` | 500 | `My exception with body` |
  | GET `/exception/ImATeapotException` | `ImATeapotException` | 418 | `I'm a teapot` |
  | PUT `/api/v1/pets/{petId}`, animal absent | `ImATeapotException` | 418 | `Pet $petId not found` |

  Noms incohérents voulus (moins monotone) ; statuts variés ; message fixe dans le controller (l'étudiant peut le recopier
  dans son handler, mais au 4c les deux messages différents l'obligent à utiliser `e.message`). `IllegalArgumentException`
  (ids différents au PUT) inchangée. README : sections `## Exo 4a` / `4b` / `4c` (au niveau de 3 / 3b / 3c), « sans casser »
  les précédents ; 4a : indice = lien vers la slide « RestControllerAdvice » (`#/19`) ; 4b : forme du JSON seulement (nom
  du DTO libre) ; 4c : sans indice, rien sur la dépréciation de `HttpStatus.I_AM_A_TEAPOT` (`@Deprecated(since = "7.0")`,
  « marked as unused in RFC 9110 » : un simple avertissement), remarque finale sur les 3 formats d'erreur mélangés.
  Toutes les lettres disparaîtront avec la renumérotation (G-09).
- [x] **E4-6** · Tests `Exo4a`, `Exo4b`, `Exo4c` (à la place d'`Exo4`, déplacés après `Exo3b`).
  **FAIT (2026-11-03) :** `Exo4a` : 406, `text/plain` (`contentTypeCompatibleWith`), `body` exact ; `Exo4b` : 500,
  `application/json`, `$.message`, `$.status` = 500 ; `Exo4c` : GET de démo et PUT d'un animal absent (`/pets/8`), 418,
  `application/problem+json`, `$.status`, `$.detail`. Vérifié hors dépôt : les 4 tests sont rouges au départ
  (`ServletException`) ; avec la solution appliquée étape par étape, chaque test passe au vert sans casser les précédents
  (`ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.message)`, `ResponseEntity.internalServerError().body(ErrorDto(e.message, 500))`,
  `ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(418), e.message)`) ; reste de la suite `exo0` inchangé.
  **Reste :** branche `correction` à mettre à jour à la main (commits 4a / 4b / 4c / Bonus 1).
- [x] **E4-7** · **Bonus `ErrorResponseException`** (reprend E4-3) `[décidé]` — « Exo Bonus 1 », en tête des bonus.
  **FAIT (2026-11-03) :** README « Exo Bonus 1 - Une exception qui porte sa réponse » (module `exo0`, après le 4c) :
  l'énoncé explique le mécanisme (absent du cours) avec un lien vers la doc Spring « Error Responses » ; consigne : faire
  hériter `ImATeapotException`, supprimer son handler, sans modifier les controllers ; `Exo4c` doit rester vert. Indice
  repliable : le `detail` ne vient pas du message. Vérifié hors dépôt : la variante d'E4-3
  (`ErrorResponseException(HttpStatusCode.valueOf(418))`) fait échouer `Exo4c` (« No value at JSON path $.detail ») :
  sans `ProblemDetail` fourni, il n'y a pas de `detail`, et `getMessage()` est surchargée (statut + body) ; avec
  `ErrorResponseException(status, ProblemDetail.forStatusAndDetail(status, message), null)`, `Exo4c` est vert.
- [x] **E4-8** · **Renumérotation des bonus** — nouveau Bonus 1 (`ErrorResponseException`) ; requêtes dérivées :
  Bonus 1 → 2 ; profil Postgres : Bonus 2 → 3.
  **FAIT (2026-11-03) :** titres du README, table ci-dessus, tables des notes Obsidian (`TD2.md`). Les entrées « FAIT »
  datées (G-10, EB-1, EB-2, notes Obsidian) gardent l'ancien numéro : elles décrivent l'état à leur date. Aucun test
  touché (les classes portent le nom du sujet, `ExoBonusQueries`).

### Exo 5 — surcharge de `HttpMessageNotReadableException` (ancien 26)

- [x] **E5-1** · Constat vérifié par spike : le 400 `ProblemDetail` est **déjà automatique**
  (`detail` = « Failed to read request »). L'exo n'est plus « changer le format » mais **enrichir le
  `detail`**. `[décidé]`
  **FAIT (2026-11-01) :** README réécrit : le 400 `ProblemDetail` est présenté comme déjà là (`detail` trop vague),
  l'objectif est un `detail` utile.
- [x] **E5-2** · Garder la recherche de l'exception à surcharger (point d'arrêt dans `handleException`
  **ou** `logging.level.org.springframework.web=DEBUG`, alternative VS Code, `T2-27`).
  **FAIT (2026-11-01) :** deux voies, sans dépendre de l'IDE : la sortie du test (`.andDo { print() }` → `Resolved
  Exception: Type = …HttpMessageNotReadableException`, vérifié) et le point d'arrêt dans `handleException` (TIP).
  Le log `logging.level.org.springframework.web: DEBUG` marche aussi (vérifié) mais n'est pas dans l'énoncé :
  `application.yml` n'existe qu'à l'exo 6. `T2-27` satisfait (aucune procédure propre à IntelliJ).
- [x] **E5-3** · L'étudiant surcharge `handleHttpMessageNotReadable` : le `detail` mentionne le champ
  (`kind`) et la valeur refusée (`PONY`). Piste : `ex.cause` est un
  `tools.jackson.databind.exc.InvalidFormatException`, `path` donne `[kind]`.
  **FAIT (2026-11-01) :** consigne + indice repliable (`ex.cause` = `InvalidFormatException`, `value`, `path`, `super`
  pour les autres causes, `handleExceptionInternal`). Solution vérifiée hors dépôt (`Invalid value 'PONY' for field 'kind'`).
- [x] **E5-4** · ⭐ Optionnel : propriété `field` dans le `ProblemDetail`.
  **FAIT (2026-11-01) :** sous-section « Exo 5 - Bonus » + classe de test `Exo5Bonus` (`$.field` = `kind`), vérifiée vert
  avec `problem.setProperty("field", field)`.
- [x] **E5-5** · Réécrire le test : plus de `$.message` ni `doesNotExist(title)` ; `startsWith` appliqué
  réellement (bug `T2-01`) ; rouge au départ car le `detail` par défaut ne contient pas `kind`.
  **FAIT (2026-11-01) :** test `Exo5` réécrit : `unknown enum value` (400, `application/problem+json`, `$.status`, `$.detail`
  contient `kind` et `PONY`, matchers Hamcrest réellement appliqués via `value(containsString(...))`, fin du bug
  `startsWith`) et `malformed json` (400 `problem+json`, vert dès le départ, garde-fou contre une surcharge trop
  large). Rouge sur `main` : `unknown enum value` et `Exo5Bonus`.

### Exo 6 — `banner.txt` (ancien 27)

- [x] **E6-1** · Garder comme **exo express** avec un indice. `[décidé]` Donner le `application.yml`
  (`custom.*`) et la ligne du `banner.txt`.
  **FAIT (2026-11-01) :** README : une phrase de contexte (bannière Spring Boot, `${...}` dans `banner.txt`), les deux
  fichiers à créer donnés tels quels. Le `application.yml` du squelette (résidus `r2dbc` / `flyway`, nom `exo0`) est
  **supprimé** : l'énoncé le fait créer à l'étudiant (c'était déjà le texte de 2025, mais le fichier existait).
- [x] **E6-2** · Décrire dans l'énoncé la vérification attendue (ce qu'on voit au démarrage).
  **FAIT (2026-11-01) :** vérification décrite : première ligne de la console = `mon application 1.0.1 main 123456`
  (testé hors dépôt avec `./gradlew :exo0:bootRun`). Pas de test automatisé : `@SpringBootTest` coupe la bannière.

### Exo 7 — `@Value` (ancien 28)

- [x] **E7-1** · Aligner l'exemple JSON de l'énoncé sur le test : `appName`, `1.0.1`, `main` (`T2-05`).
  **FAIT (2026-11-01) :** exemple JSON aligné sur le test (`appName`, `appVersion`, `1.0.1`, `main`, `123456`) ; ajout d'un
  lien vers le `application.yml` de l'exo 6 et d'un indice repliable sur l'échappement Kotlin `\${...}` (sinon
  c'est un template de chaîne).
- [x] **E7-2** · Vérifier `InfoTest.Exo7` (ancien `Exo28`).
  **FAIT (2026-11-01) :** test `InfoTest.Exo7` inchangé ; rouge sur `main` ; vert avec un `InfoController` à 4 `@Value`
  injectés par le constructeur (vérifié hors dépôt, avec le `application.yml` de l'exo 6).
- [x] **E7-3** · Détecter une réponse écrite en dur ; vérifier l'usage de `@Value` ?
  **FAIT (2026-11-03) :** énoncé reformulé (d'abord l'`endpoint`, puis la contrainte `@Value` et « pas écrites dans le
  code ») ; 2e test `InfoTest.Exo7.Exo7OtherName` avec `@ActiveProfiles("pony")` et `src/test/resources/application-pony.yml`
  (surcharge partielle : `appName`, `appVersion` ; `git` inchangé). Le 2e test n'est pas annoncé dans l'énoncé.
  Pas de test sur `@Value` lui-même : l'exo 8 réutilise `InfoTest.Exo7`, un test qui vise `@Value` y casserait ;
  on fait confiance (vu à la correction). Branche `correction` à mettre à jour à la main.

### Exo 8 — `@ConfigurationProperties` (ancien 29)

- [x] **E8-1** · `@EnableConfigurationProperties` ou `@ConfigurationPropertiesScan` ? `[à trancher]`
  (`T2-23`, `C2-40` ; la slide montre `@EnableConfigurationProperties`).
  **FAIT (2026-11-01) :** choix retenu `@EnableConfigurationProperties` sur une classe `@Configuration`, comme la slide
  (ma décision par défaut, à valider). `@ConfigurationPropertiesScan` sur l'application marche aussi (vérifié) ;
  il n'est pas cité dans l'énoncé (`C2-40` reste un manque du cours). `T2-23` : alignement slide ↔ correction OK.
  **CORRECTION (2026-11-03) :** « marche aussi » n'était vrai que pour `InfoTest`. Tant que `PetControllerTest` était un
  `@WebMvcTest`, la `@Configuration` à part **et** `@ConfigurationPropertiesScan` cassaient tout `PetControllerTest`
  (`No qualifying bean of type 'CustomProperties'` : le slice charge `InfoController`, mais filtre les deux). Réglé par E8-3.
- [x] **E8-2** · `InfoTest.Exo7` doit toujours passer.
  **FAIT (2026-11-01) :** `InfoTest.Exo7` reste le test de l'exo (il est rouge sur `main` tant que l'exo 7 n'est pas fait) ;
  vert avec `CustomProperties` (data class + `GitProperties` imbriquée) injectée dans le controller.
- [x] **E8-3** · Indice réduit à un renvoi au cours (G-11) ; la slide doit marcher telle quelle.
  **FAIT (2026-11-03) :** l'indice ne garde que les liens vers les slides 38 et 39 (G-10). On retire de l'énoncé l'objet
  imbriqué `git` (absent du cours, la structure du yaml le suggère) et `app-name` → `appName`.
  `PetControllerTest` passe de `@WebMvcTest` à `@SpringBootTest` + `@AutoConfigureMockMvc`, comme `InfoTest` (le cours c1
  ne montre que `@SpringBootTest`). Aucun mock : `PetController` n'a pas de service. Vérifié hors dépôt : la solution de la
  slide (`@Configuration` à part) donne tout vert jusqu'à l'exo 11 ; sur `rework` + solution exo 8, `Exo7`, `Exo7OtherName`
  et `PetControllerTest.Base` sont verts ; sur `rework` seul, résultats identiques test par test (24 rouges, 12 verts).
  Même temps de test (le contexte est partagé avec `InfoTest.Exo7`). Consigne inchangée, sans mention de `PetControllerTest`.
  **Branche `correction` :** le commit `f0c580e` (`@EnableConfigurationProperties` sur `InfoController`) devient inutile.

### Exo 9 — profils (ancien 30)

- [x] **E9-1** · `application-dev.yml` ; `InfoTest.Exo9` (ancien `Exo30`) avec `@ActiveProfiles("dev")`.
  **FAIT (2026-11-01) :** README : phrase sur les profils, commande du test, lancement `SPRING_PROFILES_ACTIVE=dev ./gradlew
  :exo0:bootRun` + `curl` (vérifié hors dépôt : profil `dev` actif, `appVersion` = `1.0.2-SNAPSHOT`, `appName` conservé).
  `InfoTest.Exo9` vert avec `application-dev.yml` (et `Exo7` reste vert).

### Exo 10 — `maxRange` (ancien 31)

- [x] **E10-1** · `maxRange` est un **paramètre de l'annotation** (comme `@MinMax(0, 10)` dans la slide),
  pas une valeur en dur dans le validateur. `[décidé en principe]` À rattacher au nouveau design de
  l'exo 3 (validation globale sur body). `[à trancher]`
  **FAIT (2026-11-01) :** `maxRange` est un paramètre de `@ValidAgeRange` (défaut `Int.MAX_VALUE`) posé sur `PetSearch`
  (`@ValidAgeRange(maxRange = 100)`), validateur lit la valeur dans `initialize`. Vérifié hors dépôt. L'étudiant
  complète son `ValidAgeRangeValidatorTest` (exo 3c) avec des cas `maxRange` : `ValidAgeRange(maxRange = 5)` est
  instanciable en Kotlin (vérifié).
- [x] **E10-2** · Écrire le test fourni : écart > max → 400. Réécrire l'énoncé (« valeur à 100 »).
  **FAIT (2026-11-01) :** nouvelle classe de test `Exo10` (3 tests : écart = 100 → 200 ; écart 101 → 400 ; écart 101 avec
  `minAge` élevé → 400). Rouge sur `main` (2 tests), vert avec la solution. Énoncé réécrit : plus de « valeur en dur à
  100 dans le validateur », la valeur est dans l'annotation.

### Exo 11 — validateur comme bean (ancien 32) ⭐

- [x] **E11-1** · Bonus. La valeur vient de la config `custom.api.pets.max-range`, injectée par
  constructeur (plus de remarque sur `lateinit`, `T2-28`).
  **FAIT (2026-11-01) :** README : exo 11 en bonus (encadré), trois étapes (clé `custom.api.pets.max-range: 100` dans
  `application.yml`, retirer `maxRange` de l'annotation, `@Value` dans le constructeur du validateur), indice
  d'échappement `\$`. La remarque 2025 sur `lateinit` disparaît (`T2-28`). Vérifié hors dépôt : l'injection par
  constructeur dans un `ConstraintValidator` fonctionne, `Exo3b` et `Exo10` restent verts.
- [x] **E11-2** · Test : `@SpringBootTest(properties = ["custom.api.pets.max-range=5"])`. Correction à
  écrire (aucun commit en 2025).
  **FAIT (2026-11-01) :** test `Exo11` dans `PetControllerTest` avec `@TestPropertySource("custom.api.pets.max-range=5")` (imbriqué
  dans le `@WebMvcTest`, plutôt qu'un `@SpringBootTest` à part) : écart 5 → 200, écart 6 → 400. Rouge sur `main`
  (écart 6), vert avec la solution. La correction est à écrire dans le commit `Exo11` de `correction` (aucun
  commit de 2025).

## Module `exo12` — JPA

### Exo 12 — H2 en mémoire + console (ancien 33)

- [x] **E12-1** · L'étudiant ajoute `starter-data-jpa`, `h2` et le module console H2 de Boot 4. `[spike]`
  (artefact exact de la console).
  **FAIT (2026-11-01) :** le spike donne l'artefact exact : `org.springframework.boot:spring-boot-h2console` (console
  servie sur `/h2-console`, 302 vers la page de connexion, vérifié). L'étudiant ajoute `starter-data-jpa`, `h2` (runtime) et
  `spring-boot-h2console` (runtime) ; `plugin.jpa` viendra à l'exo 14. Coordonnées dans l'énoncé.
- [x] **E12-2** · Config `spring.datasource.*` donnée dans l'énoncé.
  **FAIT (2026-11-01) :** config `spring.datasource.*` + `spring.h2.console.enabled` donnée telle quelle, avec la précision
  « sous la clé `spring` déjà présente » (le `application.yml` d'`exo12` contient déjà `jpa.hibernate.ddl-auto`).
- [x] **E12-3** · Vérification manuelle explicite : URL de la console, requête SQL à lancer, résultat
  attendu. `[décidé]`
  **FAIT (2026-11-01) :** vérification en 4 étapes (log `H2 console available`, connexion avec URL / user / mot de passe,
  `CREATE TABLE` puis `SELECT`, redémarrage et erreur `Table "TEST" not found`). Pas de test automatisé.

### Exo 13 — H2 dans un fichier (ancien 34)

- [x] **E13-1** · Passer à `jdbc:h2:file:./data/testdb`. Vérification manuelle + **test unitaire** qui
  vérifie que `./data/testdb.mv.db` existe après démarrage du contexte. `[décidé]`
  **FAIT (2026-11-01) :** `Exo13Test` (`@SpringBootTest`, 2 tests : l'URL de la datasource commence par `jdbc:h2:file:` ;
  `data/testdb.mv.db` existe après ouverture d'une connexion). Rouge sur `main` et avec l'URL `mem`, vert avec l'URL
  `file` (vérifié hors dépôt ; le fichier est créé dans `exo12/data/`). Vérification manuelle en 3 étapes dans le README
  (`CREATE TABLE` + `INSERT`, redémarrage, `SELECT`), et un WARNING sur le verrou H2 (arrêter l'app avant les tests).
  Le chemin est dans le dossier du module (`exo12/data/`), plus « à la racine du projet ».
- [x] **E13-2** · `data/` dans `.gitignore` (déjà fait).
  **FAIT (2026-11-01) :** `data/` était déjà dans le `.gitignore` (copié du TD1) ; rien à ajouter.

### Exo 14 — Entity + `JpaRepository` (ancien 35)

- [x] **E14-1** · L'étudiant ajoute `kotlin("plugin.jpa")` (G-02) ; entité `HumanEntity`, repository,
  mapping DTO ↔ Entity **écrit par l'étudiant** `[décidé]`. Style d'entité (`class`, `id` nullable) à
  confirmer. `[à trancher]`
  **FAIT (2026-11-01) :** README : ajout du plugin `kotlin("plugin.jpa")` (avec la raison), entité + repository + mapping écrits par
  l'étudiant, indice repliable : `class` avec `var` plutôt que `data class`, id `Int?` avec `@Id @GeneratedValue`. Style
  d'entité `class` + id nullable : ma décision par défaut (cohérente avec `C2-35`), à valider. Solution vérifiée hors dépôt
  (`Exo14` vert, rouge sur `main`).
- [x] **E14-2** · Corriger `HumanController` : `@GetMapping("/api/v1/humans/{humanId}")` (`T2-04`).
  **FAIT (2026-11-01) :** `@GetMapping("/api/v1/humans/{humanId}")` ; le test `unknown human` vérifie le 404 (jamais 500). Les
  `@RequestParam minAge/maxAge` inutilisés de `getHumans` sont retirés.
- [x] **E14-3** · `findAllHumans` partout (`T2-06`) ; renommer `PetControllerTest` → `HumanControllerTest` ;
  isoler les tests (`deleteAll()` ou `@Transactional`, `T2-07`).
  **FAIT (2026-11-01) :** `findAllHuman` → `findAllHumans` (`DatabaseProxy` et controller) ; `PetControllerTest` → `HumanControllerTest`
  (`git mv`) avec une classe imbriquée par exo (`Exo14`, `Exo15`, `Exo17`, `Exo19`) ; tests indépendants de l'ordre et des
  données déjà en base (id lu dans la réponse du POST, lecture par id, `hasItem` sur la liste) donc sans `@Transactional`
  (qui demanderait `spring-tx` dans le squelette) ni nettoyage ; tests `exo 41` / `exo 44` (TD3) supprimés ; imports morts retirés.
  Le 404 de `unknown human` et la structure des tests `Exo15`/`Exo17` sont aussi en place pour les exos suivants.

### Exo 15 — OneToOne unidirectionnel (ancien 36)

- [x] **E15-1** · `ContactEntity`, `@OneToOne`. Test `exo 15`.
  **FAIT (2026-11-01) :** README : `HumanDto` + `contact`, `ContactEntity`, `@OneToOne` unidirectionnel, conversions à adapter, indice
  (slides « One-To-One unidirectionnel », `cascade`). Tests `Exo15` (2 : création avec contact, lecture du contact par id) rouges
  sur `main`, verts avec la solution (`@OneToOne(cascade = ALL) @JoinColumn("contact_id")`, vérifié hors dépôt ; `Exo14` reste vert).

### Exo 16 — OneToOne bidirectionnel (ancien 37)

- [x] **E16-1** · Passer en bidirectionnel (`mappedBy`).
  **FAIT (2026-11-01) :** README exo 16 : explication propriétaire / inverse (`mappedBy`), indice (slides « One-To-One
  bi-directionnel », `human` nullable côté `ContactEntity`). Vérifié hors dépôt.
- [x] **E16-2** · Le test `exo 15` passe aussi en unidirectionnel (`T2-14`) : la bidirectionnalité est
  prouvée par le test de l'étape suivante.
  **FAIT (2026-11-01) :** NOTE dans l'énoncé : `Exo15` passe aussi en unidirectionnel, c'est le test de l'exo 16b qui prouve
  la relation.
- [x] **E16-3** · **Nouvel exo : `@DataJpaTest` écrit par l'étudiant** `[décidé : exo distinct]` ;
  l'étudiant ajoute `starter-data-jpa-test` (`C2-57`).
  **FAIT (2026-11-01) :** exo **16b** : l'étudiant ajoute `starter-data-jpa-test` et écrit `HumanRepositoryTest` (`@DataJpaTest`,
  étapes données : sauver, `flush` + `clear`, relire le contact, vérifier `contact.human`). Vérifié hors dépôt : vert avec
  `@OneToOne(mappedBy = "contact")`, rouge sans. **Packages Boot 4** donnés dans une NOTE (`DataJpaTest` dans
  `org.springframework.boot.data.jpa.test.autoconfigure`, `TestEntityManager` dans `org.springframework.boot.jpa.test.autoconfigure`),
  c'est le point `C2-57`. Pas de test fourni (l'étudiant l'écrit).

### Exo 17 — OneToMany unidirectionnel (ancien 38)

- [x] **E17-1** · `PetEntity`, `@OneToMany`, ajout de `pets` à `HumanDto`. Test `exo 17`.
  **FAIT (2026-11-01) :** README : `HumanDto` + `pets`, `PetEntity`, `@OneToMany` unidirectionnel, conversions, indice (slides, `var MutableList`
  = `C2-28`, `@JoinColumn(name = "human_id")` vs table de jointure, `@Enumerated(STRING)`). Tests `Exo17` (2) rouges sur `main`.
  Vérifié hors dépôt : tout `exo12` vert (Exo13, 14, 15, 17, `HumanRepositoryTest`) avec un `@OneToMany` LAZY par défaut, grâce à
  l'open-in-view de Spring Boot (pas d'EAGER nécessaire, contrairement à 2025).

### Exo 18 — OneToMany bidirectionnel (ancien 39)

- [x] **E18-1** · Bidirectionnel (`@ManyToOne` + `mappedBy`) ; collections `var MutableList`.
  **FAIT (2026-11-01) :** README : `pet.human` en plus de `human.pets`, rappel propriétaire / inverse (le `@ManyToOne` est toujours
  propriétaire), indice (slides « Many-To-One » / « One-To-Many », ne pas oublier de renseigner `human` dans la conversion).
  Vérifié hors dépôt : solution `@OneToMany(mappedBy = "human")` + `@ManyToOne @JoinColumn("human_id")` → tout `exo12` vert ; en
  oubliant `pet.human` dans la conversion, `Exo17.read the pets` et `Exo18Test` deviennent rouges.
- [x] **E18-2** · Idem `E16-2` : le test `exo 17` ne prouve pas la bidirectionnalité.
  **FAIT (2026-11-01) :** pour prouver la bidirectionnalité sans demander d'écrire un 2e test (décision : seulement 2 tests écrits
  par les étudiants), un test **fourni mais commenté** `Exo18Test` (`@DataJpaTest` + `@Import(DatabaseProxy)`, passe par
  `DatabaseProxy` donc ne dépend pas du constructeur des entités de l'étudiant) à décommenter, même mécanique que le test de
  l'exo 19. Conséquence : les entités et le repository doivent être dans `iut.nantes.exo12.repository` (précisé dans l'exo 14).

### Exo 19 — DELETE d'un pet (ancien 40) ⭐ ?

- [x] **E19-1** · `orphanRemoval = true` : **slide à ajouter au cours** (`C2-64`, déjà dans `Cours 2.md`) ;
  l'énoncé y renvoie. `[décidé]`
  **FAIT (2026-11-01) :** l'énoncé renvoie à « la slide `orphanRemoval` » (`C2-64`, **à écrire dans le cours** : la slide n'existe
  pas encore dans `jpa2.md`) ; indice : charger le human, retirer le pet de `pets`, enregistrer.
- [x] **E19-2** · Garder le test commenté (décommenter après l'ajout de `pets` à `HumanDto`) ; corriger la
  phrase et l'indiquer dans l'énoncé (`T2-15`).
  **FAIT (2026-11-01) :** test `Exo19.delete a pet` conservé commenté ; commentaire et énoncé alignés (« ne compile pas avant
  l'ajout de `contact` et `pets` à `HumanDto` », consigne `Ctrl+/`) ; import `delete` ajouté pour que le test compile une fois
  décommenté (`T2-15`). Vérifié hors dépôt : rouge sans `orphanRemoval`, vert avec.
- [x] **E19-3** · `[spike]` : le DELETE passe-t-il sans `@Transactional` (c3) ?
  **FAIT (2026-11-01) :** pas besoin de `@Transactional` : avec l'open-in-view de Spring Boot, la suppression passe (vérifié :
  `orphanRemoval = true` + `save(human)` suffisent). `@Transactional` reste un sujet de c3.
- [x] **E19-4** · Socle ou bonus : `[à trancher]` (voir G-09).
  **FAIT (2026-11-01) :** marqué **bonus** dans le README (encadré), en attendant la décision finale `T2-38` / G-09.

### Bonus

> Depuis E4-8 : Bonus 1 = `ErrorResponseException` (module `exo0`, voir E4-7), Bonus 2 = requêtes dérivées (EB-2),
> Bonus 3 = profil Postgres (EB-1).

- [x] **EB-1** · Profil Postgres : `application-postgres.yml`, commande `podman run` **et** alternative
  `docker compose`, image figée (`postgres:18`) ; procédure testée (`T2-29`).
  **FAIT (2026-11-01) :** « Exo Bonus 2 - Profil Postgres ». **Procédure testée** (docker `postgres:18` : l'application démarre avec le
  profil `postgres` sur `jdbc:postgresql://localhost:5432/postgres`, un human est créé et relu, `\dt` liste `contact_entity`,
  `human_entity`, `pet_entity` ; `docker compose up -d` testé ; `podman run` testé avec l'image complète `docker.io/library/postgres:18`,
  sans quoi podman demande de choisir un registre). Driver à ajouter par l'étudiant (`runtimeOnly("org.postgresql:postgresql")`).
  Le `-v /tmp/data` de 2025 (volume anonyme sans effet) est retiré. Conteneurs de test supprimés après vérification.
- [x] **EB-2** · Requêtes dérivées (`findByNameContainingIgnoreCase`, `findByPetsKind`) avec un test
  fourni (`T2-31`). `[à trancher]`
  **FAIT (2026-11-01) :** « Exo Bonus 1 - Requêtes dérivées » (ma décision par défaut, à valider) : `GET /api/v1/humans?name=` et
  `?petKind=`, indice repliable (`findByNameContainingIgnoreCase`, `findByPetsKind`), classe de test fournie `ExoBonusQueries` (2 tests,
  rouges sur `main`, verts avec la solution vérifiée hors dépôt). Les tests créent des humans à noms uniques et n'attendent pas
  un contenu exact (pas de nettoyage nécessaire).

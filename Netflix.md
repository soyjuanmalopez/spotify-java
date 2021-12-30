# Netflix:

- **Reference documentation:**
    - [Spring Boot](#spring-boot)
    - [Hibernate](#hibernate)
    - [Best Practices Restful](#best-practices-restful)
    - [Others](#others)
- **Project example:**
    - [Spotify](#spotify)
        - [Java 11](#java-11)
        - [FlyWay](#flyway)
        - [MapStruct](#mapstruct)
    - [Archetype](#archetype)
    - [Data base](#data-base)
    - [Endpoints](#endpoints)

# Reference documentation

## Spring Boot

## Hibernate

## Best Practices Restful

## Others

# Project example

## Spotify

### Java 11

### FlyWay

### MapStruct

## Archetype

## Data base

For this project, we have chosen to use a database in MySQL. To start with the database you need to have MySQL
installed, and you can access the explanation on
this [URL](https://gitlab.com/bootcamp-2.0/welcome-pack/-/blob/development/WelcomePack.md#sql-client)

At first, after the installation, you need to create a scheme:

![spotify1](media/DataBase/database1.png)

And set the name. On this case the name it is "Spotify"

![spotify2](media/DataBase/database2.png)

When you have created the schema, the next step it is starts to create the different tables.

Album:

```
CREATE TABLE `spotify`.`album` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(250) NULL,
  `duration` DOUBLE NULL,
  `year_release` INT NULL,
  PRIMARY KEY (`id`));
```

Song:

```
CREATE TABLE `spotify`.`song` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(250) NULL,
  `duration` DOUBLE NULL,
  `reproductions` INT NULL,
  `album_ref` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `album_fk_idx` (`album_ref` ASC) VISIBLE,
  CONSTRAINT `album_fk`
    FOREIGN KEY (`album_ref`)
    REFERENCES `spotify`.`album` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE);
```

Artist:

```
CREATE TABLE `spotify`.`artist` (
`id` INT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(250) NULL,
`description` VARCHAR(800) NULL,
PRIMARY KEY (`id`));
```

Genre:

```
CREATE TABLE `spotify`.`genre` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(90) NULL,
  PRIMARY KEY (`id`));
```

And after you have create the different tables in order, the next step it is create the diferent relations many to many or one to many.


Relation genre-song

```
CREATE TABLE `spotify`.`rel_genre_songs` (
  `id_genre` INT NOT NULL,
  `id_song` INT NOT NULL,
  PRIMARY KEY (`id_genre`, `id_song`),
  INDEX `fk_rel_song_idx` (`id_song` ASC) VISIBLE,
  CONSTRAINT `fk_rel_genre`
    FOREIGN KEY (`id_genre`)
    REFERENCES `spotify`.`genre` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_rel_song`
    FOREIGN KEY (`id_song`)
    REFERENCES `spotify`.`song` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);
```

Relation album-artist

```
CREATE TABLE `spotify`.`rel_album_artist` (
  `id_album` INT NOT NULL,
  `id_artist` INT NOT NULL,
  PRIMARY KEY (`id_album`, `id_artist`),
  INDEX `fk_rel_albumartist_artist_idx` (`id_artist` ASC) VISIBLE,
  CONSTRAINT `fk_rel_albumartist_album`
    FOREIGN KEY (`id_album`)
    REFERENCES `spotify`.`album` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_rel_albumartist_artist`
    FOREIGN KEY (`id_artist`)
    REFERENCES `spotify`.`artist` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);
```

Relation album-song

```
CREATE TABLE `spotify`.`rel_album_song` (
  `id_album` INT NOT NULL,
  `id_song` INT NOT NULL,
  PRIMARY KEY (`id_album`, `id_song`),
  INDEX `fk_rel_albumsong_song_idx` (`id_song` ASC) VISIBLE,
  CONSTRAINT `fk_rel_albumsong_album`
    FOREIGN KEY (`id_album`)
    REFERENCES `spotify`.`album` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_rel_albumsong_song`
    FOREIGN KEY (`id_song`)
    REFERENCES `spotify`.`song` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);
```

Relation song_artist

```
CREATE TABLE `spotify`.`rel_song_artist` (
  `id_song` INT NOT NULL,
  `id_artist` INT NOT NULL,
  PRIMARY KEY (`id_song`, `id_artist`),
  INDEX `fk_rel_songartist_artist_idx` (`id_artist` ASC) VISIBLE,
  CONSTRAINT `fk_rel_songartist_song`
    FOREIGN KEY (`id_song`)
    REFERENCES `spotify`.`song` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_rel_songartist_artist`
    FOREIGN KEY (`id_artist`)
    REFERENCES `spotify`.`artist` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);
```

After creating all tables, you should see something like this:

![spotify2](media/DataBase/database3.png)

## Endpoints


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

When starting a project first you have to build the structure: the archetype.
As we know from the description, in Spotify you have different objects that interact between them: songs, albums, artists, genres and awards.

That means that we need an entity for each one of them. There we are going to define it's attributes, getters and setters.

--> imagen entity definida (explicación)

Not only that, we also need other classes such as a repository in order to be able to communicate with the tables defined in our DB, a rest structure for the respose, controller and service (and it's implementation) to define the methods so then the user can interact with our programm. One of the previous one for each object that participates in the programm, as a general rule.

--> imagenes de definición + explicación

There are another important classes that are involved when coding: configuration, exceptions, responses, utils... These are not related with each object but with the general functionality of the code. Here you have some examples:

--> ejemplos de las clases con explicación de que es (no todas)

Remember that when programming is not just coding. We need some testing in each controller/service to verify that everything works how it's supposed to do and that's why we need testing classes:

--> imagen de un test (explicación)

As you know, you can use our archetype builder generator that's helps with the structure. In this [URL](https://gitlab.com/bootcamp-2.0/archetype/-/blob/main/Archetype.md) you can access to some info of how you can use it.

## Data base



## Endpoints


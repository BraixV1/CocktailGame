-- tables
-- Table: COCKTAIL
-- DROP TABLE IF EXISTS GAME_COCKTAIL;
-- DROP TABLE IF EXISTS COCKTAIL;
-- DROP TABLE IF EXISTS HINT;
-- DROP TABLE IF EXISTS APP_USER;
-- DROP TABLE IF EXISTS GAME;

DROP ALL OBJECTS;


CREATE TABLE COCKTAIL (
                          ID integer NOT NULL CONSTRAINT COCKTAIL_pk PRIMARY KEY,
                          strDrink varchar(255) NOT NULL,
                          strInstructions varchar(255) NOT NULL,
                          strDrinkThumb varchar(255),
                          strIngredient1 varchar(255),
                          strIngredient2 varchar(255),
                          strIngredient3 varchar(255),
                          strIngredient4 varchar(255),
                          strIngredient5 varchar(255),
                          strIngredient6 varchar(255),
                          strIngredient7 varchar(255),
                          strIngredient8 varchar(255),
                          strIngredient9 varchar(255),
                          strIngredient10 varchar(255),
                          strIngredient11 varchar(255),
                          strIngredient12 varchar(255),
                          strIngredient13 varchar(255),
                          strIngredient14 varchar(255),
                          strIngredient15 varchar(255)
);

-- Table: USER

CREATE TABLE APP_USER (
                      ID integer NOT NULL CONSTRAINT USER_pk PRIMARY KEY,
                      name varchar(255) NOT NULL
);

-- Table: GAME

CREATE TABLE GAME (
                      ID integer NOT NULL CONSTRAINT GAME_pk PRIMARY KEY,
                      createdAtDt date NOT NULL,
                      lastPlayedDt date NOT NULL,
                      title varchar(255) NOT NULL,
                      triesLeft integer NOT NULL,
                      score integer NOT NULL,
                      revealedName varchar(255) NOT NULL,
                      USER_ID integer NOT NULL,
                      CONSTRAINT GAME_USER FOREIGN KEY (USER_ID)
                          REFERENCES APP_USER (ID)
);

-- Table: GAME_COCKTAIL

CREATE TABLE GAME_COCKTAIL (
                               ID integer NOT NULL CONSTRAINT GAME_COCKTAIL_pk PRIMARY KEY,
                               COCKTAIL_ID integer NOT NULL,
                               GAME_ID integer NOT NULL,
                               CONSTRAINT GAME_COCKTAIL_COCKTAIL FOREIGN KEY (COCKTAIL_ID)
                                   REFERENCES COCKTAIL (ID),
                               CONSTRAINT GAME_COCKTAIL_GAME FOREIGN KEY (GAME_ID)
                                   REFERENCES GAME (ID)
);

-- Table: HINT

CREATE TABLE HINT (
                      ID integer NOT NULL CONSTRAINT HINT_pk PRIMARY KEY,
                      showStrDrinkThumb boolean,
                      showStrIngredient1 boolean,
                      showStrIngredient2 boolean,
                      showStrIngredient3 boolean,
                      showStrIngredient4 boolean,
                      showStrIngredient5 boolean,
                      showStrIngredient6 boolean,
                      showStrIngredient7 boolean,
                      showStrIngredient8 boolean,
                      showStrIngredient9 boolean,
                      showStrIngredient10 boolean,
                      showStrIngredient11 boolean,
                      showStrIngredient12 boolean,
                      showStrIngredient13 boolean,
                      showStrIngredient14 boolean,
                      showStrIngredient15 boolean,
                      GAME_ID integer NOT NULL,
                      CONSTRAINT HINT_GAME FOREIGN KEY (GAME_ID)
                          REFERENCES GAME (ID)
);

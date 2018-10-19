# DROP TABLE IF EXISTS person;
#create DATABASE familyTree;
CREATE TABLE person (
  id                LONG         not null        auto_increment primary key,
  first_name        VARCHAR(255) NOT NULL,
  last_name         VARCHAR(255),
  last_name_on_born VARCHAR(255),
  born_date         DATE,
  death_date        DATE,
  is_alive          BOOL         NOT NULL        DEFAULT true,
  photo_path        VARCHAR(255)
);

-- auto-generated definition
create table person
(
  id                 int auto_increment,
  first_name         varchar(255)           not null,
  last_name          varchar(255)           null,
  last_name_on_birth varchar(255)           null,
  in_alive           tinyint(1) default '1' null,
  birth_date         date                   null,
  death_date         date                   null,
  photo              varchar(255)           null,
  gender             int                    null,
  constraint person_id_uindex
  unique (id)
);

alter table person
  add primary key (id);


CREATE TABLE account (
  FIRST_NAME VARCHAR(255) NOT NULL,
  PASSWORD   VARCHAR(255) NOT NULL,
  ID         SERIAL,
  ENABLED    BOOL DEFAULT true
);
-- JOURNAL TABLE: ENTRY
DROP TABLE IF EXISTS entry;
CREATE TABLE entry (
  ID      BIGINT(20) NOT NULL AUTO_INCREMENT,
  CREATED DATETIME            DEFAULT NULL,
  SUMMARY VARCHAR(255)        DEFAULT NULL,
  TITLE   VARCHAR(255)        DEFAULT NULL,
  PRIMARY KEY (ID)
);
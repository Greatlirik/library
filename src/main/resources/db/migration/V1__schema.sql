CREATE TABLE author (
    id SERIAL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_author_id PRIMARY KEY (id)
);

CREATE TABLE book (
    id SERIAL,
    title VARCHAR(255) NOT NULL,
    genre VARCHAR (255) NOT NULL,
    year SMALLINT NOT NULL,
    quantity SMALLINT NOT NULL,
    CONSTRAINT pk_book_id PRIMARY KEY (id)
);

/*
 TODO add one more table for many to many mapping 'author_book'

 */



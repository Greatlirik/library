CREATE TABLE author (
    id SERIAL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_author_id PRIMARY KEY (id)
);

CREATE TABLE account (
    id SERIAL,
    name VARCHAR(255) NOT NULL,
    password VARCHAR (255) NOT NULL,
    active BOOLEAN NOT NULL,
    CONSTRAINT pk_account_id PRIMARY KEY (id)
);

CREATE TABLE book (
    id SERIAL,
    title VARCHAR(255) NOT NULL,
    genre VARCHAR (255) NOT NULL,
    year SMALLINT NOT NULL,
    quantity SMALLINT NOT NULL,
    free BOOLEAN NOT NULL,
    CONSTRAINT pk_book_id PRIMARY KEY (id)
);

CREATE TABLE author_book (
    id SERIAL,
    author_id INTEGER NOT NULL,
    book_id INTEGER NOT NULL,
    CONSTRAINT pk_author_book_id PRIMARY KEY (id),
    CONSTRAINT fk_author_book_author_id FOREIGN KEY (author_id) REFERENCES author (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_author_book_book_id FOREIGN KEY (book_id) REFERENCES book (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE role (
    id SERIAL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_role_id PRIMARY KEY (id),
    CONSTRAINT uk_role_name UNIQUE (name)
);

CREATE TABLE account_role (
    id SERIAL,
    account_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
    CONSTRAINT pk_account_role_id PRIMARY KEY (id),
    CONSTRAINT fk_account_role_account_id FOREIGN KEY (account_id) REFERENCES account (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_account_role_role_id FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE account_book (
    id SERIAL,
    account_id INTEGER NOT NULL,
    book_id INTEGER NOT NULL,
    CONSTRAINT pk_account_book_id PRIMARY KEY (id),
    CONSTRAINT fk_account_book_account_id FOREIGN KEY (account_id) REFERENCES account (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_account_book_book_id FOREIGN KEY (book_id) REFERENCES book (id) ON DELETE CASCADE ON UPDATE CASCADE
);


# System Library
Web application development (project library) :
- backend on Java (Spring Boot, Maven + Lombok)
- database container Docker
- RDBMS PostgreSQL
- Flyway for database-migration
- web template system Mustache
- markup language HTML, CSS + using Bootstrap
- tests Junit

## Functionality
The reader has the ability to search and order Books in the Catalog. The Librarian issues a Book to the Reader for a subscription or to the reading room. 
The book can be present in the Library in one or more copies.

## Components


#### Web security
Implemented through Spring Security with the addition of special functionality for the administrator, the addition of a password encoder, and data entry into the database.


#### Controllers
Added controllers and repositories for entities "book", "account", "author" and logic between them.


#### Database
Created a table in the database for each entity, migrations are carried out through flyway

#### Templates
Created htmls-templates with web template system Mustache for primitive logic between entities

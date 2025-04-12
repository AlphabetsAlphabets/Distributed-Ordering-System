# DCOMS assignment

# Pre-requisites

1. Install Apache Maven.
2. Install Postgresql.
3. Auto create required tables.

# Configuring Maven

Install all dependecies:

```
mvn clean install
```

# Configuring postgressql

1. Make sure that the postgres server is running.
2. Create a location for the database to be stored in.
3. Create a user account for the postgres server.
4. Give the newly created user account `CREATEDB` access.

Make sure to use the `postgres` user to start the `pgql` command.

```
sudo -u postgres psql
```

Then items 3 and 4 can be done with these two commands:

```
CREATE DATABASE mydb;
CREATE ROLE star WITH LOGIN PASSWORD 'password';
ALTER ROLE star CREATEDB;
```

3. Create a database named `database`. The application expects that database to exist.

```
createdb database
```

# Auto create required tables

Please run the code in `CreateDB.java` before you start the demo using `App.java`. Otherwise, the program will fail as the expected tables are unavailable.

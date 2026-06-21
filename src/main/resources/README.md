# Getting Started with the Database

### First Steps
- Install Postgres(version ???). Here's a handy guide on [installing postgres using brew](https://github.com/CUNYTechPrep/ctp2018/blob/master/guides/installing-postgresql.md)
- Start postgres service by running `brew services start postgresql` (On Mac fyi; Brew's alternative on Windows is Chocolatey)
- open a postgres shell by typing `psql postgres`
- once open we want to create a new database called "sneaky" run: `create database sneaky;`
- run this java app (instructions in base README.md)
- type `\c sneaky` in the psql shell to connect to the sneaky database that you just created
- if you now type `\dt`, you should be able to see your database's tables
- now you can just do basic sql to inspect the tables in your db :)
- this is a cool link with a [sql cheatsheet](https://data36.com/wp-content/uploads/2018/12/sql-cheat-sheet-for-data-scientists-by-tomi-mester.pdf)

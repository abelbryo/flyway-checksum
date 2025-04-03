### What is this?
A simple tool to print out the checksum of a SQL migration file
### Usage

```bash
$ flyway-checksum V001__create_users_table.sql
Checksum of V001__create_users_table.sql is 683653209
```

### WHY
If you managing your SQL migration with flyway, sometimes you would run into validation errors where the checksum of the local file does not match the checksum in the database. This tool is a simple way to check the checksum of a SQL migration file.

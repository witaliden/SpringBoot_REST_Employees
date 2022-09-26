CREATE TABLE IF NOT EXISTS employees_fw (
    id serial PRIMARY KEY,
    name varchar(60) UNIQUE NOT NULL
)
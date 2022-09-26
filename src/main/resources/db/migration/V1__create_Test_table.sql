CREATE TABLE IF NOT EXISTS test_table (
    id serial PRIMARY KEY,
    name varchar(60) UNIQUE NOT NULL
)
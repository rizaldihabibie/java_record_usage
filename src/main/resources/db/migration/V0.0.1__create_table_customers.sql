CREATE TABLE customers (
  id BIGSERIAL NOT NULL PRIMARY KEY,
  name varchar(255) NOT NULL,
  address varchar(255) NOT NULL,
  phone_number varchar(30) NOT NULL,
  birth_date date DEFAULT NULL
)
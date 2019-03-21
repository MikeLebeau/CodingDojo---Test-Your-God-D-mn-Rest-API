CREATE TABLE my_user (
	id 			SERIAL PRIMARY KEY,
	firstName 	varchar(30),
	lastName	varchar(30),
	age			int8,
	active		bool
);

INSERT INTO my_user (firstName, lastName, age, active) VALUES
('Ada',       'Lovelace', 36, true),
('Rich',      'Hickey',   42, true),
('Martin',    'Fowler',   56, true),
('Alistair',  'Cockburn', 65, true),
('James',     'Gosling',  63, true);
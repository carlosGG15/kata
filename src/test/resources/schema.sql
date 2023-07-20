-- DROP DATABASE IF EXISTS kata;

CREATE TABLE channels(
	id int IDENTITY(1,1) PRIMARY KEY,
	name varchar(255) NOT NULL,
);

CREATE TABLE order_priorities(
	id int IDENTITY(1,1) PRIMARY KEY,
	name varchar(255) NOT NULL,
);

CREATE TABLE order_types(
	id int IDENTITY(1,1) PRIMARY KEY,
	name varchar(255) NOT NULL,
);

CREATE TABLE regions(
	id int IDENTITY(1,1) PRIMARY KEY,
	name varchar(255) NOT NULL,
);

CREATE TABLE countries(
	id int IDENTITY(1,1) PRIMARY KEY,
	name varchar(255) NOT NULL,
	region_id int FOREIGN KEY REFERENCES regions(id),
);

CREATE TABLE orders(
	id int IDENTITY(1,1) PRIMARY KEY,
	order_id int NOT NULL,
	country_id int FOREIGN KEY REFERENCES countries(id),
	units_sold int,
	unit_price FLOAT,
	unit_cost FLOAT,
	total_revenue FLOAT,
	total_cost FLOAT,
	total_profit FLOAT
);
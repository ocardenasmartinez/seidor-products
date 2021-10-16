# Products Service

## Technology
- Java 11
- Maven 3
- Spring Boot
- Postgres


## Install database
Run postgres and create table

```bash
CREATE TABLE public.products (
id serial NOT NULL,
sku varchar NOT NULL,
"name" varchar NOT NULL,
brand varchar NOT NULL,
"size" varchar NOT NULL,
price float8 NOT NULL,
image varchar NOT NULL,
CONSTRAINT products_pkey PRIMARY KEY (id)
);
  ```

## Build and execution
In the console

```bash
1.- git clone https://github.com/ocardenasmartinez/seidor-products.git
2.- cd seidor-products
3.- mvn package
4.- java -jar target/seidor-products-1.0.jar
```

## Brief explanation about architectura

Three-layer architecture, controller layer for endpoints, business layer for handle business
logic and data layer for handle data
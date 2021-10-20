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
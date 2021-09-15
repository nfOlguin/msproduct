DROP TABLE public.product CASCADE;
DROP TABLE public.productoptionalimage CASCADE;



CREATE TABLE public.product (
	id int8 NOT NULL,
	sku varchar NOT NULL,
	name varchar(50) NOT NULL,
	brand varchar(50) NOT NULL,
	"size" NULL,
	price int8 NOT NULL,
	imageurl varchar NOT NULL,

	createdDate timestamp NOT NULL DEFAULT now(),
	updatedDate timestamp NULL,
	active boolean NOT NULL,

	CONSTRAINT id_pk PRIMARY KEY (id),
	CONSTRAINT sku_unique UNIQUE (sku)
);
CREATE INDEX sku_index ON public.product USING btree (sku);

CREATE TABLE public.productoptionalimage (
	id int8 NOT NULL,
	idproduct int8 NOT NULL,
	url varchar NOT NULL,

	createdDate timestamp NOT NULL DEFAULT now(),
    updatedDate timestamp NULL,
    active boolean NOT NULL,

	CONSTRAINT optionalimage_pkey PRIMARY KEY (id),
	CONSTRAINT optionalimage_idproduct_fk FOREIGN KEY (idproduct) REFERENCES product(id)
);
CREATE INDEX idproduct_index ON public.productoptionalimage USING btree (idproduct);

CREATE TABLE address (
    id_address NUMBER(9) NOT NULL,
    "Number"   VARCHAR2(5) NOT NULL,
    street     VARCHAR2(20),
    city       VARCHAR2(20) NOT NULL,
    province   VARCHAR2(20) NOT NULL,
    postalcode VARCHAR2(6) NOT NULL,
    id_city    NUMBER(9) NOT NULL,
    id_user    NUMBER(9) NOT NULL,
    id_country NUMBER(9) NOT NULL
);

ALTER TABLE address ADD CONSTRAINT address_pk PRIMARY KEY ( id_address );

CREATE TABLE adminrank (
    id_rank  NUMBER(9) NOT NULL,
    rankname VARCHAR2(15) NOT NULL
);

ALTER TABLE adminrank ADD CONSTRAINT adminrank_pk PRIMARY KEY ( id_rank );

CREATE TABLE attribute (
    name_attribute VARCHAR2(20) NOT NULL,
    value          VARCHAR2(20) NOT NULL,
    id_model       NUMBER(9) NOT NULL
);

CREATE TABLE corporatecustomer (
    id_user      NUMBER(9) NOT NULL,
    nip          NUMBER(10) NOT NULL,
    regon        NUMBER(9) NOT NULL,
    tin          VARCHAR2(9) NOT NULL,
    company_name VARCHAR2(30) NOT NULL
);

ALTER TABLE corporatecustomer ADD CONSTRAINT corporatecustomer_pk PRIMARY KEY ( id_user );

CREATE TABLE country (
    id_country   NUMBER(9) NOT NULL,
    country_name VARCHAR2(20) NOT NULL,
    country_code VARCHAR2(5) NOT NULL
);

ALTER TABLE country ADD CONSTRAINT country_pk PRIMARY KEY ( id_country );

ALTER TABLE country ADD CONSTRAINT country_country_name_un UNIQUE ( country_name );

ALTER TABLE country ADD CONSTRAINT country_country_code_un UNIQUE ( country_code );

CREATE TABLE deliverymethod (
    id_delivery   NUMBER(9) NOT NULL,
    delivery_name VARCHAR2(30) NOT NULL
);

ALTER TABLE deliverymethod ADD CONSTRAINT deliverymethod_pk PRIMARY KEY ( id_delivery );

ALTER TABLE deliverymethod ADD CONSTRAINT delivery_name_un UNIQUE ( delivery_name );

CREATE TABLE model (
    id_model   NUMBER(9) NOT NULL,
    name_model VARCHAR2(20),
    id_product NUMBER(9) NOT NULL,
    price      NUMBER(5, 2) NOT NULL,
    quantity   NUMBER(9) NOT NULL
);

ALTER TABLE model ADD CONSTRAINT model_pk PRIMARY KEY ( id_model );

ALTER TABLE model ADD CONSTRAINT name_model_un UNIQUE ( name_model );

CREATE TABLE orderdetail (
    id_order         NUMBER(9) NOT NULL,
    id_model         NUMBER(9) NOT NULL,
    quantity         NUMBER(5) NOT NULL,
    discountamout    NUMBER(5),
    transationprice  NUMBER(5) NOT NULL,
    deliverydiscount NUMBER(5) NOT NULL
);

ALTER TABLE orderdetail ADD CONSTRAINT orderdetail_pk PRIMARY KEY ( id_order,
                                                                    id_model );

CREATE TABLE orderheader (
    id_order     NUMBER(9) NOT NULL,
    orderdate    DATE NOT NULL,
    deliverycost NUMBER(5, 2) NOT NULL,
    id_user      NUMBER(9) NOT NULL,
    id_delivery  NUMBER(9) NOT NULL,
    id_payment   NUMBER(9) NOT NULL,
    id_status    NUMBER(9) NOT NULL,
    id_address   NUMBER(9) NOT NULL
);

ALTER TABLE orderheader ADD CONSTRAINT orderheader_pk PRIMARY KEY ( id_order );

CREATE TABLE orderstatus (
    id_status   NUMBER(9) NOT NULL,
    name_status VARCHAR2(40) NOT NULL
);

ALTER TABLE orderstatus ADD CONSTRAINT orderstatus_pk PRIMARY KEY ( id_status );

ALTER TABLE orderstatus ADD CONSTRAINT orderstatus_name_status_un UNIQUE ( name_status );

CREATE TABLE paymentmethod (
    id_payment     NUMBER(9) NOT NULL,
    payment_method VARCHAR2(30) NOT NULL
);

ALTER TABLE paymentmethod ADD CONSTRAINT paymentmethod_pk PRIMARY KEY ( id_payment );

ALTER TABLE paymentmethod ADD CONSTRAINT payment_method_un UNIQUE ( payment_method );

CREATE TABLE permissions (
    id_rank        NUMBER(9) NOT NULL,
    orderservice   CHAR(1) NOT NULL,
    creatinguser   CHAR(1) NOT NULL,
    deletinguser   CHAR(1) NOT NULL,
    databaseaccess CHAR(1) NOT NULL
);

ALTER TABLE permissions ADD CONSTRAINT permissions_pk PRIMARY KEY ( id_rank );

CREATE TABLE product (
    id_product            NUMBER(9) NOT NULL,
    name                  VARCHAR2(20) NOT NULL,
    description           VARCHAR2(500) NOT NULL,
    price                 NUMBER(5, 2) NOT NULL,
    productsubcategorykey NUMBER(9) NOT NULL,
    quantity              NUMBER(5) NOT NULL
);

ALTER TABLE product ADD CONSTRAINT product_pk PRIMARY KEY ( id_product );

CREATE TABLE productcategory (
    productcategorykey  NUMBER(9) NOT NULL,
    productcategoryname VARCHAR2(20) NOT NULL
);

ALTER TABLE productcategory ADD CONSTRAINT productcategory_pk PRIMARY KEY ( productcategorykey );

ALTER TABLE productcategory ADD CONSTRAINT productcategoryname_un UNIQUE ( productcategoryname );

CREATE TABLE productsubcategory (
    productsubcategorykey  NUMBER(9) NOT NULL,
    productsubcategoryname VARCHAR2(20) NOT NULL,
    productcategorykey     NUMBER(9) NOT NULL
);

ALTER TABLE productsubcategory ADD CONSTRAINT productsubcategory_pk PRIMARY KEY ( productsubcategorykey );

ALTER TABLE productsubcategory ADD CONSTRAINT productsubcategoryname_un UNIQUE ( productsubcategoryname );

CREATE TABLE retailcustomer (
    id_user   NUMBER(9) NOT NULL,
    firstname VARCHAR2(20) NOT NULL,
    lastname  VARCHAR2(20) NOT NULL
);

ALTER TABLE retailcustomer ADD CONSTRAINT retailcustomer_pk PRIMARY KEY ( id_user );

CREATE TABLE shoppingcart (
    id_user  NUMBER(9) NOT NULL,
    id_model NUMBER(9) NOT NULL,
    quantity NUMBER(9) NOT NULL
);

ALTER TABLE shoppingcart ADD CONSTRAINT shoppingcart_pk PRIMARY KEY ( id_user,
                                                                      id_model );

CREATE TABLE "User" (
    id_user  NUMBER(9) NOT NULL,
    login    VARCHAR2(20) NOT NULL,
    password VARCHAR2(20) NOT NULL,
    email    VARCHAR2(20) NOT NULL
);

ALTER TABLE "User" ADD CONSTRAINT user_pk PRIMARY KEY ( id_user );

ALTER TABLE "User" ADD CONSTRAINT user_login_un UNIQUE ( login );

ALTER TABLE "User" ADD CONSTRAINT user_email_un UNIQUE ( email );

CREATE TABLE userrank (
    id_user NUMBER(9) NOT NULL,
    id_rank NUMBER(9) NOT NULL
);

CREATE UNIQUE INDEX userrank__idx ON
    userrank (
        id_user
    ASC );

ALTER TABLE userrank ADD CONSTRAINT userrank_pk PRIMARY KEY ( id_user,
                                                              id_rank );

ALTER TABLE orderheader
    ADD CONSTRAINT address_fk FOREIGN KEY ( id_address )
        REFERENCES address ( id_address );

ALTER TABLE attribute
    ADD CONSTRAINT attribute_model_fk FOREIGN KEY ( id_model )
        REFERENCES model ( id_model );

ALTER TABLE corporatecustomer
    ADD CONSTRAINT corporatecustomer_user_fk FOREIGN KEY ( id_user )
        REFERENCES "User" ( id_user );

ALTER TABLE address
    ADD CONSTRAINT country_fk FOREIGN KEY ( id_country )
        REFERENCES country ( id_country );

ALTER TABLE orderheader
    ADD CONSTRAINT deliverymethod_fk FOREIGN KEY ( id_delivery )
        REFERENCES deliverymethod ( id_delivery );

ALTER TABLE orderdetail
    ADD CONSTRAINT model_fk FOREIGN KEY ( id_model )
        REFERENCES model ( id_model );

ALTER TABLE shoppingcart
    ADD CONSTRAINT model_fkv1 FOREIGN KEY ( id_model )
        REFERENCES model ( id_model );

ALTER TABLE model
    ADD CONSTRAINT model_product_fk FOREIGN KEY ( id_product )
        REFERENCES product ( id_product );

ALTER TABLE orderdetail
    ADD CONSTRAINT orderheader_fk FOREIGN KEY ( id_order )
        REFERENCES orderheader ( id_order );

ALTER TABLE orderheader
    ADD CONSTRAINT orderstatus_fk FOREIGN KEY ( id_status )
        REFERENCES orderstatus ( id_status );

ALTER TABLE orderheader
    ADD CONSTRAINT paymentmethod_fk FOREIGN KEY ( id_payment )
        REFERENCES paymentmethod ( id_payment );

ALTER TABLE permissions
    ADD CONSTRAINT permissions_adminrank_fk FOREIGN KEY ( id_rank )
        REFERENCES adminrank ( id_rank );

ALTER TABLE productsubcategory
    ADD CONSTRAINT productcategory_fk FOREIGN KEY ( productcategorykey )
        REFERENCES productcategory ( productcategorykey );

ALTER TABLE product
    ADD CONSTRAINT productsubcategory_fk FOREIGN KEY ( productsubcategorykey )
        REFERENCES productsubcategory ( productsubcategorykey );

ALTER TABLE retailcustomer
    ADD CONSTRAINT retailcustomer_user_fk FOREIGN KEY ( id_user )
        REFERENCES "User" ( id_user );

ALTER TABLE address
    ADD CONSTRAINT user_fk FOREIGN KEY ( id_user )
        REFERENCES "User" ( id_user );

ALTER TABLE orderheader
    ADD CONSTRAINT user_fkv1 FOREIGN KEY ( id_user )
        REFERENCES "User" ( id_user );

ALTER TABLE shoppingcart
    ADD CONSTRAINT user_fkv2 FOREIGN KEY ( id_user )
        REFERENCES "User" ( id_user );

ALTER TABLE userrank
    ADD CONSTRAINT userrank_adminrank_fk FOREIGN KEY ( id_rank )
        REFERENCES adminrank ( id_rank );

ALTER TABLE userrank
    ADD CONSTRAINT userrank_user_fk FOREIGN KEY ( id_user )
        REFERENCES "User" ( id_user );
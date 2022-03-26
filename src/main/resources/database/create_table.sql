
CREATE TABLE customer(
	id bigint NOT NULL PRIMARY KEY auto_increment,
	fullname VARCHAR(50) NOT NULL,
    phonenumber VARCHAR(25) NOT NULL,
    
    createddate TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL,
    createdby VARCHAR(255) NULL,
    modifiedby VARCHAR(255) NULL
);

CREATE TABLE user(
	id bigint NOT NULL PRIMARY KEY auto_increment,
	username VARCHAR(50) NOT NULL,
    fullname VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    role int NOT NULL,
    address VARCHAR(255) NULL,
    
    createddate TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL,
    createdby VARCHAR(255) NULL,
    modifiedby VARCHAR(255) NULL
);

CREATE TABLE bill(
	id bigint NOT NULL PRIMARY KEY auto_increment,
	idcustomer bigint NOT NULL,
    iduser bigint NOT NULL,
    date bigint NOT NULL,
    totalmoney bigint NOT NULL,
    
    createddate TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL,
    createdby VARCHAR(255) NULL,
    modifiedby VARCHAR(255) NULL
);
ALTER TABLE bill ADD CONSTRAINT fk_bill_customer FOREIGN KEY (idcustomer) REFERENCES customer(id);
ALTER TABLE bill ADD CONSTRAINT fk_bill_user FOREIGN KEY (iduser) REFERENCES user(id);

CREATE TABLE item(
	id bigint NOT NULL PRIMARY KEY auto_increment,
	code VARCHAR(25) NOT NULL,
    name VARCHAR(255) NULL,
    fromdate bigint NOT NULL,
    todate bigint NOT NULL,
    price bigint NOT NULL,
    brand VARCHAR(25) NOT NULL,
    
    createddate TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL,
    createdby VARCHAR(255) NULL,
    modifiedby VARCHAR(255) NULL
);

CREATE TABLE storehouse(
	id bigint NOT NULL PRIMARY KEY auto_increment,
	iditem bigint NOT NULL,
    codeitem VARCHAR(25) NOT NULL,
    number int NOT NULL,
    date long NOT NULL,
    
    createddate TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL,
    createdby VARCHAR(255) NULL,
    modifiedby VARCHAR(255) NULL
);
ALTER TABLE storehouse ADD CONSTRAINT fk_storehouse_item FOREIGN KEY (iditem) REFERENCES item(id);

CREATE TABLE billdetail(
	id bigint NOT NULL PRIMARY KEY auto_increment,
	idbill bigint NOT NULL,
    iditem bigint NOT NULL,
    number int NOT NULL,
    
    createddate TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL,
    createdby VARCHAR(255) NULL,
    modifiedby VARCHAR(255) NULL
);
ALTER TABLE billdetail ADD CONSTRAINT fk_billdetail_bill FOREIGN KEY (idbill) REFERENCES bill(id);
ALTER TABLE billdetail ADD CONSTRAINT fk_billdetail_item FOREIGN KEY (iditem) REFERENCES item(id);

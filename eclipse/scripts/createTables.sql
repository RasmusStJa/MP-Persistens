--use DMA-CSD-V26_10730816

create table zipcode (
	zipcode int primary key not null,
	city varchar(32) not null
)

create table customer (
	phoneno varchar(16) primary key not null,
	name varchar(64) not null,
	address varchar(32) not null,
	zipcode int not null,
	type varchar(16) not null,
	constraint fk_c_zipcode foreign key(zipcode) references zipcode(zipcode)
)

create table saleOrder (
	orderNo int primary key identity(1,1),
	cust_phoneno_id varchar(16) not null,
	date Date not null,
	deliveryStatus varchar(64) not null,
	deliveryDate Date not null,
	discountGiven int not null,
	constraint fk_so_phoneno foreign key(cust_phoneno_id) references customer(phoneno)
)

create table freight (
	id int primary key identity(1,1),
	method varchar(64) not null,
	baseCost decimal(10,2) not null,
	freeThreshold int not null,
	orderNo_id int unique not null,
	constraint fk_f_orderNo foreign key(orderNo_id) references saleOrder(orderNo)
)

create table invoice (
	invoiceNo int primary key identity(1,1),
	dueDate Date not null,
	paymentDate Date not null,
	orderNo_id int unique not null,
	constraint fk_inv_orderNo foreign key(orderNo_id) references saleOrder(orderNo)
)

create table product (
	productNumber int primary key not null,
	name varchar(64) not null,
	minStock int not null,
	reservedStock int not null,
	type varchar(32) not null
)

create table clothing (
	productNumber int primary key not null,
	size varchar(8) not null,
	colour varchar(12) not null,
	constraint fk_c_prodnum foreign key(productNumber) references product(productNumber)
)

create table equipment (
	productNumber int primary key not null,
	material varchar(64) not null,
	style varchar(64) not null,
	constraint fk_equip_prodnum foreign key(productNumber) references product(productNumber)
)

create table gunReplica (
	productNumber int primary key not null,
	calibre varchar(8) not null,
	material varchar(32) not null,
	constraint fk_gr_prodnum foreign key(productNumber) references product(productNumber)
)

create table price (
	productNumber_id int not null,
	timestamp DATETIME2 not null, -- natural PK part
	price decimal(10,2) not null,
	primary key(productNumber_id, timestamp),
	constraint fk_price_prodnum foreign key(productNumber_id) references product(productNumber)
)

create table warehouse (
	number int primary key identity(1,1),
	name varchar(64) not null,
	description varchar(200) not null,
)

create table stock (
	productNumber_id int not null,
	warehouse_no int not null, -- natural PK part
	availableQty int not null,
	primary key(productNumber_id, warehouse_no),
	constraint fk_stck_pnumid foreign key(productNumber_id) references product(productNumber),
	constraint fk_stck_whno foreign key(warehouse_no) references warehouse(number)
)

create table supplier (
	id int primary key identity(1,1),
	name varchar(64) not null,
	address varchar(32) not null,
	country varchar(32) not null,
	phoneno varchar(16) not null,
	email varchar(32) not null
)

create table supplier_Products (
	supplier_id int not null,
	productNumber_id int not null,
	primary key(supplier_id, productNumber_id),
	constraint fk_sp_supid foreign key(supplier_id) references supplier(id),
	constraint fk_sp_pnumid foreign key(productNumber_id) references product(productNumber)
)

create table orderLineItem (
	id int primary key identity(1,1),
	quantity int not null,
	orderNo_id int not null,
	productNumber_id int not null,
	constraint fk_oli_orderid foreign key(orderNo_id) references saleOrder(orderNo),
	constraint fk_oli_pnumid foreign key(productNumber_id) references product(productNumber)
)

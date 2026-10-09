
-- Insert zipcodes
insert into zipcode (zipcode, city) values
(8800, 'Viborg'),
(9000, 'Aalborg'),
(8000, 'Aarhus C'),
(7400, 'Herning');

-- Insert customers
-- Type: 'CLUB' or 'PRIVATE' 
insert into customer (phoneno, name, address, zipcode, type) values
('86610000', 'Viborg Square Dance Klub', 'Mathias Gade 12', 8800, 'CLUB'),
('20123456', 'Jens Hansen', 'Aagade 4', 8800, 'PRIVATE'),
('98123456', 'Aalborg Line Dance Club', 'Vesterbro 45', 9000, 'CLUB'),
('30876543', 'Mette Jensen', 'Haraldsgade 8', 8000, 'PRIVATE');

-- Insert saleOrders
insert into saleOrder (cust_phoneno_id, date, deliveryStatus, deliveryDate, discountGiven) values
('86610000', '2023-10-01', 'Dispatched', '2023-10-03', 10),
('20123456', '2023-10-02', 'Delivered', '2023-10-04', 0),
('98123456', '2023-10-05', 'Pending', '2023-10-08', 15);

-- Insert freight
insert into freight (method, baseCost, freeThreshold,orderNo_id) values
('GLS Express', 0.00, 1500, 1),
('Postnord Standard', 45.00, 2500, 2),
('DAO Home',0.00, 1500, 3);

-- Insert invoice
insert into invoice (dueDate, paymentDate, orderNo_id) values
('2023-10-15', '2023-10-03', 1),
('2023-10-16', '2023-10-04', 2),
('2023-10-19', '2023-10-07', 3);

-- Insert products
insert into product (productNumber, name, minStock, reservedStock, type) values
(1001, 'Stetson Cowboy Hat', 10, 2, 'Clothing'),
(1002, 'Læder Cowboystøvler', 5, 1, 'Clothing'),
(1003, 'Texas Western Skjorte', 15, 3, 'Clothing'),
(2001, 'Zippo Lighter Western', 20, 5, 'Equipment'),
(2002, 'Læder Bælte med Bæltespænde', 10, 0, 'Equipment'),
(3001, 'Colt Single Action Revolver Replica', 5, 1, 'GunReplica'),
(3002, 'Winchester Lever-Action Rifle Replica', 3, 0, 'GunReplica');

-- Insert prod-type clothing
insert into clothing (productNumber, size, colour) values
(1001, 'L', 'Brown'),
(1002, '43', 'Black'),
(1003, 'XL', 'Red');

-- Insert prod-type equipment
insert into equipment (productNumber, material, style) values
(2001, 'Chrome/Brass', 'Classic Engraved'),
(2002, 'Leather &amp; Metal', 'Western Buckle');

-- Insert prod-type gunReplica
insert into gunReplica (productNumber, calibre, material) values
(3001, '.45', 'Zinc Alloy / Wood'),
(3002, '.44-40', 'Steel / Wood');

-- Insert price
insert into price (productNumber_id, timestamp, price) values
(1001, '2023-01-01 08:00:00', 899.00),
(1002, '2023-01-01 08:00:00', 1499.00),
(1003, '2023-01-01 08:00:00', 499.00),
(2001, '2023-01-01 08:00:00', 349.00),
(2002, '2023-01-01 08:00:00', 399.00),
(3001, '2023-01-01 08:00:00', 1299.00),
(3002, '2023-01-01 08:00:00', 2199.00);

-- Insert warehouse
insert into warehouse (name, description) values
('Hovedlager Viborg', 'Hovedbutik og primært lager placeret i Viborg'),
('Mobil Lager', 'Mobilvogn og lagerenhed til festivaller, markeder og koncerter');

-- Insert stock
insert into stock (productNumber_id, warehouse_no, availableQty) values
(1001, 1, 45),
(1001, 2, 10),
(1002, 1, 20),
(1002, 2, 5),
(1003, 1, 60),
(2001, 1, 30),
(2002, 1, 25),
(3001, 1, 12),
(3002, 1, 8);

-- Insert supplier
insert into supplier (name, address, country, phoneno, email) values
('Texas Western Outfitters Co.', '100 Frontier Way', 'U.S.A.', '+15550192834', 'sales@texaswestern.com'),
('Danube Leather &amp; Crafts Kft.', 'Váci út 12', 'Hungary', '+3612345678', 'info@danubeleather.hu'),
('Silesian Gun Replicas Sp. z o.o.', 'ul. Stawowa 5', 'Poland', '+48321654987', 'kontakt@silesianguns.pl');

-- Insert supplier_Products
insert into supplier_Products (supplier_id, productNumber_id) values
(1, 1001),
(1, 1003),
(2, 1002),
(2, 2001),
(2, 2002),
(3, 3001),
(3, 3002);
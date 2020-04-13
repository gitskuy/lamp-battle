CREATE TABLE transactions(customer_id int NOT NULL,item_name char(50),price int(50));

CREATE TABLE pelangan (customer_id int NOT NULL,customer_name char(50),age int(3));

end_at TIME
birth DATE
CREATE TABLE pet (name VARCHAR(20), owner VARCHAR(20),
       species VARCHAR(20), sex CHAR(1), birth DATE, death DATE);



CREATE TABLE employee(e_id int NOT NULL,e_name char(50),e_username char(50),e_password char(50),type char(50),salary int(20));
CREATE TABLE absen(e_id int NOT NULL,tanggal DATE,jam TIME);
CREATE TABLE laporan(e_id int NOT NULL,tanggal DATE,pekerjaan char(1000));
CREATE TABLE sistemlog(e_id int NOT NULL,tanggal DATE,jam TIME);

INSERT INTO employee
       VALUES (1,'Ahmad','ahmad@yazid.id','Qwerty!1234','SPV',10000000);


INSERT INTO employee
       VALUES (2,'Yazid','yazid@emp.id','Qwerty!1234','EMP',5000000);

INSERT INTO employee
       VALUES (3,'Harhar','harharah@hr.id','Qwerty!1234','HR',6000000);

INSERT INTO employee
       VALUES (4,'Dizay','dizay@emp.id','Qwerty!1234','EMP',5000000);

INSERT INTO employee
       VALUES (5,'Damha','damha@emp.id','Qwerty!1234','EMP',5000000);

INSERT INTO employee
       VALUES (6,'Zid','zid@emp.id','Qwerty!1234','EMP',5000000);
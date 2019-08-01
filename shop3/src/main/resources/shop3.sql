--/shop3/main/resources/shop3.sql
create table sale (
   saleid int(10) primary key,
   userid varchar(20),
   updatetime datetime
)

create table saleitem (
   saleid int(10),
   saleitemid int(3),
   itemid int(5),
   quantity int(3),
   updatetime datetime,
   primary key(saleid, saleitemid)
)
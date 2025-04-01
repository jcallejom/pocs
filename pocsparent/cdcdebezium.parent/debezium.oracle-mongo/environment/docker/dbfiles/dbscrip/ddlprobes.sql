
--basic updates
SELECT id, name, description, weight
FROM products;
UPDATE products SET name='scooter REPLICA_7557' where id=101;

SELECT ID, FIRST_NAME, LAST_NAME, EMAIL
FROM CUSTOMERS;
UPDATE CUSTOMERS SET FIRST_NAME='trece' WHERE ID='1001';


SELECT ID, ORDER_DATE, PURCHASER, QUANTITY FROM ORDERS;
UPDATE orders SET quantity=9 WHERE id=10001;

--listar privilegios
select * from role_sys_privs;
select * from role_sys_privs where role = 'c##dbzuser' ;
--listar tablesapce
SELECT TABLESPACE_NAME, STATUS, CONTENTS FROM USER_TABLESPACES;
select tablespace_name, con_id from cdb_tablespaces;
--Listar Esquemas y Tablas de Oracle 
SELECT DISTINCT owner AS SCHEMA
FROM dba_segments
WHERE owner IN (SELECT username  
                FROM dba_users 
                WHERE default_tablespace NOT IN ('SYSTEM','SYSAUX'))
ORDER BY owner ASC;
--Listar todas las tablas de un SCHEMA
SELECT DISTINCT owner, object_name
FROM all_objects
WHERE object_type = 'TABLE';

SELECT DISTINCT owner, object_name
FROM all_objects
WHERE object_type = 'TABLE' AND owner = 'SYSTEM' AND OBJECT_NAME = 'PRODUCTS';

SELECT DISTINCT owner, object_name
FROM all_objects
WHERE object_type = 'TABLE' AND owner = 'SYSTEM';
--Listar todas las tablas de todos los SCHEMAS
SELECT DISTINCT owner, object_name
FROM all_objects 
WHERE object_type = 'TABLE' AND
owner IN (SELECT username
          FROM dba_users 
          WHERE default_tablespace NOT IN ('SYSTEM','SYSAUX'))
ORDER BY owner ASC;

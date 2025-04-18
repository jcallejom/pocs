#!/bin/sh

## cambiar¿?ALTER SYSTEM SET db_recovery_file_dest = '/opt/oracle/oradata/ORCLCDB' scope=spfile; 
#1.Configure Oracle: Archive logs
# Set archive log mode and enable GG replication
#printf "\nConfigure Oracle: Archive logs...\n"
ORACLE_SID=ORCLCDB
export ORACLE_SID
sqlplus /nolog <<- EOF
	CONNECT sys/Oradoc_db1 AS SYSDBA
	alter system set db_recovery_file_dest_size = 10G;
	alter system set db_recovery_file_dest = '/ORCL/u02/app/oracle/oradata/recovery_data' scope=spfile;
	shutdown immediate
	startup mount
	alter database archivelog;
	alter database open;
        -- Should show "Database log mode: Archive Mode"
	archive log list
	exit;
EOF

#Configurar Oracle: Redo logs ¿?
#ALTER DATABASE CLEAR LOGFILE GROUP 2;
#ALTER DATABASE DROP LOGFILE GROUP 2;
#ALTER DATABASE ADD LOGFILE GROUP 2 ('/opt/oracle/oradata/ORCLCDB/redo01.log') size 400M REUSE;

#
# Enable LogMiner required database features/settings
#printf "\nConfigure Oracle: Supplemental Logging...\n"
sqlplus sys/Oradoc_db1@//localhost:1521/ORCLCDB.localdomain as sysdba <<- EOF
  ALTER DATABASE ADD SUPPLEMENTAL LOG DATA;
  ALTER PROFILE DEFAULT LIMIT FAILED_LOGIN_ATTEMPTS UNLIMITED;
  exit;
EOF

# Create Log Miner Tablespace and User
sqlplus sys/Oradoc_db1@//localhost:1521/ORCLCDB.localdomain as sysdba <<- EOF
  CREATE TABLESPACE LOGMINER_TBS DATAFILE '/ORCL/u02/app/oracle/oradata/ORCLCDB/logminer_tbs.dbf' SIZE 25M REUSE AUTOEXTEND ON MAXSIZE UNLIMITED;
  exit;
EOF

sqlplus sys/Oradoc_db1@//localhost:1521/ORCLPDB1.localdomain as sysdba <<- EOF
  CREATE TABLESPACE LOGMINER_TBS DATAFILE '/ORCL/u02/app/oracle/oradata/ORCLCDB/ORCLPDB1/logminer_tbs.dbf' SIZE 25M REUSE AUTOEXTEND ON MAXSIZE UNLIMITED;
  exit;
EOF

sqlplus sys/Oradoc_db1@//localhost:1521/ORCLCDB.localdomain as sysdba <<- EOF
  CREATE USER c##dbzuser IDENTIFIED BY dbz DEFAULT TABLESPACE LOGMINER_TBS QUOTA UNLIMITED ON LOGMINER_TBS CONTAINER=ALL;
  GRANT CREATE SESSION TO c##dbzuser CONTAINER=ALL;
  GRANT SET CONTAINER TO c##dbzuser CONTAINER=ALL;
  GRANT SELECT ON V_\$DATABASE TO c##dbzuser CONTAINER=ALL;
  GRANT FLASHBACK ANY TABLE TO c##dbzuser CONTAINER=ALL;
  GRANT SELECT ANY TABLE TO c##dbzuser CONTAINER=ALL;
  GRANT SELECT_CATALOG_ROLE TO c##dbzuser CONTAINER=ALL;
  GRANT EXECUTE_CATALOG_ROLE TO c##dbzuser CONTAINER=ALL;
  GRANT SELECT ANY TRANSACTION TO c##dbzuser CONTAINER=ALL;
  GRANT SELECT ANY DICTIONARY TO c##dbzuser CONTAINER=ALL;
  GRANT LOGMINING TO c##dbzuser CONTAINER=ALL;
  GRANT CREATE TABLE TO c##dbzuser CONTAINER=ALL;
  GRANT ALTER ANY TABLE TO c##dbzuser CONTAINER=ALL;
  GRANT LOCK ANY TABLE TO c##dbzuser CONTAINER=ALL;
  GRANT CREATE SEQUENCE TO c##dbzuser CONTAINER=ALL;
  GRANT EXECUTE ON DBMS_LOGMNR TO c##dbzuser CONTAINER=ALL;
  GRANT EXECUTE ON DBMS_LOGMNR_D TO c##dbzuser CONTAINER=ALL;
  GRANT SELECT ON V_\$LOGMNR_LOGS TO c##dbzuser CONTAINER=ALL;
  GRANT SELECT ON V_\$LOGMNR_CONTENTS TO c##dbzuser CONTAINER=ALL;
  GRANT SELECT ON V_\$LOGFILE TO c##dbzuser CONTAINER=ALL;
  GRANT SELECT ON V_\$ARCHIVED_LOG TO c##dbzuser CONTAINER=ALL;
  GRANT SELECT ON V_\$ARCHIVE_DEST_STATUS TO c##dbzuser CONTAINER=ALL;
  exit;
EOF

sqlplus sys/Oradoc_db1@//localhost:1521/ORCLPDB1.localdomain as sysdba <<- EOF
  CREATE USER debezium IDENTIFIED BY dbz;
  GRANT CONNECT TO debezium;
  GRANT CREATE SESSION TO debezium;
  GRANT CREATE TABLE TO debezium;
  GRANT CREATE SEQUENCE to debezium;
  ALTER USER debezium QUOTA 100M on users;
  exit;
EOF
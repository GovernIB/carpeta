
del /Q create_db.sql

del /Q drop_db.sql

call mvn clean install

call mvn -Dtest=TestSchemaGenerator test -Dmaven.test.skip=false

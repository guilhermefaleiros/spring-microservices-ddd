CREATE DATABASE IF NOT EXISTS `order-database`;
CREATE DATABASE IF NOT EXISTS `product-database`;
CREATE DATABASE IF NOT EXISTS `user-database`;
CREATE DATABASE IF NOT EXISTS `payment-database`;

GRANT EXECUTE, PROCESS, SELECT, SHOW DATABASES, SHOW VIEW, ALTER, ALTER ROUTINE,
CREATE, CREATE ROUTINE, CREATE TEMPORARY TABLES, CREATE VIEW, DELETE, DROP,
    EVENT, INDEX, INSERT, REFERENCES, TRIGGER, UPDATE, CREATE USER, FILE,
                                                                  LOCK TABLES, RELOAD, REPLICATION CLIENT, REPLICATION SLAVE, SHUTDOWN,
                                                                  SUPER
                                               ON *.* TO administrator@'%'
                                               WITH GRANT OPTION;

FLUSH PRIVILEGES;
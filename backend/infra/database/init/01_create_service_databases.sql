\echo 'Ensuring service roles exist'

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_roles WHERE rolname = 'category') THEN
        CREATE ROLE category WITH LOGIN PASSWORD 'category';
    END IF;
    IF NOT EXISTS (SELECT 1 FROM pg_roles WHERE rolname = 'invoice') THEN
        CREATE ROLE invoice WITH LOGIN PASSWORD 'invoice';
    END IF;
    IF NOT EXISTS (SELECT 1 FROM pg_roles WHERE rolname = 'product') THEN
        CREATE ROLE product WITH LOGIN PASSWORD 'product';
    END IF;
    IF NOT EXISTS (SELECT 1 FROM pg_roles WHERE rolname = '"user"') THEN
        CREATE ROLE "user" WITH LOGIN PASSWORD 'user';
    END IF;
END
$$;

\echo 'Ensuring service databases exist'

SELECT 'CREATE DATABASE category OWNER category'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'category')\gexec

SELECT 'CREATE DATABASE invoice OWNER invoice'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'invoice')\gexec

SELECT 'CREATE DATABASE product OWNER product'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'product')\gexec

SELECT 'CREATE DATABASE "user" OWNER "user"'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'user')\gexec

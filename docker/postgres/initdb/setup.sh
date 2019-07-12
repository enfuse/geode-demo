#!/bin/bash
set -e

# Spring Cloud Data Flow
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE USER scdf WITH PASSWORD 'scdf';
    CREATE DATABASE scdf;
    GRANT ALL PRIVILEGES ON DATABASE scdf TO scdf;
EOSQL

# App database or whatever
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE USER app WITH PASSWORD 'app';
    CREATE DATABASE app;
    GRANT ALL PRIVILEGES ON DATABASE app TO app;
EOSQL

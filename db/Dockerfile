FROM postgres:16.0-alpine

# Environment variables for PostgreSQL
# Environment variables for PostgreSQL
ENV POSTGRES_USER myuser
ENV POSTGRES_PASSWORD mysecretpassword
ENV POSTGRES_DB mydatabase

# Copy the initialization script to the container
COPY /sql-scripts/init.sql /docker-entrypoint-initdb.d/

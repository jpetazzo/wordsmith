FROM postgres:15-alpine

COPY words.sql /docker-entrypoint-initdb.d/
ENV POSTGRES_HOST_AUTH_METHOD=trust

EXPOSE 5432

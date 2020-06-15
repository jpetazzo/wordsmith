FROM postgres:12.3

# NOTE: pass env variable as args
ENV POSTGRES_HOST_AUTH_METHOD=trust
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres

# NOTE: Should mount a volume somewhere to keep persisted data

# NOTE: shoud download the init from a remote address
ADD words.sql /docker-entrypoint-initdb.d/init-words.sql

EXPOSE 5432
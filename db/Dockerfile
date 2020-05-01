FROM postgres

# The postgres official image has a "magic" entrypoint,
# that will automatically execute scripts and load .sql
# files located in that specific directory below.
COPY words.sql /docker-entrypoint-initdb.d/words.sql

# The postgres official image requires us to either
# set a password, or explicitly allow authentication
# without passwords. We *do not* set the variable
# below, meaning that we will *have to* provide a
# password (using the POSTGRES_PASSWORD environment
# variable).
#ENV POSTGRES_HOST_AUTH_METHOD=trust

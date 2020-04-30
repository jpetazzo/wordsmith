FROM ubuntu

# Many packages "want" some configuration information.
# (For instance, the tzdata package wants to know in
# which timezone we are, to set the TZ environment
# variable accordingly.)
# On Debian/Ubuntu, this configuration is managed by
# debconf. The following line prevents debconf from
# asking questions on the terminal (the default), which
# wouldn't work in a Dockerfile, since Dockerfile must
# not interact with the user (since there might not be
# an user to interact with).
ENV DEBIAN_FRONTEND=noninteractive

# Update package list, and install the Go toolchain.
RUN apt-get update
RUN apt-get install golang-go -y

# Copy only the files we need.
# Note that when copying a directory (like "static"),
# the *content* of the directory gets copied to the
# target; so if we do "COPY static ." the content of
# "static" is copied directly to the destination
# directory (instead of being copied to a "static"
# subdirectory).
COPY dispatcher.go .
COPY static static

# Compile the Go program as instructed.
RUN go build dispatcher.go

# Using the exec syntax (JSON list of strings) makes
# sure that "dispatcher" will be PID 1, and will therefore
# receive signals correctly (allowing Ctrl-C to work
# correctly, for instance).
CMD ["./dispatcher"]

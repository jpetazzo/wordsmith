FROM golang

# Copy only the source file. We do not need the "static"
# directory in the build phase.
COPY dispatcher.go .

# Compile the Go program as instructed.
RUN go build dispatcher.go

# Start a new stage.
FROM ubuntu

# Copy the binary.
# Note that the golang image uses /go as the WORKDIR.
COPY --from=0 /go/dispatcher .

# Copy the static directory.
COPY static static

# Using the exec syntax (JSON list of strings) makes
# sure that "dispatcher" will be PID 1, and will therefore
# receive signals correctly (allowing Ctrl-C to work
# correctly, for instance).
CMD ["./dispatcher"]

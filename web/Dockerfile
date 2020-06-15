# Builder
FROM golang:1.14 AS builder

WORKDIR /build
COPY . .

# Make the binary standalone
RUN CGO_ENABLED=0 GOOS=linux go build -a -installsuffix cgo -ldflags '-w -extldflags "-static"' -o main

# Application
FROM scratch

WORKDIR /app
COPY --from=builder /build .

EXPOSE 80
ENTRYPOINT ["./main"]

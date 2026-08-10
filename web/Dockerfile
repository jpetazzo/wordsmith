FROM golang:1.21-alpine AS builder

WORKDIR /src
COPY dispatcher.go .
RUN go build -ldflags="-s -w" -o /dispatcher dispatcher.go

FROM scratch
WORKDIR /app
COPY --from=builder /dispatcher .
COPY static ./static

EXPOSE 80
CMD ["./dispatcher"]

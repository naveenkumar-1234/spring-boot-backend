# Base image
FROM gcc:latest

# Set working directory
WORKDIR /app

# Copy files into container and compile
RUN echo "Building C code inside container"

CMD ["sh", "-c", "gcc -o main main.c solution.c && ./main"]

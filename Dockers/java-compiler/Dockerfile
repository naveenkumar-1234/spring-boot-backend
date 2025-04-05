FROM openjdk:17-slim

WORKDIR /app

# Copy Main.java (Solution.java will be provided dynamically)
#COPY Main.java /app/

# Ensure the container keeps running for dynamic execution
CMD javac Solution.java Main.java 2> compile_errors.log; \
    if [ -s compile_errors.log ]; then cat compile_errors.log; else java -cp /app Main; fi

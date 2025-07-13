FROM openjdk:17-slim

WORKDIR /app

# Copier les sources Java
COPY src/ ./src/
COPY lib/ ./lib/
COPY public/ ./public/


# Compiler les fichiers Java
RUN javac -cp "lib/gson-2.8.9.jar" src/*.java

# Lancer l'application Java
CMD ["java", "-cp", "lib/gson-2.8.9.jar:src", "Main"]

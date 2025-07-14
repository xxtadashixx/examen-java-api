FROM openjdk:17-slim

WORKDIR /app

# Installation de curl
RUN apt-get update && apt-get install -y curl

COPY src/ ./src/
COPY public/ ./public/

# Télécharger Gson directement dans l'image
RUN mkdir lib \
 && curl -L -o lib/gson-2.8.9.jar https://repo1.maven.org/maven2/com/google/code/gson/gson/2.8.9/gson-2.8.9.jar

# Compiler les fichiers Java
RUN javac -cp "lib/gson-2.8.9.jar" src/*.java

# Lancer l'application
CMD ["java", "-cp", "lib/gson-2.8.9.jar:src", "Main"]

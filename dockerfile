FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/desafio-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

#Construir a imagem
#docker build -t desafio-spring .

#Iniciando container
#docker run -p 8080:8080 desafio-spring

#Visualizar containers em execução
#docker ps ou docker ps -a
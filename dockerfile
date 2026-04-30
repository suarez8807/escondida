#Version liviana de java
FROM eclipse-temurin:21-jdk
#Directorio de trabajo
WORKDIR /app
#Copia el archivo jar a la imagen
COPY target/escondida-0.0.1-SNAPSHOT.jar app.jar
#Puerto que usa la aplicacion
EXPOSE 8081
#Ejecuta la aplicacion
ENTRYPOINT ["java", "-jar", "app.jar"]


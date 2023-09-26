# Utilisez une image de base Java
FROM openjdk:22-jdk-slim

# Créez un répertoire d'applications dans l'image
RUN mkdir /app

# Copiez le fichier JAR de votre application dans l'image
COPY target/wallet-msir-jour-groupe2-0.0.1-SNAPSHOT.jar /app/app.jar

# Définissez le répertoire de travail de l'image
WORKDIR /app

# Exposez le port sur lequel votre application Spring Boot écoute (par défaut, 8080)
EXPOSE 8088

# Commande pour exécuter votre application Spring Boot lorsque le conteneur démarre
CMD ["java", "-jar", "app.jar"]


#deployement de l'application sur docker (en creant un conteneur docker)

#Ajouter un dockerFile dans notre projet
#docker build -t tp-docker . contruction de l'image docker de l'application Spring boot
#docker run  -p 8080:8080 tp-docker Execution du conteneur docker de l'application
#docker stop nom-conteneur Arrêter le conteneur docker en cours d'execution
#docker rm nom_conteneur Supprimer le conteneur docker
# mvn clean package pour regenerer le jar de l'application; changer le port d'ecoute de l'appli en 8088


###changer la base de h2 en postgres
#docker run -d -p 5432:5432 --name localpostgres -e POSTGRES_PASSWORD=password postres:11.6 (11.6=tag) lancer postgres avec docker

##docker-compose up: pour lancer le docker-compose.yml (creation automatique de l'image postgres,generer le jar de l'application, mettre image de l'application sur docker, creer le conteneur et demarrer l'application)

FROM tomcat:9.0-jdk17-openjdk-slim

LABEL maintainer="dev-team@entreprise.com"
LABEL version="1.0"
LABEL description="Système de Gestion de Colis et Transporteurs - WAR Deployment"

RUN rm -rf /usr/local/tomcat/webapps/*

COPY target/gestion-colis-transporteurs-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

ENV CATALINA_OPTS="-Xmx512m -Xms256m -Dspring.profiles.active=prod"

ENV SPRING_DATA_MONGODB_URI="mongodb://admin:password@mongodb:27017/logistique?authSource=admin"

ENV JWT_SECRET="mySecretKeyForJWTGenerationInProductionEnvironment2024"

CMD ["catalina.sh", "run"]

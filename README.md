mvn clean package
docker build -t monitor-sensors .
docker run -p 8081:8080 monitor-sensors
docker-compose up

На даний момент докер не працює, також liquibase відключений

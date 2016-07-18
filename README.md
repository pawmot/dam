# Digital Asset Managment

A simple project that I use for getting used to a couple of technologies, e.g.:
* Apache Camel
* ActiveMQ
* Spring Boot
* Docker
* Hibernate

## Running the application
_(You will need **docker-compose** version 1.6 or later since this project utilizes version 2 of the Compose file format.)_

Get into the root directory of the project and execute:
_./gradlew dockerize_

After about 30s (YMMV) the app will respond at **localhost:8080** (that's mapped to port 80 of dam-rest container).
This port can be remapped in **docker/docker-compose.yml**.

The dockerized application also exposes the MySQL and ActiveMQ usual ports (**3306** for MySQL, **61616** and **8161** for ActiveMQ, all TCP) for purposes of monitoring (**3306** and **8161**) and interacting with the application (**61616** and also **8161** using the ActiveMQ Web Console Send function).

If your machine already has listeners on the aforementioned ports the application won't be fully functional or won't work at all. You can remap/hide those ports in **docker/docker-compose.yml**. The only ports that you'll need are dam-rest:80 (currently mapped to host:8080) and activemq ports if you'd like to use that to send requests.
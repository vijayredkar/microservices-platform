         Platform components required for a Microservice Architecture.

1-Centralized Configuration Server - Implemented with Spring Cloud Config Server\
2-Service Discovery		            - Implemented with Eureka\
3-API-Gateway                      - Implemented with Zuul\
4-Distributed Log Tracing		      - Implemented with Sleuth + Zipkin Server + Zipkin UI\
5-Circuit Breaker/Fault Tolerance  - Implemented with Hystrix\
6-Client Side Load Balancer	      - Ribbon\
7-API Documentation		            - Swagger

Since the focus of this implementation is creating platform components, hence the micro services related to business application are kept trivial.\
In the business logic flow the catalog-svc calls the pricing-svc synchronously using REST to gather the item and the price.


Steps to build and run- 

Pre-requisites -\
JDK (preferably 1.8)\
Maven (preferably >= 3.x)

1- Checkout the master in to your local directory.\
   git clone https://github.com/vijayredkar/microservices-platform.git

2- Configuration Server\
   cd microservices-platform/config-server\
   mvn clean install\
   java -jar target/config-server.jar

Note that the catalog-svc.properties and pricing-svc.properties are located in the same GIT repo\
On startup, config server will read these properties and make it available to the registered business micro services.\
If config-server is unable to access these properties then business micro services use defaults.\
When the properties values change in the GIT repo, below POST invocation can be used to refresh the properties in the business micro services without having to rebuild/restart.

catalog-svc properties refresh -     localhost:9080/actuator/refresh\
pricing-svc properties refresh -     localhost:9087/actuator/refresh

3- Service Discovery\
   cd microservices-platform/service-discovery\
   mvn clean install\
   java -jar target/svc-discovery.jar

   Eureka Service Discovery can be accessed via   http://localhost:8761/

4- API Gateway\
   cd microservices-platform/api-gateway\
   mvn clean install\
   java -jar target/api-gateway.jar

5- Distributed Log Tracing\
   cd microservices-platform/log-manage\
   mvn clean install\
   java -jar target/log-manage.jar\

   Zipkin UI can be accessed via http://localhost:9412/zipkin/

6- catalog-svc  - Business Microservice Service\
   cd microservices-platform/catalog-svc\
   mvn clean install\
   java -jar target/catalog-svc.jar

7- pricing-svc  - Business Microservice Service\
   cd microservices-platform/pricing-svc\
   mvn clean install\
   java -jar target/pricing-svc.jar

8- Hystrix Dashboard\
   catalog-svc Hystrix Dashboard can be accessed via http://localhost:9080/hystrix\
   pricing-svc Hystrix Dashboard can be accessed via http://localhost:9087/hystrix

9- Actuator\
   catalog-svc Actuator endpoints can be viewed via http://localhost:9080/actuator\
   pricing-svc Actuator endpoints can be viewed via http://localhost:9087/actuator

10- Swagger Dashboard can be accessed via http://localhost:9080/swagger-ui.html

11- Verify from the Eureka Dashboard (via  http://localhost:8761/) that api-gateway, catalog-svc and pricing-svc are up and running 

12- The catalog-svc can be accessed via the URL http://localhost:8084/api/ecom/catalog/inventory/items\
    This call is intercepted by the api-gateway and dynamically routed to the catalog-svc.
    
13- The output on the browser will show a table with the item name and it's coresponding price fetched from the pricing-svc.
    

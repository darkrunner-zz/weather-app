In order to run the project execute mvn spring-boot:run in the following sequence:
1. Micro-eurekaserver (Starts Eureka Service Registry for the service scaling)
2. Micro-weather-service (Feign client proxy to the Open Weather Map api)
3. Micro-client-service-gateway (API Gateway client of the weather-service with embedded Ribbon load balancer working
with the Eureka server)

Additional:
Visit:
1. http://localhost:8761/ in order to view the Registered services
2. http://localhost:8360/v2/api-docs in order to view the weather-service api in json or
http://localhost:8360/swagger-ui.html for UI version
3. http://localhost:8080 for the UI service (Known Bug: First call to the weather-service through feign results in error
500 due to bug in FeignClient. Resolution: Refresh the page)
4. http://localhost:8080/forecast?long={longitude}&lat={latitude} - Coordinates version of the service
5. http://localhost:8080/forecast?name={city}[,country e.g. uk,bg] - Name based version of the service



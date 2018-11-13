# common-svc
This repo have common function in all micro-svc's

### There are Four Services 
##### 1.customer-svc  
This is simple sping boot web mvc with one controller and one endpoint.
##### 2.eureka-svc
This enable with eureka server which register all other micro service.
##### 3.gateway-svc
This is with zuul proxy which as client of eureka server and work as gateway to other service to find other service by there eureka registed name not by there host and port.
##### 4.message-svc
This is same as customer-svc.
##### 5.javaDS
This repo is for test/write Algos & DataStructure problems.
###### After Run all services
 Goto console or use postman to hit customer/message svc endpoint by host and port of gateway-svc instead of customer/message svc.
```sh
$ curl http://localhost:9094/message/message/info
```
The first 'message' keyword in the url for gateway-svc to find the service through the eureka-svc registory
Then it goto to that service(which is message-svc) and find endpoint with :
```
GET REQUEST /info
```
###### NOTE:
By Default curl make GET request when no any other method is provided.

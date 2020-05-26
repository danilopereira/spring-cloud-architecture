# Homework
A project with studies about microservices on the Spring Cloud Architecture.

The business logic consists in: based on an existing user, create a customer and apply him/her to new loan service.

The project has been built with the following technologies:
- Java 8
- Spring Boot
- Spring Cloud
- Eureka
- JWT

## Startup

To build and run the project, you just have to execute the following commands respecting order of execution:

```
.
├── eureka
        Default Port: 8761
        To start use: mvn spring-boot:run
        or use the make run-eureka command from the root path

└── auth
    Default Port: 9180
    To start use: mvn spring-boot:run
    or use the make run-auth command from the root path

└── customer-service
     Default Port: 9181
     To start use: mvn spring-boot:run
     or use the make run-customer command from the root path

└── loan-applications-service
    Default Port: 9182
    To start use: mvn spring-boot:run
    or use the make run-loan command from the root path

└── gateway
    Default Port: 9080
    To start use: mvn spring-boot:run
    or use the make run-gateway command from the root path

```

##Testing
To test it, we can edit and use the following curl commands in this respective order:

**Generate Access Token**

```shell script
curl --location --request POST 'localhost:9080/oauth/token' \
--header 'Authorization: Basic Y2xpZW50OnNlY3JldA==' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'username=jack' \
--data-urlencode 'password=jack' \
--data-urlencode 'grant_type=password' \
--data-urlencode 'client_id=client' \
--data-urlencode 'client_secret=secret'
```

**Get user by ID**

```shell script
curl --location --request GET 'localhost:9080/api/users/{{userId}}' \
--header 'Authorization: Bearer {{access_token}}' 
```

**Create cutomer**

```shell script
curl --location --request POST 'localhost:9080/api/customers' \
--header 'Authorization: Bearer {{access_token}}' \
--header 'Content-Type: application/json' \
--data-raw '{
	"userId": "{{userId}",
	"firstName": "Luke",
	"lastName": "Skywalker",
	"email": "starkiller@email.com",
	"phone": "+49 176 4567890678"
}'
```

**Get customer by ID**

```shell script
curl --location --request GET 'localhost:9080/api/customers/{{customerId}}' \
--header 'Authorization: Bearer {{access_token}}'
```

**Customer loan application**

```shell script
curl --location --request POST 'localhost:9080/api/loanapplications' \
--header 'Authorization: Bearer {{access_token}}' \
--header 'Content-Type: application/json' \
--data-raw '{
	"customerId": "{{customerId}}",
	"amount": 1000,
	"duration": 12
}'
```

**Get Loan applications by customer**

```shell script
curl --location --request GET 'localhost:9080/api/loanapplications?customerId={{customerId}}' \
--header 'Authorization: Bearer {{access_token}}' 
```

or you can use the **Postman** collection attached to this project: *danilopereira.postman_collection.json*. Just import and use it!

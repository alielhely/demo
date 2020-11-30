# Welcome to the Demo 

Please refer to the YOUR-WORKING-DIR/demo/src/main/resources/swagger/Demo-API-1.0.0.yaml which explain the API specs



## to run the application

Prequirments:
Java installed
Maven installed 

Steps:
Navigate to root path YOUR-WORKING-DIR\demo
run maven command
    maven clean install (Linux machine)
    mvn clean install (Windows machine)

run java -jar target/demo-0.0.1.jar

## to test the application

Prequirments:
CURL or Postman installed

while the application starting testing data will be loaded in momery.
CUSTOMER0001
CUSTOMER0011
CUSTOMER0011

You can use those customer Ids in testing. 
first retrieve the customer details by running below CURL command which will call customerDetails endpoint.
Windows
curl --location --request GET "http://localhost:8080/demo/customer/accounts/CUSTOMER0001"
Linux
curl --location --request GET 'http://localhost:8080/demo/customer/accounts/CUSTOMER0001'

Then you can run below CURL commands to add current account to existing customer

Windows
curl --location --request POST "http://localhost:8080/demo/accounts/current" --header "Content-Type: application/json" --data-raw "{ \"customerID\" : \"CUSTOMER0001\", \"initialCredit\" : \"3\" }"
Linux
curl --location --request POST 'http://localhost:8080/demo/accounts/current' \ --header 'Content-Type: application/json' \ --data-raw '{ "customerID" : "CUSTOMER0001", "initialCredit" : "3" }'


you can use your postman application to import below josn file to test it using postman
YOUR-WORKING-DIR/demo/src/test/postman/demo-postmand.postman_collection.json


Press ctrl + c to stop the application 


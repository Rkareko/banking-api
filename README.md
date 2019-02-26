# banking-api
A simple online banking API with makeDeposit, makeWithdwal and getBalance end-points.
This application is built using spring-boot framework and data is stored in H2 (in-memory database).
The API accepts requests in JSON format and sends out JSON responses as well.

# How to deploy the application.
In the root folder i.e. banking-api run the following commands:-
./gradlew clean build			This command cleans and builds the application.
./gradlew bootRun				This commands starts the project 

This starts the application on http://localhost:8080/ and a message "Welcome to online banking" is displayed.
The console for viewing the H2 database is located at http://localhost:8080/h2-console 
The jdbc Url is jdbc:h2:mem:testdb 

The H2 database is pre-populated with the following test data for a customer:-
email		:			jane@onlinebank.com
name					Jane Doe
national Id number	:	12345678
passport Number		:	ke-12345
account balance		:	4500.00

# How to check balance.
Request Method GET
Request url  http://{hostname}:{port}/customer/balance/{nationalId} e.g if running on localhost at port 8080 http://localhost:8080/customer/balance/12345678 
Response format 
http://localhost:8080/customer/balance/12345678 

Sample success response
{
"timestamp": "Tue Feb 26 07:16:31 EAT 2019",
"status": "200",
"error": null,
"message": "4500.00"
}

Sample error response
{
"timestamp": "Tue Feb 26 07:26:51 EAT 2019",
"status": "400",
"error": "Could not find customer with national id 1234567",
"message": "Request processing failed"
}


# How to make a deposit
Request Method POST
Request url  http://{hostname}:{port}/customer/deposit  e.g if running on localhost at port 8080 http://localhost:8080/customer/deposit

Sample request body
{
"nationalId":"12345678",
"amount":"200"
}

Sample success response
{
"timestamp": "Mon Feb 25 22:41:14 EAT 2019",
"status": "200",
"error": null,
"message": "Deposit successfully made"
}

Sample error response
{
"timestamp": "Mon Feb 25 22:42:13 EAT 2019",
"status": "400",
"error": "Please provide an amount greater than zero",
"message": "Request processing failed"
}

# How to withdraw
Request Method POST
Request url  http://{hostname}:{port}/customer/withdraw e.g if running on localhost at port 8080 http://localhost:8080/customer/withdraw

Sample request body
{
"nationalId":"12345678",
"amount":"300"
}

Sample success response
{
"timestamp": "Mon Feb 25 22:44:58 EAT 2019",
"status": "200",
"error": null,
"message": "Deposit successfully made"
}

Sample error response
{
"timestamp": "Mon Feb 25 22:43:40 EAT 2019",
"status": "400",
"error": "Please provide customer's national id number and amount to process",
"message": "Request processing failed"
}
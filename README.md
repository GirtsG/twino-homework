### Homework Application for Twino
#### System requirements
* JDK 14 installed - 
https://www.oracle.com/java/technologies/javase-jdk14-downloads.html
* JAVA_HOME environment variable pointed to JDK installation directory
#### Installation and run
1) download this repository
2) run application by using command: 
```./gradlew bootRun```
3) execute tests by using commands:
```./gradlew test```
4) find some data (DB access for example) in *resources/application.properties*
#### API end points
POST /loan-application/submit - apply for new loan, examples:
```
curl -X POST -H 'Content-Type: application/json' -i http://localhost:8080/loan-application/submit --data '{
    "amount": 560.01,
    "termInDays": 30,
    "personalId": "121282-88882",
    "name": "Gatis",
    "surname": "Izmēģinājums",
    "ipAddress": "91.105.0.0"
}'
```
```
curl -X POST -H 'Content-Type: application/json' -i http://localhost:8080/loan-application/submit --data '{
    "amount": 789.06,
    "termInDays": 60,
    "personalId": "111111-11410",
    "name": "Māra",
    "surname": "Paraudziņš",
    "ipAddress": "194.8.9.0"
}'
```
GET /loan-application/{id} - when you have created Loan Application in DB, view it by ID

GET /loan/list/approved - list all approved loans
```$xslt
curl -X GET -H 'Content-Type: application/json' -i http://localhost:8080/loan/list/approved
```

GET /loan/list/approved/person/{personalId} - list all approved loans by user
```$xslt
curl -X GET -H 'Content-Type: application/json' -i http://localhost:8080/loan/list/approved/person/121282-88882
```

#### Database
Database Console is under */db* URI as per configuration in *resources/application.properties*

Database is first created, then reused in each subsequent run. However if you want to create new database, just delete files by name *homeworkdb* in project root directory. 
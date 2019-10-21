# BANK API


BANK API is a (Spring) Rest API to simulate transfer amounts between accounts.

The application use Tomcat server to host and Hibernate and JPA to persiste the entities in memory (H2).


Logs - Log4j
Tests - Surefire
Maven project

## Installation

Download  git project

mvn clean install



## Running Tests

After Run the application you can run the unit tests

mvn plugin tomcat7:run

Open a new instance and run test

mvn surefire:test

## Logs

(Logs) - bank_api/target/tomcat/logs

# Getting Started

### Database class Configuration - H2 in Memory
br.com.dbtest.bank.config.SpringJpaConfig


### Entities class
br.com.dbtest.bank.domain.ContaCorrente
br.com.dbtest.bank.domain.Lancamento


You can create new exeptions here
br.com.dbtest.bank.Exception
and add new errors response in
br.com.dbtest.bank.resource.exception.RestExceptionHandler


### Dispatcher - http://localhost:8080/rest 

pom.xml
<properties>
        <project>rest</project>
        <spring.version>4.3.11.RELEASE</spring.version>
        <java.version>1.8</java.version>
</properties>

### Dispatcher and HTTP requests - http://localhost:8080/rest/transfs
br.com.dbtest.bank.resource.rest.ContaRestController
@RestController
@RequestMapping(
        value = "/transfs",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
)

### Dispatcher and HTTP requests - http://localhost:8080/rest/transfs
br.com.dbtest.bank.resource.rest.LancamentoRestController

@RestController
@RequestMapping(
        value = "/lanc",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
)

### Log4J Properties
WEB-INF/log4j.properties
WEB-INF/web.xml

### JPQL queries
br.com.dbtest.bank.dao.ContaDaoImpl
br.com.dbtest.bank.dao.LancamentoDaoImpl

### Bussiness Rulles

br.com.dbtest.bank.service.LancamentoServiceImpl
br.com.dbtest.bank.service.ContaServiceImpl


# Usage

After run the application you can test using POSTMAN - https://www.getpostman.com/

Default Header for all HTTP requests
Content Type - application/json

Json Template body to add account to memory

*** You Need less two account to execute a first transfer
For exemple:

### POST http://localhost:8080/rest/transfs

{"agencia" : "22301",
"conta" : "223000123",
"saldo" : "2000",
"limite" : "100",
"tipo" : "corrente"
}

### POST http://localhost:8080/rest/transfs

{"agencia" : "12301",
"conta" : "123000123",
"saldo" : "2000",
"limite" : "100",
"tipo" : "corrente"
}

Json Template to execute the transfer
### POST http://localhost:8080/lanc

*** this values fields must match with entities existing on database
"agenciaOrig": 00001,
"contaOrig": 123456789,
"agenciaDest": 00002,
"contaDest": 23456789,

{
    "agenciaOrig":12301,
    "contaOrig":123000123,
    "agenciaDest":22301,
    "contaDest":223000123,
    "valor": 10.0,
    "tipo": "corrente",
    "date": "123123123123123",
    "status": "ok"
}

Json fields rules validation
agenciaOrig and agenciaDest- lengh 5 only numbers
contaOrig and contaDest - lengh 9 only numbers
valor plus limite must be greater than transfer value

### GET - http://localhost:8080/rest/transfs

Exemple result

[
    {
        "id": 1,
        "agencia": 22301,
        "conta": 223000123,
        "saldo": 2010.0,
        "limite": 100.0,
        "tipo": "corrente",
        "lancamento": [
            {
                "id": 2,
                "agenciaOrig": 12301,
                "contaOrig": 123000123,
                "agenciaDest": 22301,
                "contaDest": 223000123,
                "valor": 10.0,
                "tipo": "corrente",
                "date": "123211130723123",
                "status": "ok"
            }
        ]
    },
    {
        "id": 2,
        "agencia": 12301,
        "conta": 123000123,
        "saldo": 1990.0,
        "limite": 100.0,
        "tipo": "corrente",
        "lancamento": [
            {
                "id": 1,
                "agenciaOrig": 12301,
                "contaOrig": 123000123,
                "agenciaDest": 22301,
                "contaDest": 223000123,
                "valor": 10.0,
                "tipo": "corrente",
                "date": "123211130723123",
                "status": "ok"
            }
        ]
    }
]



## Roadmap


Improve security with (Spring security)

Add Lombook to simplify getters

GET request to list Lancamentos Entity

Configure tomcat console

Validation fields for ContaCorrente.class

Validation fields for Lancamento.class

Regex pattern validation fields for lancamento.class

Regex pattern validation fields for lancamento.class

Regex pattern  validation for transfer of same origin

Ignore message properties of tomcat7 METAINF bug

Constants for type Column entity

Profiling tests

Improve log messages

## Authors and acknowledgment
https://www.linkedin.com/in/victor-hugo-soares-lima-ab8b0215/

### License
Spring Data REST is Open Source software released under the Apache 2.0 license.


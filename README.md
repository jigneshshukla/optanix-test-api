## About optanix-test-api

```
This API provides two HTTP POST Restful Endpoints.

1. API: /word_count_per_sentence
   Description: This API takes simple key/value pair as input payload where key = arbitrary user provided name or identifier for the paragraph to be evaluated
   and the value = a string with two or more sentences. For each input paragraph (identified by key), it calculates and returns mapping of individual sentence from given paragraph to total number of words.
   A sentence in a paragraph is any grouping of letters and words ending in a period, question mark or exclamation point.

2. API: /total_letter_count
   Description: This API takes simple key/value pair as input where key = arbitrary user provided name or identifier for the text to be evaluated
   and the value = a string representing text to be evaluated. For each input text (identified by key), it calculates and returns mapping of case-insensitive distinct character (including non-alphabets)
   from given text to total number of occurrences of that character in entire text.

This project uses Swagger API for documentation. Full API documentation can be accessed at http://localhost:8080/swagger-ui.html#/

```

### Special Instructions

```
NOTE: THIS PROJECT USES PROJECT LOMBOK FOR GENERATING BOILER-PLATE CODE (e.g. getter,setter,constructor etc). DEPENDING ON WHAT IDE YOU ARE USING FOR DEVELOPMENT,
      YOU MAY NEED TO INSTALL IDE SPECIFIC PLUGINS TO HAVE LOMBOK ANNOTATIONS RECOGNIZED AND PROCESSED BY IDE. OTHERWISE, YOUR IDE MIGHT SHOW ERRORS IN CODE.
      YOU WOULD STILL BE ABLE TO RUN THE PROJECT AS-IS WITHOUT ANY SPECIAL NEED FOR LOMBOK LIBRARY IF YOU'RE BUILDING AND RUNNING IT DIRECTLY FROM COMMAND-PROMPT/TERMINAL.
```

## Prerequisites

Docker, JDK-8 and Maven

## Running Locally

```
mvn spring-boot:run
```

### Test

```
API can be tested using Swagger UI at http://localhost:8080/swagger-ui.html#/
```

### Build Docker Image

```
docker build -t optanix-test-api .
```

### Run Container

Stop the application if currently running.

```
docker run --rm -it -p 8080:8080 optanix-test-api
```

### Test Container

```
Container API can be tested using Swagger UI at http://localhost:8080/swagger-ui.html#/
```
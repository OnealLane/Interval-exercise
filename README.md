
# Interval Merge and Exclude Service

This project is a Spring Boot application written in Java 21 that merges a set of include intervals and subtract a set  
of exclude intervals. The result is a list of non-overlapping, sorted intervals.

### Notes 
I made a few mistakes choosing Java and Spring boot for this task. 

I spent significant time getting the setup to work properly due to environment issues  
which was a result of doing this on a work laptop, with already preconfigured environment setup.

After being a bit stubborn I stuck with it and later encountered more issues.  
The idea was always to add swagger implementation so that one would not have to call  
the endpoint manually through e.g. Postman. 

Alas, the local Maven setup made it difficult to set up Swagger and import it properly in pom.xml file.  
Due to time constraints I did not use more resources on changing the m2 properties, in case it messes up other work.



Given:
- A list of **include intervals**
- A list of **exclude intervals**


### Example

**Input:**
- Includes: `[10-100, 200-300]`
- Excludes: `[20-30, 95-205]`

**Output:**
- `[10-19, 31-94, 206-300]`

## How to Run

### Prerequisites
- Java 21
- Maven
- Spring Boot
- Postman / Insomnia or similar 

### Run Locally

- mvn clean install

## Example Postman request
- Method: POST 
- Example endpoint: http://localhost:8080/api/intervals/calculate
- Example body:
  
- ` {
    "includes": [
    { "start": 40, "end": 50 },
    { "start": 100, "end": 150 }], `  
 `
      "excludes": [
      { "start": 30, "end": 40 },
      { "start": 105, "end": 133 }]} `
# British Spoken Time Formatter
## Challenge Details
### Implement a time formatter that converts input time in "HH:mm" 24-hour format into a British spoken form, e.g., "12:00" → "twelve o'clock". The program validates the input time format and handles edge cases like midnight and noon correctly.

## Explanation of the Code
### The main logic parses the input time, validates it, and formats it into spoken English using a structured approach. The code includes:

- Input validation with regex and range checks.

- Conversion from 24-hour time to 12-hour spoken format. 

- Handling special cases such as midnight ("midnight") and noon ("noon").

- Comprehensive unit tests ensuring correctness.

## Prerequisites to Run
- **Java 21 JDK installed.**

- Maven installed and configured (mvn available).

- Docker installed if running containerized.
- Please clone the repo into local.

## Clone the repository
```
git clone https://github.com/JSUBIN24/british-spoken-time.git
cd british-spoken-time 
```


### Step 1

```bash
  ./mvnw clean install
```

### Step 2
```docker build -t my-api:latest ```

### Step 3

``` docker run -d -p 8080:8080 --name my-api my-api:latest```


### Steps to run in terminal

```bash 

mvn clean spring-boot:run
```


## package & run jar
mvn clean package
java -jar target/*-SNAPSHOT.jar

## run tests
mvn clean verify

## Swagger Endpoint Details
```
Swagger UI : `http://localhost:8080/swagger-ui/index.html#/`
```

- The OpenAPI specification JSON can be accessed at /v3/api-docs.

- This allows easy exploration and testing of REST endpoints.

## Key Design Decisions
- Strict input validation using regex and logical ranges.
- Clear separation between validation and formatting logic.
- Use of Strategy Design Pattern for flexible formatting.
- Application of SOLID principles for clean, maintainable code.
- Separation of concerns for testability and extensibility.
- Use of standard Java libraries and Spring Boot for ease of deployment.
- Modular design to allow independent testing.

## Testing
- Unit tests cover input validation, normal and edge time cases.

- Executed via mvn test and integrated with JaCoCo for code coverage.

- Encourages Test-Driven Development and regression safety.

## **JaCoCo Code Coverage**
- Integrated via Maven plugin to generate coverage reports after tests.

- Run locally with mvn clean test jacoco:report.

- Reports are created in target/site/jacoco/index.html for detailed coverage insights.

## SonarQube Integration
- Static code analysis and vulnerability detection integrated using Sonar Maven Plugin.

- Run using mvn sonar:sonar with project key, host URL, and token configured.

- Ensures code quality, identifies bugs, vulnerabilities, and code smells early.

## Improvements in the Future
- Support for additional time formats or locales.

- Enhanced API security with OAuth2/JWT.

- Implement asynchronous logging and monitoring.

- Container orchestration with Kubernetes and advanced CI/CD pipelines and GitHub Actions.

- User-friendly web UI for input and spoken time visualization.

## Additional Information
- Dockerfile provided for containerizing the app to simplify deployment.

- Code style and best practices maintained for maintainability.

- Detailed README.md and documentation for onboarding new developers.

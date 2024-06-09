# Exceptions in Java

## Introduction

An exception is an event that disrupts the normal flow of a program's execution. These exceptions can occur during the runtime of a program and can be caused by various issues such as incorrect input, network problems, or hardware malfunctions.

Exceptions in Java are represented by objects from classes that extend the Throwable class. There are two main types of exceptions in Java: checked exceptions and unchecked exceptions. Checked exceptions are checked at compile time, while unchecked exceptions are not.

Handling exceptions properly is important for writing robust and maintainable Java programs. It helps in dealing with unexpected situations effectively and ensures that the program does not crash or terminate abruptly.

## Problem statement

> Send a GET request to Google.com (https://www.google.com) and prints the HTML response.

- [Solution 1](./src/main/java/info/jab/problems/Solution1.java)
- [Solution 2](./src/main/java/info/jab/problems/Solution2.java)
- [Solution 3](./src/main/java/info/jab/problems/Solution3.java)
- [Solution 4](./src/main/java/info/jab/problems/Solution4.java)
- [Solution 5](./src/main/java/info/jab/problems/Solution5.java)
- [Solution 6](./src/main/java/info/jab/problems/Solution6.java)

## Either<L,R>

*Either<L, R>* is a commonly used data type that encapsulates a value of one of two possible types. It represents a value that can be either an "error" (left) or a "success" (right). This is particularly useful for error handling and avoiding exceptions.

### Example:

```java
public class Solution6 implements ISolution {

    private static final Logger logger = LoggerFactory.getLogger(Solution4.class);

    sealed interface ConnectionProblem permits InvalidURI, InvalidConnection {}

    record InvalidURI() implements ConnectionProblem {}

    record InvalidConnection() implements ConnectionProblem {}

    //The business logic is splitted in parts
    //Reducing the number of exceptions handling in the class
    @Override
    public String extractHTML(String address) {
        //Error handling handling in the origin
        Function<String, Either<ConnectionProblem, String>> toHTML = param -> {
            try {
                URI uri = new URI(param);
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                return Either.right(response.body());
            } catch (URISyntaxException | IllegalArgumentException ex) {
                logger.warn(ex.getLocalizedMessage(), ex);
                return Either.left(new InvalidURI());
            } catch (IOException | InterruptedException ex) {
                logger.warn(ex.getLocalizedMessage(), ex);
                return Either.left(new InvalidConnection());
            }
        };

        var result = toHTML.apply(address);
        return switch (result) {
            case Either.Right<ConnectionProblem, String> right -> right.value();
            default -> "";
        };
    }
}
```

### Who is using Either in Github?

- https://github.com/SeleniumHQ/selenium/
- https://github.com/bazelbuild/bazel/
- https://github.com/strimzi/strimzi-kafka-operator/
- https://github.com/camunda/camunda/
- https://github.com/awslabs/dqdl/

## How to build in local?

```bash
git submodule update --init --recursive

sdk env install
./mvnw clean verify
./mvnw clean compile exec:java -Dexec.mainClass="info.jab.jdk.ExceptionFinderExample"
./mvnw clean compile exec:java -Dexec.mainClass="info.jab.problems.Solution1"
./mvnw clean test -Dtest=ExceptionFinderTest#should_group_exceptions_by_javaModule
./mvnw clean test -Dtest=Solution1Test


./mvnw prettier:write
```

## Exceptions evolution

![](./docs/exception-evolution.png)

| JDK   | Checked Exceptions | Unchecked Exceptions | Diff |
|-------|--------------------|----------------------|------|
| JDK6  | 166                | 102                  | -64  |
| JDK7  | 128                | 79                   | -49  |
| JDK8  | 171                | 114                  | -57  |
| JDK9  | 150                | 136                  | -14  |
| JDK10 | 149                | 135                  | -14  |
| JDK11 | 119                | 121                  | 2    |
| JDK12 | 121                | 124                  | 3    |
| JDK13 | 121                | 124                  | 3    |
| JDK14 | 120                | 124                  | 4    |
| JDK15 | 117                | 119                  | 2    |
| JDK16 | 117                | 121                  | 4    |
| JDK17 | 116                | 120                  | 4    |
| JDK18 | 117                | 119                  | 2    |
| JDK19 | 118                | 122                  | 4    |
| JDK20 | 117                | 122                  | 5    |
| JDK21 | 120                | 122                  | 2    |
| JDK22 | 120                | 122                  | 2    |
| JDK   | 120                | 121                  | 1    |

## The Top 10 most used Exceptions in java.base module

- CheckedException, IOException, 2837
- UncheckedException, IllegalArgumentException, 2357
- UncheckedException, NullPointerException, 1385
- CheckedException, RuntimeException, 834
- UncheckedException, UnsupportedOperationException, 747
- UncheckedException, SecurityException, 695
- UncheckedException, IllegalStateException, 664
- UncheckedException, IndexOutOfBoundsException, 453
- UncheckedException, ClassCastException, 381
- CheckedException, InterruptedException, 306

## References

- https://github.com/openjdk/
- https://github.com/openjdk/jdk
- https://www.oracle.com/technical-resources/articles/enterprise-architecture/effective-exceptions-part1.html
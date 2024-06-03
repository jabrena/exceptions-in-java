# Exceptions in Java

## Introduction

An exception is an event that disrupts the normal flow of a program's execution. These exceptions can occur during the runtime of a program and can be caused by various issues such as incorrect input, network problems, or hardware malfunctions.

Exceptions in Java are represented by objects from classes that extend the Throwable class. There are two main types of exceptions in Java: checked exceptions and unchecked exceptions. Checked exceptions are checked at compile time, while unchecked exceptions are not.

Handling exceptions properly is important for writing robust and maintainable Java programs. It helps in dealing with unexpected situations effectively and ensures that the program does not crash or terminate abruptly.

![](docs/exceptions.gif)

Source: https://web.deu.edu.tr/doc/oreily/java/langref/ch09_04.htm 

```bash
java --list-modules
```

## How to build in local?

```bash
git submodule update --init --recursive

sdk env install
./mvnw clean verify
./mvnw clean compile exec:java -Dexec.mainClass="info.jab.jdk.ExceptionCounterExample" -Dexec.args="--enable-preview"

./mvnw prettier:write
```

## Exception inventory in Java 22

- [Checked Exceptions in Java 22](./docs/jdk22-checked-exceptions.md)
- [Unchecked Exceptions in Java 22](./docs/jdk22-unchecked-exceptions.md)

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

## References

- https://github.com/openjdk/
- https://github.com/openjdk/jdk
- https://www.oracle.com/technical-resources/articles/enterprise-architecture/effective-exceptions-part1.html
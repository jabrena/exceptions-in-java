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
| JDK   | 120                | 121                  | 1    |

## Exceptions from module java.base

```
ExceptionDetail[jdk=jdk22, type=CheckedException, name=IOException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=CheckedException, name=CloneNotSupportedException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=CheckedException, name=InterruptedException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=CheckedException, name=ReflectiveOperationException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=CheckedException, name=RuntimeException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=CheckedException, name=LambdaConversionException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=CheckedException, name=StringConcatException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=CheckedException, name=URISyntaxException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=CheckedException, name=GeneralSecurityException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=CheckedException, name=PrivilegedActionException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=CheckedException, name=ParseException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=CheckedException, name=TooManyListenersException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=CheckedException, name=BrokenBarrierException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=CheckedException, name=ExecutionException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=CheckedException, name=TimeoutException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=CheckedException, name=DataFormatException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=CheckedException, name=DestroyFailedException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=CheckedException, name=RefreshFailedException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=CheckedException, name=UnsupportedCallbackException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=CheckedException, name=CertificateException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=CheckedException, name=AnalyzerException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=CheckedException, name=SAXException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=CheckedException, name=XMLStreamException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=CheckedException, name=TranslatedException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=CheckedException, name=FtpProtocolException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=CheckedException, name=LocaleSyntaxException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=CheckedException, name=UnixException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=CheckedException, name=WindowsException.java, javaModule=java.base]

ExceptionDetail[jdk=jdk22, type=UncheckedException, name=UncheckedIOException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=ArithmeticException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=ArrayStoreException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=ClassCastException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=EnumConstantNotPresentException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=IllegalArgumentException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=IllegalCallerException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=IllegalMonitorStateException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=IllegalStateException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=IndexOutOfBoundsException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=LayerInstantiationException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=MatchException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=NegativeArraySizeException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=NullPointerException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=SecurityException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=TypeNotPresentException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=UnsupportedOperationException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=WrongThreadException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=AnnotationTypeMismatchException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=IncompleteAnnotationException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=WrongMethodTypeException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=FindException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=InvalidModuleDescriptorException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=ResolutionException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=InaccessibleObjectException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=MalformedParameterizedTypeException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=MalformedParametersException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=UndeclaredThrowableException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=FileSystemAlreadyExistsException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=FileSystemNotFoundException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=ProviderNotFoundException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=ProviderException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=DateTimeException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=ConcurrentModificationException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=EmptyStackException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=IllformedLocaleException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=MissingResourceException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=NoSuchElementException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=CompletionException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=RejectedExecutionException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=StructureViolationException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=UnsupportedClassVersionException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=NotImplementedException.java, javaModule=java.base]
ExceptionDetail[jdk=jdk22, type=UncheckedException, name=PendingException.java, javaModule=java.base]
```

## References

- https://github.com/openjdk/
- https://github.com/openjdk/jdk
- https://www.oracle.com/technical-resources/articles/enterprise-architecture/effective-exceptions-part1.html
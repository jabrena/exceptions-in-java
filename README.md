# Exceptions in Java

**Checked Exceptions:**
Checked exceptions are exceptions that are checked at compile-time. The Java compiler requires methods that can throw checked exceptions to either handle them within a try-catch block or declare them using the throws keyword in the method signature. This ensures that the programmer is aware of potential issues and has made provisions to handle them. Common examples of checked exceptions include IOException, SQLException, and ClassNotFoundException.

**Unchecked Exceptions:**
Unchecked exceptions, also known as runtime exceptions, are exceptions that are not checked at compile-time but rather at runtime. These exceptions extend the RuntimeException class. The Java compiler does not require methods to explicitly handle or declare unchecked exceptions. They typically indicate programming errors such as logic mistakes or improper use of an API. Examples include NullPointerException, ArrayIndexOutOfBoundsException, and IllegalArgumentException.

![](docs/exceptions.gif)
Source: https://web.deu.edu.tr/doc/oreily/java/langref/ch09_04.htm 

![](./docs/exception-evolution.png)

| JDK   | Checked exceptions | Unchecked exceptions |
|-------|--------------------|----------------------|
| JDK6  | 239                | 178                  |
| JDK7  | 244                | 178                  |
| JDK8  | 322                | 239                  |
| JDK9  | 362                | 345                  |
| JDK10 | 349                | 344                  |
| JDK11 | 353                | 544                  |
| JDK12 | 359                | 544                  |
| JDK13 | 354                | 546                  |
| JDK14 | 360                | 367                  |
| JDK15 | 363                | 361                  |
| JDK16 | 367                | 364                  |
| JDK17 | 369                | 362                  |
| JDK18 | 372                | 364                  |
| JDK19 | 381                | 376                  |
| JDK20 | 387                | 378                  |
| JDK21 | 394                | 381                  |
| JDK22 | 396                | 395                  |



## How to build in local?

```bash
git submodule add https://github.com/openjdk/jdk6
git submodule add https://github.com/openjdk/jdk7
git submodule add https://github.com/openjdk/jdk8
git submodule add https://github.com/openjdk/jdk9
git submodule add https://github.com/openjdk/jdk10
git submodule add https://github.com/openjdk/jdk11
git submodule add https://github.com/openjdk/jdk12
git submodule add https://github.com/openjdk/jdk13
git submodule add https://github.com/openjdk/jdk14
git submodule add https://github.com/openjdk/jdk15
git submodule add https://github.com/openjdk/jdk16

git submodule add https://github.com/openjdk/jdk17
git submodule add https://github.com/openjdk/jdk18
git submodule add https://github.com/openjdk/jdk19
git submodule add https://github.com/openjdk/jdk20
git submodule add https://github.com/openjdk/jdk21
git submodule add https://github.com/openjdk/jdk22

git submodule update --init --recursive
git submodule update --remote

./mvnw clean compile exec:java -Dexec.mainClass="info.jab.jdk.ExceptionCounterExample" -Dexec.args="--enable-preview"

./mvnw prettier:write
```
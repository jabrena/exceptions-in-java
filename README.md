# Exceptions in Java

**Checked Exceptions:**
Checked exceptions are exceptions that are checked at compile-time. The Java compiler requires methods that can throw checked exceptions to either handle them within a try-catch block or declare them using the throws keyword in the method signature. This ensures that the programmer is aware of potential issues and has made provisions to handle them. Common examples of checked exceptions include IOException, SQLException, and ClassNotFoundException.

**Unchecked Exceptions:**
Unchecked exceptions, also known as runtime exceptions, are exceptions that are not checked at compile-time but rather at runtime. These exceptions extend the RuntimeException class. The Java compiler does not require methods to explicitly handle or declare unchecked exceptions. They typically indicate programming errors such as logic mistakes or improper use of an API. Examples include NullPointerException, ArrayIndexOutOfBoundsException, and IllegalArgumentException.

![](docs/exceptions.gif)
Source: https://web.deu.edu.tr/doc/oreily/java/langref/ch09_04.htm 

![](./docs/exception-evolution.png)

| JDK   | Checked Exceptions | Unchecked Exceptions |
|-------|--------------------|----------------------|
| JDK6  | 166                | 102                  |
| JDK7  | 131                | 80                   |
| JDK8  | 174                | 114                  |
| JDK9  | 166                | 141                  |
| JDK10 | 161                | 141                  |
| JDK11 | 137                | 128                  |
| JDK12 | 141                | 133                  |
| JDK13 | 139                | 134                  |
| JDK14 | 139                | 137                  |
| JDK15 | 136                | 132                  |
| JDK16 | 136                | 134                  |
| JDK17 | 137                | 139                  |
| JDK18 | 138                | 138                  |
| JDK19 | 143                | 143                  |
| JDK20 | 142                | 141                  |
| JDK21 | 146                | 140                  |
| JDK22 | 146                | 143                  |

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
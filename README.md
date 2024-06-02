# exceptions-in-java

![](docs/exceptions.png)

```bash
git submodule add https://github.com/openjdk/jdk8
git submodule add https://github.com/openjdk/jdk9
git submodule add https://github.com/openjdk/jdk10
git submodule add https://github.com/openjdk/jdk11
git submodule add https://github.com/openjdk/jdk12


git submodule update --init --recursive
git submodule update --remote

./mvnw clean compile exec:java -Dexec.mainClass="info.jab.jdk.ExceptionCounterExample" -Dexec.args="--enable-preview"

./mvnw prettier:write
```
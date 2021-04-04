
#Logging
```
Log4j1.x has been deprecated in 2015.
https://blogs.apache.org/foundation/entry/apache_logging_services_project_announces

Reasons to upgrade from Log4j 1.x to Log4j 2
https://stackoverflow.com/questions/30019585/log4j2-why-would-you-use-it-over-log4j
https://logging.apache.org/log4j/2.x/manual/configuration.html

How to use log4j2
https://www.scalyr.com/blog/maven-log4j2-project/

log4j2 vs slf4j
https://stackoverflow.com/questions/41498021/is-it-worth-to-use-slf4j-with-log4j2

Using slf4 facade with log4j2
https://krishankantsinghal.medium.com/logback-slf4j-log4j2-understanding-them-and-learn-how-to-use-d33deedd0c46


import org.apache.log4j.Logger;
static final Logger LOGGER = Logger.getLogger(MyClass.class);

<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.30</version>
</dependency>

<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-core</artifactId>
    <version>1.2.3</version>
</dependency>

<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.2.3</version>
</dependency>
```

#Database Connections
```
DriveManger vs OracleDataSource

changed global user name
```

#Links
```
How to exclude Tomcat from SpringBoot
https://stackoverflow.com/questions/42275516/excluding-tomcat-dependencies-from-spring-boot-in-gradle

```
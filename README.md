# spring-boot-ldap-authentication-learning

This Project's Goal,use LDAP Embedded and External/Online Service in Spring Boot



## Technologies

- Spring Boot
- LDAP
- Docker-Compose
- Spring Boot Security
- Lombok(Optional)
- PhpLdapAdmin
- OpenLdap


## Installation

Dependency  this libraries in pom.xml

```java
 <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-ldap</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.ldap</groupId>
            <artifactId>spring-ldap-core</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-ldap</artifactId>
        </dependency>
        <dependency>
            <groupId>com.unboundid</groupId>
            <artifactId>unboundid-ldapsdk</artifactId>
        </dependency>
```

- Run openldap service with docker-compose up -d 
## Owner
[Muharrem Koç](https://github.com/muharremkoc)

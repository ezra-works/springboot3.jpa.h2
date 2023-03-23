# springboot3.jpa.h2

# Testing

>
> ### Unit Test [No Http calls, No database persist]
> ### Web Layer Test [Controller Tests]
> ### Integration Test [End to End Tests including persist (database, Storage)]

### @SpringBootTest

- Scans all the way to **Main method's @SpringBoot annotation** to load to applicationContext
- meaning will start tomcat with @ComponentScan, @Configuration, @EnableAutoConfiguration
- use @SpringBootTest if you want to do integration test and not for testing mock web-layer or unit testing
- takes time to complete the test

### Unit Testing

- does not require @SpringBootTest, or @WebMvcTest
- runs in milliseconds (ms)
- use it for service testing, bean/pojo testing
- mock service, repository at this testing

### Web Layer Test

- use @WebMvcTest annotation
- 
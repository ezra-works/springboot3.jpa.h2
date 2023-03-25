# springboot3.jpa.h2

## Why SpringBoot ?

- SpringBoot is a faster development framework to built web, desktop applications cutting down development time
- Most of the components are available out of the box which gives the advantage in cutting dev time
- These components are opinionated approach, meaning majority decides the framework
- The framework comes with in-built tomcat, solving app server installation/configuration etc
- The framework comes with in-built annotations which helps reduce boilerplate codes, configs(web.xml)
- The framework provides dependency injection or inversion of control
    - dependency injection helps mainly to test application in isolation
    - i.e. you can test application unit wise, web layer & integration layer
    - dependency injection helps in garbage collection as it allows only one instance at the application level

## Why JPA

- Jakarta Persistence API formerly called Java Persistence API
- Java uses POJO class based style and database use rows and columns, this API binds them
- i.e. taking a pojo(plain java object) and persist (save) it to a database
- each pojo.field represents a column in the database
- each pojo.field.value represents the data in that column in the database
- This API is the specification on how pojo objects should persist in the database

### So what is hibernate then ?

- hibernate is the implementation of the JPA specification
- hibernate being popular JPA implementation is the default in Springboot

# Testing

>
> ### Unit Test
> ### Web Layer Test
> ### Integration Test

### @SpringBootTest

- Scans all the way to **Main method's @SpringBoot annotation** to load to applicationContext
- meaning will start tomcat with @ComponentScan, @Configuration, @EnableAutoConfiguration
- use @SpringBootTest if you want to do integration test and not for testing mock web-layer or unit testing
- takes time to complete the test

### Unit Testing

[No Http calls, No database persist]

- does not require @SpringBootTest, or @WebMvcTest
- runs in milliseconds (ms)
- use it for service testing, bean/pojo testing
- mock service, repository at this testing

### Web Layer Test

[Controller Tests]

- use @WebMvcTest annotation
- use mockBean service to mock objects to the Spring application context
- In order to fire http request & response we need application context
- In this testing, we test the following
    - mockBean(service) to return stubbed values
    - use MockMvc to fire http request & response i.e. we test if http requests are fired and are mapped to the right
      controller, its mapping methods and their response
    - general unit test will not be able to fire such requests.
- These test will take slight time when compared with unit tests

### Integration Test

[End to End Tests including persist (database, Storage)]
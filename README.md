# wevo-backend

## Assumptions

The following assumptions made in the development process:
1. Created Feed controller and service to return XML string data of RSS feed by topic coming from 'https://medium.com/feed/'
2. Created Suggestions controller and service to save distinct last 5 successfull searches (the response from Medium website is not empty) in a Queue
3. Created tests for all controllers and services
4. Did NOT convert XML to JSON (to object) so the processing of the XML will be in the client (found Flutter web_feed library very useful)
5. Decide not to convert XML to model because I wanted to practice Flutter and I didn't need any information to save on the server regarding it, e.g: save to DB
Maybe this causes a slight performance issue and not clear API data returned but for the purpose of this exercise I found it more useful to parse XML on the client (so just return the response coming from Medium website)
6. I didn't use any cache regading the feed (also not for the suggestion's feed) cause articales maybe be added. It is possible to add cache and add a scheduler to update it when necessary)

## Requirements

Minimal [Spring Boot](http://projects.spring.io/spring-boot/) sample app.

For building and running the application you need:

- [JDK 20](https://www.oracle.com/java/technologies/downloads/#java20)
- [Maven 3](https://maven.apache.org)

## Running the application locally

You can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```



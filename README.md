# CodingDojo - Test your God d*mn ReST APIs

Wassup :ghost: ?

## Prerequisites

Some knowledge about Java/Maven.

## Story

### What is a REST API

In 2000, **Roy Fielding** introduced the **RE**presentational **S**tate **T**ransfer (REST) in his dissertation.

API is for Application Programming Interface

It’s a **contract** that explain how we use the service

### How to code serenely => Test

To code in good conditions we gotta write tests which allows you to be sure about :
 - Following the specs
 - Make no regressions

To do that, I'm gonna introduce you Rest Assured.

### Introduce to Rest-Assured

Rest Assured is a tool to help you to test your REST API in Java. It following the Given-When-Then "pattern".

For a little reminder :
- The **given** part describes the state of the world before you begin the behavior you're specifying in this scenario. You can think of it as the pre-conditions to the test.
- The **when** section is that behavior that you're specifying.
- Finally the **then** section describes the changes you expect due to the specified behavior.

```java
void awesomeTest(){
	given()
	// Here you configure your request, example: set the header, contentType, etc.  
	.when()
	// Here you give the url, exemple: get("lolilol/loooool")
	.then()
	// Here you check whatever, example: the field "id" equals "lol"
}
```

### Let's test our API
[Exercises sheet](./EXERSICES.md)

## Conclusion
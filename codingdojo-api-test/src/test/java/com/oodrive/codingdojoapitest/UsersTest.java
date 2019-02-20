package com.oodrive.codingdojoapitest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

import org.apache.http.HttpStatus;
import org.junit.Test;

public class UsersTest {

	@Test
	public void getAllUsers(){
		given()
				.baseUri("http://localhost")
				.port(8080)
				.basePath("/users")
		.when()
				.get()
		.then()
				.statusCode(HttpStatus.SC_OK)
				.body("id", contains(0, 1, 2, 3, 4));
	}

	@Test
	public void getOneUser(){
		given()
				.baseUri("http://localhost")
				.port(8080)
				.basePath("/users")
		.when()
				.pathParam("id", 0)
				.get("/{id}")
		.then()
				.statusCode(HttpStatus.SC_OK)
				.body("id", equalTo(0))
				.body("lastName", equalTo("Lovelace"));
	}

	@Test
	public void disableUser(){
		// TODO
	}

	@Test
	public void enableUser(){
		// TODO
	}
}

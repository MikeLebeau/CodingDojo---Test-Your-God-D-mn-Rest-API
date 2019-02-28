package com.oodrive.codingdojoapitest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

import com.oodrive.codingdojoapitest.models.User;


public class UsersTestSolution {

	/**
	 * SCENARIO: I wanna get all users
	 * GIVEN:
	 * 	I've the base URI value of my ReST API (http://localhost)
	 * 	and I've the port number of my ReST API (8080)
	 * 	and I've the base path (/users)
	 * WHEN:
	 * 	I send a GET Request
	 * THEN:
	 * 	I should have a response's status code 200
	 *	I should have the list of all users
	 */
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
				.body("id", contains(1, 2, 3, 4, 5));
	}


	/**
	 * SCENARIO: I wanna get one user by his identifier
	 * GIVEN:
	 * 	I've the base URI value of my ReST API (http://localhost)
	 * 	and I've the port number of my ReST API (8080)
	 * 	and I've the base path (/users)
	 * WHEN:
	 * 	I send a GET Request with the user's identifier '1' as path parameter (/1)
	 * THEN:
	 * 	I should have a response's status code 200
	 *	I should have the user with '1' as identifier: Ada Lovelace
	 */
	@Test
	public void getOneUser(){
		given()
				.baseUri("http://localhost")
				.port(8080)
				.basePath("/users")
		.when()
				.pathParam("id", 1)
				.get("/{id}")
		.then()
				.statusCode(HttpStatus.SC_OK)
				.body("id", equalTo(1))
				.body("lastName", equalTo("Lovelace"));
	}

	/**
	 * SCENARIO: I wanna disable a user
	 * GIVEN:
	 * 	I've the base URI value of my ReST API (http://localhost)
	 * 	and I've the port number of my ReST API (8080)
	 * 	and I've the base path (/users)
	 * WHEN:
	 * 	I send a PUT Request with the user's identifier '2' as path parameter (/2/disable)
	 * THEN:
	 * 	I should have a response's status code 200
	 *	I should have the disabled user with '2' as identifier: Rich Hickey
	 */
	@Test
	public void disableUser(){
		given()
				.baseUri("http://localhost")
				.port(8080)
				.basePath("/users")
		.when()
				.pathParam("id", 2)
				.put("/{id}/disable")
		.then()
				.statusCode(HttpStatus.SC_OK)
				.body("id", equalTo(2))
				.body("lastName", equalTo("Hickey"))
				.body("active", equalTo(false));
	}

	/**
	 * SCENARIO: I wanna delete a user
	 * GIVEN:
	 *	I've the base URI value of my ReST API (http://localhost)
	 * 	and I've the port number of my ReST API (8080)
	 * 	and I've the base path (/users)
	 * WHEN:
	 *	I send a DELETE Request with the user's identifier '5' as path parameter (/5)
	 * THEN:
	 * 	I should have a response's status code 200
	 */
	@Test
	public void deleteUser(){
		given()
				.baseUri("http://localhost")
				.port(8080)
				.basePath("/users")
		.when()
				.pathParam("id", 5)
				.delete("/{id}")
		.then()
				.statusCode(HttpStatus.SC_OK);

		List<User> users = given()
				.baseUri("http://localhost")
				.port(8080)
				.basePath("/users")
		.when()
				.get()
		.then()
				.statusCode(HttpStatus.SC_OK)
				.body("size()", equalTo(4))
				.extract().as(List.class);

		Assert.assertEquals(4, users.size());
	}

	/**
	 * New function
	 *
	 * SCENARIO: I wanna get all users sorted by lastName
	 * GIVEN:
	 *	I've the base URI value of my ReST API (http://localhost)
	 * 	and I've the port number of my ReST API (8080)
	 * 	and I've the base path (/users)
	 * WHEN:
	 *	I send a GET Request with 'lastName' as path parameter (?sort=lastName)
	 * THEN:
	 * 	I should have a response's status code 200
	 * 	and all users in this order => Cockburn, Fowler, Gosling, Hickey, Lovelace
	 */
	@Test
	public void getUsersOrdered(){
		given()
				.baseUri("http://localhost")
				.port(8080)
				.basePath("/users")
		.when()
				.queryParam("sort", "lastName")
				.get()
		.then()
				.statusCode(HttpStatus.SC_OK)
				.body("lastName", contains(
						"Cockburn",
						"Fowler",
						"Gosling",
						"Hickey",
						"Lovelace"));
	}
}

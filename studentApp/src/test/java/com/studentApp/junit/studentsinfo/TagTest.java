package com.studentApp.junit.studentsinfo;

import org.junit.Test;
import org.junit.runner.RunWith;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;

@RunWith(SerenityRunner.class)
public class TagTest {
	
	@WithTag("studentfeature:NEGATIVE")
	@Title("Provide a 405 status code when incorrect HTTP method is used to access a resource")
	@Test
	public void invalidMethod() {
		SerenityRest.rest().given().when().post("/list")
		.then().statusCode(405).log().all(); //validate an incorrect HTTP
	}
	
	@WithTags({ //to have groups of tests
		@WithTag("studentfeature:SMOKE"),
		@WithTag("studentfeature:POSITIVE")})
	@WithTag("studentfeature:POSITIVE")
	@Title("This will verify if a status code of 200 is returned for GET request")
	public void verifyIfTheStatusCodeIs200() {
		SerenityRest.rest().given().when()
		.get("/list").then().statusCode(200); //validate if the resource was found
	}
	
	@WithTags({
			@WithTag("studentfeature:SMOKE"),
			@WithTag("studentfeature:NEGATIVE")})
	@WithTag("studentfeature:NEGATIVE")
	@Title("This test will provide an error code of 400 when the user tries to access an invalid resource")
	@Test
	public void incorrectResource() {
		SerenityRest.rest().given().when().get("/list123")
		.then().statusCode(400).log().all();
		
	}
	
	//c61a766d2841410b8c295f59e1502ffa
}

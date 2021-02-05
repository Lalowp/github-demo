package com.studentApp.junit.studentsinfo;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Title;

@RunWith(SerenityRunner.class)
public class FirstSerenityTest {
	
	@BeforeClass
	public static void init(){
		RestAssured.baseURI="http://localhost:9090/student";
	}
	
	@Test
	public void getAllStudents(){
		SerenityRest.given()
		.when()
		.get("/list")
		.then()
		.statusCode(200);
	}
	
	@Test
	public void thisIsAFailingTest() {
		SerenityRest.given()
		.when()
		.get("/list")
		.then()
		.statusCode(500); 
	}
	
	@Pending //when I dont want to run the test yet
	@Test
	public void thisIsAPendingTest() {
		
	}
	
	@Ignore //to ignore the test or to be skipped for no execution
	@Test
	public void thisIsASkippedTest() {
		
	}
	
	//Arithmetic error
	@Test
	public void thisIsATestWithError() {
		System.out.println("error: "+5/0);
	}
	
	@Test
	public void fileDoesNotExist() throws IOException {
		File file = new File("E://text.txt");
		FileReader fr = new FileReader(file); 
		fr.close();
	}
	
	@Manual //For not using an automated tool
	@Test
	public void thisIsAManualTest() {
		
	}
	
	@Title("This test will get the info of all students within the StudentApp")
	@Test
	public void test01() {
		SerenityRest.given()
		.when()
		.get("/list")
		.then()
		.statusCode(200);		
	}
	
	
	
	

}

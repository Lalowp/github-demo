package com.studentApp.junit.studentsinfo;




import java.util.ArrayList;
import java.util.HashMap;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.studentApp.cucumber.serenity.StudentSerenitySteps;
import com.studentApp.testbase.TestBase;
import com.studentApp.util.ReuseableSpecifications;
import com.studentApp.util.TestUtils;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentsCrudTest extends TestBase {
	
	static String firstName = "SMOKEUSER"+TestUtils.getRandomValue();
	static String lastname  = "SMOKEUSER"+TestUtils.getRandomValue();
	static String programme = "ComputerScience";
	static String email = TestUtils.getRandomValue()+"xyz@gmail.com";
	static int studentId; 
	
	@Steps
	StudentSerenitySteps steps; //create the object
	
	@Title("This test will create a new student")
	@Test
	public void test001() { //createStudent
		
		ArrayList<String> courses = new ArrayList<String>();
		courses.add("JAVA");
		courses.add("C++");
		
		steps.createStudent(firstName, lastname, email, programme, courses).statusCode(201)
		.spec(ReuseableSpecifications.getGenericResponseSpec()); 
		
	}
	
	@Title("Verify if the student was added to the application")
	@Test
	public void test002() { //getStudent
		
		HashMap<String, Object> value = steps.getStudentInfoByFirstName(firstName);
		//assertThat(value, hasValue(firstName));  deprecated method
		studentId = (int) value.get("id");
	}
	
	@Title("Update the user information and verify the updated information")
	@Test
	public void test003() {
		String p1 = "findAll{it.firstName=='";
		String p2 = "'}.get(0)";
		ArrayList<String> courses = new ArrayList<String>();
		courses.add("JAVA");
		courses.add("C++");
		
		firstName = firstName + "_Updated"; 
		steps.updateStudent(studentId, firstName, lastname, email, programme, courses);
		
		//To check again if it is in the list
		HashMap<String, Object> value = steps.getStudentInfoByFirstName(firstName);
	}
	
	@Title("Delete the student and verify if the student is deleted!")
	@Test
	public void test004() {
		
		steps.deleteStudent(studentId);
		steps.getStudentById(studentId).statusCode(404); //when checking again if the response matches
	}

}

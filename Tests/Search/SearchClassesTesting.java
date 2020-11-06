package finalproject201;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import java.awt.List;
import java.io.*;

import org.junit.*;

public class SearchClassesTesting
{	
	public static void main(String[] args) {
		System.out.println(whiteBoxTests());
		// System.out.println(blackBoxTests());
	}
	
	// white box testing: testing inputs against desired outputs as a developer
	public static String whiteBoxTests() {
		if (unitTests()) {
			return "Passed all white box tests";
		} 
		else {
			return "Failed white box - unit tests";
		}
	}
	
	// black box testing: testing inputs against desired outputs as a user
	public static String blackBoxTests() {
		if(regressionTests()) {
			return "Passed all black box tests";
		}
		else {
			return "Failed black box - regression tests";
		}
	}

	// unit testing: test methods individually to ensure they are outputting as expected
	public static Boolean unitTests() {
		SearchClasses mySearch;
		
		// Test f2a
		mySearch = setUp1a();
		if (!f2a(mySearch)) {
			System.out.println("Failed printClassSearchRes");
			return false;
		}
		
		// Test f2b
		mySearch = setUp1b();
		if (!f2b(mySearch)) {
			System.out.println("Failed printClassSearchRes");
			return false;
		}
		
		// Test f3a
		mySearch = setUp2a();
		if (!f3a(mySearch)) {
			System.out.println("Failed selectProf");
			return false;
		}
		
		// Test f3b
		mySearch = setUp2b();
		if (!f3b(mySearch)) {
			System.out.println("Failed selectProf");
			return false;
		}
		
		// Test f4a
		mySearch = setUp2a();
		if (!f4a(mySearch)) {
			System.out.println("Failed printReviews");
			return false;
		}
		
		// Test f4b
		mySearch = setUp2b();
		if (!f4b(mySearch)) {
			System.out.println("Failed printReviews");
			return false;
		}
		
		return true;
	}
	
	// setUp1
	public static SearchClasses setUp1a() {
		// input CSCI-201
		System.out.println("Input: CSCI-201");
		SearchClasses mySearch = new SearchClasses(); 
		mySearch.initialize1();
		
		return mySearch;
	}
	
	// setUp2
	public static SearchClasses setUp1b() {
		// input CSCI
		System.out.println("Input: CSCI");
		SearchClasses mySearch = new SearchClasses();
		mySearch.initialize1();

				
		return mySearch;
	}
	
	// setUp1
		public static SearchClasses setUp2a() {
			// input CSCI-201
			System.out.println("Input: CSCI-201\nInput: 2");
			SearchClasses mySearch = new SearchClasses(); 
			mySearch.initialize2();
			
			return mySearch;
		}
		
		// setUp2
		public static SearchClasses setUp2b() {
			// input CSCI
			System.out.println("Input: CSCI\nInput: 5");
			SearchClasses mySearch = new SearchClasses();
			mySearch.initialize2();

					
			return mySearch;
		}
		
	// printClassSearchRes #1
	public static Boolean f2a(SearchClasses mySearch) {
		try {
			ArrayList<String> profNames = mySearch.printClassSearchRes();

			if (profNames.size() != 3) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	// printClassSearchRes #2
	public static Boolean f2b(SearchClasses mySearch) {
		try {
			ArrayList<String> profNames = mySearch.printClassSearchRes();

			if (profNames.size() != 20) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	// selectProf #1
	public static Boolean f3a(SearchClasses mySearch) {
		int profNum = mySearch.selectProf();
		
		if (profNum != 2) {
			return false;
		}
		
		return true;
	}
	
	// selectProf #2
	public static Boolean f3b(SearchClasses mySearch) {
		int profNum = mySearch.selectProf();
		
		if (profNum != 5) {
			return false;
		}
		
		return true;
	}
	
	// printReviews #1
	public static Boolean f4a(SearchClasses mySearch) {
		try {
			mySearch.printReviews();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	// printReviews #2
		public static Boolean f4b(SearchClasses mySearch) {
			try {
				mySearch.printReviews();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return true;
		}
	
	// regression testing: tests that ensure changes made to code down the line don't break things
	public static Boolean regressionTests() {
		SearchClasses mySearch = new SearchClasses();
		
		// test 1
		InputStream sysInBackup = System.in;
		String data = "CSCI" + System.lineSeparator() + "2";
		
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		
		System.setIn(sysInBackup);
		
		// test 2
		 
		 
		return true;
		
	}
	
}
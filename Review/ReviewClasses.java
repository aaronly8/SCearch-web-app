import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.awt.List;
import java.io.*;

public class ReviewClasses {
	private static String db = "jdbc:mysql://localhost/ReviewDummy?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=America/Los_Angeles";
	private static String user = "root";
	private static  String pwd  = "root";
	private static Integer year = Calendar.getInstance().get(Calendar.YEAR);
	
	public static void main(String[] args) {	
		uploadReview("student@usc.edu","CSCI 270","Kempe","CS",year,"Hello there");
		editReview("bruhhhh",year,1);
		deleteReview(4);
	}
	
	
	public static Boolean uploadReview(String userEmail,String classs,String prof,String major,Integer yearUpload,String reviewContent) {
		//userEmail,class,prof,major,yearUpload,reviewContent
		//UPLOAD review
		String upload = "INSERT INTO Review (userEmail,class,prof,major,yearUpload,reviewContent)\n" + 
				" values(?,?,?,?,?,?);";
		try (Connection conn = DriverManager.getConnection(db, user, pwd);
				CallableStatement st= conn.prepareCall(upload);){
					st.setString(1,userEmail);
					st.setString(2,classs);
					st.setString(3,prof);
					st.setString(4,major);
					st.setInt(5,yearUpload);
					st.setString(6,reviewContent);
					int success = st.executeUpdate();
					if(success>0) {
						System.out.println("Success");
						return true;
					}else {
						System.out.println("Fail");
						return false;
					}						
				} 
				catch (SQLException ex)
				{System.out.println("SQLException: " + ex.getMessage());
					return false;
				}
	}
	
	public static Boolean editReview(String reviewContent, Integer yearUpload,Integer id) {
		
		//EDIT review
		//Assuming user is logged in
		String edit = "UPDATE Review\n" + "SET \n" + "reviewContent = ?,\n" + 
				"yearUpload = ?\n" + "WHERE\n" + "    id = ?;";
		try (Connection conn = DriverManager.getConnection(db, user, pwd);
				CallableStatement st= conn.prepareCall(edit);){
					st.setString(1,reviewContent);
					st.setInt(2,yearUpload);
					st.setInt(3,id);
					int success = st.executeUpdate();
					if(success>0) {
						System.out.println("Success");
						return true;
					}else {
						System.out.println("Fail");
						return false;
					}						
					
				} 
				catch (SQLException ex)
				{System.out.println("SQLException: " + ex.getMessage());
				return false;
				}
		
	}
	
	public static Boolean deleteReview(Integer id) {
		String delete = "DELETE FROM Review WHERE id = ?";
		try (Connection conn = DriverManager.getConnection(db, user, pwd);
				CallableStatement st= conn.prepareCall(delete);){
					st.setInt(1,id);
					int success = st.executeUpdate();
					if(success>0) {
						System.out.println("Success");
						return true;
					}else {
						System.out.println("Fail");
						return false;
					}						
				} 
				catch (SQLException ex)
				{System.out.println("SQLException: " + ex.getMessage());
				return false;
				}
		
		
	}
	
	
	

	/*
	 * 
	DROP DATABASE IF EXISTS ReviewDummy;
CREATE DATABASE ReviewDummy;
use ReviewDummy;

create table Review (
  id INT primary key AUTO_INCREMENT, 
  userEmail varchar(100), 
  class varchar(100), 
  prof varchar(50),
  major varchar (50),
  yearUpload INT,
  reviewContent varchar(10000)
  );

*/
	
	
	
	
	

}

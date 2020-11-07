import java.sql.*;
import java.util.*;
import java.awt.List;
import java.io.*;

public class ReviewFactory {
	
	private static String db = "jdbc:mysql://scearch.cgmp7xzel2am.us-west-1.rds.amazonaws.com:3306/scearch";
	private static String user = "admin";
	private static String pwd = "admin123";
	private static Integer year = Calendar.getInstance().get(Calendar.YEAR);
	public ReviewFactory(String db, String user, String pwd) {
		this.db = db;
		this.user = user;
		this.pwd = pwd;
	}
	
	public static void main(String[] args) {
		String db = "jdbc:mysql://scearch.cgmp7xzel2am.us-west-1.rds.amazonaws.com:3306/scearch";
		String user = "admin";
		String pwd = "admin123";
		
		ReviewFactory rev = new ReviewFactory(db, user, pwd);
		
		try {
			Connection conn = DriverManager.getConnection(db, user, pwd);
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}
		
		rev.uploadReview("Anonymous","CSCI-270","Introduction to Algorithms","David Kempe","Very cool class!",year,"CSCI");
		rev.editReview("bruhhhh",1);
		rev.deleteReview(2581278);
	}
	
	
	public Boolean uploadReview(String display, String section, String classname, String prof, String body, Integer year, String major) {
		
		//generate some ID
		Random rand = new Random();
		Integer id = rand.nextInt(2147483647);
		
		//id, user, section, classname, prof, body, year, major
		//UPLOAD review
		String upload = "INSERT INTO reviews (display, section, classname, prof, body, year, major)\n" + 
				" values(?,?,?,?,?,?,?);";
		try (Connection conn = DriverManager.getConnection(db, user, pwd);
				CallableStatement st= conn.prepareCall(upload);){
					st.setString(1, display);
					st.setString(2, section);
					st.setString(3, classname);
					st.setString(4, prof);
					st.setString(5, body);
					st.setInt(6, year);
					st.setString(7, major);
					
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
	
	public Boolean editReview(String body, Integer id) {
		
		//EDIT review
		//Assuming user is logged in
		String edit = "UPDATE reviews " + "SET " + "body = ?" + "WHERE id = ?";
		try (Connection conn = DriverManager.getConnection(db, user, pwd);
				CallableStatement st= conn.prepareCall(edit);){
					st.setString(1,body);
					st.setInt(2,id);
					int success = st.executeUpdate();
					if(success>0) {
						System.out.println("Success");
						return true;
					} else {
						System.out.println("Fail");
						return false;
					}						
					
				} 
				catch (SQLException ex)
				{System.out.println("SQLException: " + ex.getMessage());
				return false;
				}
		
	}
	
	public Boolean deleteReview(Integer id) {
		String delete = "DELETE FROM reviews WHERE id = ?";
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
}

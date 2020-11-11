import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.List;
import java.io.*;

public class SearchClasses
{
	static final String db = "jdbc:mysql://scearch.cgmp7xzel2am.us-west-1.rds.amazonaws.com:3306/scearch?serverTimezone=PST";
	static final String user = "admin";
	static final String pwd = "admin123";
	static final String sql = "SELECT Instructor, Overall_Rating, Days, Time\n" + 
			"FROM ClassInfo\n" + 
			"WHERE Course_number LIKE ? ";
	static final String sql2 = "SELECT user, body "+ 
			"FROM reviews\n" + 
			"WHERE prof= ? ";
	
	private static ResultSet rs;
	private static ResultSet rs2;
	
	private static String course;
	private static ArrayList<String> profNames;
	private static int profNum;
	private static int max;
	
	static Scanner sc;
	
	public ResultSet getRs() {
		return rs;
	}
	
	public int getProfNum() {
		return profNum;
	}
	
	// Initialization for first two functions
	public void initialize1 ()
	{
		try (Connection conn = DriverManager.getConnection(db, user, pwd);
				PreparedStatement ps = conn.prepareStatement(sql);) {
			course = getCourseToSearch();
			ps.setString(1, course);
			rs = ps.executeQuery();
			
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		} finally {
			sc.close();	
		}
	}
	
	// Initialization for third and fourth function
	public void initialize2()
	{
		try (Connection conn = DriverManager.getConnection(db, user, pwd);
				PreparedStatement ps = conn.prepareStatement(sql);
				PreparedStatement ps2 = conn.prepareStatement(sql2);) {

			course = getCourseToSearch();
			ps.setString(1, course);
			rs = ps.executeQuery();
			
			printClassSearchRes();
			max = profNames.size();

			profNum = selectProf();
			ps2.setString(1, profNames.get(profNum));
			
			rs2 = ps2.executeQuery();
			
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		} finally {
			sc.close();
		}
	}
	
	
	public void mainFunc(String[] args) {
		try (Connection conn = DriverManager.getConnection(db, user, pwd);
				PreparedStatement ps = conn.prepareStatement(sql);
				PreparedStatement ps2 = conn.prepareStatement(sql2);) {
			
			course = getCourseToSearch();
			ps.setString(1, course);
			rs = ps.executeQuery();
			
			printClassSearchRes();
			max = profNames.size();
			
			profNum = selectProf();
			ps2.setString(1, profNames.get(profNum));
			
			rs2 = ps2.executeQuery();
			printReviews();
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		} finally {
			sc.close();
		}
	}
	
	
	public String getCourseToSearch() {
		sc = new Scanner(System.in);
		System.out.print("Enter a class to search for (Ex: CSCI-201): ");
		String course= "%"+sc.nextLine().trim()+"%";
		return course;
	}
	
	public ArrayList<String> printClassSearchRes() throws SQLException{
		profNames = new ArrayList<String>();
		int i=1;
		while (rs.next()) {
			StringBuffer sb = new StringBuffer(i +". ");
			profNames.add(rs.getString("Instructor"));
			
			if (rs.getString("Instructor").length() < 12) {
				sb.append(rs.getString("Instructor") + "\t" + "\t" +
				rs.getString("Overall_Rating") + "\t");
			}
			else {
				sb.append(rs.getString("Instructor") + "\t" +
				rs.getString("Overall_Rating") + "\t");
			}
			
			if (rs.getString("Days").length() < 8) {
				sb.append(rs.getString("Days") + "\t" + "\t" +
				rs.getString("Time"));
			}
			else {
				sb.append(rs.getString("Days") + "\t" +
				rs.getString("Time"));
			}
			
			System.out.println(sb.toString());

			i++;
		}
		return profNames;
	}
	
	public int selectProf() {
		int num=-1;
		
		while(num==-1) {
			sc = new Scanner(System.in);
			System.out.print("Select a professor to see their past students:");
			String number= sc.nextLine().trim();
			try {
				num=Integer.parseInt(number);
				if(num>max || num<=0 )
					throw new Exception();
			}
			catch(Exception e){
				System.out.println("Please enter a valid professor number");
			}
		}
		
		return num-1;
	}
	
	public void printReviews() throws SQLException{
		while (rs2.next())
			System.out.println (
				rs2.getString("user") + "\t" +
				rs2.getString("body"));
	}
}

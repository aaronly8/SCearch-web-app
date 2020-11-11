import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.List;
import java.io.*;

public class SearchClasses
{
	public static void main (String[] args)
	{
		String db = "jdbc:mysql://scearch.cgmp7xzel2am.us-west-1.rds.amazonaws.com:3306/scearch?serverTimezone=PST";
		String user = "admin";
		String pwd = "admin123";
		String sql = "SELECT Instructor, Overall_Rating, Days, Time\n" + 
				"FROM ClassInfo\n" + 
				"WHERE Course_number LIKE ? ";
		String sql2 = "SELECT user, body "+ 
				"FROM reviews\n" + 
				"WHERE prof= ? ";

		try (Connection conn = DriverManager.getConnection(db, user, pwd);
			  PreparedStatement ps = conn.prepareStatement(sql);
				PreparedStatement ps2 = conn.prepareStatement(sql2);) {
			String course= getCourseToSearch();
			ps.setString(1, course);
			ResultSet rs = ps.executeQuery();
			ArrayList<String> profNames=printClassSearchRes(rs);
			int profNum= selectProf(profNames.size());
			ps2.setString(1, profNames.get(profNum));
			rs = ps2.executeQuery();
			printReviews(rs);
			
			
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
	}
	public static String getCourseToSearch() {
		
		Scanner sc= new Scanner(System.in);
		System.out.print("Enter a class to search for (Ex: CSCI-201): ");
		String course= "%"+sc.nextLine().trim()+"%";
		return course;
	}
	public static ArrayList<String> printClassSearchRes(ResultSet rs) throws SQLException{
		ArrayList<String> profNames=new ArrayList();
		int i=1;
		while (rs.next()) {
			profNames.add(rs.getString("Instructor"));
			System.out.println (i+". "+
				rs.getString("Instructor") + "\t" +
				rs.getString("Overall_Rating") + "\t" +
				rs.getString("Days") + "\t" +
				rs.getString("Time"));
			i++;
		}
		return profNames;
	}
	public static int selectProf(int max) {
		int num=-1;
		
		while(num==-1) {
			Scanner sc= new Scanner(System.in);
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
	public static void printReviews(ResultSet rs) throws SQLException{
		while (rs.next())
			System.out.println (
				rs.getString("user") + "\t" +
				rs.getString("body"));
	}
}
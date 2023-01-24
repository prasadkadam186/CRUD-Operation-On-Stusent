package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.protocol.Resultset;
@WebServlet("/displaylink")
public class displayStudent extends HttpServlet{
	Connection con;
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja7","root","sql@123");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Statement stmt=null;
		ResultSet rs=null;
		PrintWriter pw=resp.getWriter();
		String quary="select * from student_info";
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(quary);
			pw.print("<table border='2px'>");
			pw.print("<tr>");
			pw.print("<th>STUDENT_ID</td>");
			pw.print("<th>STUDENT_NAME</td>");
			pw.print("<th>STUDENT_STREAM</td>");
			pw.print("<th>STUDENT_DOB</td>");
			pw.print("</tr>");
			while(rs.next())
			{
				int id=rs.getInt(1);
				String name=rs.getString(2);
				String stream=rs.getString(3);
				String DOB=rs.getString(4);
				pw.print("<tr>");
				pw.print("<td>"+id+"</td>");
				pw.print("<td>"+name+"</td>");
				pw.print("<td>"+stream+"</td>");
				pw.print("<td>"+DOB+"</td>");
				pw.print("</tr>");
			}
			pw.print("</table>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

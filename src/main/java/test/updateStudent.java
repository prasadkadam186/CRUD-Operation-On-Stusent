package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/updatelink")
public class updateStudent extends HttpServlet {
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("studentid");
		String newstream=req.getParameter("studentstream");
		
		int sid=Integer.parseInt(id);
		
		PreparedStatement pstmt=null;
		
		String quary="update student_info set stream=? where sid=?";
		
		try {
			pstmt=con.prepareStatement(quary);
			pstmt.setString(1,newstream);
			pstmt.setInt(2, sid);
			int count=pstmt.executeUpdate();
			
			PrintWriter pw=resp.getWriter();
			pw.print("<h1>"+count+" RECORD UPDATED SUCESSFULLY" +"</h1>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

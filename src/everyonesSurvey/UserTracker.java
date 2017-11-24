package everyonesSurvey;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

import com.mysql.*;
import com.mysql.jdbc.Driver;
import java.util.*;
import java.util.Date;
/**
 * Servlet implementation class Main
 */
@MultipartConfig
public class UserTracker extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final static String SQL_PASSWORD="drwssp";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserTracker() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String mAct=request.getParameter("mact");
		
		response.setCharacterEncoding("utf-8");
		if(mAct==null){
			response.sendRedirect("displayquestion");
	
		}else{
			
			switch(mAct){
			case "login":
				try {
					login(request,response);
				} catch (SQLException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "signup":
				try {
					signup(request,response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			
			case "getfirstname":
				getFirstName(request, response);
				break;
			
			case "checklogin":
				checkLogin(request,response);
				break;
			case "hrefCheckLogin":
				hrefCheckLogin(request,response);
				break;
			default:
				response.sendRedirect("displayquestion");
				;
			}
		}
		
	}
	private void login(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/everyoneq","root",SQL_PASSWORD);
		Statement stmt=conn.createStatement();
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String nextPage=request.getParameter("nextPage");
		if(nextPage==null){
			nextPage="displayquestion";
		}
		ResultSet rs=stmt.executeQuery("SELECT password, firstname, lastname, userid from user WHERE username='"+username+"'");
		if(rs.next()){
			String realPass=rs.getString(1);
			if(password.equals(realPass)){
				HttpSession session=request.getSession();
				session.setAttribute("loggedin", true);
				session.setAttribute("username", username);
				String firstname=rs.getString(2);
				System.out.println("firstname:"+firstname);
				session.setAttribute("firstname", firstname);
				String lastname=rs.getString(3);
				session.setAttribute("lastname", lastname);
				long userid=rs.getLong(4);
				session.setAttribute("userid", userid);
				
				
				
				ResultSet rsEMail=stmt.executeQuery("select email from user where userid="+userid );
				rsEMail.next();
				
				String eMail=rsEMail.getString(1);
				User user=new User(username, firstname, lastname, eMail, userid);
				session.setAttribute("user", user);
				PrintWriter pw= response.getWriter();
				pw.write("loggedin"+nextPage);
				pw.close();
			}else{
				
				PrintWriter pw= response.getWriter();
				pw.write("Incorrect password");
			}
		}else{
			PrintWriter pw= response.getWriter();
			pw.write("Incorrect Username");
		}
		conn.close();
	}
	private void signup(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/everyoneq","root",SQL_PASSWORD);
		Statement lookup=conn.createStatement();
		ResultSet rs= lookup.executeQuery("SELECT * from user WHERE username='"+username+"'");
		if(rs.next()){
			response.getWriter().println("username already exists");
			return;
		}
		Statement stmt=conn.createStatement();
		stmt.executeUpdate("INSERT INTO user(username, password, email, firstname, lastname) VALUE('"
		+username+"','"+password+"','"+email+"','"+firstname+"','"+lastname+"')");
		response.sendRedirect("login.jsp");
		conn.close();
	}
	
	private void getFirstName(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter pw=response.getWriter();
		String firstname=(String) request.getSession().getAttribute("firstname");
		pw.print(firstname);
	}
	
	private void checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		if(request.getSession().getAttribute("loggedin")==null){
			response.getWriter().print("no");
			return;
		}else if((boolean)request.getSession().getAttribute("loggedin")==false){
			response.getWriter().print("no");
			return;
		}
		response.getWriter().print("yes");
		
	}
	private void hrefCheckLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String nextPage=request.getParameter("nextPage");
		if(request.getSession().getAttribute("loggedin")==null||(boolean)request.getSession().getAttribute("loggedin")==false){
			request.setAttribute("nextPage", nextPage);
			request.getRequestDispatcher("/login.jsp")
			.forward(request, response);
		}else{
			response.sendRedirect(nextPage);
		}
	}
	private void redirectToLogin(HttpServletRequest request, HttpServletResponse response){
		
	}
	
	
	
	
}

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
					try {
						signup(request,response);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
			case "logOut":
				logOut(request,response);
				break;
			case "retrievePass":
				try {
					retrievePass(request, response);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				response.sendRedirect("displayquestion");
				;
			}
		}
		
	}
	private void login(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ClassNotFoundException{
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String nextPage=request.getParameter("nextPage");
		if(nextPage==null){
			nextPage="displayquestion";
		}
		User user=DBConnector.getUserByUsername(username);
		if(user!=null){
			String realPass=DBConnector.getPassWdByUsername(username);
			if(password.equals(realPass)){
				HttpSession session=request.getSession();
				session.setAttribute("loggedin", true);
				session.setAttribute("username", username);
				String firstname=user.getFirstName();
				System.out.println("firstname:"+firstname);
				session.setAttribute("firstname", firstname);
				String lastname=user.getLastName();
				session.setAttribute("lastname", lastname);
				long userid=user.getId();
				session.setAttribute("userid", userid);
				String eMail=user.getEmail();
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
		
	}
	private void signup(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException{
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		Connection conn=DBConnector.getConnection();
		Statement lookupUser=conn.createStatement();
		ResultSet rsUsr= lookupUser.executeQuery("SELECT * from user WHERE username='"+username+"'");
		if(rsUsr.next()){
			response.getWriter().println("username already exists");
			return;
		}
		Statement lookupEmail=conn.createStatement();
		ResultSet rsEml= lookupEmail.executeQuery("SELECT * from user WHERE email='"+email+"'");
		if(rsEml.next()){
			response.getWriter().println("email already in database.");
			return;
		}
		Statement stmt=conn.createStatement();
		stmt.executeUpdate("INSERT INTO user(username, password, email, firstname, lastname) VALUE('"
		+username+"','"+password+"','"+email+"','"+firstname+"','"+lastname+"')");
		response.getWriter().write("success");
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
	private void logOut(HttpServletRequest request, HttpServletResponse response){
		request.getSession().setAttribute("loggedin", false);
	}
	private void retrievePass(HttpServletRequest request, HttpServletResponse response) 
			throws ClassNotFoundException, SQLException, ServletException, IOException{
		String emailAddr=request.getParameter("email");
		String title="Your password";
		String passWd=DBConnector.getPassWdByEmail(emailAddr);
		String text;
		if(passWd!=null){
			text="Your password is:"+passWd;
			request.setAttribute("serverMessage", "We have sent you an email with your password. Please check your spam.");
		}else{
			request.setAttribute("serverMessage", "You have not signed up yet.");
			text="You have not signed up yet";
		}
		
		request.setAttribute("qnTitle", "");
		request.getRequestDispatcher("/serverMessage.jsp")
		.forward(request, response);
		
		Mailman.sendMail("noreply@everyoneq.com", emailAddr, title, text);
		
	}
}

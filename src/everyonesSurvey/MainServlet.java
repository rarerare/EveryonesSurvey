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
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
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
		
		System.out.println("mact:"+mAct);
		response.setCharacterEncoding("utf-8");
		if(mAct==null){
			response.sendRedirect("main.jsp");
	
		}else{
			
			switch(mAct){
			case "login":
				try {
					login(request,response);
				} catch (SQLException e) {
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
				
			case "getsvypops":
				try {
					GetPopQ.getSurveysPop(request,response);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
			
			case "getfirstname":
				getFirstName(request, response);
				break;
			case "question":
				try {
					makeQuestion(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "checklogin":
				checkLogin(request,response);
				break;
			case "searchQ":
				try {
					searchQ(request,response);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				;
			}
		}
		
	}
	private void login(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
		
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/everyoneq","root","");
		Statement stmt=conn.createStatement();
		
		String userName=request.getParameter("username");
		String password=request.getParameter("password");
		
		ResultSet rs=stmt.executeQuery("select password, firstname, lastname, userid from user WHERE username='"+userName+"'");
		if(rs.next()){
			String realPass=rs.getString(1);
			if(password.equals(realPass)){
				HttpSession session=request.getSession();
				session.setAttribute("loggedin", true);
				session.setAttribute("username", userName);
				String firstname=rs.getString(2);
				System.out.println("firstname:"+firstname);
				session.setAttribute("firstname", firstname);
				String lastname=rs.getString(3);
				session.setAttribute("lastname", lastname);
				long userid=rs.getLong(4);
				session.setAttribute("userid", userid);
				
				PrintWriter pw= response.getWriter();
				
				pw.write("main");
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
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/everyoneq","root","");
		Statement lookup=conn.createStatement();
		ResultSet rs= lookup.executeQuery("select * from user where username='"+username+"'");
		if(rs.next()){
			response.getWriter().println("username already exists");
			return;
		}
		Statement stmt=conn.createStatement();
		stmt.executeUpdate("insert into user(username, password, email, firstname, lastname) value('"
		+username+"','"+password+"','"+email+"','"+firstname+"','"+lastname+"')");
		response.sendRedirect("login.jsp");
	}
	
	private void getFirstName(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter pw=response.getWriter();
		String firstname=(String) request.getSession().getAttribute("firstname");
		pw.print(firstname);
	}
	private void makeQuestion(HttpServletRequest request, HttpServletResponse response) throws SQLException{
		
		String title=request.getParameter("title");
		String description=request.getParameter("description");
		
		String category=request.getParameter("category");
		
		
		long time =(new Date()).getTime();
		long userid=(long) request.getSession().getAttribute("userid");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/everyoneq","root","");
		Statement insertQ=conn.createStatement();
		insertQ.executeUpdate("insert into question (title, userid, timeint,"
				+ "description, category) value('"+title+"',"+userid+","+time+",'"+description
				+"','"+category+"')");
		ResultSet qidrs=insertQ.executeQuery("select last_insert_id()");
		qidrs.next();
		long qid=qidrs.getLong(1);
		
		
		switch(category){
		case "samc":
			int opNums=Integer.valueOf(request.getParameter("opnum"));
			Statement opss=conn.createStatement();
			for(int i=1;i<=opNums;i++){
				opss.executeUpdate("insert into sachoices(description,qid, num) value('"
						+request.getParameter("choice"+i)+"',"+qid+","+i+")");
			}
			
			break;
		case "mamc":
			int opNumm=Integer.valueOf(request.getParameter("opnum"));
			Statement opsm=conn.createStatement();
			for(int i=1;i<=opNumm;i++){
				opsm.executeUpdate("insert into machoices(description,qid, num) value('"
						+request.getParameter("choice"+i)+"',"+qid+","+i);
				
			}
			break;
		case "fr":
			break;
		}
	}
	private void checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException{
		boolean loggedin;
		if(request.getSession().getAttribute("loggedin")==null){
			response.getWriter().print("no");
			return;
		}else if((boolean)request.getSession().getAttribute("loggedin")==false){
			response.getWriter().print("no");
			return;
		}
		response.getWriter().print("yes");
		
	}
	private void searchQ(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, IOException{
		String searchKey=request.getParameter("searchkey");
		System.out.println(searchKey);
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/everyoneq", "root","");
		Statement stmt=conn.createStatement();
		ResultSet rs= stmt.executeQuery("select user.username, question.title, question.description"
		+",question.popularity,question.category, question.qid"
				+ "  from question, user where (question.userid=user.userid) and (title like '%"
		+searchKey+"%' or description like '%"+searchKey+"%')");
		
		String responseStr="<h2 id=\"popqh2\">Search Results</h2>";
		while(rs.next()){
			responseStr=(new Question(rs.getString(1), rs.getString(2), rs.getString(3)
					, rs.getInt(4),QCategory.valueOf(rs.getString(5)), rs.getLong(6))).addQ(responseStr);
			System.out.println("responseStr"+responseStr);
		}
		PrintWriter pw=response.getWriter();
		pw.print(responseStr);
	}
	
}

package everyonesSurvey;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 * Servlet implementation class Main
 */

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
		response.setCharacterEncoding("utf-8");
		if(mAct==null){
			response.sendRedirect("login.jsp");
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
			default:
				;
			}
		}
		
	}
	private void login(HttpServletRequest request, HttpServletResponse response) throws SQLException{
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","");
		Statement stmt=conn.createStatement();
		
		String userName=request.getParameter("username");
		String password=request.getParameter("password");
		ResultSet rs=stmt.executeQuery("select password from info WHERE username='"+userName+"'");
		if(rs.next()){
			String realPass=rs.getString(1);
			if(password.equals(realPass)){
				System.out.println("success");
			}else{
				System.out.println("wrong password");
			}
		}else{
			System.out.println("wrong username");
		}
		conn.close();
	}
	private void signup(HttpServletRequest request, HttpServletResponse response) throws SQLException{
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","");
		Statement stmt=conn.createStatement();
		stmt.executeUpdate("insert into info value('"+username+"','"+password+"','"+email+"')");
	}
}

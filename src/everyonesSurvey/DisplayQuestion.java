package everyonesSurvey;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DisplayQuestion
 */

public class DisplayQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int POPQNUM=255;
	private ArrayList<Question> popQs=new ArrayList<Question>();
	private ArrayList<Questionnaire> popQns;
	private final static String SQL_PASSWORD="drwssp";
	private void initPopQs() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/everyoneq", "root",SQL_PASSWORD);
		Statement stmt=conn.createStatement();
		ResultSet rs= stmt.executeQuery("SELECT user.username, question.title, question.description"
		+",question.popularity,question.category, question.qid"
				+ "  FROM question, user WHERE question.userid=user.userid ORDER BY question.popularity" );
		int i=0;
		popQs=new ArrayList<Question>();
		while(rs.next()&&i<POPQNUM){
			popQs.add(new Question(rs.getString(1), rs.getString(2), rs.getString(3)
					, rs.getInt(4),QCategory.valueOf(rs.getString(5)), rs.getLong(6)));
			i++;
		}
		conn.close();
	}
	private void initPopQns() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/everyoneq", "root",SQL_PASSWORD);
		Statement stmt=conn.createStatement();
		ResultSet rs= stmt.executeQuery("SELECT qNaire.qnid, qNaire.title, qNaire.userid"
		+",qNaire.qNum"
				+ "  FROM qNaire, user WHERE qNaire.userid=user.userid ORDER BY qNaire.popularity" );
		int i=0;
		popQns=new ArrayList<Questionnaire>();
		while(rs.next()&&i<POPQNUM){
			popQns.add(new Questionnaire(rs.getLong(1), rs.getString(2), rs.getLong(3),rs.getInt(4)));
			i++;
		}
		conn.close();
	}
	
	private void getPopQuestions(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ClassNotFoundException, ServletException{
		initPopQs();
		request.setAttribute("popQs", popQs);
		request.getRequestDispatcher("/popq.jsp")
		.forward(request, response);
		
	}
	private void getPopQns(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
		initPopQns();
		request.setAttribute("popQns", popQns);
		request.getRequestDispatcher("/main.jsp")
		.forward(request, response);
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mAct=request.getParameter("mact");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		if(mAct==null){
			try {
				getPopQns(request, response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}else{
			
			switch(mAct){
			
			case "getpopqs":
				try {
					getPopQuestions(request,response);
				}catch(Exception e){
					e.printStackTrace();
				}
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
			
			case "displayQn":
				try {
					displayQn(request,response);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
				
			default:
				try {
					getPopQns(request, response);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				;
			}
		}
	}
	
	private void searchQ(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, IOException, ServletException{
		String searchKey=request.getParameter("searchkey");
		System.out.println(searchKey);
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/everyoneq", "root",SQL_PASSWORD);
		Statement stmt=conn.createStatement();
		
		ResultSet rsQn= stmt.executeQuery("SELECT qNaire.qnid, qNaire.title, qNaire.userid"
				+",qNaire.qNum  FROM qNaire, user  WHERE (qNaire.userid=user.userid) AND (qNaire.title like '%"
		+searchKey+"%' )");
		ArrayList<Questionnaire> surveys=new ArrayList<Questionnaire>();
		while(rsQn.next()){
			surveys.add(new Questionnaire(rsQn.getLong(1), rsQn.getString(2), rsQn.getLong(3),rsQn.getInt(4)));
			
		}
		
		conn.close();
		request.setAttribute("surveys", surveys);
		request.getRequestDispatcher("/searchResults.jsp")
		.forward(request, response);
	}
	private void displayQn(HttpServletRequest request, HttpServletResponse response) 
			throws ClassNotFoundException, SQLException, ServletException, IOException{
		
		long qnId=Long.parseLong(request.getParameter("qnid"));
		Questionnaire qn= Questionnaire.getQnById(qnId);
		ArrayList<Question> questions=qn.getQList();
		
		request.setAttribute("questions", questions);
		request.setAttribute("questionnaire", qn);
		request.getRequestDispatcher("/displayQn.jsp")
		.forward(request, response);
		
	}

}

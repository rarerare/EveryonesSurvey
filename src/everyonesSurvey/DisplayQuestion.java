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
	private Question[] popQs=new Question[POPQNUM] ;
	private ArrayList<Questionnaire> popQns;
	
	private void initPopQs() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/everyoneq", "root","");
		Statement stmt=conn.createStatement();
		ResultSet rs= stmt.executeQuery("select user.username, question.title, question.description"
		+",question.popularity,question.category, question.qid"
				+ "  from question, user where question.userid=user.userid order by question.popularity" );
		int i=0;
		while(rs.next()&&i<POPQNUM){
			popQs[i]=new Question(rs.getString(1), rs.getString(2), rs.getString(3)
					, rs.getInt(4),QCategory.valueOf(rs.getString(5)), rs.getLong(6));
			i++;
		}
		conn.close();
	}
	private void initPopQns() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/everyoneq", "root","");
		Statement stmt=conn.createStatement();
		ResultSet rs= stmt.executeQuery("select qNaire.qnid, qNaire.title, qNaire.userid"
		+",qNaire.qNum"
				+ "  from qNaire, user where qNaire.userid=user.userid order by qNaire.popularity" );
		int i=0;
		popQns=new ArrayList<Questionnaire>();
		while(rs.next()&&i<POPQNUM){
			popQns.add(new Questionnaire(rs.getLong(1), rs.getString(2), rs.getLong(3),rs.getInt(4)));
			i++;
		}
		conn.close();
	}
	
	private void getPopQuestions(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException{
		initPopQs();
		PrintWriter pw=response.getWriter();
		String responseStr="";
		for(int i =0; i< POPQNUM&&popQs[i]!=null;i++){
			responseStr=popQs[i].addQ(responseStr);
					
		}
		pw.print(responseStr);
		
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
		conn.close();
	}

}

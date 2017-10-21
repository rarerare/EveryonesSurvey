package everyonesSurvey;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RecordQuestion
 */

public class RecordQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordQuestion() {
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
			response.sendRedirect("main.jsp");
	
		}else{
			
			switch(mAct){
			
			
			
			case "question":
				try {
					makeSingleQuestion(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			
			
			default:
				response.sendRedirect("main.jsp");
				
				;
			}
		}
	}
	

	private void makeSingleQuestion(HttpServletRequest request, HttpServletResponse response) throws SQLException{
		String title=request.getParameter("title");
		String description=request.getParameter("description");
		
		String category=request.getParameter("category");
		long time =(new Date()).getTime();
		long userid=(long) request.getSession().getAttribute("userid");
		ArrayList<String> optTitles=new ArrayList<String>();
		int optNum = 0;
		switch(category){
		case "samc":
			optNum=Integer.valueOf(request.getParameter("opnum"));
			
			for(int i=1;i<=optNum;i++){
				optTitles.add(request.getParameter("choice"+i));
			}
			
			break;
		case "mamc":
			optNum=Integer.valueOf(request.getParameter("opnum"));
			for(int i=1;i<=optNum;i++){
				optTitles.add(request.getParameter("choice"+i));
			}
			
			break;
		case "fr":
			break;
		}
		makeQuestion(title, description, category, time, userid, optNum, optTitles);
	}
	private void makeQuestion(String title
			, String description
			, String category
			, long time
			, long userid
			, int optNum
			, ArrayList<String> optTitles ) throws SQLException{
		
		
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
			
			Statement opss=conn.createStatement();
			for(int i=0;i<optNum;i++){
				opss.executeUpdate("insert into sachoices(description,qid, num) value('"
						+optTitles.get(i)+"',"+qid+","+i+")");
			}
			
			break;
		case "mamc":
			
			Statement opsm=conn.createStatement();
			for(int i=0;i<optNum;i++){
				
				opsm.executeUpdate("insert into machoices(description,qid, num) value('"
						+optTitles.get(i)+"',"+qid+","+i+")");
				
			}
			break;
		case "fr":
			break;
		}
	}

}

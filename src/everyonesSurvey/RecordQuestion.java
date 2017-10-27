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
			case "sbmtSnglQstn":
				try {
					makeSingleQuestion(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			
			case "sbmtQnr":
				try {
					makeQnr(request,response);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
	

	private void makeSingleQuestion(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, ClassNotFoundException{
		String title=request.getParameter("title");
		String description=request.getParameter("description");
		String category=request.getParameter("category");
		long time =(new Date()).getTime();
		long userid=(long) request.getSession().getAttribute("userid");
		ArrayList<String> optTitles=new ArrayList<String>();
		int optNum = 0;
		switch(category){
		case "samc":
			optNum=Integer.valueOf(request.getParameter("optNum"));
			
			for(int i=1;i<=optNum;i++){
				optTitles.add(request.getParameter("option"+i));
			}
			
			break;
		case "mamc":
			optNum=Integer.valueOf(request.getParameter("optNum"));
			for(int i=1;i<=optNum;i++){
				optTitles.add(request.getParameter("option"+i));
			}
			
			break;
		case "fr":
			break;
		}
		makeQuestion(title, description, category, time, userid,null, optNum, optTitles);
	}
	private void makeQnr(HttpServletRequest request, HttpServletResponse response) 
			throws ClassNotFoundException, SQLException{
		String title=request.getParameter("qnTitle");
		int qNum=Integer.parseInt(request.getParameter("qNum"));
		long userid=(long) request.getSession().getAttribute("userid");
		long time =(new Date()).getTime();
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/everyoneq","root","");
		Statement createQN=conn.createStatement();
		ResultSet qnidRS; 
		synchronized(this.getClass()){
			createQN.executeUpdate("insert into qNaire (title, qNum, userid) values('"+title+"',"+qNum+","+userid+")");
			qnidRS=createQN.executeQuery("select qnid from qNaire order by qnid desc limit 1");
			
		}
		qnidRS.next();
		long qnid=qnidRS.getLong(1);
		
		for(int i=1;i<=qNum;i++){
			String qTitle=request.getParameter("qTitle"+i);
			String qDescription=request.getParameter("qDescription"+i);
			String category=request.getParameter("category"+i);
			int optNum=Integer.parseInt(request.getParameter("optNum"+i));
			ArrayList<String> optTitles=new ArrayList<String>();
			for(int j=1;j<=optNum;j++){
				optTitles.add(request.getParameter("q"+i+"option"+j));
			}
			makeQuestion(qTitle, qDescription, category, time, userid, qnid, optNum, optTitles);
		}
	}
	private void makeQuestion(String title
			, String description
			, String category
			, long time
			, long userid
			, Long qnid
			, int optNum
			, ArrayList<String> optTitles ) throws SQLException, ClassNotFoundException{
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/everyoneq","root","");
		Statement insertQ=conn.createStatement();
		ResultSet qidRS;
		synchronized(this.getClass()){
			if(qnid==null){
				insertQ.executeUpdate("insert into question (title, userid, timeint,"
						+ "description, category) value('"+title+"',"+userid+","+time+",'"+description
						+"','"+category+"')");
			}else{
				System.out.println("insert into question (title, userid, timeint,"
						+ "description, category, qnid) value('"+title+"',"+userid+","+time+",'"+description
						+"','"+category+"'"+qnid+")");
				insertQ.executeUpdate("insert into question (title, userid, timeint,"
						+ "description, category, qnid) value('"+title+"',"+userid+","+time+",'"+description
						+"','"+category+"',"+qnid+")");
			}
			
			qidRS=insertQ.executeQuery("select qid from question order by qid desc limit 1");
		}
		
		qidRS.next();
		long qid=qidRS.getLong(1);
		
		
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
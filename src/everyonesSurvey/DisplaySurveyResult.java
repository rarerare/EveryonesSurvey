package everyonesSurvey;

import java.io.IOException;
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
 * Servlet implementation class DisplaySurveyResult
 */
@WebServlet("/DisplaySurveyResult")
public class DisplaySurveyResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplaySurveyResult() {
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
			response.sendRedirect("displayquestion");
	
		}else{
			
			switch(mAct){
			/*case "getQList":
				try {
					displayUserQList(request, response);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;*/
			case "getQnList":
				try {
					displayUserQnList(request,response);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "getQnResult":
				break;
			
			}
		}	
	}
	/*private void displayUserQList(HttpServletRequest request, HttpServletResponse response) 
			throws ClassNotFoundException, SQLException, ServletException, IOException{
		
		User user=(User)request.getSession().getAttribute("user");
		long userId=user.getId();
		String userName=user.getUsername();
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/everyoneq", "root","");
		Statement stmt=conn.createStatement();
		ResultSet singleQRs= stmt.executeQuery("SELECT (title, description, popularity, category,qid)"
				+ " FROM question WHERE qnid IS NULL AND userid="+userId);
		ArrayList<Question> singleQs=new ArrayList<Question>();
		while(singleQRs.next()){
			singleQs.add(new Question(userName,singleQRs.getString(1), singleQRs.getString(2)
					, singleQRs.getInt(3), QCategory.valueOf(singleQRs.getString(4)),singleQRs.getLong(5) ));
		}
		request.setAttribute("singleQs", singleQs);
		request.getRequestDispatcher("/userQList.jsp")
		.forward(request, response);
		conn.close();
		
	}*/
	private void displayUserQnList(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException, IOException{
		User user=(User)request.getSession().getAttribute("user");
		long userId=user.getId();
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/everyoneq", "root","");
		Statement stmt=conn.createStatement();
		ResultSet qnRs=stmt.executeQuery("SELECT qnid, title, qNum FROM qNaire WHERE userid="+userId);
		ArrayList<Questionnaire> qns=new ArrayList<Questionnaire>();
		while(qnRs.next()){
			qns.add(new Questionnaire(qnRs.getLong(1),qnRs.getString(2),userId, qnRs.getInt(3)));
		}
		request.setAttribute("qns", qns);
		request.getRequestDispatcher("/userQnList.jsp").forward(request, response);
		conn.close();
		
	}
	private void displayQnResult(HttpServletRequest request, HttpServletResponse response) 
			throws ClassNotFoundException, SQLException, ServletException, IOException{
		
		long qnId=Long.valueOf(request.getParameter("qnId"));
		
		Questionnaire qn= Questionnaire.getQnById(qnId);
		request.setAttribute("qn", qn);
		
		request.getRequestDispatcher("/displayQn.jsp")
		.forward(request, response);
	}
}

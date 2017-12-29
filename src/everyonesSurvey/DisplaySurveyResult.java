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
				try {
					displayQnResult(request, response);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			
			}
		}	
	}
	
	private void displayUserQnList(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException, IOException{
		User user=(User)request.getSession().getAttribute("user");
		long userId=user.getId();
		
		ArrayList<Questionnaire> qns=DBConnector.getUserQnList(userId);
		
		request.setAttribute("qns", qns);
		request.getRequestDispatcher("/userQnList.jsp").forward(request, response);
		
		
	}
	private void displayQnResult(HttpServletRequest request, HttpServletResponse response) 
			throws ClassNotFoundException, SQLException, ServletException, IOException{
		
		long qnId=Long.valueOf(request.getParameter("qnId"));
		
		Questionnaire qn= Questionnaire.getQnById(qnId);
		request.setAttribute("qn", qn);
		
		request.getRequestDispatcher("/displayResult.jsp")
		.forward(request, response);
	}
}

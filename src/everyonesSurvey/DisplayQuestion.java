package everyonesSurvey;

import java.io.IOException;
import java.io.PrintWriter;



import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
	
	private void initPopQns() throws SQLException, ClassNotFoundException{
		popQns=DBConnector.getPopQns(POPQNUM);
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
		try {
			recordVisit(request);
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
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
		ArrayList<Questionnaire> surveys=DBConnector.searchQn(searchKey);
		
		
		request.setAttribute("surveys", surveys);
		request.getRequestDispatcher("/searchResults.jsp")
		.forward(request, response);
	}
	private void displayQn(HttpServletRequest request, HttpServletResponse response) 
			throws ClassNotFoundException, SQLException, ServletException, IOException{
		
		long qnId=Long.parseLong(request.getParameter("qnid"));
		Questionnaire qn= DBConnector.getQnById(qnId);
		ArrayList<Question> questions=qn.getQList();
		
		request.setAttribute("questions", questions);
		request.setAttribute("questionnaire", qn);
		request.getRequestDispatcher("/displayQn.jsp")
		.forward(request, response);
		
	}
	private void recordVisit(HttpServletRequest request)
			throws ClassNotFoundException, SQLException{
		Date date=new Date();
		
		String dateTimeStr=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		String sessionId=request.getSession().getId();
		Long userid=null;
		if(UserTracker.loggedIn(request)){
			User user=UserTracker.getCurrUser(request);
			if(user!=null){
				userid=user.getId();
			}
		}
			
		String ipv4=request.getRemoteAddr();
		DBConnector.recordVisit(ipv4, dateTimeStr, userid, sessionId);
	}

}

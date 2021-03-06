package everyonesSurvey;

import java.io.IOException;

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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		if(mAct==null){
			response.sendRedirect("displayquestion");
	
		}else{
			
			switch(mAct){
			
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
	

	
	private void makeQnr(HttpServletRequest request, HttpServletResponse response) 
			throws ClassNotFoundException, SQLException, IOException{
		String title=request.getParameter("qnTitle");
		int qNum=Integer.parseInt(request.getParameter("qNum"));
		long userid=(long) request.getSession().getAttribute("userid");
		
		Date date=new Date();
		long time =date.getTime();
		String dateTimeStr=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		
		
		long qnid=DBConnector.addEmptyQnr(title, qNum, userid, dateTimeStr);
		
		for(int i=1;i<=qNum;i++){
			String qTitle=request.getParameter("qTitle"+i);
			String qDescription="";
			String category=request.getParameter("category"+i);
			if(category==null){
				category="fr";
			}
			int optNum=Integer.parseInt(request.getParameter("optNum"+i));
			ArrayList<String> optTitles=new ArrayList<String>();
			for(int j=1;j<=optNum;j++){
				optTitles.add(request.getParameter("q"+i+"option"+j));
			}
			makeQuestion(qTitle, qDescription, category, time, userid, qnid, optNum, optTitles);
		}
		response.sendRedirect("displayquestion?mact=displayQn&qnid="+qnid);
		
	}
	private void makeQuestion(String title
			, String description
			, String category
			, long time
			, long userid
			, Long qnid
			, int optNum
			, ArrayList<String> optTitles ) throws ClassNotFoundException, SQLException{
		DBConnector.addQuestion(title, description, category, time, userid, qnid, optNum, optTitles);
	}
	

}

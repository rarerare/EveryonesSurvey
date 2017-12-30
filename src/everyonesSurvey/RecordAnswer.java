package everyonesSurvey;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RecordAnswer
 */
@WebServlet("/RecordAnswer")
public class RecordAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String NUM_ANS_TABLE_NAME = "num_ans";
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordAnswer() {
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
		request.setCharacterEncoding("utf-8");
		
		response.setCharacterEncoding("utf-8");
		String mAct=request.getParameter("mact");
		System.out.println(mAct);
		if(mAct==null){
			response.sendRedirect("displayquestion");
	
		}else{
			switch(mAct){
			case "recordQnAnswer":
				try {
					recordQnAnswer(request,response);
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
	private void recordQnAnswer(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException, IOException{
		
		
		long qnId=Long.parseLong(request.getParameter("qnId"));
		//long userId=Long.parseLong()
		int qNum=Integer.parseInt(request.getParameter("qNum"));
		Enumeration<String> params=request.getParameterNames();
		
		while(params.hasMoreElements()){
			
			String param=params.nextElement();
			
			if(param.contains("__")){
				String[] parts=param.split("__");
				QCategory category=QCategory.valueOf(parts[0]);
				long qId=Long.parseLong(parts[1]);
				
				
				switch(category){
				case mamc:
					System.out.println(param);
					String[] cIdStrs=request.getParameterValues(param);
					System.out.println(cIdStrs);
					long[] cIds=new long[cIdStrs.length];
					for(int i=0;i<cIds.length;i++){
						cIds[i]=Long.parseLong(cIdStrs[i]);
					}
					recordMultipleChoice( cIds, category);
					break;
					
				case samc:
					long cId=Long.parseLong(request.getParameter(param));
					DBConnector.recordChoice(cId, category);
					break;
					
				case fr:
					String text=request.getParameter(param);
					DBConnector.recordFreeResponse(qId,text);
					break;
				case number:
					double answer=Double.parseDouble(request.getParameter(param));
					DBConnector.recordNumAnswer(qId, answer);
					break;
				default:
					break;
				}
			}
		}
		request.setAttribute("serverMessage", "Your response has been recorded.");
		request.setAttribute("qnTitle", DBConnector.getQnById(qnId).getTitle());
		request.getRequestDispatcher("/serverMessage.jsp")
		.forward(request, response);
	}
	
	
	private void recordMultipleChoice( long[] cIds, QCategory category) throws ClassNotFoundException, SQLException{
		for(long cId:cIds){
			DBConnector.recordChoice(cId, category);
		}
	}
	
	

}

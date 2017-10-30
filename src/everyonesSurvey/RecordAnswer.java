package everyonesSurvey;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
			case "recordSingleQAnswer":
				try {
					recordSingleQAnswer(request, response);
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
			throws ClassNotFoundException, SQLException{
		
		
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
					recordMultipleChoice(qId, cIds);
					break;
					
				case samc:
					long cId=Long.parseLong(request.getParameter(param));
					recordSingleChoice(qId,cId);
					break;
					
				case fr:
					String text=request.getParameter(param);
					recordFreeResponse(qId,text);
					break;
				case number:
					double answer=Double.parseDouble(request.getParameter(param));
					recordNumAnswer(qId, answer);
					break;
				default:
					break;
				}
			}
		}
		
	}
	private void recordSingleQAnswer(HttpServletRequest request, HttpServletResponse response) 
			throws ClassNotFoundException, SQLException, IOException{
		long qId=Long.parseLong(request.getParameter("qId"));
		QCategory category=QCategory.valueOf(request.getParameter("qCategory"));
		if(category==QCategory.samc){
			long cId=Long.parseLong(request.getParameter(category+"__"+qId));
			recordSingleChoice(qId,cId);
		}else if(category==QCategory.mamc){
			String[] cIdStrs=request.getParameterValues(category+"__"+qId);
			long[] cIds=new long[cIdStrs.length];
			for(int i=0;i<cIds.length;i++){
				cIds[i]=Long.parseLong(cIdStrs[i]);
			}
			recordMultipleChoice(qId, cIds);
		}else if(category==QCategory.fr){
			String text=request.getParameter(category+"__"+qId);
			recordFreeResponse(qId,text);
		}else if(category==QCategory.number){
			double answer=Double.parseDouble(request.getParameter(category+"__"+qId));
			recordNumAnswer(qId, answer);
		}
		PrintWriter pw=response.getWriter();
		pw.print("successfully submitted");
		
	}
	private void recordSingleChoice(long qId, long cId) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/everyoneq","root","");
		Statement recordC=conn.createStatement();
		String selectionTableName="saSelections";
		/*switch(category){
		case mamc:
			selectionTableName="maSelections";
			break;
		case samc:
			selectionTableName="saSelections";
			break;
		default:
			break;
		}*/
		
		recordC.executeUpdate("insert into "+selectionTableName+"(cId) values("+cId+")");
		conn.close();
		
		
	}
	private void recordMultipleChoice(long qId, long[] cIds) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/everyoneq","root","");
		Statement recordC=conn.createStatement();
		String selectionTableName="maSelections";
		for(long cId:cIds){
			recordC.executeUpdate("insert into "+selectionTableName+"(cId) values("+cId+")");
		}
		conn.close();
	}
	private void recordFreeResponse(long qId, String text) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/everyoneq","root","");
		Statement recordA=conn.createStatement();
		recordA.executeUpdate("insert into frAnswers (qid,answer) values("+qId+",'"+text+"')");
		conn.close();
	}
	private void recordNumAnswer(long qId, double answer) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/everyoneq","root","");
		Statement recordA=conn.createStatement();
		recordA.executeUpdate("insert into numAnswer (qid, answer) values("+qId+","+answer+")");
		conn.close();
	}

}

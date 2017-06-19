package everyonesSurvey;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.*;
import com.mysql.jdbc.Driver;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class GetPopQ {
	private static final int POPQNUM=3;
	private static Question[] popqs=new Question[POPQNUM] ;
	private static void initQs() throws SQLException{
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/everyoneq", "root","");
		Statement stmt=conn.createStatement();
		ResultSet rs= stmt.executeQuery("select user.username, question.title, question.description"
		+",question.popularity,question.category, question.qid"
				+ "  from question, user where question.userid=user.userid order by popularity" );
		int i=0;
		while(rs.next()&&i<1023){
			popqs[i]=new Question(rs.getString(1), rs.getString(2), rs.getString(3)
					, rs.getInt(4),QCategory.valueOf(rs.getString(5)), rs.getLong(6));
			i++;
		}
		
	}
	
	public static void getSurveysPop(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
		initQs();
		PrintWriter pw=response.getWriter();
		String responseStr="";
		for(int i =0; i< POPQNUM&&popqs[i]!=null;i++){
			responseStr=addQ(responseStr,popqs[i]);
					
		}
		pw.print(responseStr);
		
	}
	private static String addQ(String responseStr,Question q) throws SQLException{
		System.out.println("entered addQ");
		responseStr+="<div class=\"mainq\"><h4>"+q.getTitle()+"</h4><hr>"
				+q.getDescription()+"<br><form action='mainservlet?mact=answerq'>";
		
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/everyoneq", "root","");
		Statement stmt=conn.createStatement();
		long qid=q.getId();
		String inputname=q.getCategory()+"_"+qid;
		ResultSet rs= stmt.executeQuery("select num, description from sachoices where qid="+qid  );
		while(rs.next()){
			String description=rs.getString(2);
			
			responseStr+="<input type='radio' name='"+inputname+"'>"+description+"<br>";
		}
		responseStr+="<button type='submit' id='loginsubmit'>submit</button><form></div>";
		return responseStr;
		
	}
}

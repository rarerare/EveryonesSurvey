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
		+",question.popularity  from question, user where question.userid=user.userid order by popularity" );
		int i=0;
		while(rs.next()&&i<1023){
			popqs[i]=new Question(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			i++;
		}
		
	}
	
	public static void getSurveysPop(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
		initQs();
		PrintWriter pw=response.getWriter();
		pw.println("<div class=\"question\">");
		for(int i =0; i< POPQNUM&&popqs[i]!=null;i++){
			pw.println("<div class=\"qtitle\">"+popqs[i].getTitle()+"</div>");
			pw.println("<div class=\"qdescription\">"+popqs[i].getDescription()+"</div>");
			
		}
		pw.println("</div>");
		
	}
}

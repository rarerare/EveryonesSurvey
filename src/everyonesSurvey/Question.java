package everyonesSurvey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Question {
	private String user;
	private String title;
	private String description;
	private int popularity;
	private QCategory category;
	private long qid;
	public Question(String user, String title, String description, int popularity, QCategory category, long qid){
		this.user=user;
		this.title=title;
		this.description=description;
		this.popularity=popularity;
		this.category=category;
		this.qid=qid;
	}
	public String getTitle(){
		return title;
	}
	public String getDescription(){
		return description;
	}
	public String getUser(){
		return user;
	}
	public QCategory getCategory(){
		return this.category;
	}
	public long getId(){
		return qid;
	}
	String addQ(String responseStr) throws SQLException{
		System.out.println("entered addQ");
		responseStr+="<div class=\"mainq\"><h4>"+getTitle()+"</h4><hr>"
				+getDescription()+"<br><form action='mainservlet?mact=answerq'>";
		
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/everyoneq", "root","");
		Statement stmt=conn.createStatement();
		long qid=getId();
		String inputname=getCategory()+"_"+qid;
		ResultSet rs= stmt.executeQuery("select num, description from sachoices where qid="+qid  );
		while(rs.next()){
			String description=rs.getString(2);
			
			responseStr+="<input type='radio' name='"+inputname+"'>"+description+"<br>";
		}
		responseStr+="<button type='submit' id='loginsubmit'>submit</button><form></div>";
		return responseStr;
		
	}
}

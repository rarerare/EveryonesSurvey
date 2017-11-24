package everyonesSurvey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Question {
	private String userName;
	private String title;
	private String description;
	private int popularity;
	private QCategory category;
	private long qid;
	public Question(String userName, String title, String description, int popularity, QCategory category, long qid){
		this.userName=userName;
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
	public String getUserName(){
		return userName;
	}
	public QCategory getCategory(){
		return this.category;
	}
	public long getId(){
		return qid;
	}
	
}

package everyonesSurvey;


import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Questionnaire {
	
	private long qnid;
	private String title;
	private long userId;
	private int qNum;
	private Date createServerTime;
	public Questionnaire(long qnid, String title,long userId, int qNum, Date createServerTime){
		this.qnid=qnid;
		this.title=title;
		this.userId=userId;
		this.qNum=qNum;
		this.createServerTime=createServerTime;
	}
	
	public long getId(){
		return qnid;
	}
	public String getTitle(){
		return title;
	}
	public long getUserId(){
		return userId;
	}
	public int getQNum(){
		return qNum;
	}
	public Date getCreateTime(){
		return createServerTime;
	}
	public String getCreateTimeStr(){
		if(createServerTime==null){
			return "";
		}
		return new SimpleDateFormat("MMM dd, yyyy").format(createServerTime);
	}
	public String getUserName() throws ClassNotFoundException, SQLException{
		return DBConnector.getUserById(userId).getUsername();
	}
	public ArrayList<Question> getQList() throws ClassNotFoundException, SQLException{
		ArrayList<Question> questions=DBConnector.getQListByQnId(getId());
		return questions;
	}
	
}

package everyonesSurvey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Questionnaire {
	private final static String SQL_PASSWORD="drwssp";
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
	public static Questionnaire getQnById(long id) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/everyoneq", "root",SQL_PASSWORD);
		Statement stmt=conn.createStatement();
		ResultSet rsQn= stmt.executeQuery("SELECT qNaire.qnid, qNaire.title, qNaire.userid"
				+",qNaire.qNum, qNaire.server_time"
						+ "  FROM qNaire WHERE qNaire.qnid="+id );
		rsQn.next();
		Questionnaire qn= new Questionnaire(rsQn.getLong(1), rsQn.getString(2), rsQn.getLong(3),rsQn.getInt(4), rsQn.getDate(5));
		conn.close();
		return qn;
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
		return User.getById(userId).getUsername();
	}
	public ArrayList<Question> getQList() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/everyoneq", "root",SQL_PASSWORD);
		Statement stmt=conn.createStatement();
		ResultSet rsQuestion= stmt.executeQuery("SELECT user.username, question.qid"
				+ ", question.title, question.userid,question.description, question.category"
				+ " FROM user, question, qNaire WHERE question.userid=user.userid "
				+ "AND question.qnid=qNaire.qnid AND qNaire.qnid="+getId());
		ArrayList<Question> questions=new ArrayList<Question>();
		while(rsQuestion.next()){
			if(QCategory.valueOf(rsQuestion.getString(6)).isFinAns()){
				questions.add(new FinAnsQuestion(rsQuestion.getString(1),rsQuestion.getString(3)
						,rsQuestion.getString(5),0,QCategory.valueOf(rsQuestion.getString(6)),rsQuestion.getLong(2)));
			}else{
				questions.add(new InfiAnsQuestion(rsQuestion.getString(1),rsQuestion.getString(3)
						,rsQuestion.getString(5),0,QCategory.valueOf(rsQuestion.getString(6)),rsQuestion.getLong(2)));
			}
			
		}
		return questions;
	}
	
}

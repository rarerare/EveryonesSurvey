package everyonesSurvey;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public class DBConnector {
	private final static String SQL_PASSWORD="drwssp";
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/everyoneq", "root",SQL_PASSWORD);
		return conn;
	}
	public static ArrayList<Questionnaire> getPopQns(int num) throws ClassNotFoundException, SQLException{
		Connection conn=getConnection();
		Statement stmt=conn.createStatement();
		ResultSet rs= stmt.executeQuery("SELECT qNaire.qnid, qNaire.title, qNaire.userid"
		+",qNaire.qNum, qNaire.server_time"
				+ "  FROM qNaire, user WHERE qNaire.userid=user.userid ORDER BY qNaire.popularity" );
		int i=0;
		ArrayList<Questionnaire> popQns=new ArrayList<Questionnaire>();
		while(rs.next()&&i<num){
			popQns.add(new Questionnaire(rs.getLong(1), rs.getString(2), rs.getLong(3),rs.getInt(4), rs.getDate(5)));
			i++;
		}
		conn.close();
		return popQns;
	}
	public static ArrayList<Questionnaire> searchQn(String key) throws ClassNotFoundException, SQLException{
		Connection conn=getConnection();
		Statement stmt=conn.createStatement();
		
		ResultSet rsQn= stmt.executeQuery("SELECT qNaire.qnid, qNaire.title, qNaire.userid"
				+",qNaire.qNum, qNaire.server_time  FROM qNaire, user  WHERE (qNaire.userid=user.userid) AND (qNaire.title like '%"
		+key+"%' )");
		ArrayList<Questionnaire> surveys=new ArrayList<Questionnaire>();
		while(rsQn.next()){
			surveys.add(new Questionnaire(rsQn.getLong(1), rsQn.getString(2), rsQn.getLong(3),rsQn.getInt(4), rsQn.getDate(5)));
			
		}
		
		conn.close();
		return surveys;
	}
	public static ArrayList<Questionnaire> getUserQnList(long userId) throws ClassNotFoundException, SQLException{
		
		Connection conn=getConnection();
		Statement stmt=conn.createStatement();
		ResultSet qnRs=stmt.executeQuery("SELECT qnid, title, qNum, server_time FROM qNaire WHERE userid="+userId);
		ArrayList<Questionnaire> qns=new ArrayList<Questionnaire>();
		while(qnRs.next()){
			qns.add(new Questionnaire(qnRs.getLong(1),qnRs.getString(2),userId, qnRs.getInt(3), qnRs.getDate(4)));
		}

		conn.close();
		return qns;
	}
	public static ArrayList<Question> getQListByQnId(long qnId) throws SQLException, ClassNotFoundException{
		
		Connection conn=getConnection();
		Statement stmt=conn.createStatement();
		ResultSet rsQuestion= stmt.executeQuery("SELECT user.username, question.qid"
				+ ", question.title, question.userid,question.description, question.category"
				+ " FROM user, question, qNaire WHERE question.userid=user.userid "
				+ "AND question.qnid=qNaire.qnid AND qNaire.qnid="+qnId);
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
		conn.close();
		return questions;
	}
	public static Questionnaire getQnById(long id) throws  SQLException, ClassNotFoundException{
		
		Connection conn=getConnection();
		Statement stmt=conn.createStatement();
		ResultSet rsQn= stmt.executeQuery("SELECT qNaire.qnid, qNaire.title, qNaire.userid"
				+",qNaire.qNum, qNaire.server_time"
						+ "  FROM qNaire WHERE qNaire.qnid="+id );
		rsQn.next();
		Questionnaire qn= new Questionnaire(rsQn.getLong(1), rsQn.getString(2), rsQn.getLong(3),rsQn.getInt(4), rsQn.getDate(5));
		conn.close();
		return qn;
	}
	public static User getUserById(long id) throws ClassNotFoundException, SQLException{
		
		Connection conn=getConnection();
		Statement stmt=conn.createStatement();
		ResultSet rsQn= stmt.executeQuery("SELECT username, firstname, lastname"
				+", email"
						+ "  FROM user WHERE userid="+id );
		rsQn.next();
		User user= new User(rsQn.getString(1), rsQn.getString(2), rsQn.getString(3),rsQn.getString(4), id);
		conn.close();
		return user;
	}
	public static String getPassWdByEmail(String emailAddr) throws SQLException, ClassNotFoundException{
		
		Connection conn=getConnection();
		Statement lookupEmail=conn.createStatement();
		ResultSet rsEml= lookupEmail.executeQuery("SELECT password from user WHERE email='"+emailAddr+"'");
		String passWd=null;
		if(rsEml.next()){
			passWd=rsEml.getString(1);
		}else{
			passWd=null;
		}
		conn.close();
		return passWd;
	}
	public static User getUserByUsername(String username) throws SQLException, ClassNotFoundException{
		Connection conn=DBConnector.getConnection();
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery("SELECT password, firstname, lastname, userid from user WHERE username='"+username+"'");
		if(rs.next()){
			String realPass=rs.getString(1);
			String firstname=rs.getString(2);
			String lastname=rs.getString(3);
			long userid=rs.getLong(4);
			ResultSet rsEMail=stmt.executeQuery("select email from user where userid="+userid );
			rsEMail.next();
			String eMail=rsEMail.getString(1);
			User user=new User(username, firstname, lastname, eMail, userid);
			conn.close();
			return user;
		}
		conn.close();
		return null;
	}
	public static String getPassWdByUsername(String username) throws SQLException, ClassNotFoundException{
		
		Connection conn=getConnection();
		Statement lookupEmail=conn.createStatement();
		ResultSet rs= lookupEmail.executeQuery("SELECT password from user WHERE username='"+username+"'");
		String passWd=null;
		if(rs.next()){
			passWd=rs.getString(1);
		}else{
			passWd=null;
		}
		conn.close();
		return passWd;
	}
	
}

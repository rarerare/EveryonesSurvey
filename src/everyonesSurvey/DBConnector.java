package everyonesSurvey;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public class DBConnector {
	private static final String SA_OPTION_TABLE_NAME = QCategory.samc.getOptTable();
	private static final String SA_SELECTION_TABLE_NAME = QCategory.samc.getAnsTable();
	private static final String QUESTION_TABLE_NAME = "question";
	private static final String MA_OPTION_TABLE_NAME = QCategory.mamc.getOptTable();
	private static final String MA_SELECTION_TABLE_NAME = QCategory.mamc.getAnsTable();
	private static final String FR_ANS_TABLE_NAME = QCategory.fr.getAnsTable();
	private static final String NUM_ANS_TABLE_NAME = QCategory.number.getAnsTable();
	private static final String Q_NAIRE_TABLE_NAME = "qnaire";
	private final static String SQL_PASSWORD="drwssp";
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/everyoneq", "root",SQL_PASSWORD);
		return conn;
	}
	public static ArrayList<Questionnaire> getPopQns(int num) throws ClassNotFoundException, SQLException{
		Connection conn=getConnection();
		Statement stmt=conn.createStatement();
		ResultSet rs= stmt.executeQuery("SELECT "+Q_NAIRE_TABLE_NAME+".qnid, "+Q_NAIRE_TABLE_NAME+".title, "+Q_NAIRE_TABLE_NAME+".userid"
		+","+Q_NAIRE_TABLE_NAME+".qNum, "+Q_NAIRE_TABLE_NAME+".server_time"
				+ "  FROM "+Q_NAIRE_TABLE_NAME+", user WHERE "+Q_NAIRE_TABLE_NAME+".userid=user.userid ORDER BY "+Q_NAIRE_TABLE_NAME+".popularity" );
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
		
		ResultSet rsQn= stmt.executeQuery("SELECT "+Q_NAIRE_TABLE_NAME+".qnid, "+Q_NAIRE_TABLE_NAME+".title, "+Q_NAIRE_TABLE_NAME+".userid"
				+","+Q_NAIRE_TABLE_NAME+".qNum, "+Q_NAIRE_TABLE_NAME+".server_time  FROM "+Q_NAIRE_TABLE_NAME
				+", user  WHERE ("+Q_NAIRE_TABLE_NAME+".userid=user.userid) AND ("+Q_NAIRE_TABLE_NAME+".title like '%"
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
		ResultSet qnRs=stmt.executeQuery("SELECT qnid, title, qNum, server_time FROM "+Q_NAIRE_TABLE_NAME+" WHERE userid="+userId);
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
				+ " FROM user, question, "+Q_NAIRE_TABLE_NAME+" WHERE question.userid=user.userid "
				+ "AND question.qnid="+Q_NAIRE_TABLE_NAME+".qnid AND "+Q_NAIRE_TABLE_NAME+".qnid="+qnId);
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
		ResultSet rsQn= stmt.executeQuery("SELECT "+Q_NAIRE_TABLE_NAME+".qnid, "+Q_NAIRE_TABLE_NAME+".title, "+Q_NAIRE_TABLE_NAME+".userid"
				+","+Q_NAIRE_TABLE_NAME+".qNum, "+Q_NAIRE_TABLE_NAME+".server_time"
						+ "  FROM "+Q_NAIRE_TABLE_NAME+" WHERE "+Q_NAIRE_TABLE_NAME+".qnid="+id );
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
		username=username.replace("'", "''");
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
		username=username.replace("'", "''");
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
	public static void addUser(String username, String email, String firstname, String lastname, String password) 
			throws ClassNotFoundException, SQLException{
		
		Connection conn=DBConnector.getConnection();
		PreparedStatement stmt=
				conn.prepareStatement("INSERT INTO user(username, password, email, firstname, lastname)"
						+ " VALUE(?,?,?,?,?)");
		stmt.setString(1, username);
		stmt.setString(2, password);
		stmt.setString(3, email);
		stmt.setString(4, firstname);
		stmt.setString(5, lastname);
		
		stmt.execute();
		conn.close();
	}
	
	public static void addQuestion(String title
			, String description
			, String category
			, long time
			, long userid
			, Long qnid
			, int optNum
			, ArrayList<String> optTitles ) throws SQLException, ClassNotFoundException{
		
		Connection conn=DBConnector.getConnection();
		PreparedStatement insertQ;
		ResultSet qidRS;
		synchronized(DBConnector.class){
			if(qnid==null){
				insertQ=conn.prepareStatement("INSERT INTO question (title, userid, timeint,"
						+ "description, category) VALUES(?,?,?,?,?)");
				insertQ.setString(1, title);
				insertQ.setLong(2, userid);
				insertQ.setLong(3, time);
				insertQ.setString(4, description);
				insertQ.setString(5, category);
				insertQ.executeUpdate();
			}else{
				
				insertQ=conn.prepareStatement("INSERT INTO question (title, userid, timeint,"
						+ "description, category, qnid) VALUES(?,?,?,?,?,?)");
				insertQ.setString(1, title);
				insertQ.setLong(2, userid);
				insertQ.setLong(3, time);
				insertQ.setString(4, description);
				insertQ.setString(5, category);
				insertQ.setLong(6, qnid);
				insertQ.executeUpdate();
			}
			
			qidRS=insertQ.executeQuery("SELECT qid FROM question ORDER BY qid DESC LIMIT 1");
		}
		
		qidRS.next();
		long qid=qidRS.getLong(1);
		QCategory qCategory=QCategory.valueOf(category);
		if(qCategory.isFinAns()){
			Statement opss=conn.createStatement();
			for(int i=0;i<optNum;i++){
				opss.executeUpdate("INSERT INTO " 
						+qCategory.getOptTable()+" (description,qid, position) VALUE('"
						+optTitles.get(i)+"',"+qid+","+i+")");
			}
		}
		
		conn.close();
	}
	public synchronized static long addEmptyQnr(String title, int qNum, long userid, String dateTimeStr) throws ClassNotFoundException, SQLException{
		Connection conn=DBConnector.getConnection();
		Statement createQN=conn.createStatement();
		ResultSet qnidRS;
		createQN.executeUpdate("INSERT INTO "+Q_NAIRE_TABLE_NAME
				+" (title, qNum, userid, server_time) VALUES('"+title+"',"+qNum+","+userid+",'"+dateTimeStr+"')");
		qnidRS=createQN.executeQuery("SELECT qnid FROM "+Q_NAIRE_TABLE_NAME+" ORDER BY qnid DESC LIMIT 1");
		qnidRS.next();
		long qnid=qnidRS.getLong(1);
		conn.close();
		return qnid;
	}
	public static void recordVisit(String ipv4, String datetimeStr, Long userId, String sessionId)
			throws ClassNotFoundException, SQLException{
		Connection conn=DBConnector.getConnection();
		PreparedStatement stmt=
				conn.prepareStatement("INSERT INTO visit(ipv4, server_time, userid, sessionid) VALUES(?,?,?,?)");
		
		stmt.setString(1, ipv4);
		stmt.setString(2, datetimeStr);
		if(userId!=null){
			stmt.setLong(3, userId);
		}else{
			stmt.setLong(3, -1);
		}
		
		stmt.setString(4, sessionId);
		stmt.execute();
		conn.close();
	}
	public static void emptyQnaires() throws ClassNotFoundException, SQLException{
		Connection conn=DBConnector.getConnection();
		Statement stmt=conn.createStatement();
		stmt.execute("delete from "+FR_ANS_TABLE_NAME);
		stmt.execute("delete from "+MA_SELECTION_TABLE_NAME);
		stmt.execute("delete from "+MA_OPTION_TABLE_NAME);
		stmt.execute("delete from "+NUM_ANS_TABLE_NAME);
		stmt.execute("delete from "+Q_NAIRE_TABLE_NAME+"");
		stmt.execute("delete from "+QUESTION_TABLE_NAME);
		stmt.execute("delete from "+SA_SELECTION_TABLE_NAME);
		stmt.execute("delete from "+SA_OPTION_TABLE_NAME);
		conn.close();
	}
	public static void recordChoice( long cId, QCategory category) throws ClassNotFoundException, SQLException{
		Connection conn=DBConnector.getConnection();
		Statement recordC=conn.createStatement();
		String selectionTableName=category.getAnsTable();
		recordC.executeUpdate("insert into "+selectionTableName+"(cId) values("+cId+")");
		conn.close();
	}
	public static void recordFreeResponse(long qId, String text) throws ClassNotFoundException, SQLException{
		Connection conn=DBConnector.getConnection();
		PreparedStatement recordA=conn.prepareStatement("insert into fr_ans (qid,answer) values(?,?)");
		recordA.setLong(1, qId);
		recordA.setString(2, text);
		recordA.execute();
		conn.close();
	}
	public static void recordNumAnswer(long qId, double answer) throws ClassNotFoundException, SQLException{
		Connection conn=DBConnector.getConnection();
		Statement recordA=conn.createStatement();
		recordA.executeUpdate("insert into "+NUM_ANS_TABLE_NAME+" (qid, answer) values("+qId+","+answer+")");
		conn.close();
	}
	public static ArrayList<String> getAnsList(QCategory category, long qid) throws SQLException, ClassNotFoundException{
		ArrayList<String>  answers=new ArrayList<String>();
		
		Connection conn=DBConnector.getConnection();
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery("SELECT answer FROM "+category.getAnsTable()+" WHERE qid="+qid);
		while(rs.next()){
			answers.add(rs.getString(1));
		}
		conn.close();
		return answers;
	}
	public static long getSelectCount(QCategory category, long cId) throws ClassNotFoundException, SQLException{
		Connection conn=DBConnector.getConnection();
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery("select count(*) from "+category.getAnsTable()+" where cId= "+cId );
		rs.next();
		long selectCount=rs.getLong(1);
		conn.close();
		return selectCount;
	}
	public static ArrayList<FinOption> getQOptions(Question question) throws SQLException, ClassNotFoundException{
		ArrayList<FinOption> options=new ArrayList<FinOption>();
		long qId=question.getId();
		Connection conn=DBConnector.getConnection();
		Statement stmt=conn.createStatement();
		ResultSet rs= stmt.executeQuery("SELECT position, description, cid FROM "+question.getCategory().getOptTable()+" WHERE qid="+qId  );
		
		while(rs.next()){
			String description=rs.getString(2);
			long cId=rs.getLong(3);
			options.add(new FinOption(cId, question, description));
		}
		conn.close();
		return options;
		
	}
	
}

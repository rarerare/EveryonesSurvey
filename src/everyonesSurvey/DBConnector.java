package everyonesSurvey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnector {
	private final static String SQL_PASSWORD="drwssp";
	private static Connection getConnection() throws ClassNotFoundException, SQLException{
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
}

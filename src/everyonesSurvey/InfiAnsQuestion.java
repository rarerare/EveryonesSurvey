package everyonesSurvey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class InfiAnsQuestion extends Question{
	private final static String SQL_PASSWORD="drwssp";
	public InfiAnsQuestion(String userName, String title, String description, int popularity, QCategory category,
			long qid) {
		super(userName, title, description, popularity, category, qid);
		// TODO Auto-generated constructor stub
	}
	public ArrayList<String> getAnsList() throws SQLException, ClassNotFoundException{
		ArrayList<String>  answers=new ArrayList<String>();
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/everyoneq", "root",SQL_PASSWORD);
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery("SELECT answer FROM "+getCategory().getAnsTable()+" WHERE qid="+getId());
		while(rs.next()){
			answers.add(rs.getString(1));
		}
		return answers;
		
	}

}

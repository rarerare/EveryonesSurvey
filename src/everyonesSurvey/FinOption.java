package everyonesSurvey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FinOption {
	private final static String SQL_PASSWORD="drwssp";
	private String description;
	private long id;
	
	private Question question;
	public FinOption(long id, Question q, String description){
		this.id=id;
		this.question=q;
		this.description=description;
	}
	public long getId(){
		return id;
	}
	public String getDescription(){
		return description;
	}
	public Question getQ(){
		return question;
	}
	public long selectCount() throws ClassNotFoundException, SQLException{
		QCategory category=question.getCategory();
		
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/everyoneq", "root",SQL_PASSWORD);
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery("select count(*) from "+category.getAnsTable()+" where cId= "+id );
		rs.next();
		long selectCount=rs.getLong(1);
		conn.close();
		return selectCount;
	}
}

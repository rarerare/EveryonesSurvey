package everyonesSurvey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FinAnsQuestion extends Question{
	
	public FinAnsQuestion(String userName, String title, String description, int popularity, QCategory category,
			long qid) {
		super(userName, title, description, popularity, category, qid);
		// TODO Auto-generated constructor stub
	}
	public ArrayList<FinOption> getOptions() throws SQLException, ClassNotFoundException{
		ArrayList<FinOption> options=new ArrayList<FinOption>();
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/everyoneq", "root","");
		Statement stmt=conn.createStatement();
		long qId=getId();
		ResultSet rs= stmt.executeQuery("SELECT position, description, cid FROM "+this.getCategory().getOptTable()+" WHERE qid="+qId  );
		
		while(rs.next()){
			String description=rs.getString(2);
			long cId=rs.getLong(3);
			options.add(new FinOption(cId, this, description));
		}
		conn.close();
		return options;
		
	}
	
}

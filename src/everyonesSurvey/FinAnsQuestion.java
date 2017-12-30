package everyonesSurvey;


import java.sql.SQLException;

import java.util.ArrayList;

public class FinAnsQuestion extends Question{
	
	public FinAnsQuestion(String userName, String title, String description, int popularity, QCategory category,
			long qid) {
		super(userName, title, description, popularity, category, qid);
		// TODO Auto-generated constructor stub
	}
	public ArrayList<FinOption> getOptions() throws SQLException, ClassNotFoundException{
		
		
		return DBConnector.getQOptions(this);
		
	}
	
}

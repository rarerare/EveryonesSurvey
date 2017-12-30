package everyonesSurvey;


import java.sql.SQLException;

import java.util.ArrayList;

public class InfiAnsQuestion extends Question{
	
	public InfiAnsQuestion(String userName, String title, String description, int popularity, QCategory category,
			long qid) {
		super(userName, title, description, popularity, category, qid);
		// TODO Auto-generated constructor stub
	}
	public ArrayList<String> getAnsList() throws SQLException, ClassNotFoundException{
		ArrayList<String>  answers=DBConnector.getAnsList(getCategory(), getId());
		return answers;
		
	}

}

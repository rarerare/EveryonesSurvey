package everyonesSurvey;


import java.sql.SQLException;


public class FinOption {
	
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
		return DBConnector.getSelectCount(category, id);
	}
}

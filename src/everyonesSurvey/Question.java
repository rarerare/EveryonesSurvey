package everyonesSurvey;

public class Question {
	private String user;
	private String title;
	private String description;
	private int popularity;
	public Question(String user, String title, String description, int popularity){
		this.user=user;
		this.title=title;
		this.description=description;
		this.popularity=popularity;
	}
	public String getTitle(){
		return title;
	}
	public String getDescription(){
		return description;
	}
	public String getUser(){
		return user;
	}
	
}

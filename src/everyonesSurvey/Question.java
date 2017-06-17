package everyonesSurvey;

public class Question {
	private String user;
	private String title;
	private String description;
	private int popularity;
	private QCategory category;
	public Question(String user, String title, String description, int popularity, QCategory category){
		this.user=user;
		this.title=title;
		this.description=description;
		this.popularity=popularity;
		this.category=category;
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
	public QCategory getCategory(){
		return this.category;
	}
}

package everyonesSurvey;

public class User {
	private String username;
	private String firstName;
	private String lastName;
	private String eMail;
	private long id;
	public User(String username, String firstName, String lastName, String eMail, long id){
		this.username=username;
		this.firstName=firstName;
		this.lastName=lastName;
		this.eMail=eMail;
		this.id=id;
	}
	public String getUsername(){
		return username;
	}
	public String getFirstName(){
		return firstName;
	}
	public String getLastName(){
		return lastName;
	}
	public String eMail(){
		return eMail;
	}
	public long getId(){
		return id;
	}
}

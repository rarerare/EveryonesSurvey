package everyonesSurvey;

public class Questionnaire {
	private long qnid;
	private String title;
	private long userId;
	private int qNum;
	public Questionnaire(long qnid, String title,long userId, int qNum){
		this.qnid=qnid;
		this.title=title;
		this.userId=userId;
		this.qNum=qNum;
	}
	public long getId(){
		return qnid;
	}
	public String getTitle(){
		return title;
	}
	public long getUserId(){
		return userId;
	}
	public int getQNum(){
		return qNum;
	}
}

package everyonesSurvey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Question {
	private String userName;
	private String title;
	private String description;
	private int popularity;
	private QCategory category;
	private long qid;
	public Question(String userName, String title, String description, int popularity, QCategory category, long qid){
		this.userName=userName;
		this.title=title;
		this.description=description;
		this.popularity=popularity;
		this.category=category;
		this.qid=qid;
	}
	public String getTitle(){
		return title;
	}
	public String getDescription(){
		return description;
	}
	public String getUserName(){
		return userName;
	}
	public QCategory getCategory(){
		return this.category;
	}
	public long getId(){
		return qid;
	}
	/*public String addQ(String responseStr) throws SQLException{
		
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/everyoneq", "root","");
		Statement stmt=conn.createStatement();
		long qid=getId();
		String inputname=getCategory()+"__"+qid;
		String optionTableName=null;
		String inputType="";
		if(getCategory()==QCategory.samc){
			optionTableName="sachoices";
			inputType="radio";
		}else if(getCategory()==QCategory.mamc){
			optionTableName="machoices";
			inputType="checkbox";
		}else if(getCategory()==QCategory.fr){
			inputType="text";
		}else if(getCategory()==QCategory.number){
			inputType="number";
		}
		if(optionTableName!=null){
			ResultSet rs= stmt.executeQuery("SELECT position, description, cid FROM "+optionTableName+" WHERE qid="+qid  );
			
			while(rs.next()){
				String description=rs.getString(2);
				long cId=rs.getLong(3);
				responseStr+="<input type='"+inputType+"' name='"+inputname+"' value='"+cId+"'>"+description+"<br>";
			}
		}else{
			if(getCategory()==QCategory.number){
				responseStr+="<input type='"+inputType+"' name='"+inputname+"' step='any'><br>";
			}else{
				responseStr+="<input type='"+inputType+"' name='"+inputname+"'><br>";
			}
			
		}
		
		
		
		conn.close();
		return responseStr;
		
	}*/
}

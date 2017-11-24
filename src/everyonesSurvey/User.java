package everyonesSurvey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
	private final static String SQL_PASSWORD="drwssp";
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
	public static User getById(long id) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/everyoneq", "root",SQL_PASSWORD);
		Statement stmt=conn.createStatement();
		ResultSet rsQn= stmt.executeQuery("SELECT username, firstname, lastname"
				+", email"
						+ "  FROM user WHERE userid="+id );
		rsQn.next();
		User user= new User(rsQn.getString(1), rsQn.getString(2), rsQn.getString(3),rsQn.getString(4), id);
		conn.close();
		return user;
	}
}

import java.sql.*;
/**
 * 
 * @author A.Buicenka
 *Prisijungimas prie duomenø bazës
 */
public class DBase
{
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/";
	String db = "Book";
	String username = "root";
	String password = "qwerty";
	
	private static DBase instance = null;
	public static DBase getInstance() 
	{
		if (instance == null) 
		{
			instance = new DBase();
		}
		return instance;
	}
	
	private Connection connection = null;
	public Connection getConnection () 
	{
		return connection;
	}
	
	private	DBase (){
		try 
		{
		Class.forName(driver);
		connection = DriverManager.getConnection(url+db,username,password);
		System.out.println("Connected");
		} catch (Exception e) {System.out.println("Connection problems--> " + e.getMessage());}
	}
}
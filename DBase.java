import java.sql.*;
/**
 * 
 * @author A.Buicenka
 *Prisijungimas prie duomenø bazës
 */
public class DBase
{
	String DRIVER = "com.mysql.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/";
	String DB = "Book";
	String USERNAME = "root";
	String PASSWORD = "qwerty";
	
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
		Class.forName(DRIVER);
		connection = DriverManager.getConnection(URL+DB,USERNAME,PASSWORD);
		System.out.println("Connected");
		} catch (Exception e) {System.out.println("Connection problems--> " + e.getMessage());}
	}
}
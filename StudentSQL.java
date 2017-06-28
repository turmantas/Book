import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentSQL
{
    public boolean addUser (Student student) throws Exception
    {
    	String query = "insert into student (name, email) values (?, ?);"; 
		try
		{
			PreparedStatement ps = DBase
					.getInstance()
					.getConnection()
					.prepareStatement(query, 1);
			ps.setString(1, student.name);
			ps.setString(2, student.email);
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if (!rs.next())
				return false;
			student.student_id = rs.getInt(1);
			return true;
		}
		catch (SQLException e) {
			System.out.println(query);
			System.out.println(e.getMessage());
		}
		return false;    
    }
    
    public boolean isEmail (String email) throws SQLException
    {
    	String query = "select * from student where email = ?";
    	try
    	{
    		PreparedStatement ps = DBase
					.getInstance()
					.getConnection()
					.prepareStatement(query, 1);
    		ps.setString(1, email);
    		ResultSet result = ps.executeQuery();
    		if (result.next())
    			System.out.println("Studentas su elektroniniu pastu: '" + result.getString(3) + "' yra uzregistruotas");
    		else
    			System.out.println("Atsiprasome, bet studento su elektroniniu pastu: '" + email + "' nera sarase");
    	} catch (SQLException e) {
			System.out.println(e.getMessage());
    	}
        return false;
    }

    public ArrayList<Student> getStudents () throws SQLException
    {
    	String query = "Select * from student";
    	ArrayList<Student> list = new ArrayList<>();
    	Statement st;
    	try
    	{
    		st = DBase
    				.getInstance()
    				.getConnection()
    				.createStatement();
    		ResultSet rs = st.executeQuery(query);
    		while (rs.next())
    		{
    			Student student = new Student ();
    			student.student_id = rs.getInt("student_id");
    			student.name = rs.getString("name");
    			student.email = rs.getString("email");
    			student.avg_point = rs.getDouble("avg_point");
    			list.add(student);
    		}
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
        return list;
    }
    
    public void showListStudents(ArrayList<Student> list) 
    {
    	System.out.println(" id |       name                |    email             |average point ");
    	System.out.println("----+---------------------------+----------------------+--------------");
		for (Student student : list)
			System.out.println(	student.student_id + "\t" + 
								student.name + "\t" + 
								student.email + "\t" +
								student.avg_point);
	}
    
	public double getAvgPoints(int student_id) throws SQLException 
	{

		Statement st;
		
		String query = "SELECT AVG (point) FROM point WHERE student_id = " + student_id + ";";
			st = DBase.
					getInstance().
					getConnection().
					createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next())
		{
			System.out.println("Studento vidurkis (kurio ID = " + student_id + " ) yra = " + rs.getDouble(1));
			return rs.getDouble(1);
		}
		return 0.0;
	}

    public void updateAvgPoints (int student_id) throws SQLException
    {
    	Statement st;
    	String query = "update student join (select student_id, avg(point) as avg_point from point group by student_id) pt using (student_id) set student.avg_point = pt.avg_point"; 
    		st = DBase
    				.getInstance()
    				.getConnection()
    				.createStatement();
    	st.executeUpdate(query);
		System.out.println(query);
    }

}

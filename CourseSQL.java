import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseSQL
{
	public boolean addCourse (Course course) throws SQLException
    {
		String query = "insert into course (title) values (?);"; 
		try
		{
			PreparedStatement ps = DBase
					.getInstance()
					.getConnection()
					.prepareStatement(query, 1);
			ps.setString(1, course.title);
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if (!rs.next())
				return false;
			course.course_id = rs.getInt(1);
			return true;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
    }
    
    public boolean isTitle (String title) throws SQLException
    {
    	String query = "select * from course where title = ?";
    	try
    	{
    		PreparedStatement ps = DBase
					.getInstance()
					.getConnection()
					.prepareStatement(query, 1);
    		ps.setString(1, title);
    		ResultSet result = ps.executeQuery();
    		if (result.next())
    			System.out.println("Kursas '" + result.getString(2) + "' yra sarase");
    		else
    			System.out.println("Atsiprasome, bet tokio kurso '" + title + "' nera sarase");
    	} catch (SQLException e) {
			System.out.println(e.getMessage());
    	}
        return false;
    }
    
    public ArrayList<Course> getCourses () throws SQLException
    {
    	String query = "Select * from course";
    	ArrayList<Course> list = new ArrayList<>();
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
    			Course course = new Course ();
    			course.course_id = rs.getInt("course_id");
    			course.title = rs.getString("title");
    			list.add(course);
    		}
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
        return list;
    }
    
    public void showListCourses (ArrayList<Course> list) 
    {
    	System.out.println(" id |       Dalykas   ");
    	System.out.println("-------+---------------");
		for (Course course : list)
			System.out.println(course.course_id + "\t" + course.title );
	
	}
}
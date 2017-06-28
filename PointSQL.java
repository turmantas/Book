import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PointSQL
{
    public boolean addPoint (Point point) throws Exception
    {
    	String query = "insert into point (course_id, student_id, point, point_date) values (?, ?, ?, now());"; 
		try
		{
			PreparedStatement ps = DBase
					.getInstance()
					.getConnection()
					.prepareStatement(query, 1);
			ps.setInt(1,point.course_id);
			ps.setInt(2,point.student_id);
			ps.setInt(3,point.point);
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if (!rs.next())
				return false;
			point.point_id = rs.getInt(1);
			return true;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
        return false;
    }
    
    public String point (int course_id, int student_id, int point)
    {
        return "unknown error";
    }
}

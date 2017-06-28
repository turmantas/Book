
public class Point 
{
	public int point_id; 
	public int course_id; 
	public int student_id; 
	public int point; 
	public int point_date;
	@Override
	public String toString() {
		return "Point [point_id=" + point_id + ", course_id=" + course_id + ", student_id=" + student_id + ", point="
				+ point + ", point_date=" + point_date + "]";
	}
	
}

import java.sql.SQLException;
import java.util.*;

public class Menu 
{
    Scanner scanner = new Scanner (System.in);
    
    public void show ()
    {
        System.out.println();
        System.out.println("=== MENIU ===");
        System.out.println("1. Registruoti nauja kursa.");
        System.out.println("2. Perziureti visus kursus.");
        System.out.println("3. Patikrinti kursa sarase.");
        System.out.println("4. Registruoti nauja studenta.");
        System.out.println("5. Perziureti studentu sarasa.");
        System.out.println("6. Patikrinti studenta pagal elektroninio pasto adresa.");
        System.out.println("7. Isvesti pasirinkto studento vidurki.");
        System.out.println("8. Ivesti nauja pazymi");
        System.out.println("0. Iseiti");
    }
    
    public int getItem ()
    {
        int item = 0;
        do
        {
        	System.out.print(" > ");
        	item = scanner.nextInt();
        	scanner.nextLine();
        	if (item < 0 || item > 8) System.out.println("Skaicius turi buti nuo 1 iki 5"); return item;
        } while (item < 0 || item > 8);
    }
    
    public void start () throws Exception
    {
    	int item;
    	do
        {
    		show ();
    		item = getItem ();
    		switch (item)
    		{
    		case 1 : doAddCourse(); break;
    		case 2 : doShowCourses(); break;
    		case 3 : doShowCourse(); break;
    		case 4 : doAddStudent(); break;
    		case 5 : doShowStudents(); break;
    		case 6 : doShowStudent(); break;		
    		case 7 : doShowAVGStudent(); break;
    		case 8 : doAddPoint(); break;
  			}
        } while (item != 0);
    	System.out.println("Bye");
    }

    private void doAddCourse() throws Exception
    {
    	Course course = new Course ();
        System.out.println("Registruoti nauja kursa.");
        System.out.print("Iveskite kurso pavadinima: ");
        course.title = scanner.nextLine();
        
        CourseSQL sql = new CourseSQL();
        if (sql.addCourse (course))
        	System.out.println("Added new Record to " + course);
        else
        	System.out.println("Error");
    }

    private void doShowCourses () throws Exception 
    {
        System.out.println("Perziureti visus kursus.");
        CourseSQL sql = new CourseSQL ();
        sql.showListCourses (sql.getCourses());
    }
    
    private void doShowCourse () throws Exception 
    {
    	CourseSQL sql = new CourseSQL();
    	System.out.print("Iveskite kurso pavadinima, kuri norite patikrinti sarase: ");
    	String title = scanner.nextLine();
    	sql.isTitle(title);
    }

	private void doAddStudent() throws Exception
    {
		Student student = new Student ();
        System.out.println("Registruoti nauja studenta.");
        System.out.println("Iveskite studento varda");
        student.name = scanner.nextLine();
        System.out.println("Iveskite elektronini pasta");
        student.email = scanner.nextLine();
        
        StudentSQL sql = new StudentSQL ();     
        if (sql.addUser(student))
        	System.out.println("Added new Record#" + student.student_id);
        else
        	System.out.println("Error");
    }

    private void doShowStudents() throws Exception 
    {
        System.out.println("Perziureti studentu sarasa.");
        StudentSQL sql = new StudentSQL ();
        sql.showListStudents (sql.getStudents());
    }
    
    private void doShowStudent () throws Exception 
    {
    	StudentSQL sql = new StudentSQL();
    	System.out.print("Iveskite studento elektroninio pasto adresa: ");
    	String email = scanner.nextLine();
    	sql.isEmail(email);
    }
    
	private void doShowAVGStudent() throws SQLException {

		System.out.println("----------------------getAVGStrudent function------------");
		System.out.print("Iveskit ID studento -> ");
		int student_id = scanner.nextInt();
		StudentSQL sql = new StudentSQL();
		sql.getAvgPoints(student_id);
		
	}
    
	private void doAddPoint() throws Exception 
    {
		Point point = new Point ();
        System.out.println("Ivesti nauja pazymi.");
        System.out.println("Iveskite kurso id");
        point.course_id = scanner.nextInt();
        System.out.println("Iveskite studento id");
        point.student_id = scanner.nextInt();
        System.out.println("Iveskite pazymi");
        point.point = scanner.nextInt();  
        if (point.point < 0 || point.point > 10) System.out.println("Pazymys turi buti nuo 1 iki 10");
        else 
        {
	        PointSQL sql = new PointSQL ();     
	        if (sql.addPoint(point))
	        	System.out.println("Added new Record#" + point.point_id);
	        else
	        	System.out.println("Error");
	        
	        StudentSQL sql2 = new StudentSQL();
			sql2.updateAvgPoints(0);        
        }
    }

   

    public String getErrorText (String error)
    {
        switch (error)
        {
            case "course not found"  : return "Kursas nerastas"; 
            case "student not found" : return "Studentas nerastas";
            case "invalid point"     : return "Pazymis turi buti nuo 1 iki 10";
            case "ok"                : return "Pazymis uzregistruota";
            default                  : return "Nepazistama klaida";
        }
    }
}

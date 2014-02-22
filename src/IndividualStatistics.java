import java.util.*;

public class IndividualStatistics 
{
	
	public static void main(String[] args) 
	{				
        Student s = StatisticsResource.getStudent(response(), "data.xls");		
        err(s); 
        
        // first and last semester + duration in school
        System.out.println("\nDuration in school: " + s.getNumOfSemesters() + " semesters" );              
        System.out.println("Student's first semester: " + s.getMinSemester().getDm_month() + "/" + s.getMinSemester().getDm_year());
        System.out.println("Student's last semester: " + s.getMaxSemester().getDm_month() + "/" + s.getMaxSemester().getDm_year());
        
        // Graduated or not
        System.out.println("\n" + s.didGraduate());
        
        //Cumulative and discipline GPA
        System.out.println("\nStudent's GPA is: " + s.getCumulativeGPA());
	}

	public static long response ()
	{
		long l;
		Scanner student = new Scanner(System.in);
        System.out.println("Enter student's ID: ");
        l = student.nextInt();
		return l;
	}
	
	public static void err (Student s)
    {
        if (s == null)
        {
        	System.out.println("Student was not found");      	
        }     
    }
	
}

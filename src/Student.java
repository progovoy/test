import java.util.*;

public class Student 
{
	private long dm_id;
	private ArrayList<Course> dm_courses;
	private ArrayList<Semester> dm_semesters;
	
	public Student(long id)
	{
		this.dm_id = id;
		this.dm_courses = new ArrayList<Course>();
		this.dm_semesters = new ArrayList<Semester>();
	}
	
	public void addCourse(Course c)
	{
		this.dm_courses.add(c);
		boolean found = false;
		
		for (int i = 0; i < this.dm_semesters.size(); i++)
		{
			if (c.getDm_semester().getDm_month() == this.dm_semesters.get(i).getDm_month() && 
				c.getDm_semester().getDm_year() == this.dm_semesters.get(i).getDm_year())
			{
				found = true;
			}
		}
		
		if (found == false)
		{
			this.dm_semesters.add(c.getDm_semester());
		}
	}
	
	public Semester getMinSemester()
	{
		int min_month = this.dm_semesters.get(0).getDm_month();
		int min_year = this.dm_semesters.get(0).getDm_year();
		int min_index = 0;
		
		for (int i = 1; i < this.dm_semesters.size(); i++)
		{
			if (this.dm_semesters.get(i).getDm_year() < min_year)
			{
				min_month = this.dm_semesters.get(i).getDm_month();
				min_year = this.dm_semesters.get(i).getDm_year();
				min_index = i;
			}
			else if (this.dm_semesters.get(i).getDm_year() == min_year && 
					this.dm_semesters.get(i).getDm_month() < min_month)
			{
				min_month = this.dm_semesters.get(i).getDm_month();
				min_year = this.dm_semesters.get(i).getDm_year();
				min_index = i;
			}
		}
		
		return this.dm_semesters.get(min_index);
	}
	
	public Semester getMaxSemester()
	{
		int max_month = this.dm_semesters.get(0).getDm_month();
		int max_year = this.dm_semesters.get(0).getDm_year();
		int max_index = 0;
		
		for (int i = 1; i < this.dm_semesters.size(); i++)
		{
			if (this.dm_semesters.get(i).getDm_year() > max_year)
			{
				max_month = this.dm_semesters.get(i).getDm_month();
				max_year = this.dm_semesters.get(i).getDm_year();
				max_index = i;
			}
			else if (this.dm_semesters.get(i).getDm_year() == max_year && 
					this.dm_semesters.get(i).getDm_month() > max_month)
			{
				max_month = this.dm_semesters.get(i).getDm_month();
				max_year = this.dm_semesters.get(i).getDm_year();
				max_index = i;
			}
		}
		
		return this.dm_semesters.get(max_index);
	}
	
	public int getNumOfSemesters()
	{
		return this.dm_semesters.size();
	}
	
	public int getTotalNumOfCredits()
	{
		int n = 0;
		
		for (int i = 0; i < dm_courses.size(); i++)
		{
			if (dm_courses.get(i).getDm_credits() > (int)'F')
				continue;
			
			n += dm_courses.get(i).getDm_credits();
		}
		
		return n;
	}
	
	public int getTotalNumOfCreditsByCourseCode(String code)
	{
		int n = 0;
		
		for (int i = 0; i < dm_courses.size(); i++)
		{
			if (dm_courses.get(i).getDm_credits() > (int)'F' || dm_courses.get(i).getDm_code().compareTo(code) != 0)
				continue;
			
			n += dm_courses.get(i).getDm_credits();
		}
		
		return n;
	}
	
	public int getNumOfValidCredits()
	{
		int n = 0;
		
		for (int i = 0; i < dm_courses.size(); i++)
		{
			if (dm_courses.get(i).getDm_final() != 'F' && dm_courses.get(i).getDm_final() != 'W')
			{
				n += dm_courses.get(i).getDm_credits();
			}
		}
		
		return n;
	}
	
	public boolean didGraduate()
	{
		return this.getNumOfValidCredits() >= 128;
	}
	
	public double getCumulativeGPA()
	{
		double grade = 0;
		double credits = 0;
		double gpa;
		
		for (int i = 0; i < dm_courses.size(); i++)
		{	
			if (dm_courses.get(i).getDm_credits() > (int)'F')
				continue;
			
			grade += ('F' - dm_courses.get(i).getDm_final()) * dm_courses.get(i).getDm_credits();
		}
		
		credits = (double)this.getTotalNumOfCredits();
			
		gpa = grade / credits;
		gpa = Math.round(gpa * 100);
		gpa = gpa / 100;
		return gpa;
	}
	
	public double getDisciplineGPA()
	{
		double grade = 0;
		double credits = 0;
		double gpa;
		
		for (int i = 0; i < dm_courses.size(); i++)
		{	
			if (dm_courses.get(i).getDm_credits() > (int)'F' || dm_courses.get(i).getDm_code().compareTo("CPSC") != 0)
				continue;
			
			grade += ('F' - dm_courses.get(i).getDm_final()) * dm_courses.get(i).getDm_credits();
		}
		
		credits = (double)this.getTotalNumOfCreditsByCourseCode("CPSC");
			
		gpa = grade / credits;
		gpa = Math.round(gpa * 100);
		gpa = gpa / 100;
		return gpa;
	}
}

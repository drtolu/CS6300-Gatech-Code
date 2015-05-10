package edu.gatech.seclass.gradescalc;

public class Student {
	
	int    iAttendance;
	String szName;
	String szGtID;
	String szTeam;
	
	public Student(double t_dAttendance,
			       String t_szName,
	               String t_szGtID,
	               String t_szTeam)
	{
		iAttendance = (int)t_dAttendance;
		szName = t_szName;
		szGtID = t_szGtID;
		szTeam = t_szTeam;
	}
	
	public String getName()
	{
		return szName;
	}
	
	public String getGtid()
	{
		return szGtID;
	}
	
	public int getAttendance()
	{
		return iAttendance;
	}
	
	public String getTeam()
	{
		return szTeam;
	}
	
	public Student(String t_szName,
			       String t_szGtID,
			       Course course)
    {
		szName = t_szName;
		szGtID = t_szGtID;
		
		for (int i =1; i< course.GetGrades().GetAttendance().size(); i++)
		{
			if(course.GetGrades().GetAttendance().get(i).equals(szName))
				iAttendance = Integer.parseInt(course.GetGrades().GetAttendance().get(i).get(1));
		}
		
		for (int i=1; i < course.GetStudents().GetTeams().size(); i++)
		{
			for(int j=1; j< course.GetStudents().GetTeams().get(i).size(); j++)
			{
				if (course.GetStudents().GetTeams().get(i).get(j).equals(szName))
					szTeam = course.GetStudents().GetTeams().get(i).get(0);
			}
		}		       
    }
	
	public Student(String t_szName, String t_szGtID) //need to revisit as i am not sure how to populate attendance and team.
	{
		szName = t_szName;
		szGtID = t_szGtID;
		iAttendance = 0;
		szTeam = "";
	}
	
	public void setTeam(String s)
	{
		szTeam = s;
	}
}

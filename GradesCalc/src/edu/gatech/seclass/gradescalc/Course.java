package edu.gatech.seclass.gradescalc;

import java.util.HashMap;
import java.util.HashSet;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Course {

	HashSet<Student> studentsRost = new HashSet<Student>();	
	Students m_students;
	Grades   m_grades;
	String   m_szFormula;
	
	public Students GetStudents(){ return m_students;}
	public Grades   GetGrades(){ return m_grades;}

	public Course(Students students, Grades grades)
	{
		m_students = students;
		m_grades = grades;
		m_szFormula = "ATT * 0.2 + AAG * 0.4 + APG * 0.4";

		for(int i=1; i<m_students.GetStudentInfo().size(); i++)
		{
			String szName = m_students.GetStudentInfo().get(i).get(0);
			String szGtID = m_students.GetStudentInfo().get(i).get(1);
			String szTeam = "";
			Double   dAttendance = 0.0;

			for(int j=1; j<m_students.GetTeams().size(); j++)
			{
				for(int k=1; k< m_students.GetTeams().get(j).size(); k++)
				{
					if(m_students.GetTeams().get(j).get(k).equals(szName))
						szTeam = m_students.GetTeams().get(j).get(0);
				}
			}

			for(int j=1; j<m_grades.GetAttendance().size(); j++)
			{
				for(int k=0; k<m_grades.GetAttendance().get(j).size(); k++)
				{
					if(m_grades.GetAttendance().get(j).get(k).equals(szGtID))
						dAttendance = Double.parseDouble(m_grades.GetAttendance().get(j).get(1));
				}
			}
			
			Student student = new Student(dAttendance, szName, szGtID, szTeam);
			studentsRost.add(student);
		}
	}

	public int getNumStudents()
	{
		return m_students.GetStudentInfo().size() -1;		
	}

	public int getNumAssignments()
	{
		if(m_grades.GetIndividualGrades().size() > 0)
			return m_grades.GetIndividualGrades().get(0).size()-1;
		else
			return 0;
	}

	public int getNumProjects()
	{
		if(m_grades.GetIndividualContribs().size() > 0)
			return m_grades.GetIndividualContribs().get(0).size()-1;
		else
			return 0;
	}

	public HashSet<Student> getStudents()
	{
		return studentsRost;	
	}

	public Student getStudentByName(String szStudentName)
	{	
		for(Student student : studentsRost)
		{
			if (student.getName().equals(szStudentName))
				return student;
		}
		
		Student student = null;
		return student;
	}

	public Student getStudentByID(String szStudentID)
	{
		for(Student student : studentsRost)
		{
			if (student.getGtid().equals(szStudentID))
				return student;
		}
		
		Student student = null;
		return student;
	}
	
	public void addAssignment(String szAssignmentName)
	{
		for(int i=0;i < m_grades.GetIndividualGrades().size(); i++)
		{
			if (i == 0)
				m_grades.GetIndividualGrades().get(i).add(szAssignmentName);
			else
				m_grades.GetIndividualGrades().get(i).add("");
		}
	}
				
	public void updateGrades(Grades grades)
	{
		grades.updateGradesDB(m_grades.GetIndividualGrades(), m_grades.GetIndividualContribs());
	}
	
	public void addGradesForAssignment(String szAssignmentName, HashMap<Student, Integer> grades)
	{
		for (Student student : grades.keySet()) 
		{
			int row = 0;
			int col = 0;
			
			for (int i =1; i<m_grades.GetIndividualGrades().size(); i++)
			{
				if(m_grades.GetIndividualGrades().get(i).get(0).equals(student.getGtid()))
					row = i;	
			}
			
			for (int j = 1; j< m_grades.GetIndividualGrades().get(0).size();j++)
			{
				if(m_grades.GetIndividualGrades().get(0).get(j).equals(szAssignmentName))
					col = j;
			}
			
			if (row !=0 && col !=0)
				m_grades.GetIndividualGrades().get(row).set(col, grades.get(student).toString());	
		}		
	}
	
	public int getAverageAssignmentsGrade(Student student)
	{
		int iNoOfAssignments = 0;
		
		for (int i=1; i < m_grades.GetIndividualGrades().size(); i++)
		{
			int iTotal = 0;
			if(m_grades.GetIndividualGrades().get(i).get(0).equals(student.getGtid()))
			{
				
				for (int j = 1; j < m_grades.GetIndividualGrades().get(i).size(); j++)
				{	
					try
					{
						iTotal += Integer.parseInt(m_grades.GetIndividualGrades().get(i).get(j));
						iNoOfAssignments++;		
					}
					catch(Exception e) {}
				}
				try{
				return ((int) Math.round((double)iTotal / (double)iNoOfAssignments));
				}
				catch(Exception e){return 0;}
			}
		}
		
		return 0;		
	}
	
	public int getAverageProjectsGrade(Student student)
	{
		int iNoOfProjects = 0;
		double dTotal = 0;
		
		if(student.getTeam().equals(""))
		{
			for (int i=1; i < m_students.GetTeams().size(); i++)
			{
				for(int j=1; j< m_students.GetTeams().get(i).size(); j++)
				{
					if (m_students.GetTeams().get(i).get(j).equals(student.getName()))
						student.setTeam(m_students.GetTeams().get(i).get(0));
				}
			}
		}
		
		try{
		for (int i=1; i < m_grades.GetIndividualContribs().size(); i++)
		{		
			if(m_grades.GetIndividualContribs().get(i).get(0).equals(student.getGtid()))
			{			
				for (int j = 1; j < m_grades.GetIndividualContribs().get(i).size(); j++)
				{					
					String szProjectName = m_grades.GetIndividualContribs().get(0).get(j);
					
					for (int k =1; k<m_grades.GetTeamGrades().size();k++)
						for (int l =1;l<m_grades.GetTeamGrades().get(0).size();l++)
							if(m_grades.GetTeamGrades().get(0).get(l).equals(szProjectName) && m_grades.GetTeamGrades().get(k).get(0).equals(student.getTeam()))
						    {
								dTotal = dTotal + ((Double.parseDouble(m_grades.GetIndividualContribs().get(i).get(j))/100) * Double.parseDouble(m_grades.GetTeamGrades().get(k).get(l)));
								iNoOfProjects++;
						    }
				}
			}
		}
		
		return ((int) Math.round((double)dTotal / (double)iNoOfProjects));}
		catch(Exception e){return 0;}
	}
	
	public void addIndividualContributions(String szProjectName, HashMap<Student, Integer> contributions)
	{
		for (Student student : contributions.keySet()) 
		{
			int row = 0;
			int col = 0;
			
			for (int i =1; i<m_grades.GetIndividualContribs().size(); i++)
			{
				if(m_grades.GetIndividualContribs().get(i).get(0).equals(student.getGtid()))
					row = i;	
			}
			
			for (int j = 1; j< m_grades.GetIndividualContribs().get(0).size();j++)
			{
				if(m_grades.GetIndividualContribs().get(0).get(j).equals(szProjectName))
					col = j;
			}
			
			if (row !=0 && col !=0)
				m_grades.GetIndividualContribs().get(row).set(col, contributions.get(student).toString());	
		}
	}
	
	public void PrintGradesDB(Grades grades)
	{
		for (int i=0; i < grades.GetIndividualGrades().size();i++)
		{
			for(int j=0; j< grades.GetIndividualGrades().get(i).size();j++)
				System.out.print(grades.GetIndividualGrades().get(i).get(j) + " :: ");
			System.out.println();
		}		
	}
	
	public void addStudent(Student student)
	{
	}
	
	public void updateStudents(Students students)
	{
	}
	
	public void addProject(String szProjectName)
	{
	}
	
	public void addGradesForProject(String szProjectName, HashMap<Student, Integer> grades)
	{
	}
	
	public int getTotalTeamProjectGrade(Student student)
	{
		return 0;
	}
	
	public int getAttendance(Student student)
	{
		return student.getAttendance();
	}
	
	public String getTeam(Student student)
	{
		return student.getTeam();
	}
	
	public String getEmail(Student student)
	{
		for (int i =1; i < m_students.GetStudentInfo().size(); i++)
		{
			if(m_students.GetStudentInfo().get(i).get(0).equals(student.getName()))
				return m_students.GetStudentInfo().get(i).get(2);
		}
		return "";
	}
	
	public void setFormula(String szFormula)
	{
		m_szFormula = szFormula;
	}
	
	public String getFormula()
	{
		return m_szFormula;
	}
	
	public int getOverallGrade (Student student)
	{
		try{
			int ATT = student.getAttendance();
			int AAG = getAverageAssignmentsGrade(student);
			int APG = getAverageProjectsGrade(student);

			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("js");
			engine.put("ATT", ATT);
			engine.put("AAG", AAG);
			engine.put("APG", APG);

			Object obj = engine.eval(m_szFormula); 
			return (int)Math.round(Double.parseDouble(obj.toString()));
		}
		catch(Exception e)
		{
			throw new GradeFormulaException("Exception found for formula :" + m_szFormula);
		}	
	}
}

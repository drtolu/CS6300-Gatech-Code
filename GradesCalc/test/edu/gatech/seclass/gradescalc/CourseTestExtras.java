package edu.gatech.seclass.gradescalc;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourseTestExtras {

    Students students = null;
    Grades grades = null;
    Course course = null;
    static final String GRADES_DB = "DB" + File.separator + "GradesDatabase6300-grades.xlsx";
    static final String GRADES_DB_GOLDEN = "DB" + File.separator + "GradesDatabase6300-grades-goldenversion.xlsx";
    static final String STUDENTS_DB = "DB" + File.separator + "GradesDatabase6300-students.xlsx";
    static final String STUDENTS_DB_GOLDEN = "DB" + File.separator + "GradesDatabase6300-students-goldenversion.xlsx";
    
    @Before
    public void setUp() throws Exception {
        FileSystem fs = FileSystems.getDefault();
        Path gradesdbfilegolden = fs.getPath(GRADES_DB_GOLDEN);
        Path gradesdbfile = fs.getPath(GRADES_DB);
        Files.copy(gradesdbfilegolden, gradesdbfile, REPLACE_EXISTING);
        Path studentsdbfilegolden = fs.getPath(STUDENTS_DB_GOLDEN);
        Path studentsdbfile = fs.getPath(STUDENTS_DB);
        Files.copy(studentsdbfilegolden, studentsdbfile, REPLACE_EXISTING);    	
    	students = new Students(STUDENTS_DB);
        grades = new Grades(GRADES_DB);
        course = new Course(students, grades);
    }

    @After
    public void tearDown() throws Exception {
        students = null;
        grades = null;
        course = null;
    }
    
    @Test
    public void testAddStudent() {
    	Student student1 = new Student(90, "Namit Gupta", "8094518", "Team1");
        course.addStudent(student1);
        course.updateGrades(new Grades(GRADES_DB));
        course.updateStudents(new Students(STUDENTS_DB));
        assertEquals(17, course.getNumStudents());
        Student student2 = new Student(95, "Dipanshu Gupta", "8094519", "Team5");
        course.addStudent(student2);
        course.updateGrades(new Grades(GRADES_DB));
        course.updateStudents(new Students(STUDENTS_DB));
        assertEquals(18, course.getNumStudents());
    }
    
    @Test
    public void testAddProject() {
        course.addProject("Project: purple-box testing");
        course.updateGrades(new Grades(GRADES_DB));
        assertEquals(4, course.getNumProjects());
        course.addProject("Project: pink-box testing");
        course.updateGrades(new Grades(GRADES_DB));
        assertEquals(5, course.getNumProjects());
    }
    
    @Test
    public void testAddGradesForProject() {
        String projectName = "Project: category partition";
        Student student1 = new Student("Josepha Jube", "901234502", course);
        Student student2 = new Student("Christine Schaeffer", "901234508", course);
        Student student3 = new Student("Ernesta Anderson", "901234510", course);
        course.addProject(projectName);
        course.updateGrades(new Grades(GRADES_DB));
        HashMap<Student, Integer> grades = new HashMap<Student, Integer>();
        grades.put(student1, 87);
        grades.put(student2, 94);
        grades.put(student3, 100);
        course.addGradesForProject(projectName, grades);
        course.updateGrades(new Grades(GRADES_DB));
        assertEquals(375, course.getTotalTeamProjectGrade(student1));
        assertEquals(382, course.getTotalTeamProjectGrade(student2));
        assertEquals(388, course.getTotalTeamProjectGrade(student3));
    }   
}
    

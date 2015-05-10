package edu.gatech.seclass.gradescalc;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.junit.Assert.*;

import java.awt.TextArea;
import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GradesCalcGUITest {

    GradesCalcGUI gui = null;
    static final String GRADES_DB = "DB" + File.separator + "GradesDatabase6300-grades.xlsx";
    static final String GRADES_DB_GOLDEN = "DB" + File.separator + "GradesDatabase6300-grades-goldenversion.xlsx";
    static final String STUDENTS_DB = "DB" + File.separator + "GradesDatabase6300-students.xlsx";
    static final String STUDENTS_DB_GOLDEN = "DB" + File.separator + "GradesDatabase6300-students-goldenversion.xlsx";
    JComboBox<String> studentsComboBox = null;
    JTextField formulaField = null;
    JEditorPane studentInfoArea = null;
    JPanel formulaPanel = null;
    JButton updateFormulaButton = null;

    @Before
    public void setUp() throws Exception {
        FileSystem fs = FileSystems.getDefault();
        Path gradesdbfilegolden = fs.getPath(GRADES_DB_GOLDEN);
        Path gradesdbfile = fs.getPath(GRADES_DB);
        Files.copy(gradesdbfilegolden, gradesdbfile, REPLACE_EXISTING);
        Path studentsdbfilegolden = fs.getPath(STUDENTS_DB_GOLDEN);
        Path studentsdbfile = fs.getPath(STUDENTS_DB);
        Files.copy(studentsdbfilegolden, studentsdbfile, REPLACE_EXISTING);    	
        gui = new GradesCalcGUI(STUDENTS_DB, GRADES_DB);
        // Getting the GUI elements
        Field field = null;
        field = gui.getClass().getDeclaredField("studentsComboBox");
        field.setAccessible(true);
        studentsComboBox = (JComboBox<String>) field.get(gui);
        field = gui.getClass().getDeclaredField("studentInfoArea");
        field.setAccessible(true);
        studentInfoArea = (JEditorPane) field.get(gui);
        field = gui.getClass().getDeclaredField("formulaField");
        field.setAccessible(true);
        formulaField = (JTextField) field.get(gui);
        field = gui.getClass().getDeclaredField("formulaPanel");
        field.setAccessible(true);
        formulaPanel = (JPanel) field.get(gui);
        field = gui.getClass().getDeclaredField("updateFormulaButton");
        field.setAccessible(true);
        updateFormulaButton = (JButton) field.get(gui);
    }

    @After
    public void tearDown() throws Exception {
        gui = null;
    }

    @Test
    public void testStudentsInfoGUI() throws InterruptedException {
        studentsComboBox.setSelectedItem("Rastus Kight");
        Thread.sleep(500);
        String output = studentInfoArea.getText().trim();
        String expectedOutput = "Name: Rastus Kight\nEmail: rk@gatech.edu\nGTID: 901234512\n\nAttendance: 97\nAverage assignments grade: 80\nAverage projects grade: 86\n\nOverall Grade: 86";
        assertEquals(expectedOutput, output);
    }

    @Test(expected = GradeFormulaException.class)
    public void testIncorrectFormulaGUI() {
        formulaField.setText("a + b");
        updateFormulaButton.doClick();
    }

    @Test
    public void testFormulaUpdateGUI1() throws InterruptedException {
        formulaField.setText("ATT * 0.2 + AAG * 0.3 + APG * 0.5");
        updateFormulaButton.doClick();
        studentsComboBox.setSelectedItem("Freddie Catlay");
        Thread.sleep(500);
        String output = studentInfoArea.getText().trim();
        String expectedOutput = "Name: Freddie Catlay\nEmail: fc@gatech.edu\nGTID: 901234501\n\nAttendance: 93\nAverage assignments grade: 90\nAverage projects grade: 81\n\nOverall Grade: 86";
        assertEquals(expectedOutput, output);
    }

    @Test
    public void testFormulaUpdateGUI2() throws InterruptedException {
        formulaField.setText("APG * 1");
        updateFormulaButton.doClick();
        studentsComboBox.setSelectedItem("Laraine Smith");
        Thread.sleep(500);
        String output = studentInfoArea.getText().trim();
        String expectedOutput = "Name: Laraine Smith\nEmail: ls@gatech.edu\nGTID: 901234505\n\nAttendance: 100\nAverage assignments grade: 100\nAverage projects grade: 74\n\nOverall Grade: 74";
        System.out.println("Expected:\n" + expectedOutput);
        System.out.println("Actual:\n" + output);        
        assertEquals(expectedOutput, output);
    }
}

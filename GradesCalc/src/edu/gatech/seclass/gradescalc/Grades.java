package edu.gatech.seclass.gradescalc;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class Grades {

	String m_szGradesDB ="";
	ArrayList<ArrayList<String>> m_Attendance         = new ArrayList<ArrayList<String>>();	
	ArrayList<ArrayList<String>> m_IndividualGrades   = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> m_IndividualContribs = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> m_TeamGrades         = new ArrayList<ArrayList<String>>();

	public Grades(String szGradesDB)
	{
		m_szGradesDB = szGradesDB;

		try
		{
			FileInputStream file = new FileInputStream(new File(m_szGradesDB));
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			//Get first sheet from the workbook
			XSSFSheet sheet0 = workbook.getSheetAt(0);

			for (Row row : sheet0) 
			{
				ArrayList<String> ArrayRow = new ArrayList<String>();

				for (Cell cell : row) 
				{
					String data="";

					if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
						data =  Integer.toString((int)(cell.getNumericCellValue()));
					else
						if(cell.getCellType() == Cell.CELL_TYPE_STRING)
							data = cell.getStringCellValue();

					ArrayRow.add(data);
				}

				m_Attendance.add(ArrayRow);

			}

			//Get Second from the workbook
			XSSFSheet sheet1 = workbook.getSheetAt(1);

			for (Row row : sheet1) 
			{
				ArrayList<String> ArrayRow = new ArrayList<String>();

				for (Cell cell : row) 
				{
					String data="";

					if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
						data =  Integer.toString((int)(cell.getNumericCellValue()));
					else
						if(cell.getCellType() == Cell.CELL_TYPE_STRING)
							data = cell.getStringCellValue();

					ArrayRow.add(data);
				}

				m_IndividualGrades.add(ArrayRow);

			}
			
			//Get Third from the workbook
			XSSFSheet sheet2 = workbook.getSheetAt(2);

			for (Row row : sheet2) 
			{
				ArrayList<String> ArrayRow = new ArrayList<String>();

				for (Cell cell : row) 
				{
					String data="";

					if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
						data =  Integer.toString((int)(cell.getNumericCellValue()));
					else
						if(cell.getCellType() == Cell.CELL_TYPE_STRING)
							data = cell.getStringCellValue();

					ArrayRow.add(data);
				}

				m_IndividualContribs.add(ArrayRow);

			}
			
			//Get Fourth from the workbook
			XSSFSheet sheet3 = workbook.getSheetAt(3);

			for (Row row : sheet3) 
			{
				ArrayList<String> ArrayRow = new ArrayList<String>();

				for (Cell cell : row) 
				{
					String data="";

					if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
						data =  Integer.toString((int)(cell.getNumericCellValue()));
					else
						if(cell.getCellType() == Cell.CELL_TYPE_STRING)
							data = cell.getStringCellValue();

					ArrayRow.add(data);
				}

				m_TeamGrades.add(ArrayRow);

			}
			file.close();
		}
		catch(Exception e)
		{

		}

	}
	
	public ArrayList<ArrayList<String>> GetAttendance()
	{
		return m_Attendance;
	}
	
	public ArrayList<ArrayList<String>> GetIndividualGrades()
	{
		return m_IndividualGrades;
	}
	
	public ArrayList<ArrayList<String>> GetIndividualContribs()
	{
		return m_IndividualContribs;
	}
	
	public ArrayList<ArrayList<String>> GetTeamGrades()
	{
		return m_TeamGrades;
	}
	
	public void updateGradesDB(ArrayList<ArrayList<String>> NewGrades, ArrayList<ArrayList<String>> NewProjectGrades)
	{
		try {
		    FileInputStream file = new FileInputStream(new File(m_szGradesDB));
		 
		    XSSFWorkbook workbook = new XSSFWorkbook(file);
		    XSSFSheet sheet = workbook.getSheetAt(1);
		    
		    for (int i=0; i<NewGrades.size(); i++)
		    {
		    	for(int j=0;j<NewGrades.get(i).size();j++)
		    	{
		    		Cell cell = null;
		    			    		
		    		cell = sheet.getRow(i).getCell(j);
		    		
		    		if(cell == null)
		    		{
		    			sheet.getRow(i).createCell(j);
		    			cell = sheet.getRow(i).getCell(j);		    			
		    		}
		    			
		    		cell.setCellValue(NewGrades.get(i).get(j)); 
		    	}
		    }	
		    
		    XSSFSheet sheet2 = workbook.getSheetAt(2);
		    
		    for (int i=0; i<NewProjectGrades.size(); i++)
		    {
		    	for(int j=0;j<NewProjectGrades.get(i).size();j++)
		    	{
		    		Cell cell = null;
		    			    		
		    		cell = sheet2.getRow(i).getCell(j);
		    		
		    		if(cell == null)
		    		{
		    			sheet2.getRow(i).createCell(j);
		    			cell = sheet2.getRow(i).getCell(j);		    			
		    		}
		    			
		    		cell.setCellValue(NewProjectGrades.get(i).get(j)); 
		    	}
		    }	
		     
		    FileOutputStream outFile =new FileOutputStream(new File(m_szGradesDB));
		    workbook.write(outFile);
		    file.close();
		    outFile.close();
		     
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
}

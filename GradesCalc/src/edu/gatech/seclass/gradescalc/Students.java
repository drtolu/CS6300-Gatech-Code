package edu.gatech.seclass.gradescalc;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class Students {

	String m_szStudentDB ="";
	ArrayList<ArrayList<String>> m_StudentInfo = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> m_Teams = new ArrayList<ArrayList<String>>();

	public Students(String szStudentDB)
	{
		m_szStudentDB = szStudentDB;

		try
		{
			FileInputStream file = new FileInputStream(new File(m_szStudentDB));
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

				m_StudentInfo.add(ArrayRow);

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

				m_Teams.add(ArrayRow);

			}
		}
		catch(Exception e)
		{

		}

	}
	
	public ArrayList<ArrayList<String>> GetStudentInfo()
	{
		return m_StudentInfo;
	}
	
	public ArrayList<ArrayList<String>> GetTeams()
	{
		return m_Teams;
	}
}

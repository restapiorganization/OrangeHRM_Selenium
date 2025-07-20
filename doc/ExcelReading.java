package script;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReading {

	public static String getData(String xlPath,String sheetName,String key)
	{
		LinkedHashMap<String,String>  data=new LinkedHashMap<String,String>();
		try
		{
			Workbook wb = WorkbookFactory.create(new FileInputStream(xlPath));
			Sheet sheet = wb.getSheet(sheetName);
			int rc=sheet.getLastRowNum();
			for(int i=1;i<=rc;i++)
			{
				String k=sheet.getRow(i).getCell(0).toString();
				String v=sheet.getRow(i).getCell(1).toString();
				data.put(k, v);
			}
			
			wb.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return data.get(key);
	}
	
	public static void main(String[] args) {

		
		String un=getData("doc/data.xlsx","sheet1","username");
		System.out.println(un);
		
	
		
	}

}

package generic;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FileUtil {

	public static String getProperty(String filePath,String key)
	{
		String value="";
		Properties p=new Properties();
		try 
		{
			p.load(new FileInputStream(filePath));
			value=p.getProperty(key);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		System.out.println("Property Value:"+value);
		
		return value;
	}
	
	public static String getXlData(String xlPath,String sheet,int row,int col)
	{
		String value="";
		try {
			Workbook wb = WorkbookFactory.create(new FileInputStream(xlPath));
			value=wb.getSheet(sheet).getRow(row).getCell(col).getStringCellValue();
			wb.close();
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("XL data:"+value);
		return value;
	}
}

package script;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Demo1 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		Workbook wb = WorkbookFactory.create(new FileInputStream("./data/input.xlsx"));
		Sheet s = wb.getSheet("Sheet1");
		String v=s.getRow(0).getCell(0).getStringCellValue();
		System.out.println(v);
		wb.close();

	}

}

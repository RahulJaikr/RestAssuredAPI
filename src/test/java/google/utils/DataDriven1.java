package google.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class DataDriven1 {

	
	public ArrayList getDataFromExcel(String testcasename) throws IOException {
		ArrayList al = new ArrayList();
		String path = "C:\\Users\\52404445\\JsonPath\\BDD1.xlsx";
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook xss = new XSSFWorkbook(fis);
		int sheets = xss.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (xss.getSheetName(i).equalsIgnoreCase("testdata")) {
				XSSFSheet sheet = xss.getSheetAt(i);

				// identify testcase coloum by scanning the entire first row
				Iterator<Row> rows = sheet.iterator();
				Row firstRow = rows.next();
				Iterator<Cell> ce = firstRow.cellIterator();
				int column = 0, k = 0;
				while (ce.hasNext()) {
					Cell value = ce.next();
					if (value.getStringCellValue().equalsIgnoreCase("testCase")) {
						column = k;
					}
					k++;
				}

				System.out.println("take a wov" + column);

				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(column).getStringCellValue().equalsIgnoreCase("purcahse")) {
						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {
							al.add(cv.next().getStringCellValue());
						}
					}
				}
			}

		}

		return al;
	}

	@Test
	public void run1() throws IOException

	{
		ArrayList a = getDataFromExcel("testcase");
		System.out.println(a);
	}
}
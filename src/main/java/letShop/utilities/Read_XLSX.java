package letShop.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Read_XLSX {

	public static Object[][] getTestData() throws IOException {

		FileInputStream fis = new FileInputStream(
				"D:\\Playground\\Workspace\\LetShop\\src\\test\\resources\\testdata\\TestData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		DataFormatter df = new DataFormatter();
		// Object[][] data = null;

		// Get Number of sheets
		int noOfSheets = workbook.getNumberOfSheets();
		int sheetIndex = workbook.getSheetIndex("TestCase 2");
		XSSFSheet sheet = workbook.getSheetAt(sheetIndex);

		// Get the number of rows
		int noOfRows = sheet.getLastRowNum() + 1;
		System.out.println("Number of Rows " + noOfRows);

		// Get the number of columns
		int noOfColumns = sheet.getRow(0).getLastCellNum();
		System.out.println("Number of columns " + noOfColumns);

		// Create an array for storing the TestData
		Object[][] data = new Object[noOfRows-1][noOfColumns - 1];

		// Find the Data To Run Column
		int dataToRunCol = findDataToRunColumn(sheet);
		System.out.println("Data To Run Column " + dataToRunCol);

		// Add data to the array
		int l = 0;
		int m = 0;
		for (int j = 1; j < noOfRows; j++) {
			XSSFRow row = sheet.getRow(j);
			if (row.getCell(dataToRunCol).getStringCellValue().equalsIgnoreCase("Y")) {
				m = 0;
				for (int k = 1; k < noOfColumns; k++) {
					if (k != dataToRunCol) {
						String cell = df.formatCellValue(row.getCell(k));
						// System.out.println(cell);
						if (cell.equals(null)) {
							data[l][m] = "";
						} else {
							data[l][m] = cell;
							m++;
						}
					}
				}
				l++;
			}
		}
		System.out.println("data length " + data.length);
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < m; j++) {
				System.out.println(data[i][j]);
			}
		}
		return data;
	}// End of getTesData method

	public static int findDataToRunColumn(XSSFSheet sheet) throws IOException {

		int k = 0, column = 0;
		for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
			String cellValue = sheet.getRow(0).getCell(i).getStringCellValue();
			if (cellValue.equalsIgnoreCase("Data To Run")) {
				column = k;
			}
			k++;
		}
		return column;
	}// End of findDataToRunColumn method

}

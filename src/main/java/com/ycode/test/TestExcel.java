package com.ycode.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.ycode.model.TestData;
import com.ycode.mysql.jdbc.MySqlTransferData;

public class TestExcel
{
	public static void main(String[] args)
			throws FileNotFoundException, IOException, EncryptedDocumentException, InvalidFormatException
	{
		String path = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "test.xlsx";
		File file = new File(path);
		Workbook hssfWorkbook = WorkbookFactory.create(file);
		Sheet sheetAt = hssfWorkbook.getSheetAt(0);
		String cellStr = null;// 单元格，最终按字符串处理
		List<TestData> list = new ArrayList<>();

		for (int i = 0; i < sheetAt.getLastRowNum(); i++)
		{
			TestData testData = new TestData();
			Row row = sheetAt.getRow(i);
			if (null == row)
			{
				continue;
			}
			for (int j = 0; j < row.getLastCellNum(); j++)
			{
				Cell cell = row.getCell(j);
				if (cell == null)
				{// 单元格为空设置cellStr为空串
					cellStr = "";
				}
				else if (cell.getCellTypeEnum() == CellType.BOOLEAN)
				{// 对布尔值的处理
					cellStr = String.valueOf(cell.getBooleanCellValue());
				}
				else if (cell.getCellTypeEnum() == CellType.NUMERIC)
				{// 对数字值的处理
					cellStr = String.valueOf(cell.getNumericCellValue());
				}
				else
				{// 其余按照字符串处理
					cellStr = cell.getStringCellValue();
				}
				//下面按照数据出现位置封装到bean中
				if (j == 0)
				{
					testData.setName(cellStr);
				}
				else if (j == 1)
				{
					testData.setAge(cellStr);
				}
				else if (j == 2)
				{
					testData.setSex(cellStr);
				}
			}
			list.add(testData);
		}
		System.out.println("the sheet is " + list.size());
		System.out.println(list.toString());
		MySqlTransferData.batch(list);
	}
}

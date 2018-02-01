package com.core.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.st.model.UserModel;

public class ExcelUtils {

	// public static void main(String[] args) throws Exception {
	// // new ExcelUtils().showExcel("D:\\qq.xls");
	// List<TestModel> list = new ExcelUtils().showExcel3("D:\\qq.xls", "Sheet1");
	// for (TestModel o : list) {
	// System.out.print(o.getName() + "\t");
	// System.out.print(o.getSco() + "\t\n");
	// }
	//
	// String[] columnNames = { "姓名", "分数" };
	// String[] methodNames = { "getName", "getSco" };
	// Workbook excel = ExcelExporter.export2Excel("题头", "脚注", "sheet1",
	// columnNames, methodNames, list);
	// ExcelExporter.saveWorkBook2007(excel, "D:\\qqqqqqq.xls");
	// }

	// private Cell cell;

	// 读取，全部sheet表及数据
	public void showExcel(String filepath) throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(filepath)));
		XSSFSheet sheet = null;
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {// 获取每个Sheet表
			sheet = workbook.getSheetAt(i);
			for (int j = 0; j < sheet.getLastRowNum() + 1; j++) {// getLastRowNum，获取最后一行的行标
				XSSFRow row = sheet.getRow(j);
				if (row != null) {
					for (int k = 0; k < row.getLastCellNum(); k++) {// getLastCellNum，是获取最后一个不为空的列是第几个
						if (row.getCell(k) != null) { // getCell 获取单元格数据
							System.out.print(row.getCell(k) + "\t");
						} else {
							System.out.print("\t");
						}
					}
				}
				System.out.println(""); // 读完一行后换行
			}
			System.out.println("读取sheet表：" + workbook.getSheetName(i) + " 完成");
		}
	}

	// public List<TestModel> showExcel3(String filepath, String sheetname) throws
	// Exception {
	// List<TestModel> list = new ArrayList<>();
	// XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new
	// File(filepath)));
	// XSSFSheet sheet = null;
	// int i = workbook.getSheetIndex(sheetname); // sheet表名
	// sheet = workbook.getSheetAt(i);
	// for (int j = 1; j < sheet.getLastRowNum() + 1; j++) {// getLastRowNum
	// TestModel test = new TestModel();
	// XSSFRow row = sheet.getRow(j);// 获取最后一行的行标
	// String[] temp = new String[row.getLastCellNum()];
	// if (row != null) {
	// for (int k = 0; k < row.getLastCellNum(); k++) {// getLastCellNum
	// if (row.getCell(k) != null) { // getCell 获取单元格数据
	// // System.out.print(row.getCell(k) + "\t");
	// temp[k] = row.getCell(k).toString();
	// } else {
	// // System.out.print("\t");
	// }
	// }
	// }
	// test.setName(temp[0]);
	// test.setSco(Double.valueOf((temp[1])));
	// list.add(test);
	// // System.out.println("");
	// }
	// return list;
	// }

	public List<UserModel> showExcel4(MultipartFile excelfile, String sheetname) throws Exception {
		List<UserModel> list = new ArrayList<>();
		XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
		XSSFSheet sheet = null;
		int i = workbook.getSheetIndex(sheetname); // sheet表名
		sheet = workbook.getSheetAt(i);
		for (int j = 1; j < sheet.getLastRowNum() + 1; j++) {// getLastRowNum
			UserModel test = new UserModel();
			XSSFRow row = sheet.getRow(j);// 获取最后一行的行标
			String[] temp = new String[row.getLastCellNum()];
			DecimalFormat df = new DecimalFormat("#");
			if (row != null) {
				for (int k = 0; k < row.getLastCellNum(); k++) {// getLastCellNum
					if (row.getCell(k) != null) { // getCell 获取单元格数据
						// System.out.print(row.getCell(k) + "\t");
						// 判断是否包含小数点，如果不含小数点，则以字符串读取，如果含小数点，则转换为Double类型的字符串
						if (k != 3) {
							temp[k] = row.getCell(k).toString();
						} else {
							temp[3] = df.format(row.getCell(k).getNumericCellValue());
						}
					} else {
						// System.out.print("\t");
						temp[k] = "null";
					}
				}
			}
			test.setUserName(temp[0].split("\\.")[0]);
			test.setPassword(temp[1].split("\\.")[0]);
			test.setTrueName(temp[2].split("\\.")[0]);
			test.setPhone(temp[3].split("\\.")[0]);
			test.setAddress(temp[4].split("\\.")[0]);
			list.add(test);
		}
		return list;
	}

	// 读取，指定sheet表及数据
	public void showExcel2(String filepath, String sheetname) throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(filepath)));
		XSSFSheet sheet = null;
		// int i = workbook.getSheetIndex(sheetname); // sheet表名
		// sheet = workbook.getSheetAt(i);
		sheet = workbook.getSheet(sheetname);
		for (int j = 0; j < sheet.getLastRowNum() + 1; j++) {// getLastRowNum
																// 获取最后一行的行标
			XSSFRow row = sheet.getRow(j);
			if (row != null) {
				for (int k = 0; k < row.getLastCellNum(); k++) {// getLastCellNum
					if (row.getCell(k) != null) { // getCell 获取单元格数据
						System.out.print(row.getCell(k) + "\t");
					} else {
						System.out.print("\t");
					}
				}
			}
			System.out.println("");
		}
	}

	// 写入，往指定sheet表的单元格
	public void insertExcel3() throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File("E:/temp/t1.xls"))); // 读取的文件
		XSSFSheet sheet = null;
		int i = workbook.getSheetIndex("xt"); // sheet表名
		sheet = workbook.getSheetAt(i);

		XSSFRow row = sheet.getRow(0); // 获取指定的行对象，无数据则为空，需要创建
		if (row == null) {
			row = sheet.createRow(0); // 该行无数据，创建行对象
		}

		Cell cell = row.createCell(1); // 创建指定单元格对象。如本身有数据会替换掉
		cell.setCellValue("tt"); // 设置内容

		FileOutputStream fo = new FileOutputStream("E:/temp/t1.xls"); // 输出到文件
		workbook.write(fo);

	}

}

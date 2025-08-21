package com.bookingzoneadmin.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UtilityClass {

	// File paths for property file and Excel data file
	private static final String PROPERTY_FILE_PATH = ".\\src\\main\\java\\com\\bookingzoneadmin\\utils\\Admin.properties";
	private static final String EXCEL_FILE_PATH = ".\\src\\test\\resources\\Admin_Bussiness_Create_Data.xlsx";
	
	/**
	 * Method to read data from the property file.
	 *
	 * @param key The key to retrieve the value from the property file.
	 * @return The value corresponding to the key from the property file.
	 * @throws IOException If an error occurs while reading the property file.
	 */
	public static String getDataFromPF(String key) throws IOException {
		try (FileInputStream file = new FileInputStream(PROPERTY_FILE_PATH)) {
			Properties prop = new Properties();
			prop.load(file);
			return prop.getProperty(key);
		} catch (IOException e) {
			throw new IOException("Error reading property file: " + e.getMessage(), e);
		}
	}

	/**
	 * Method to read data from the Excel sheet.
	 *
	 * @param rowIndex  The row index in the Excel sheet.
	 * @param cellIndex The cell index in the Excel sheet.
	 * @param sheetName The name of the Excel sheet.
	 * @return The data from the specified cell in the Excel sheet.
	 * @throws IOException If an error occurs while reading the Excel file.
	 */
	public static String getDataFromEs(int rowIndex ,String columnName, String sheetName) throws IOException {
		
		try (FileInputStream file = new FileInputStream(EXCEL_FILE_PATH);
	             XSSFWorkbook workbook = new XSSFWorkbook(file)) {
	           // XSSFSheet sheet = workbook.getSheet(sheetName);
	            XSSFSheet sheet = workbook.getSheet(sheetName.trim());
	            
	            if (sheet == null) {
	                throw new IllegalArgumentException("Sheet '" + sheetName + "' not found in the workbook.");
	            }

	            XSSFRow headerRow = sheet.getRow(0); // Assuming the first row is the header row
	            if (headerRow == null) {
	                throw new IllegalArgumentException("Header row not found in the sheet.");
	            }

	            int columnIndex = -1;
	            for (Cell cell : headerRow) {
	            	System.out.println("Header cell value: '" + cell.getStringCellValue() + "'"); // Debug print
	                if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().equalsIgnoreCase(columnName)) {
	                    columnIndex = cell.getColumnIndex();
	                    break;
	                }
	            }

	            if (columnIndex == -1) {
	                throw new IllegalArgumentException("Column '" + columnName + "' not found in the header row.");
	            }

	            XSSFRow row = sheet.getRow(rowIndex);
	            if (row == null) {
	                throw new IllegalArgumentException("Row '" + rowIndex + "' not found in the sheet.");
	            }

	            XSSFCell cell = row.getCell(columnIndex);
	            if (cell == null) {
	                return null;
	            }

	            if (cell.getCellType() == CellType.STRING) {
	                return cell.getStringCellValue();
	            } else if (cell.getCellType() == CellType.NUMERIC) {
	                double value = cell.getNumericCellValue();
	                DecimalFormat df = new DecimalFormat("0");
	                return df.format(value);
	            }
	            // Handle other cell types if needed
	            return null;
	        } catch (IOException | EncryptedDocumentException e) {
	            throw new IOException("Error reading Excel file: " + e.getMessage(), e);
	        }
	}


	/**
	 * Method to upload an image using Robot class.
	 *
	 * @param path The path of the image file to upload.
	 * @throws AWTException If an AWT error occurs.
	 */
	public static void uploadImage(String path) throws AWTException {
		if (path == null || path.isEmpty()) {
			throw new IllegalArgumentException("Image path is invalid or empty.");
		}

		Robot robot = new Robot();
		robot.delay(2000);

		// Copy image path to clipboard
		StringSelection ss = new StringSelection(path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

		// Check if clipboard content is set successfully
		if (!Toolkit.getDefaultToolkit().getSystemClipboard().isDataFlavorAvailable(DataFlavor.stringFlavor)) {
			throw new AWTException("Clipboard content not set successfully.");
		}

		// Paste and submit image path
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	
	
	 // Method to generate timestamp in 12hr format with AM/PM
    public static String getFormattedTimestamp() {
        return new java.text.SimpleDateFormat("dd_MMM_yyyy_hh_mm_ss_a").format(new java.util.Date());
    }
   
}

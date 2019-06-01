package system;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * The Class InvoiceCreator.
 */
public abstract class GenerateReport {

	/**
	 * Creates the invoice.
	 *
	 * @param placeholder
	 *            the placeholder
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void createReport() throws IOException {
		// Code and comments modified from
		// https://www.tutorialspoint.com/apache_poi

		//update references to room and booking objects
		FileSystem.readRoomObjects();
		FileSystem.readBookingObjects();
		
		// Create blank workbook
		XSSFWorkbook wb = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet ss = wb.createSheet("Report");

		// Create row object
		XSSFRow row;
		
		// Create first row object
		XSSFRow row1 = ss.createRow(0);

		String[] titles = { "Room Number", "Booked Date", "Booked Time" };
		
		// create first merged cell
		for(int i = 0; i < titles.length; i++) {
			row1.createCell(i);
			XSSFCell cell = row1.createCell(i);
			XSSFCellStyle cellStyle = wb.createCellStyle();
			cell.setCellValue(titles[i]);
			cellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
			Font cellFont = wb.createFont();
			cellFont.setBold(true);
			cellStyle.setFont(cellFont);
			cell.setCellStyle(cellStyle);
		}
		
		// This data needs to be written (Object[])
		Map<Integer, Object[]> empInfo = new TreeMap<Integer, Object[]>();
		
		int count = 2;
		for(Booking booking : FileSystem.bookings) {
			empInfo.put(count, new Object[] { booking.getRoomBooked().getRoomNumber(), booking.getStartDate() + " to " + booking.getEndDate(), booking.getStartTime() + " to " + booking.getEndTime()});
			count++;
		}

		// Iterate over data and write to sheet
		Set<Integer> keyid = empInfo.keySet();
		int rowid = 1;

		for (int key : keyid) {
			row = ss.createRow(rowid++);
			Object[] objectArr = empInfo.get(key);
			int cellid = 0;

			for (Object obj : objectArr) {
				Cell cell = row.createCell(cellid++);
				
				XSSFCellStyle cellStyle = wb.createCellStyle();
				cellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
				cell.setCellStyle(cellStyle);
				if(obj instanceof Integer) {
					cell.setCellValue((int) obj);
				} else {
					cell.setCellValue((String) obj);
				}
			}  
		}
		
		//Automatically size the first three columns
		for(int i = 0; i < titles.length; i++) {
			ss.autoSizeColumn(i);
		}

		// Write the workbook in file system
		FileOutputStream out = new FileOutputStream(new File("Bookings.xlsx"));
		wb.write(out);
		out.close();
		System.out.println("Bookings.xlsx written successfully");
		
		// close the workbook
		wb.close();

		Desktop dt = Desktop.getDesktop();
		dt.open(new File("Bookings.xlsx"));
	}
}

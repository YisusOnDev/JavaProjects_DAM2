package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import io.ReadWrite;

public class Logger {

	private static ArrayList<String> logLines = new ArrayList<String>();
	public static void printConsole(String text) {
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
		 LocalDateTime now = LocalDateTime.now();  
		 String msg = "LOG | " + dtf.format(now) + " | " + text;
		 System.out.println(msg);
		 logLines.add(msg);
	}
	
	public static void printCurrentLog() {
		System.out.println("\nFULL LOG:\n");
		for (String text : logLines) {
			System.out.println(text);
		}
	}
	
	public void saveLogToFile(String outPath) {
		if (ReadWrite.writeLogFile("log_csv.txt", logLines) == true) {
            Logger.printConsole("Log file saved in: " + outPath);	
		} else {
			Logger.printConsole("Could not save log file in " + outPath);
		}
	}
}

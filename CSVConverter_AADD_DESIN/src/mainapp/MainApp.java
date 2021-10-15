package mainapp;

import io.ReadWrite;
import views.MainView;

public class MainApp {
	public static void main(String[] args) {
		// Test without UI
		ReadWrite.convertCSVToBinary("origCsv.csv", "origCsv.dat");
		ReadWrite.convertBinaryToCsv("origCsv.dat", "origCsv_converted.csv");
		ReadWrite.sortCsv("origCsv.csv", "origCsv_ord.csv");
		ReadWrite.sortBinary("origCsv.dat", "origCsv_ord.dat");
		ReadWrite.binaryFileToSortedCsv("origCsv.dat", "origCsv_convertedandsorted.csv");
		// Logger.saveLogToFile("log.txt")

		// Initialize UI
		new MainView();
	}

}

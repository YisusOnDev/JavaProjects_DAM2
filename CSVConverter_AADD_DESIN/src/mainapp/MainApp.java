package mainapp;

import io.ReadWrite;
import utils.Logger;

public class MainApp {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadWrite.convertCSVToBinary("origCsv.csv", "origCsv.dat");
		ReadWrite.convertBinaryToCsv("origCsv.dat", "origCsv_converted.csv");
		ReadWrite.sortCsv("origCsv.csv", "origCsv_ord.csv");
		ReadWrite.sortBinary("origCsv.dat", "origCsv_ord.dat");
		ReadWrite.binaryFileToSortedCsv("origCsv.dat", "origCsv_convertedandsorted.csv");
	}

}

package io;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import utils.Logger;

public class ReadWrite {

	public static ArrayList<String> readCsv(String filePath) {
		ArrayList<String> lines = new ArrayList<String>();
		try {
			File myObj = new File(filePath);
			Scanner sc = new Scanner(myObj, "UTF-8");

			while (sc.hasNextLine()) {
				String stringChain = sc.nextLine();
				lines.add(stringChain);
				Logger.printConsole("Readed csv line: " + stringChain);
			}
			sc.close();
			Logger.printConsole("Finished read csv from: " + filePath);
			return lines;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<String> readBinary(String filePath) {
		ArrayList<String> csvLines = new ArrayList<String>();
		try {
			FileInputStream fileStream = new FileInputStream(filePath);
			ObjectInputStream objStream = new ObjectInputStream(fileStream);

			try {
				while (objStream.available() != -1) {
					String stringChain = (String) objStream.readObject();
					csvLines.add(stringChain);
					Logger.printConsole("Readed binary line: " + stringChain);
				}
				objStream.close();
			} catch (EOFException e) {
				objStream.close();
				Logger.printConsole("Finished read binary from: " + filePath);
				return csvLines;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static boolean writeBinary(String outPath, ArrayList<String> lines) {
		try {
			ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(outPath));
			for (String line : lines) {
				fileWriter.writeObject(line);
				Logger.printConsole("Writing line into " + outPath + " -> " + line);
			}
			fileWriter.close();
			Logger.printConsole("Finished writing binary file: " + outPath);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean writeCsv(String outPath, ArrayList<String> lines) {
		try {
			FileWriter fileWriter = new FileWriter(outPath);

			for (String line : lines) {
				Logger.printConsole("Writing line into " + outPath + " -> " + line);
				fileWriter.write(line);
				fileWriter.write(System.getProperty("line.separator"));
			}
			fileWriter.close();
			Logger.printConsole("Finished writing csv file: " + outPath);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean convertCSVToBinary(String filePath, String outPath) {
		ArrayList<String> lines = readCsv(filePath);
		if (lines != null && writeBinary(outPath, lines) == true) {
			Logger.printConsole("CSV from " + filePath + " succesfully converted to: " + outPath);
			return true;
		} else {
			return false;
		}
	}

	public static boolean convertBinaryToCsv(String filePath, String outPath) {
		ArrayList<String> csvLines = readBinary(filePath);

		if (csvLines != null && writeCsv(outPath, csvLines) == true) {
			Logger.printConsole("Binary from " + filePath + " succesfully converted to: " + outPath);
			return true;
		} else {
			return false;
		}
	}

	public static boolean writeLogFile(String outPath, ArrayList<String> log) {
		try {
			FileWriter fileWriter = new FileWriter(outPath);

			for (String line : log) {
				fileWriter.write(line);
				fileWriter.write(System.getProperty("line.separator"));
			}
			Logger.printConsole("Succesfully written log file into: " + outPath);
			fileWriter.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		Logger.printConsole("Error while trying to write log file into: " + outPath);
		return false;
	}

	public static ArrayList<String> sortLines(ArrayList<String> lines) {
		ArrayList<String> toSortedLines = lines;
		ArrayList<String> sortedLines = new ArrayList<String>();
		String firstLine = toSortedLines.get(0);
		toSortedLines.remove(0);
		Collections.sort(toSortedLines);
		sortedLines.add(firstLine);
		for (String line : toSortedLines) {
			sortedLines.add(line);
		}
		return sortedLines;
	}

	public static boolean sortCsv(String filePath, String outPath) {
		ArrayList<String> csvLines = readCsv(filePath);
		if (csvLines != null) {
			csvLines = sortLines(csvLines);
			if (writeCsv(outPath, csvLines) == true) {
				Logger.printConsole("CSV from " + filePath + " succesfully sorted to: " + outPath);
				return true;
			} else {
				Logger.printConsole("Error trying to sort csv file from " + filePath);
				return false;
			}
		}
		return false;
	}

	public static boolean sortBinary(String filePath, String outPath) {
		ArrayList<String> binaryLines = readBinary(filePath);
		if (binaryLines != null) {
			binaryLines = sortLines(binaryLines);
			if (writeBinary(outPath, binaryLines) == true) {
				Logger.printConsole("Binary from " + filePath + " succesfully sorted to: " + outPath);
				return true;
			} else {
				Logger.printConsole("Error trying to sort binary file from " + filePath);
				return false;
			}
		}
		return false;
	}

	public static boolean binaryFileToSortedCsv(String filePath, String outPath) {
		ArrayList<String> binaryLines = readBinary(filePath);
		if (binaryLines != null) {
			binaryLines = sortLines(binaryLines);
			if (binaryLines != null) {
				if (writeCsv(outPath, binaryLines) == true) {
					Logger.printConsole("Binary from " + filePath + " succesfully sorted and converted to: " + outPath);
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}

		return false;
	}

}

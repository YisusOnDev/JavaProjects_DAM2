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

	/**
	 * Method that load/read a csv file and return all of his lines on an ArrayList
	 * 
	 * @param filePath path of the file we want to read.
	 * @return
	 */
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

	/**
	 * Method that load/read a dat/binary file and return all of his lines on an
	 * ArrayList
	 * 
	 * @param filePath path of the file we want to read.
	 * @return
	 */
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

	/**
	 * Method that writes a dat/binary file
	 * 
	 * @param outPath Where we want to save the converted file
	 * @param lines   ArrayList of file String lines
	 * @return true if done false if fails
	 */
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

	/**
	 * Method that writes a csv file
	 * 
	 * @param outPath Where we want to save the converted file
	 * @param lines   ArrayList of file String lines
	 * @return true if done false if fails
	 */
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

	/**
	 * Method that converta csv file to dat/binary file and save it on the preferred
	 * path.
	 * 
	 * @param filePath where's main file located
	 * @param outPath  where we want to save the converted one
	 * @return true if done false if fails
	 */
	public static boolean convertCSVToBinary(String filePath, String outPath) {
		ArrayList<String> lines = readCsv(filePath);
		if (lines != null && writeBinary(outPath, lines) == true) {
			Logger.printConsole("CSV from " + filePath + " succesfully converted to: " + outPath);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method that converta binary/dat file to csv file and save it on the preferred
	 * path.
	 * 
	 * @param filePath where's main file located
	 * @param outPath  where we want to save the converted one
	 * @return true if done false if fails
	 */
	public static boolean convertBinaryToCsv(String filePath, String outPath) {
		ArrayList<String> csvLines = readBinary(filePath);

		if (csvLines != null && writeCsv(outPath, csvLines) == true) {
			Logger.printConsole("Binary from " + filePath + " succesfully converted to: " + outPath);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method that save the log built on program run
	 * 
	 * @param outPath where the file is going to be saved
	 * @param log     ArrayList<String> of the Logger
	 * @return true if done false if fails
	 */
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

	/**
	 * Method that sort an ArrayList of Strings
	 * 
	 * @param lines the ArrayList<String>
	 * @return an ArrayList<String> but sorted
	 */
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

	/**
	 * Method that sort a csv file and save it on the preferred path
	 * 
	 * @param filePath original csv path
	 * @param outPath  where we want to save the sorted one
	 * @return true if done false if fails
	 */
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

	/**
	 * Method that sort a binary/dat file and save it on the preferred path
	 * 
	 * @param filePath original binary/dat path
	 * @param outPath  where we want to save the sorted one
	 * @return true if done false if fails
	 */
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

	/**
	 * Special method to convert a binary/dat file to a sorted csv one
	 * 
	 * @param filePath original binary/dat file path
	 * @param outPath  target path to the converted and sorted csv
	 * @return true if done false if fails
	 */
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

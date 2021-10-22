package mainapp;

import java.util.ArrayList;

import io.ReadWrite;

public class MainApp {

	public static void main(String[] args) {
		ArrayList<Object> fileData = ReadWrite.fileToArray("testFile.txt");
		
	}
}

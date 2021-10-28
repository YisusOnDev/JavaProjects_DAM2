package mainapp;

import java.util.ArrayList;

import dao.DBManager;
import io.ReadWrite;

public class MainApp {

	public static void main(String[] args) {
		ArrayList<Object> fileData = ReadWrite.fileToArray("testFile.txt");
		new DBManager().insertTeacherWithData(fileData);
		
	}
}

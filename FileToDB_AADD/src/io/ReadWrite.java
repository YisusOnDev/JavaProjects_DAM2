package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import models.Subject;
import models.Teacher;

public class ReadWrite {

	public static ArrayList<Object> fileToArray(String filePath) {
		ArrayList<Object> tempList = new ArrayList<Object>();
		try {
			File myObj = new File(filePath);
			Scanner sc = new Scanner(myObj, "UTF-8");
			while (sc.hasNextLine()) {
				var stringChain = sc.nextLine();
				var stringChains = stringChain.split(";");

				for (String s : stringChains) {
					while (s.endsWith(",")) {
						s = s.substring(0, s.length() - 1);
					}

					s = s.trim();
				}
				
				if (stringChains[0].equals("Profesor")) {
					tempList.add(new Teacher(stringChains[1], stringChains[2], stringChains[3], stringChains[4]));
				} else if (stringChains[0].equals("Asignatura")) {
					tempList.add(new Subject(stringChains[1], stringChains[2]));
				}	
			}

			sc.close();
			return tempList;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}

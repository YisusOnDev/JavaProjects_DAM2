package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import model.User;

public class ReadWrite {
	/**
	 * Método que a partir de una ruta a un archivo genera un ArrayList de "User"
	 * con los datos obtenidos del fichero.
	 * 
	 * @param filePath Nombre del fichero
	 * @return ArrayList<User>
	 */
	public static ArrayList<User> fileToArray(String filePath) {
		ArrayList<User> tempList = new ArrayList<User>();
		try {
			File myObj = new File(filePath);
			Scanner sc = new Scanner(myObj, "UTF-8");
			while (sc.hasNextLine()) {
				var stringChain = sc.nextLine();

				// Separamos en cadenas separando por ", "
				var stringChains = stringChain.split(", ");

				for (String s : stringChains) {
					while (s.endsWith(", ")) {
						s = s.substring(0, s.length() - 1);
					}

					s = s.trim();
				}

				// Añadimos un User al ArrayList leyendo la parte de la cadena que necesitemos
				User u = new User();
				for (String string : stringChains) {
					System.out.println(string);
				}
				u.setName(stringChains[0]);
				u.setLastname(stringChains[1]);
				u.setUsername(u.GetUsernameFromFullName(u.getName(), u.getLastname()));
				tempList.add(u);
			}

			sc.close();
			return tempList;
		} catch (FileNotFoundException e) {
			System.out.println("El fichero proporcionado no existe.");
			e.printStackTrace();
		}
		return null;
	}

}

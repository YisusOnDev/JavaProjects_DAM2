package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import dao.DBManager;
import model.Categorias;
import model.Productos;
import model.Proveedores;
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
				tempList.add(new User(stringChains[1], stringChains[0]));
			}

			sc.close();
			return tempList;
		} catch (FileNotFoundException e) {
			System.out.println("El fichero proporcionado no existe.");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Método que a partir de una id de categoria obtiene los datos de los
	 * productos, la categoría y los proveedores y los inserta en un fichero
	 * 
	 * @param categoryId id de la categoria
	 * @param fileName   nombre del archivo al escribir
	 */
	public static void almacenarProductosEnFichero(int categoryId, String fileName) {
		ArrayList<Productos> products = new ArrayList<Productos>();
		ArrayList<Categorias> categories = new ArrayList<Categorias>();
		ArrayList<Proveedores> suppliers = new ArrayList<Proveedores>();

		// Obtenemos los datos desde la DB
		ArrayList<Object> dbData = DBManager.getProductsFromCategory(categoryId);

		// Grabamos los datos obtenidos en sus respecitivos ArrayList
		for (Object object : dbData) {
			if (object instanceof Productos) {
				products.add((Productos) object);
			} else if (object instanceof Categorias) {
				categories.add((Categorias) object);
			} else if (object instanceof Proveedores) {
				suppliers.add((Proveedores) object);
			}
		}

		// Escribimos el fichero
		try {
			OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(fileName),
					StandardCharsets.UTF_8);

			fileWriter.write(categories.get(0).getHeader());
			for (Categorias c : categories) {
				fileWriter.write(c.toString());
			}

			fileWriter.write("\n");

			fileWriter.write(suppliers.get(0).getHeader());
			for (Proveedores c : suppliers) {
				fileWriter.write(c.toString());
			}

			fileWriter.write("\n");

			fileWriter.write(products.get(0).getHeader());
			for (Productos c : products) {
				fileWriter.write(c.toString());
			}

			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que a partir de un fichero genera un ArrayList con objetos de
	 * categorias, proovedores y productos que después llama otro método para
	 * insertarlos de manera "safe" en la base de datos.
	 * 
	 * @param fileName path donde esta el archivo
	 */
	public static ArrayList<Object> fichero_a_tablas(String fileName) {
		ArrayList<Object> tempList = new ArrayList<Object>();
		BufferedReader bufferedReader;

		// Leemos el archivo
		try {
			bufferedReader = new BufferedReader(new FileReader(fileName, StandardCharsets.UTF_8));
			String line = bufferedReader.readLine();
			ArrayList<String> lines = new ArrayList<String>();
			while (line != null) {
				// Generamos un ArrayList<String> con cada línea suprimiendo las líneas en
				// blanco
				if (!line.isBlank()) {
					lines.add(line);
				}
				line = bufferedReader.readLine();
			}
			bufferedReader.close();

			// Leemos todas las líneas y según su tipo generamos un objeto y lo insertamos
			// en el ArrayList<Object>
			String currentType = null;
			for (int i = 0; i < lines.size(); i++) {
				if (lines.get(i).startsWith("CATEGORIAS")) {
					currentType = "cat";
					i++;
				}
				if (lines.get(i).startsWith("PROVEEDORES")) {
					currentType = "prov";
					i++;
				}
				if (lines.get(i).startsWith("PRODUCTOS")) {
					currentType = "prod";
					i++;
				}
				if (!lines.get(i).startsWith("id;")) {
					var stringChains = lines.get(i).split(";");

					for (String s : stringChains) {
						while (s.endsWith(";")) {
							s = s.substring(0, s.length() - 1);
						}

						s = s.trim();
					}

					switch (currentType) {
					case "cat": {
						Categorias c = new Categorias(Integer.parseInt(stringChains[0]), stringChains[1],
								stringChains[2]);
						tempList.add(c);
						break;
					}
					case "prov": {
						Proveedores p = new Proveedores(Integer.parseInt(stringChains[0]), stringChains[1],
								stringChains[2]);
						tempList.add(p);
						break;
					}
					case "prod": {
						Productos pd = new Productos(Integer.parseInt(stringChains[0]), stringChains[1],
								Integer.parseInt(stringChains[2]), Integer.parseInt(stringChains[3]), stringChains[4],
								Double.parseDouble(stringChains[5]), Integer.parseInt(stringChains[6]),
								Integer.parseInt(stringChains[7]), Integer.parseInt(stringChains[8]),
								Integer.parseInt(stringChains[9]));

						tempList.add(pd);
						break;
					}
					default:
						throw new IllegalArgumentException("Unexpected value: " + currentType);
					}
				}
			}
			return tempList;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}

package mainapp;

import dao.DBManager;
import io.ReadWrite;

public class MainApp {
	private static final String FILE_PATH = "Alumnado_nuevo.txt";	

	public static void main(String[] args) {
		// Main 1 (Ejer1)
		DBManager.crearTablaSuperusuarios(FILE_PATH);
		// Main 2 (Ejer1)
		ReadWrite.almacenarProductosEnFichero(1, "kekw.txt");
		// Main 2 (Ejer2)
		DBManager.insertFromArray(ReadWrite.fichero_a_tablas("kekw.txt"));
	}
}

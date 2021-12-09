package dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import io.ReadWrite;
import model.User;

public class DBManager {

	/**
	 * Método que crea una tabla en la base de datos llamada "Superusuarios" e
	 * inserta datos en ella en lotes de 10 usando PreparedStatement y Batch.
	 * 
	 * @param filePath Ruta del archivo dónde estan los datos a insertar
	 */
	public static void crearTablaSuperusuarios(String filePath) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();
		ArrayList<User> users = ReadWrite.fileToArray(filePath);

		Session session = sf.openSession();
		session.getTransaction().begin();

		int insertCount = 0;

		// Batch por lotes de 10
		for (User user : users) {
			session.save(user);
			session.persist(user);
			insertCount += 1;
			if (insertCount == 10) {
				session.getTransaction().commit();
				insertCount = 0;
			}
		}
		if (session.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
			session.getTransaction().commit();
		}
		session.close();
		sf.close();
	}
}

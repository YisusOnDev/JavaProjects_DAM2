package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import io.ReadWrite;
import model.Categorias;
import model.Productos;
import model.Proveedores;
import model.User;

public class DBManager {

	private static final String CONNECTION = "jdbc:mysql://localhost/bd_neptuno";
	private static final String USER = "root";
	private static final String PWD = "";

	/**
	 * Método que crea una tabla en la base de datos llamada "Superusuarios" e
	 * inserta datos en ella en lotes de 10 usando PreparedStatement y Batch.
	 * 
	 * @param filePath Ruta del archivo dónde estan los datos a insertar
	 */
	public static void crearTablaSuperusuarios(String filePath) {
		try {
			Connection conn = DriverManager.getConnection(CONNECTION, USER, PWD);
			PreparedStatement stmt = null;

			// Guardamos en un ArrayList los Usuarios a insertar
			ArrayList<User> users = ReadWrite.fileToArray(filePath);

			String createTable = "CREATE TABLE IF NOT EXISTS Superusuarios "
					+ "(IdUser INTEGER NOT NULL AUTO_INCREMENT, " + " Nombre VARCHAR(255), "
					+ " Apellidos VARCHAR(255), " + " User VARCHAR(6), " + " PRIMARY KEY ( IdUser ))";

			String insertDataToTable = "INSERT INTO Superusuarios(Nombre, Apellidos, User) VALUES (?,?,?)";

			// Creación de la tabla Superusuarios
			stmt = conn.prepareStatement(createTable);
			stmt.executeUpdate(createTable);
			stmt.close();
			stmt = null;

			// Inserción de usuarios
			stmt = conn.prepareStatement(insertDataToTable);
			conn.setAutoCommit(false);

			int insertCount = 0;

			// Batch por lotes de 10
			for (User user : users) {
				stmt.setString(1, user.getName());
				stmt.setString(2, user.getLastname());
				stmt.setString(3, user.getUsername());
				stmt.addBatch();
				insertCount += 1;
				if (insertCount == 10) {
					stmt.executeBatch();
					insertCount = 0;
				}
			}
			stmt.executeBatch();
			conn.commit();
			stmt.close();
			conn.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * ^ Método que en base a una id de categoria saca los datos de la categoria,
	 * sus productos e proveedores y los devuelve en un ArrayList de objetos.
	 * 
	 * @param categoryId id de categoria
	 * @return ArrayList<Object> con el resultado de cada cosa a devolver
	 */
	public static ArrayList<Object> getProductsFromCategory(int categoryId) {
		try {
			Connection conn = DriverManager.getConnection(CONNECTION, USER, PWD);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(
					"SELECT p.*, pv.* FROM productos p INNER JOIN proveedores pv ON p.proveedor_id = pv.id WHERE p.categoria_id = "
							+ categoryId + " GROUP BY(pv.id)");
			ArrayList<Object> tempList = new ArrayList<Object>();

			while (rs.next()) {
				Integer id = rs.getInt("p.id");
				String producto = rs.getString("p.producto");
				Integer proveedorId = rs.getInt("p.proveedor_id");
				Integer categoriaId = rs.getInt("p.categoria_id");
				String cantidadPorUnidad = rs.getString("p.cantidad_por_unidad");
				Double precioUnidad = rs.getDouble("p.precio_unidad");
				Integer unidadesExistencia = rs.getInt("p.unidades_existencia");
				Integer unidadesPedido = rs.getInt("p.unidades_pedido");
				Integer nivelNuevoPedido = rs.getInt("p.nivel_nuevo_pedido");
				Integer suspendido = rs.getInt("p.suspendido");
				Productos p = new Productos(id, producto, proveedorId, categoriaId, cantidadPorUnidad, precioUnidad,
						unidadesExistencia, unidadesPedido, nivelNuevoPedido, suspendido);

				tempList.add(p);

				Integer idpv = rs.getInt("pv.id");
				String empresa = rs.getString("pv.empresa");
				String contacto = rs.getString("pv.contacto");
				Proveedores pv = new Proveedores(idpv, empresa, contacto);
				tempList.add(pv);

			}

			rs = statement.executeQuery("SELECT * FROM categorias WHERE id = " + categoryId);

			while (rs.next()) {
				int id = rs.getInt(1);
				String categoria = rs.getString(2);
				String descripcion = rs.getString(3);

				Categorias c = new Categorias(id, categoria, descripcion);
				tempList.add(c);
			}

			rs.close();
			statement.close();
			conn.close();

			return tempList;

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	/**
	 * Método que a través de un ArrayList de Objectos extrae los productos,
	 * categorias y proveedores y los inserta de manera "safe" a la base de datos
	 * usando preparedStatements.
	 * 
	 * @param fileData ArrayList<Object> con Productos, categorías y proveedores.
	 */
	public static void insertFromArray(ArrayList<Object> fileData) {
		ArrayList<Productos> products = new ArrayList<Productos>();
		ArrayList<Categorias> categories = new ArrayList<Categorias>();
		ArrayList<Proveedores> suppliers = new ArrayList<Proveedores>(); // 1

		for (Object object : fileData) {
			if (object instanceof Productos) {
				products.add((Productos) object);
			} else if (object instanceof Categorias) {
				categories.add((Categorias) object);
			} else if (object instanceof Proveedores) {
				suppliers.add((Proveedores) object);
			}
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(CONNECTION, USER, PWD);
			conn.setAutoCommit(false);

			PreparedStatement stmt = null;
			boolean firstTime = true;

			for (Categorias c : categories) {
				if (!firstTime)
					conn.commit();

				firstTime = false;
				stmt = conn
						.prepareStatement("INSERT INTO categorias_new (id, categoria, descripcion) VALUES (?, ?, ?)");
				stmt.setInt(1, c.getId());
				stmt.setString(2, c.getCategoria());
				stmt.setString(3, c.getDescripcion());
				stmt.execute();
			}

			conn.commit();
			stmt = null;
			firstTime = true;

			for (Proveedores p : suppliers) {
				if (!firstTime)
					conn.commit();

				firstTime = false;
				stmt = conn.prepareStatement("INSERT INTO proveedores_new (id, empresa, contacto) VALUES (?, ?, ?)");
				stmt.setInt(1, p.getId());
				stmt.setString(2, p.getEmpresa());
				stmt.setString(3, p.getContacto());
				stmt.execute();
			}

			conn.commit();
			stmt = null;
			firstTime = true;

			for (Productos pd : products) {
				if (!firstTime)
					conn.commit();

				firstTime = false;
				// En mi base de datos la id no es auto incremental, en todo caso solo habría
				// que quitar el campo id de la query y del preparedStatement.
				stmt = conn.prepareStatement(
						"INSERT INTO productos_new (id, producto, proveedor_id, categoria_id, cantidad_por_unidad, precio_unidad, unidades_existencia, unidades_pedido, nivel_nuevo_pedido, suspendido) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				stmt.setInt(1, pd.getId());
				stmt.setString(2, pd.getProducto());
				stmt.setInt(3, pd.getProveedorId());
				stmt.setInt(4, pd.getCategoriaId());
				stmt.setString(5, pd.getCantidadPorUnidad());
				stmt.setDouble(6, pd.getPrecioUnidad());
				stmt.setInt(7, pd.getUnidadesExistencia());
				stmt.setInt(8, pd.getUnidadesPedido());
				stmt.setInt(9, pd.getNivelNuevoPedido());
				stmt.setInt(10, pd.getSuspendido());
				stmt.execute();
			}

			conn.commit();
		} catch (SQLException se) {
			try {
				conn.rollback();
				System.out.println("Found an error while trying to insert data. Rolled back.");
			} catch (SQLException e) {
			}
		}

	}
}

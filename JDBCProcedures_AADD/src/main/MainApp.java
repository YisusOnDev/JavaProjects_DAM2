package main;

import java.sql.*;

public class MainApp {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/bd_neptuno2";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "";

	public static void main(String[] args) {
		/**
		 * CREATED PROCEDURE:
		 * 
		 * CREATE DEFINER=`root`@`localhost` PROCEDURE
		 * `bd_neptuno2`.`Empleado_por_sexo`( in var_sexo varchar(6), out outNombre
		 * varchar(50)) BEGIN IF var_sexo = "HOMBRE" THEN select nombre into outNombre
		 * FROM empleados where tratamiento IN ('Sr.', 'Dr.') limit 1; ELSE IF var_sexo
		 * = "MUJER" THEN select nombre into outNombre FROM empleados where tratamiento
		 * IN ('Sra.', 'Dra.', 'Srta.') limit 1; END IF; END IF; END
		 * 
		 */
		Connection conn = null;

		System.out.println("Connecting to database...");
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String call = "{call Empleado_por_sexo(?,?)}";
			CallableStatement stmt = conn.prepareCall(call);
			stmt.setString(1, "HOMBRE");
			stmt.registerOutParameter(2, Types.VARCHAR);
			stmt.execute();
			String returned = stmt.getString(2);
			System.out.println(returned);

			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

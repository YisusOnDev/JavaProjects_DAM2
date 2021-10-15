package mainapp;

import java.util.ArrayList;

import dao.DatabaseDAO;

public class MainApp {
    public static void main(String[] args) throws Exception {
        DatabaseDAO DB = new DatabaseDAO();
        DB.createDatabase("empleados_aadd");

        ArrayList<String> creates = new ArrayList<String>();
        String table_region = "create table region (" + "codregion int(11) primary key," + "nombre varchar(50))";
        String table_provincia = "create table provincia (" + "codprovincia int(11) primary key,"
                + "nombre varchar(50)," + "codregion int(11),"
                + "foreign key (codregion) references region(codregion))";
        String table_localidad = "create table localidad (" + "codlocalidad int(11) primary key,"
                + "nombre varchar(50)," + "codprovincia int(11),"
                + "foreign key (codprovincia) references provincia(codprovincia))";
        String table_empleado = "create table empleado (" + "id int(11) primary key," + "dni varchar(9) unique,"
                + "nombre varchar(50)," + "fechanac date," + "telefono varchar(13)," + "salario int(11),"
                + "codlocalidad int(11)," + "foreign key (codlocalidad) references localidad(codlocalidad))";

        creates.add(table_region);
        creates.add(table_provincia);
        creates.add(table_localidad);
        creates.add(table_empleado);
        DB.createTable(creates);

    }
}

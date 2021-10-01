package mainapp;

import java.util.ArrayList;
import io.ReadWrite;
import models.Employee;

public class MainApp {
    public static void main(String[] args) throws Exception {
        ArrayList<Employee> employeeList = ReadWrite.fileToArray("data.txt");
        System.out.println("fileToArray Result:");
        System.out.println(employeeList);
        System.out.println("fileToArray END");
        ReadWrite.employeeListToFile(employeeList, "datastringwritten.txt");
        ReadWrite.employeeListToBinaryFileReversed(employeeList, "datareversedwritten.bnr");
        ReadWrite.printEmployeeFile("datastringwritten.txt");
        ReadWrite.printBinaryEmployeeFile("datareversedwritten.bnr");
    }
}

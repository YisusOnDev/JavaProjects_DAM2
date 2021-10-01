package io;

import java.util.ArrayList;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import models.Employee;

/**
 * ReadWrite Class
 */
public class ReadWrite {

    public static ArrayList<Employee> fileToArray(String filePath) {
        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        try {
            File myObj = new File(filePath);
            Scanner sc = new Scanner(myObj, "UTF-8");

            // Delete first line
            if (sc.hasNextLine())
                sc.nextLine();

            while (sc.hasNextLine()) {
                var stringChain = sc.nextLine();
                var stringChains = stringChain.split(",");

                for (String s : stringChains) {
                    while (s.endsWith(",")) {
                        s = s.substring(0, s.length() - 1);
                    }

                    s = s.trim();
                }

                String curCompany = stringChains[0].replaceAll("\"", "");
                int curAge = Integer.parseInt(stringChains[1].replaceAll("\"", ""));
                int curQty = Integer.parseInt(stringChains[2].replaceAll("\"", ""));
                employeeList.add(new Employee(curCompany, curAge, curQty));
            }

            sc.close();
            return employeeList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void employeeListToFile(ArrayList<Employee> employeeList, String filePath) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);

            fileWriter.write("EMPRESA,EDAD,NUM_EMPLEADOS");
            fileWriter.write(System.getProperty("line.separator"));

            for (Employee e : employeeList) {
                fileWriter.write('"' + e.getCompany() + '"' + "," + '"' + e.getAge() + '"' + ", " + '"'
                        + e.getEmployesQty() + '"');
                fileWriter.write(System.getProperty("line.separator"));
            }

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void employeeListToBinaryFileReversed(ArrayList<Employee> employeeList, String filePath) {
        try {
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(filePath));
            for (int i = employeeList.size() - 1; i >= 0; i--) {
                Employee currentEmployee = employeeList.get(i);
                fileWriter.writeObject(currentEmployee);
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printEmployeeFile(String filePath) {
        System.out.println("printEmployeeFile");
        try {
            File myObj = new File(filePath);
            Scanner sc = new Scanner(myObj, "UTF-8");

            // Delete first line
            if (sc.hasNextLine())
                sc.nextLine();

            while (sc.hasNextLine()) {
                var stringChain = sc.nextLine();
                var stringChains = stringChain.split(",");

                for (String s : stringChains) {
                    while (s.endsWith(",")) {
                        s = s.substring(0, s.length() - 1);
                    }

                    s = s.trim();
                }
                System.out.println(stringChains[0].replaceAll("\"", "") + " " + stringChains[1].replaceAll("\"", "")
                        + " " + stringChains[2].replaceAll("\"", ""));
            }

            System.out.println("printEmployeeFile END");
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void printBinaryEmployeeFile(String filePath) {
        System.out.println("printBinaryEmployeeFile");
        try {
            FileInputStream fileStream = new FileInputStream(filePath);
            ObjectInputStream objStream = new ObjectInputStream(fileStream);
            
            try {
                while (objStream.available() != -1) {
                    System.out.println(objStream.readObject());
                }
            } catch (EOFException e) {
                objStream.close();
            }
            System.out.println("printBinaryEmployeeFile END");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
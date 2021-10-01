package models;

import java.io.Serializable;

public class Employee implements Serializable {
    String company;
    int age;
    int employesQty;

    public Employee(String company, int age, int employesQty) {
        this.company = company;
        this.age = age;
        this.employesQty = employesQty;
    }


    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getEmployesQty() {
        return this.employesQty;
    }

    public void setEmployesQty(int employesQty) {
        this.employesQty = employesQty;
    }


    @Override
    public String toString() {
        return "{" +
            " company='" + getCompany() + "'" +
            ", age='" + getAge() + "'" +
            ", employesQty='" + getEmployesQty() + "'" +
            "}";
    }


}

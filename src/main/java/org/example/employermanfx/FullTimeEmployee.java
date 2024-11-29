package org.example.employermanfx;

import javafx.scene.control.TextField;

public class FullTimeEmployee extends Employee {
    private double salary;

    public FullTimeEmployee(String name, TextField salaryField) {
        super(name);

        try {
            this.salary = Double.parseDouble(salaryField.getText());
        } catch (NumberFormatException e) {
            this.salary = 0;
        }
    }

    @Override
    public String getType() {
        return "Full-Time";
    }

    @Override
    public double calculateSalary() {
        return this.salary;
    }

    public double getSalary() {
        return this.salary;
    }
}

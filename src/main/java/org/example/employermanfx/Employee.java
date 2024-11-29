package org.example.employermanfx;

public abstract class Employee {
    private String name;
    
    public Employee(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public double getSalary() {
        return calculateSalary();
    }
    public abstract double calculateSalary();
    public abstract String getType();
}
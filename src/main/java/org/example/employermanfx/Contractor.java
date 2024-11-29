package org.example.employermanfx;

public class Contractor extends Employee {
    private double hourlyRate;
    private double maxHours;

    // Constructor
    public Contractor(String name, double hourlyRate, double maxHours) {
        super(name);  // Call the constructor of Employee
        this.hourlyRate = hourlyRate;
        this.maxHours = maxHours;
    }

    // Implement abstract method from Employee
    @Override
    public double calculateSalary() {
        return hourlyRate * maxHours;  // Calculate salary for Contractor
    }

    // Getter for type specific to Contractor
    @Override
    public String getType() {
        return "Contractor";
    }
}

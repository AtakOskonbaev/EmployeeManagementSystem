    package org.example.employermanfx;

    public class PartTimeEmployee extends Employee {
        private double hourlyRate;
        private double hoursWorked;

        public PartTimeEmployee(String name, double hourlyRate, double hoursWorked) {
            super(name);
            this.hourlyRate = hourlyRate;
            this.hoursWorked = hoursWorked;
        }

        @Override
        public double calculateSalary() {
            return hourlyRate * hoursWorked;
        }

        @Override
        public String getType() {
            return "Part-Time";
        }
    }

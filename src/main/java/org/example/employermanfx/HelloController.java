package org.example.employermanfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class HelloController {
    @FXML
    private TextField NameField;

    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private TableColumn<Employee, String> columnName;

    @FXML
    private TableColumn<Employee, String> typeColumn;

    @FXML
    private TableColumn<Employee, Double> salaryColumn;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonCalculateSalary;

    @FXML
    private RadioButton buttonContractor;

    @FXML
    private RadioButton buttonFullTime;

    @FXML
    private RadioButton buttonPartTime;

    @FXML
    private TextField fieldSalary;

    @FXML
    private Text hoursText;

    @FXML
    private TextField hoursField;

    @FXML
    private Text wageText;

    @FXML
    private TextField wageField;

    @FXML
    private ToggleGroup type;

    private final ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        employeeTable.setItems(employeeList);

        type.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                handleRadioButtonSelection(((RadioButton) newValue).getText());
            }
        });
        handleRadioButtonSelection(buttonFullTime.getText());
    }

    @FXML
    private void onAddEmployee() {
        String name = NameField.getText().trim();
        String selectedType = ((RadioButton) type.getSelectedToggle()).getText();
        double hourlyRate = wageField.getText().isEmpty() ? 0 : Double.parseDouble(wageField.getText());
        double hoursWorked = hoursField.getText().isEmpty() ? 0 : Double.parseDouble(hoursField.getText());

        Employee employee = null;

        switch (selectedType) {
            case "Full-Time":
                employee = new FullTimeEmployee(name, fieldSalary);
                break;
            case "Part-Time":
                employee = new PartTimeEmployee(name, hourlyRate, hoursWorked);
                break;
            case "Contractor":
                employee = new Contractor(name, hourlyRate, hoursWorked);
                break;
        }
        if (employee != null) {
            employeeList.add(employee);
            clearFields();
        }
    }


    @FXML
    private void onCalculateSalary() {
        String selectedType = ((RadioButton) type.getSelectedToggle()).getText();
        double calculatedSalary = 0.0;
        switch (selectedType) {
            case "Full-Time":
                calculatedSalary = Double.parseDouble(fieldSalary.getText());
                break;
            case "Part-Time", "Contractor":
                double hoursWorked = hoursField.getText().isEmpty() ? 0 : Double.parseDouble(hoursField.getText());
                double hourlyRate = wageField.getText().isEmpty() ? 0 : Double.parseDouble(wageField.getText());

                calculatedSalary = hoursWorked * hourlyRate;
                break;
            default:
                fieldSalary.setText("Invalid Type Selected");
                return;
        }
        fieldSalary.setText(String.format("%.2f", calculatedSalary));
    }



    private void handleRadioButtonSelection(String selectedType) {
        switch (selectedType) {
            case "Full-Time":
                hoursText.setVisible(false);
                hoursField.setVisible(false);
                wageText.setVisible(false);
                wageField.setVisible(false);
                break;
            case "Part-Time", "Contractor":
                hoursText.setVisible(true);
                hoursField.setVisible(true);
                wageText.setVisible(true);
                wageField.setVisible(true);
                break;
        }
    }

    private void clearFields() {
        NameField.clear();
        fieldSalary.clear();
        hoursField.clear();
        wageField.clear();
        type.selectToggle(buttonFullTime);
        handleRadioButtonSelection(buttonFullTime.getText());
    }
}

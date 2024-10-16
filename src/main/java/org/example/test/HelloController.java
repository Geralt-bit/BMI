package org.example.test;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label label1;

    @FXML
    private TextField val1; // Weight input
    @FXML
    private TextField val2; // Height input

    private String unitSystem = "Metric"; // Default unit system

    @FXML
    private void onClick() {
        String weightStr = val1.getText();
        String heightStr = val2.getText();

        // Directly parse the input values
        double weight = Double.parseDouble(weightStr);
        double height = Double.parseDouble(heightStr);

        // Convert units if English is selected
        if ("English".equals(unitSystem)) {
            weight = weight * 0.453592; // Convert pounds to kilograms
            height = height * 0.0254; // Convert inches to meters
        }

        double bmi = weight / (height * height);
        String bmiCategory = categorizeBMI(bmi);
        label1.setText("Your BMI: " + bmiCategory);
    }

    private String categorizeBMI(double bmi) {
        if (bmi < 18.5) return "Underweight";
        else if (bmi < 25) return "Normal";
        else if (bmi < 30) return "Overweight";
        else return "Obese";
    }

    @FXML
    private void selectMetric() {
        unitSystem = "Metric";
        label1.setText("Using Metric Units");
    }

    @FXML
    private void selectEnglish() {
        unitSystem = "English";
        label1.setText("Using English Units");
    }

    @FXML
    private void onClear() {
        val1.clear();
        val2.clear();
        label1.setText("");
    }

    @FXML
    private void onExit() {
        javafx.application.Platform.exit();
    }

    @FXML
    private void onHelp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("BMI Calculator Instructions");
        alert.setContentText("To use this BMI calculator:\n"
                + "1. Enter your weight (in kg for Metric, lbs for English).\n"
                + "2. Enter your height (in m for Metric, in for English).\n"
                + "3. Click 'Calculate' to see your BMI category.\n"
                + "4. Use 'Clear' to reset the fields and 'Exit' to close the application.");
        alert.showAndWait();
    }
}

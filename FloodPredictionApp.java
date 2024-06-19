package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FloodPredictionApp extends Application {

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Flood Prediction and Resource Allocation");

        TextField historicalDataField = new TextField();
        historicalDataField.setPromptText("Enter historical data (comma-separated)");

        TextField forecastField = new TextField();
        forecastField.setPromptText("Enter weather forecast data (comma-separated)");

        TextField geographyField = new TextField();
        geographyField.setPromptText("Enter geographical data (comma-separated)");

        TextField totalResourcesField = new TextField();
        totalResourcesField.setPromptText("Enter total resources available");

        Button predictButton = new Button("Predict Flood Levels and Allocate Resources");
        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        gridPane.add(new Label("Historical Data:"), 0, 0);
        gridPane.add(historicalDataField, 1, 0);

        gridPane.add(new Label("Weather Forecast:"), 0, 1);
        gridPane.add(forecastField, 1, 1);

        gridPane.add(new Label("Geography:"), 0, 2);
        gridPane.add(geographyField, 1, 2);

        gridPane.add(new Label("Total Resources:"), 0, 3);
        gridPane.add(totalResourcesField, 1, 3);

        VBox vBox = new VBox(10, gridPane, predictButton, outputArea);
        vBox.setPadding(new Insets(10));

        predictButton.setOnAction(e -> {
            String historicalData = historicalDataField.getText();
            String forecastData = forecastField.getText();
            String geographyData = geographyField.getText();
            String totalResources = totalResourcesField.getText();

            if (historicalData.isEmpty() || forecastData.isEmpty() || geographyData.isEmpty() || totalResources.isEmpty()) {
                outputArea.setText("Please provide all inputs.");
                return;
            }

            int[] historicalDataArray = parseInput(historicalData);
            FloodPredictionAndResourceAllocation.WeatherForecast[] forecasts = parseForecasts(forecastData);
            FloodPredictionAndResourceAllocation.Geography[] geography = parseGeography(geographyData);
            int totalResourcesValue = Integer.parseInt(totalResources.trim());

            if (historicalDataArray == null || forecasts == null || geography == null) {
                outputArea.setText("Invalid input format. Please provide comma-separated values.");
                return;
            }

            int[][] floodLevels = FloodPredictionAndResourceAllocation.predictFloodLevels(historicalDataArray, forecasts, geography);
            FloodPredictionAndResourceAllocation.Zone[] zones = new FloodPredictionAndResourceAllocation.Zone[historicalDataArray.length];
            for (int i = 0; i < zones.length; i++) {
                zones[i] = new FloodPredictionAndResourceAllocation.Zone(i);
                zones[i].predictedFloodLevel = floodLevels[i][0];
                zones[i].requiredResources = new Random().nextInt(10) + 1;
            }

            List<FloodPredictionAndResourceAllocation.ResourceAllocation> allocations = FloodPredictionAndResourceAllocation.allocateResources(zones, totalResourcesValue);

            StringBuilder sb = new StringBuilder("Resource Allocations:\n");
            for (FloodPredictionAndResourceAllocation.ResourceAllocation allocation : allocations) {
                sb.append("Zone ").append(allocation.zoneId).append(" allocated ").append(allocation.allocatedResources).append(" resources.\n");
            }
            outputArea.setText(sb.toString());
        });

        Scene scene = new Scene(vBox, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private int[] parseInput(String input) {
        try {
            String[] parts = input.split(",");
            int[] result = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                result[i] = Integer.parseInt(parts[i].trim());
            }
            return result;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private FloodPredictionAndResourceAllocation.WeatherForecast[] parseForecasts(String input) {
        try {
            String[] parts = input.split(",");
            FloodPredictionAndResourceAllocation.WeatherForecast[] result = new FloodPredictionAndResourceAllocation.WeatherForecast[parts.length / 2];
            for (int i = 0; i < parts.length; i += 2) {
                int precipitation = Integer.parseInt(parts[i].trim());
                int windSpeed = Integer.parseInt(parts[i + 1].trim());
                result[i / 2] = new FloodPredictionAndResourceAllocation.WeatherForecast(precipitation, windSpeed);
            }
            return result;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    private FloodPredictionAndResourceAllocation.Geography[] parseGeography(String input) {
        try {
            String[] parts = input.split(",");
            FloodPredictionAndResourceAllocation.Geography[] result = new FloodPredictionAndResourceAllocation.Geography[parts.length / 2];
            for (int i = 0; i < parts.length; i += 2) {
                int elevation = Integer.parseInt(parts[i].trim());
                int riverProximity = Integer.parseInt(parts[i + 1].trim());
                result[i / 2] = new FloodPredictionAndResourceAllocation.Geography(elevation, riverProximity);
            }
            return result;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

# flood-detection-and-resource-allocation
### Description of the Flood Prediction and Resource Allocation Program

The Flood Prediction and Resource Allocation program is a JavaFX-based application designed to predict flood levels in various geographical zones and allocate resources optimally to mitigate the effects of potential flooding. The program takes user input for historical flood data, weather forecasts, geographical information, and the total resources available, processes this data to predict flood levels, and then uses an algorithm to allocate resources to different zones based on the predicted flood levels.

### Key Features

1. **User Input Fields**: 
    - **Historical Data**: Comma-separated values representing past flood data.
    - **Weather Forecast**: Comma-separated values representing precipitation and wind speed forecasts.
    - **Geography**: Comma-separated values representing geographical features like elevation and river proximity.
    - **Total Resources**: A single integer value representing the total resources available for allocation.

2. **Flood Level Prediction**: 
    - The program uses the provided historical data, weather forecasts, and geographical data to predict flood levels for each zone.

3. **Resource Allocation**: 
    - Based on the predicted flood levels, the program allocates resources optimally to minimize the impact of flooding. It uses a dynamic programming approach to ensure resources are distributed efficiently.

4. **Output Display**: 
    - The application displays the predicted flood levels and resource allocations in a user-friendly text area. The output includes the zone IDs and the amount of resources allocated to each zone.

### Implementation Details

- **JavaFX Framework**: The program uses JavaFX to create a graphical user interface (GUI) that allows users to input data and view results.
- **Data Parsing**: User inputs are parsed from comma-separated strings into arrays and objects for processing.
- **Algorithm**: The flood prediction algorithm calculates potential flood levels based on historical and forecast data. The resource allocation algorithm uses dynamic programming to distribute resources based on predicted flood levels and zone requirements.

### How to Use

1. **Input Data**:
    - Enter the historical flood data as a comma-separated string in the "Historical Data" field.
    - Enter the weather forecast data as a comma-separated string in the "Weather Forecast" field. Each forecast consists of two values: precipitation and wind speed.
    - Enter the geographical data as a comma-separated string in the "Geography" field. Each geography consists of two values: elevation and river proximity.
    - Enter the total available resources as a single integer value in the "Total Resources" field.

2. **Predict and Allocate**:
    - Click the "Predict Flood Levels and Allocate Resources" button to process the input data.

3. **View Results**:
    - The results will be displayed in the text area below the input fields, showing the predicted flood levels and the resources allocated to each zone.

### Sample Input and Output

**Input**:
- Historical Data: `100, 200, 150`
- Weather Forecast: `80, 15, 90, 20, 85, 10`
- Geography: `50, 2, 30, 5, 20, 3`
- Total Resources: `20`

**Output**:
- Resource Allocations:
  - Zone 0 allocated 3 resources.
  - Zone 1 allocated 5 resources.
  - Zone 2 allocated 2 resources.

### Conclusion

This program provides a comprehensive solution for flood prediction and resource allocation, leveraging user input and advanced algorithms to aid in effective disaster management. By using JavaFX, it ensures a modern and intuitive user experience, making it accessible for various stakeholders involved in flood management and mitigation efforts.

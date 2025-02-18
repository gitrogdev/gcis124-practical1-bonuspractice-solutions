/*
A sensor log file records temperature readings in degrees Celsius.  
Each line in the log file contains a timestamp (String) followed by  
one or more temperature readings (integers), separated by spaces.  

Example data in logs/sensor_data.txt:  

    2025-02-18 14:30:00 22 25 23 24  
    2025-02-18 14:45:00 30 32 31 29 35  
    2025-02-18 15:00:00 18 20 19  
    2025-02-18 15:15:00 15 10 12 8 5  

### Part A:  
Design a structure that allows different types of temperature analysis to be performed on the log data.  
Each analysis should take a timestamp and an array of temperature readings and return a meaningful result.  
Consider how to best enable flexibility and code reuse.  

### Part B:  
Implement at least four different types of temperature analysis:  

- **HeatwaveWarning**: If all readings in the entry are above 30°C,  
  return `"Heatwave warning at <timestamp>!"`  
  Otherwise, return `"Normal temperatures at <timestamp>."`  

- **SuddenDropAlert**: If the first reading is at least 10°C higher than  
  the last reading, return `"Sudden temperature drop at <timestamp>!"`  
  Otherwise, return `"Stable temperatures at <timestamp>."`  

- **TrendAnalyzer**: If the temperatures are strictly increasing, return  
  `"Temperature rising at <timestamp>."`  
  If they are strictly decreasing, return `"Temperature falling at <timestamp>."`  
  Otherwise, return `"No clear trend at <timestamp>."`  

- **OutlierDetection**: If any reading differs from the average by more  
  than 10°C, return `"Outliers detected at <timestamp>."`  
  Otherwise, return `"No outliers at <timestamp>."`  

### Part C:  
Create a Java class named `LogProcessor`.  

Within this class, write a static method named `processLog` that accepts:  
  - A filename (`String`)  
  - An object capable of performing one of the analyses from Part B  

The method should:  
  - Read each line of the file  
  - Extract the timestamp and temperature readings  
  - Perform the analysis and print the result  

Example output for **SuddenDropAlert**:  

    Sudden temperature drop at 2025-02-18 15:15:00!  
    Stable temperatures at 2025-02-18 14:30:00.  

Assume the file data is clean. If the file does not exist, print an  
error message and return.  
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LogProcessor {
	/**
	 * Given a log file, processes the 
	 * @param filename
	 * @param analyzer
	 */
	public static void processLog(String filename, SensorAnalyzer analyzer) {
		try {
			BufferedReader reader = new BufferedReader(
				new FileReader(filename)
			);

			String line;
			while ((line = reader.readLine()) != null) {
				String[] values = line.split(" ");
				String timestamp = String.format("%s %s", values[0], values[1]);
				
				int[] readings = new int[values.length - 2];
				for (int i = 2; i < values.length; i++) {
					readings[i - 2] = Integer.parseInt(values[i]);
				}

				System.out.println(analyzer.analyze(timestamp, readings));
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}